package com.minimarket.minimarketapp.error;

import com.minimarket.minimarketapp.persistence.model.Product;

public class ErrorHandling {

    public static String noStockMessage(Product product){
        return "No hay stock para el producto " +
                product.getId() + ": " +
                product.getDescription();
    }

    public static String TransactionNotFound(String id){
        return String.format("La transaccion id %s no existe", id);
    }

    public static String TransactionUpdateError(){
        return "No se puede actualizar una transaccion sin id";
    }

    

}
