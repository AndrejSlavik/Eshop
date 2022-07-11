package sk.stuba.fei.uim.oop.assignment3.product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductById(Long id);
    Product getAmountById(Long id);
    Product updateProduct(Long id, ProductRequest request);
    Product addAmount(Long id, AmountRequest request);
    void deleteProduct(Long id);
}
