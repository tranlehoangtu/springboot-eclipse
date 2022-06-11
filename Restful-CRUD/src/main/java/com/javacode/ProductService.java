package com.javacode;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> listAll() {
		return productRepository.findAll();
	}

	public void save(Product product) {
		productRepository.save(product);
	}

	public Optional<Product> getById(Integer id) {
		return productRepository.findById(id);
	}

	public void delete(Integer id) {
		productRepository.deleteById(id);
	}

}
