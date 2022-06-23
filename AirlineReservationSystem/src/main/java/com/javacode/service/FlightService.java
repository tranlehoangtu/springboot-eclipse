package com.javacode.service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.javacode.domain.Flight;

public interface FlightService {

	<S extends Flight> List<S> findAll(Example<S> example, Sort sort);

	<S extends Flight> List<S> findAll(Example<S> example);

	Flight getReferenceById(String id);

	void deleteAll();

	void deleteAll(Iterable<? extends Flight> entities);

	void deleteAllInBatch();

	void deleteAllById(Iterable<? extends String> ids);

	<S extends Flight, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction);

	void delete(Flight entity);

	void deleteAllByIdInBatch(Iterable<String> ids);

	void deleteById(String id);

	<S extends Flight> boolean exists(Example<S> example);

	long count();

	void deleteAllInBatch(Iterable<Flight> entities);

	<S extends Flight> long count(Example<S> example);

	<S extends Flight> Page<S> findAll(Example<S> example, Pageable pageable);

	<S extends Flight> List<S> saveAllAndFlush(Iterable<S> entities);

	<S extends Flight> S saveAndFlush(S entity);

	boolean existsById(String id);

	void flush();

	<S extends Flight> List<S> saveAll(Iterable<S> entities);

	Optional<Flight> findById(String id);

	List<Flight> findAllById(Iterable<String> ids);

	List<Flight> findAll(Sort sort);

	Page<Flight> findAll(Pageable pageable);

	List<Flight> findAll();

	<S extends Flight> Optional<S> findOne(Example<S> example);

	<S extends Flight> S save(S entity);

	List<Flight> findByFlightNameContaining(String name);

	List<Flight> findByFlightNameContainingAndFlightNoContaining(String name, String id);

	List<Flight> findByFlightNoContaining(String id);

	Page<Flight> findByFlightNameContaining(String name, Pageable pageable);

	Page<Flight> findByFlightNameContainingAndFlightNoContaining(String name, String id, Pageable pageable);

	Page<Flight> findByFlightNoContaining(String id, Pageable pageable);

}
