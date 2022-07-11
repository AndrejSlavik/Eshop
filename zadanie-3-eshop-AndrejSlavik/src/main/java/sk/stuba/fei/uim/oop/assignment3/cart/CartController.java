package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;

    @GetMapping()
    public List<CartResponse> getAllCarts() {
        return this.cartService.getAllCarts().stream().map(CartResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CartResponse getCartById(@PathVariable("id") Long id) {
        return new CartResponse(this.cartService.getCartById(id));
    }

    @PostMapping()
    public ResponseEntity<CartResponse> addCart() {
        return new ResponseEntity<>(new CartResponse(this.cartService.addCart()), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add")
    public CartResponse addProductToCart(@PathVariable("id") Long id, @RequestBody CartRequest request) throws BadRequestException {
        return new CartResponse(this.cartService.addProductToCart(id,request));
    }

    @GetMapping("/{id}/pay")
    public String payForCart(@PathVariable("id") Long id) throws BadRequestException {
        return this.cartService.payForCart(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCardById(@PathVariable("id") Long id){
        this.cartService.deleteCart(id);
    }
}
