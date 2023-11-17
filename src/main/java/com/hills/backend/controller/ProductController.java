package com.hills.backend.controller;

import com.hills.backend.service.ProductService;
import com.hills.backend.utils.ApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity getAllProducts(
//            @RequestParam(name = "category", required = false) String category,
//            @RequestParam(name = "price", required = false) String price,
//            @RequestParam(name = "sortBy", defaultValue = "name") String sortBy
            ){
        try {
            productService.saveAllProductsToCache();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }



}
