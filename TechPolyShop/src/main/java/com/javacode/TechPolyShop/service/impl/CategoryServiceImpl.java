package com.javacode.TechPolyShop.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javacode.TechPolyShop.domain.Category;
import com.javacode.TechPolyShop.repository.CategoryRepository;
import com.javacode.TechPolyShop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	CategoryRepository repo;

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Autowired
	public CategoryServiceImpl(CategoryRepository repo) {
		this.repo = repo;
	}

	@Override
	public <S extends Category> S save(S entity) {
		return repo.save(entity);
	}

	@Override
	public List<Category> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public List<Category> findByNameContaining(String name) {
		return repo.findByNameContaining(name);
	}

	@Override
	public Page<Category> findByNameContaining(String name, Pageable pageable) {
		return repo.findByNameContaining(name, pageable);
	}

}
