package com.arapayuHernan.products.service;

import com.arapayuHernan.products.ProductsApplication;
import com.arapayuHernan.products.dto.ProductDto;
import com.arapayuHernan.products.model.Product;
import com.arapayuHernan.products.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ProductServiceImplement implements ProductService{

    @Autowired
    ProductRepository productRepository;


    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {

        productRepository.delete(productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No se encontro el recurso con el id: "+id)));
    }

    @Override
    public Product updateProduct(Long id,Product product){

        Product productUpdate = productRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No se encontro el recurso con el id: "+id));

        productUpdate.setDescription(product.getDescription());
        productUpdate.setName(product.getName());
        productUpdate.setStock(product.getStock());
        productUpdate.setPrice(product.getPrice());

        return productRepository.save(productUpdate);
    }

    @Override
    public Product getProduct(Long id) {

        Optional<Product> result = productRepository.findById(id);

        if(result.isPresent()){
            return result.get();
        }else{
            throw new ResourceNotFoundException("No se encontro el recurso con el id: "+id);
        }

    }

    @Override
    public List<Product> allProductsOrderByPrice() {
        List<Product> products = productRepository.findAll().stream().sorted(Comparator.comparing(Product::getPrice)).collect(Collectors.toList());
        return products;
    }
}
