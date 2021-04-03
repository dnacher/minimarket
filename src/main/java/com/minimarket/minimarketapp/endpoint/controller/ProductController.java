package com.minimarket.minimarketapp.endpoint.controller;

import com.minimarket.minimarketapp.domain.service.ProductService;
import org.springframework.web.bind.annotation.*;
import com.minimarket.minimarketapp.persistence.model.Product;
import com.minimarket.minimarketapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("public/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "/")
    public Product saveProduct(@RequestBody Product product){
        return this.productService.saveProduct(product);
    }

    @PostMapping(value = "/mul")
    public List<Product> saveProducts(@RequestBody List<Product> products){
        List<Product> finalList = new ArrayList<>();
        products.forEach(product -> {
            finalList.add(this.productService.saveProduct(product));
        });
        return finalList;
    }

    @PutMapping(value = "/{id}")
    public Product updateProduct(@PathVariable Integer id, @RequestBody Product product){
        String msg = String.format("The Product Id %s is different from the Url Id",product.getId());
        Utils.validateUrlIdEqualsBodyId(id,product.getId(),msg);
        return this.productService.updateProduct(product);
    }

    @PutMapping(value = "/mul")
    public List<Product> updateProduct(@RequestBody List<Product> products){
        List<Product> finalList = new ArrayList<>();
        products.forEach(product -> {
            finalList.add(this.productService.updateProduct(product));
        });
        return finalList;
    }

    @GetMapping(value = "/")
    public List<Product> getProduct(){
        return this.productService.getProduct();
    }

    @GetMapping(value = "/{id}")
    public Product getProductById(@PathVariable Integer id){
        return this.productService.getProductById(id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable Integer id, Product product){
        String msg = String.format("The Product Id %s is different from the Url Id",product.getId());
        Utils.validateUrlIdEqualsBodyId(id,product.getId(),msg);
        this.productService.deleteProduct(product);
    }
}
