package com.javacode.RestfulWeb;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping(value = "/getproduct/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		if (id == 3)
			return new ResponseEntity<Product>(new Product(1, "Phone", 1400f), HttpStatus.OK);
		else
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);

	}
}
