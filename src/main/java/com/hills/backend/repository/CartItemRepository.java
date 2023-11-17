package com.hills.backend.repository;

import com.hills.backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    public CartItem findCartItemByProductIdAndCart_Id(String productId, Long cartId);
}
