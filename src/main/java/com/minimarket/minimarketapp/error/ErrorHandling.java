package com.minimarket.minimarketapp.error;

import com.minimarket.minimarketapp.persistence.model.Product;

public class ErrorHandling {

    public static final String ALREADY_EXIST_PRODUCT_STOCK = "Ya existe stock para este product";

    public static String noStockMessage(Product product) {
        return "No hay stock para el producto " +
                product.getId() + ": " +
                product.getDescription();
    }

    public static String valueNotFound(String value, Integer id) {
        return String.format("%s con id: %d no existe", value, id);
    }

    public static String valueUpdateError(String value) {
        return String.format("No se puede actualizar una %s sin id", value);
    }

}
