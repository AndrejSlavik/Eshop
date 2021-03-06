package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String unit;
    private Integer amount;
    private Number price;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.unit = product.getUnit();
        this.amount = product.getAmount();
        this.price = product.getPrice();
    }
}
