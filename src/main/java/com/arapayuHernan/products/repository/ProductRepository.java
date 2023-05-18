package com.arapayuHernan.products.repository;

import com.arapayuHernan.products.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
