package com.javacode.TechPolyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacode.TechPolyShop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
