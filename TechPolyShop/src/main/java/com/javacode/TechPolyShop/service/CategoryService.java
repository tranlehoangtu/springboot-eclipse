package com.javacode.TechPolyShop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javacode.TechPolyShop.domain.Category;

public interface CategoryService {

	<S extends Category> S save(S entity);

	List<Category> findAll();

	Optional<Category> findById(Long id);

	List<Category> findByNameContaining(String name);

	void deleteById(Long id);

	Page<Category> findByNameContaining(String name, Pageable pageable);

	Page<Category> findAll(Pageable pageable);
}
