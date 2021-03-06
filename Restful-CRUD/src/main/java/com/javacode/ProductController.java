package com.javacode;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	@Autowired
	ProductService service;

	@GetMapping(value = "/products")
	public List<Product> list() {
		return service.listAll();
	}

	@GetMapping(value = "/products/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		if (service.getById(id).isPresent())
			return new ResponseEntity<Product>(service.getById(id).get(), HttpStatus.OK);

		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@PostMapping(value = "/products")
	public void addProduct(@RequestBody Product product) {
		service.save(product);
	}

	@PutMapping(value = "/products/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product)
			throws IOException {
		if (service.getById(id).isPresent()) {
			service.save(product);
			return new ResponseEntity<Product>(HttpStatus.OK);
		}

		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping(value = "/products/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
		if (service.getById(id).isPresent()) {
			service.delete(id);
			return new ResponseEntity<Product>(HttpStatus.OK);
		}

		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}
}
