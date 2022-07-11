package sk.stuba.fei.uim.oop.assignment3.cartproduct;

import lombok.Getter;

@Getter
public class CartProductResponse {

    final private Long productId;
    final private Integer amount;

    public CartProductResponse(CartProduct cartProduct) {
        this.productId = cartProduct.getProductId();
        this.amount = cartProduct.getAmount();
    }
}
