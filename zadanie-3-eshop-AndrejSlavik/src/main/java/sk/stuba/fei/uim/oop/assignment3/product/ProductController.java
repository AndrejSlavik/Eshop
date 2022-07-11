package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest request) {
        return new ResponseEntity<>(new ProductResponse(this.service.create(request)), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ProductResponse getProductsById(@PathVariable("id") Long id) {
        return new ProductResponse(this.service.getProductById(id));
    }

    @GetMapping("/{id}/amount")
    public AmountResponse getAmountById(@PathVariable("id") Long id) {
        return new AmountResponse(this.service.getAmountById(id));
    }

    @PostMapping("/{id}/amount")
    public AmountResponse addAmount(@PathVariable("id") Long id, @RequestBody AmountRequest request) {
        return new AmountResponse(this.service.addAmount(id,request));
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable("id") Long id, @RequestBody ProductRequest request) {
        return new ProductResponse(this.service.updateProduct(id, request));
    }


    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        this.service.deleteProduct(id);
    }
}
