package com.javacode.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.javacode.domain.Account;

public interface AccountService {

	<S extends Account> List<S> findAll(Example<S> example, Sort sort);

	<S extends Account> List<S> findAll(Example<S> example);

	Account getReferenceById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Account> entities);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends String> ids);

	<S extends Account, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Account entity);

	void deleteAllByIdInBatch(Iterable<String> ids);

	void deleteById(String id);

	<S extends Account> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Account> entities);

	<S extends Account> long count(Example<S> example);

	<S extends Account> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Account> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Account> S saveAndFlush(S entity);

	boolean existsById(String id);

	void flush();

	<S extends Account> List<S> saveAll(Iterable<S> entities);

	Optional<Account> findById(String id);

	List<Account> findAllById(Iterable<String> ids);

	List<Account> findAll(Sort sort);

	Page<Account> findAll(Pageable pageable);

	List<Account> findAll();

	<S extends Account> Optional<S> findOne(Example<S> example);

	<S extends Account> S save(S entity);

}
