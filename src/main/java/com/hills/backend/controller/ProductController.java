package com.hills.backend.controller;

import com.hills.backend.model.Product;
import com.hills.backend.service.ProductService;
import com.hills.backend.utils.ApiHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity storeAllProducts(){
        try {
            productService.saveAllProductsToCache();
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }

    @GetMapping
    public ResponseEntity getAllProducts
            (@RequestParam(name = "category", required = false) String category,
             @RequestParam(name = "nameLike", required = false) String nameLike,
             @RequestParam(name = "descriptionLike", required = false) String descriptionLike,
             @RequestParam(name = "sortByPrice", required = false) String sortByPrice
            )
    {
        try {
            Collection<Product> result = productService.getProducts(
                    category,
                    nameLike,
                    descriptionLike,
                    sortByPrice
            ).values();

            Comparator<Product> comparator = null;

            if(sortByPrice.equals("asc"))
                comparator = new PriceComparator();

            if(sortByPrice.equals("desc"))
                comparator = new PriceComparator().reversed();

            if(sortByPrice != null)
                return ResponseEntity.ok(result.stream().sorted(comparator).collect(Collectors.toList()));
            else return  ResponseEntity.ok(result);
        }catch (Exception e){
            return ApiHelper.HandleApiErrorResponse(e);
        }
    }

    private class PriceComparator implements Comparator<Product> {

        @Override
        public int compare(Product product1, Product product2) {
            return product1.getPrice().compareTo(product2.getPrice());
        }

    }

}
