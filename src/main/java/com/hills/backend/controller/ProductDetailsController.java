package com.hills.backend.controller;

import com.hills.backend.cache.Storage;
import com.hills.backend.dto.ProductDetailsDto;
import com.hills.backend.model.ApiErrorResponse;
import com.hills.backend.model.Product;
import com.hills.backend.model.ProductDetails;
import com.hills.backend.model.ProductsResponse;
import com.hills.backend.service.ProductDetailsService;
import com.hills.backend.utils.ApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-details")
public class ProductDetailsController {
    @Autowired
    private ProductDetailsService productDetailsService;

    @GetMapping("/{id}")
    public ResponseEntity getProductDetails(@PathVariable String id){
        try {
            return ResponseEntity.ok(productDetailsService.getProductDetails(id));
        }
        catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }

}
