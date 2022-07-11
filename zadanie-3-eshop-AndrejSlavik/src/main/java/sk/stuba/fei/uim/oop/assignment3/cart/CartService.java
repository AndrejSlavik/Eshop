package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.cartproduct.CartProduct;
import sk.stuba.fei.uim.oop.assignment3.cartproduct.CartProductRepository;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;
import sk.stuba.fei.uim.oop.assignment3.exceptions.NotFoundException;
import sk.stuba.fei.uim.oop.assignment3.product.Product;
import sk.stuba.fei.uim.oop.assignment3.product.ProductRepository;
import java.util.List;


@Service
public class CartService implements ICartService {
    private CartRepository repository;
    private ProductRepository productRepository;
    private CartProductRepository cartProductRepository;

    @Autowired
    public CartService(CartRepository repository, ProductRepository productRepository, CartProductRepository cartProductRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.cartProductRepository = cartProductRepository;
    }

    @Override
    public List<Cart> getAllCarts() {
        return this.repository.findAll();
    }

    @Override
    public Cart addCart() {
        Cart newCart = new Cart();
        return this.repository.save(newCart);
    }

    @Override
    public void deleteCart(Long id) {
        this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
    }

    @Override
    public Cart getCartById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Cart addProductToCart(Long id, CartRequest request) throws BadRequestException {
        Cart cart = this.repository.findById(id).orElseThrow(NotFoundException::new);
        Product product = this.productRepository.findById(request.getProductId()).orElseThrow(NotFoundException::new);

        if (cart.getPayed() || (product.getAmount() < request.getAmount())){
            throw new BadRequestException();
        }
        for (CartProduct iterator : cart.getShoppingList()){
            if (iterator.getProductId().equals(request.getProductId())){
                iterator.setAmount(iterator.getAmount() + request.getAmount());
                product.setAmount(product.getAmount() - request.getAmount());
                this.productRepository.save(product);
                return this.repository.save(cart);
            }
        }
        CartProduct cartProduct = new CartProduct();
        cartProduct.setProductId(request.getProductId());
        cartProduct.setAmount(request.getAmount());
        this.cartProductRepository.save(cartProduct);
        cart.getShoppingList().add(cartProduct);
        product.setAmount(product.getAmount() - request.getAmount());
        this.productRepository.save(product);
        return this.repository.save(cart);
    }

    @Override
    public String payForCart(Long id) throws BadRequestException {
        double toPay = 0;
        Product product;
        Cart cart = this.repository.findById(id).orElseThrow(NotFoundException::new);
        if (cart.getPayed()) {
            throw new BadRequestException();
        }
        for (CartProduct iterator : cart.getShoppingList()) {
            product = this.productRepository.findById(iterator.getProductId()).orElseThrow();
            toPay += product.getPrice() * iterator.getAmount();
        }
        cart.setPayed(true);
        this.repository.save(cart);
        return String.valueOf(toPay);
    }
}
