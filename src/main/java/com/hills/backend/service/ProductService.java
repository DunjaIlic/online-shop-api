package com.hills.backend.service;

import com.hills.backend.cache.Storage;
import com.hills.backend.config.ServerConfiguration;
import com.hills.backend.model.Product;
import com.hills.backend.model.ProductDetails;
import com.hills.backend.model.ProductsResponse;
//import com.hills.backend.repository.ProductRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {
    private ServerConfiguration serverConfiguration;

    @Autowired
    public ProductService(ServerConfiguration serverConfiguration){
        this.serverConfiguration = serverConfiguration;
        saveAllProductsToCache();
    }

    public void saveAllProductsToCache() {
        ResponseEntity<ProductsResponse> products =
                serverConfiguration.serverRestTemplate().getForEntity("products.json", ProductsResponse.class);
        storeProductsInCache(products);
    }

    public Map<String, Product> getProducts(String category,
                            String nameLike,
                            String descriptionLike,
                            String sortByPrice
    ) {
        Stream<Map.Entry<String,Product>> query = Storage.products.entrySet().stream();

        if(category != null) {
            query = query.filter(entry -> entry.getValue().getCategory().toLowerCase().equals(category.toLowerCase()));
        }
        if(nameLike != null) {
            query = query.filter(entry -> entry.getValue().getName().toLowerCase().contains(nameLike.toLowerCase()));
        }
        if(descriptionLike != null) {
            query = query.filter(entry -> entry.getValue().getDescription().toLowerCase().contains(descriptionLike.toLowerCase()));
        }

        Map<String, Product> filteredMap = query.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        return filteredMap;
    }

    private void storeProductsInCache(ResponseEntity<ProductsResponse> products){
        for(Product p : Arrays.asList(products.getBody().products.data.items)){
            Storage.products.put(p.getId(), p);
        }
    }


}
