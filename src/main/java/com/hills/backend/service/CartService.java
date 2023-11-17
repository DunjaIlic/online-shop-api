package com.hills.backend.service;

import com.hills.backend.dto.CartDto;
import com.hills.backend.dto.CartItemDto;
import com.hills.backend.dto.RemoveCartItemDto;
import com.hills.backend.mapper.CartItemMapper;
import com.hills.backend.mapper.CartMapper;
import com.hills.backend.model.Cart;
import com.hills.backend.model.CartItem;
import com.hills.backend.model.Product;
import com.hills.backend.repository.CartItemRepository;
import com.hills.backend.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CartRepository cartRepository;
    private CartItemMapper cartItemMapper;
    private CartMapper cartMapper;
    private CartItemRepository cartItemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemMapper cartItemMapper,
                       CartMapper cartMapper, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.cartItemMapper = cartItemMapper;
        this.cartMapper = cartMapper;
        this.cartItemRepository = cartItemRepository;
    }

    public void createCart(Cart cart) {
        cartRepository.save(cart);
    }

    public CartDto addCartItem(CartItemDto cartItemDto) {
        Optional<Cart> optionalCart = null;
        if(cartItemDto.getCartId() != null) {
            optionalCart  = cartRepository.findById(cartItemDto.getCartId());
        }

        if (optionalCart != null && optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            CartItem cartItem = cartItemMapper.cartItemDtoToCartItem(cartItemDto, cart);
            CartItem existingCartItem = cartItemRepository.findCartItemByProductIdAndCart_Id(
                    cartItem.getProductId(), cartItemDto.getCartId());
            if(existingCartItem != null) {
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemDto.getQuantity());
                cartItemRepository.save(existingCartItem);
            }
            else {
                cart.getProducts().add(cartItem);
            }
            cartRepository.save(cart);
            return cartMapper.cartToCartDto(cart);
        } else {
            Cart newCart = new Cart();
            newCart.setProducts(new ArrayList<>());
            CartItem cartItem = cartItemMapper.cartItemDtoToCartItem(cartItemDto, newCart);
            newCart.getProducts().add(cartItem);
            cartRepository.save(newCart);
            return cartMapper.cartToCartDto(newCart);
        }
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void removeCartItem(RemoveCartItemDto removeCartItemDto) {

        if (cartRepository.findById(removeCartItemDto.getCartId()).isPresent()) {
            for (Cart cart : getAllCarts()) {
                for (CartItem cartItem : cart.getProducts()) {
                    if (cartItem.getId().equals(removeCartItemDto.getId())) {
                        cart.getProducts().remove(cartItem.getId());
                        cartRepository.save(cart);
                    }
                }
            }
        }

    }

    public void removeCartItemById(Long id) {
        cartItemRepository.deleteById(id);

    }
}
