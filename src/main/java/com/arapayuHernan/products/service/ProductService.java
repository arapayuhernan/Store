package com.arapayuHernan.products.service;

import com.arapayuHernan.products.model.Product;

import java.util.List;

public interface ProductService {
     Product saveProduct(Product product );

    void deleteProduct(Long id);

    Product updateProduct(Long id,Product product);

    Product getProduct(Long id);


   List<Product> allProductsOrderByPrice();

}
