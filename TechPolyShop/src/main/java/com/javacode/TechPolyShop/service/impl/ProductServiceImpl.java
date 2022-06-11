package com.javacode.TechPolyShop.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.javacode.TechPolyShop.domain.Product;
import com.javacode.TechPolyShop.repository.ProductRepository;
import com.javacode.TechPolyShop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	private ProductRepository repo;
	
	@Autowired
	public ProductServiceImpl(ProductRepository repo) {
		this.repo = repo;
	}

	@Override
	public <S extends Product> S save(S entity) {
		return repo.save(entity);
	}

	@Override
	public <S extends Product> Optional<S> findOne(Example<S> example) {
		return repo.findOne(example);
	}

	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public Page<Product> findAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public List<Product> findAll(Sort sort) {
		return repo.findAll(sort);
	}

	@Override
	public List<Product> findAllById(Iterable<Long> ids) {
		return repo.findAllById(ids);
	}

	@Override
	public Optional<Product> findById(Long id) {
		return repo.findById(id);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		return repo.saveAll(entities);
	}

	@Override
	public void flush() {
		repo.flush();
	}

	@Override
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}

	@Override
	public <S extends Product> S saveAndFlush(S entity) {
		return repo.saveAndFlush(entity);
	}

	@Override
	public <S extends Product> List<S> saveAllAndFlush(Iterable<S> entities) {
		return repo.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Product> Page<S> findAll(Example<S> example, Pageable pageable) {
		return repo.findAll(example, pageable);
	}

	@Override
	public <S extends Product> long count(Example<S> example) {
		return repo.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Product> entities) {
		repo.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return repo.count();
	}

	@Override
	public <S extends Product> boolean exists(Example<S> example) {
		return repo.exists(example);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<Long> ids) {
		repo.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Product entity) {
		repo.delete(entity);
	}

	@Override
	public <S extends Product, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return repo.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends Long> ids) {
		repo.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		repo.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Product> entities) {
		repo.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

	@Override
	public Product getReferenceById(Long id) {
		return repo.getReferenceById(id);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example) {
		return repo.findAll(example);
	}

	@Override
	public <S extends Product> List<S> findAll(Example<S> example, Sort sort) {
		return repo.findAll(example, sort);
	}
	
	
	
}
