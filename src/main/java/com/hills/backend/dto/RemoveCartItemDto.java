package com.hills.backend.dto;

import com.hills.backend.model.CartItem;

import java.util.List;

public class RemoveCartItemDto {
    private Long cartId;
    private String id;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
