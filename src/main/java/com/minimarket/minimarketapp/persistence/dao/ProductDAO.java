package com.minimarket.minimarketapp.persistence.dao;

import com.minimarket.minimarketapp.exceptions.MiniMarketException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.minimarket.minimarketapp.persistence.model.Product;
import com.minimarket.minimarketapp.persistence.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDAO {

    @Autowired
    private ProductRepository repository;

    public List<Product> getProduct(){
        List<Product> products = new ArrayList<>();
        this.repository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public Product getProductById(Integer id) throws MiniMarketException {
        return this.repository.findById(id).orElseThrow(() ->
        {
            String msg = String.format("The product id %s does not exist", id.toString());
            return new MiniMarketException(msg);
        });
    }

    public Product saveProduct(Product product) throws MiniMarketException {
        return this.repository.save(product);
    }

    public List<Product> saveProductes(List<Product> productes) throws MiniMarketException {
        List<Product> finalList= new ArrayList<>();
        this.repository.saveAll(productes).forEach(product -> {
            finalList.add(product);
        });
        return finalList;
    }

    public void deleteProduct(Product product){
        this.repository.delete(product);
    }

    public Product updateProduct(Product product) throws MiniMarketException {
        if(product.getId()!=null){
            return this.repository.save(product);
        }else{
            String msg = String.format("Cannot update a product without an Id");
            throw new MiniMarketException(msg);
        }
    }
}
