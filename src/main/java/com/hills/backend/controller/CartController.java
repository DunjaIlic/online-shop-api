package com.hills.backend.controller;

import com.hills.backend.dto.CartItemDto;
import com.hills.backend.dto.RemoveCartItemDto;
import com.hills.backend.service.CartService;
import com.hills.backend.utils.ApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add-item")
    public ResponseEntity addCartItem(@RequestBody CartItemDto cartItemDto){
        try {
            return ResponseEntity.ok(cartService.addCartItem(cartItemDto));
        }catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }

    @DeleteMapping("/remove-item")
    public ResponseEntity<String> removeCartItem(@RequestBody RemoveCartItemDto removeCartItemDto){
        try {
            cartService.removeCartItem(removeCartItemDto);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }

    @DeleteMapping("/remove-item/{id}")
    public ResponseEntity<String> removeCartItem(@PathVariable Long id){
        try {
            cartService.removeCartItemById(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }

}
