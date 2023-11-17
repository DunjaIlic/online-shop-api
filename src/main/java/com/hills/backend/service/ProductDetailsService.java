package com.hills.backend.service;

import com.hills.backend.cache.Storage;
import com.hills.backend.dto.ProductDetailsDto;
import com.hills.backend.mapper.ProductDetailsMapper;
import com.hills.backend.model.ProductDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDetailsService {

    private ProductDetailsMapper productDetailsMapper;

    @Autowired
    public ProductDetailsService(ProductDetailsMapper productDetailsMapper){
        this.productDetailsMapper = productDetailsMapper;
    }

    public ProductDetailsDto getProductDetails(String id){
        return productDetailsMapper.productToProductDetailsDto(Storage.products.get(id));
    }

}
