package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;
import lombok.Setter;
import sk.stuba.fei.uim.oop.assignment3.cartproduct.CartProductResponse;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class CartResponse {
    private Long id;
    private Boolean payed;
    private List<CartProductResponse> shoppingList;

    public CartResponse(Cart cart) {
        this.id = cart.getId();
        this.payed = cart.getPayed();
        this.shoppingList = cart.getShoppingList().stream().map(CartProductResponse::new).collect(Collectors.toList());
    }
}
