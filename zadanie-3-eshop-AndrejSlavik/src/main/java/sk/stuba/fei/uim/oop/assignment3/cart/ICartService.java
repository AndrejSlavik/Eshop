package sk.stuba.fei.uim.oop.assignment3.cart;

import sk.stuba.fei.uim.oop.assignment3.exceptions.BadRequestException;

import java.util.List;

public interface ICartService {
    List<Cart> getAllCarts();
    Cart addCart();
    Cart getCartById(Long id);
    Cart addProductToCart(Long id, CartRequest request) throws BadRequestException;
    String payForCart(Long id) throws BadRequestException;
    void deleteCart(Long id);
}
