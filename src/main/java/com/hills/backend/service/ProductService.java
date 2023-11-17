package com.hills.backend.service;

import com.hills.backend.cache.Storage;
import com.hills.backend.config.ServerConfiguration;
import com.hills.backend.model.Product;
import com.hills.backend.model.ProductDetails;
import com.hills.backend.model.ProductsResponse;
//import com.hills.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

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

    private void storeProductsInCache(ResponseEntity<ProductsResponse> products){
        for(Product p : Arrays.asList(products.getBody().products.data.items)){
            Storage.products.put(p.getId(), p);
        }
    }

    private class PriceComparator implements Comparator<Product> {

        @Override
        public int compare(Product product1, Product product2) {
            return product1.getPrice().compareTo(product2.getPrice());
        }

    }

    private void sortProductsByPrice(String flag){
        if(flag.equalsIgnoreCase("ascending"))
            Collections.sort(Storage.products.values().stream().toList(), new PriceComparator());
        else
            Collections.sort(Storage.products.values().stream().toList(), Collections.reverseOrder(new PriceComparator()));

    }

    private List<Product> filterProductsByCategory(String category){

        List<Product> productsByCategory = new ArrayList<>();

        for(Map.Entry<String, Product> storage : Storage.products.entrySet()){
            if(storage.getValue().getCategory().equals(category.toLowerCase()))
                productsByCategory.add(storage.getValue());
        }

        //ToDO else
        if(!productsByCategory.isEmpty())
            return productsByCategory;
        else
            return null;
    }

    private List<Product> searchProductsByNameOrDescription(String searchParam){

        List<Product> productsBySearchParam = new ArrayList<>();

        for(Map.Entry<String, Product> storage : Storage.products.entrySet()){
            if(storage.getValue().getName().equalsIgnoreCase(searchParam) ||
                storage.getValue().getDescription().equalsIgnoreCase(searchParam))
                productsBySearchParam.add(storage.getValue());
        }

        if(!productsBySearchParam.isEmpty())
            return productsBySearchParam;
        else
            return null;
    }


}
