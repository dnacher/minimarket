package com.minimarket.minimarketapp.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.minimarket.minimarketapp.persistence.dao.ProductDAO;
import com.minimarket.minimarketapp.persistence.model.Product;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    public List<Product> getProduct(){
        return productDAO.getProduct();
    }

    public Product getProductById(Integer id){
        return productDAO.getProductById(id);
    }

    public Product saveProduct(Product product){
        return productDAO.saveProduct(product);
    }

    public Product updateProduct(Product product){
        return productDAO.updateProduct(product);
    }

    public void deleteProduct(Product product){
        productDAO.deleteProduct(product);
    }
    
}
