package com.hills.backend.mapper;

import com.hills.backend.dto.CartDto;
import com.hills.backend.dto.CartItemDto;
import com.hills.backend.model.Cart;
import com.hills.backend.model.CartItem;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper {

    public CartItem cartItemDtoToCartItem(CartItemDto cartItemDto, Cart cart) {

        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDto.getId());
        cartItem.setCart(cart);
        cartItem.setName(cartItemDto.getName());
        cartItem.setProductId(cartItemDto.getProductId());
        cartItem.setPrice(cartItemDto.getPrice());
        cartItem.setQuantity(cartItemDto.getQuantity());
        return cartItem;

    }
}
