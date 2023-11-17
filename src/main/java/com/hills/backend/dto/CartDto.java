package com.hills.backend.dto;

import com.hills.backend.model.CartItem;

import java.util.List;

public class CartDto {
    private Long id;
    private List<CartItem> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CartItem> getProducts() {
        return products;
    }

    public void setProducts(List<CartItem> products) {
        this.products = products;
    }
}
