package com.hills.backend.mapper;

import com.hills.backend.dto.CartDto;
import com.hills.backend.model.Cart;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {

    public Cart cartDtoToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId());
        cart.setProducts(cartDto.getProducts());
        return cart;

    }

    public CartDto cartToCartDto(Cart cart){
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cartDto.setProducts(cart.getProducts());
        return cartDto;
    }
}
