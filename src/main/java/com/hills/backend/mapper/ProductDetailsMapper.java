package com.hills.backend.mapper;

import com.hills.backend.dto.ProductDetailsDto;
import com.hills.backend.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailsMapper {

    public ProductDetailsDto productToProductDetailsDto(Product product) {
        ProductDetailsDto productDetailsDto = new ProductDetailsDto();
        productDetailsDto.setId(product.getId());
        productDetailsDto.setCategory(product.getCategory());
        productDetailsDto.setSubcategory(product.getSubcategory());
        productDetailsDto.setDescription(product.getDescription());
        productDetailsDto.setFeatures(product.getFeatures());
        productDetailsDto.setKeywords(product.getKeywords());
        productDetailsDto.setUrl(product.getUrl());
        productDetailsDto.setImages(product.getImages());
        return  productDetailsDto;

    }

}
