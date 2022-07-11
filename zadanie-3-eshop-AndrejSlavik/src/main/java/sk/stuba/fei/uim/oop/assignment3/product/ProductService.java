package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setName(request.getName());
        newProduct.setAmount(request.getAmount());
        newProduct.setUnit(request.getUnit());
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        return this.repository.save(newProduct);
    }

    @Override
    public Product getProductById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product getAmountById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product addAmount(Long id, AmountRequest request) {
        Product addAmountProduct = this.repository.findById(id).orElseThrow();
        addAmountProduct.setAmount(request.getAmount() + addAmountProduct.getAmount());
        return this.repository.save(addAmountProduct);
    }

    @Override
    public Product updateProduct (Long id, ProductRequest request) {
        Product update = this.repository.findById(id).orElseThrow();
        if (request.getName() != null) {
            update.setName(request.getName());
        }
        if (request.getDescription() != null) {
            update.setDescription(request.getDescription());
        }
        return this.repository.save(update);
    }

    @Override
    public void deleteProduct(Long id) {
        this.repository.findById(id).orElseThrow();
        this.repository.deleteById(id);
    }
}
