package com.arapayuHernan.products.controller;

import com.arapayuHernan.products.dto.ProductDto;
import com.arapayuHernan.products.model.Product;
import com.arapayuHernan.products.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ModelMapper modelMapper;


    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        Product product = productService.getProduct(id);
        ProductDto productDto = modelMapper.map(product,ProductDto.class);

        return ResponseEntity.ok(productDto);
        
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<ProductDto>> getAllProductsSortedByPrice(){
        List<ProductDto> productsDto =  productService.allProductsOrderByPrice().stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<List<ProductDto>>(productsDto,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        Product productRequest = modelMapper.map(productDto,Product.class);
        Product product = productService.saveProduct(productRequest);

        ProductDto productResponse = modelMapper.map(product,ProductDto.class);

        return new ResponseEntity<ProductDto>(productResponse, HttpStatus.CREATED);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        Product productRequest = modelMapper.map(productDto,Product.class);
        Product product = productService.updateProduct(id,productRequest);

        ProductDto productResponse = modelMapper.map(product,ProductDto.class);
        return new ResponseEntity<ProductDto>(productResponse,HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProduct(id);

    }







}
