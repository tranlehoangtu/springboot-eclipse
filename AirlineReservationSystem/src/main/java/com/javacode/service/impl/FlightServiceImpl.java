package com.javacode.service.impl;

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

import com.javacode.domain.Flight;
import com.javacode.respository.FlightRepository;
import com.javacode.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
	private FlightRepository flightRespository;

	@Autowired
	public FlightServiceImpl(FlightRepository flightRespository) {
		this.flightRespository = flightRespository;
	}
	// Return List of Flight
	@Override
	public List<Flight> findByFlightNoContaining(String id) {
		return flightRespository.findByFlightNoContaining(id);
	}
	
	@Override
	public List<Flight> findByFlightNameContainingAndFlightNoContaining(String name, String id) {
		return flightRespository.findByFlightNameContainingAndFlightNoContaining(name, id);
	}
	
	@Override
	public List<Flight> findByFlightNameContaining(String name) {
		return flightRespository.findByFlightNameContaining(name);
	}
	
	// Page<List>
	@Override
	public Page<Flight> findByFlightNameContaining(String name, Pageable pageable) {
		return flightRespository.findByFlightNameContaining(name, pageable);
	}
	
	@Override
	public Page<Flight> findByFlightNoContaining(String id, Pageable pageable) {
		return flightRespository.findByFlightNoContaining(id, pageable);
	}
	@Override
	public Page<Flight> findByFlightNameContainingAndFlightNoContaining(String name, String id, Pageable pageable) {
		return flightRespository.findByFlightNameContainingAndFlightNoContaining(name, id, pageable);
	}

	@Override
	public <S extends Flight> S save(S entity) {
		return flightRespository.save(entity);
	}

	@Override
	public <S extends Flight> Optional<S> findOne(Example<S> example) {
		return flightRespository.findOne(example);
	}

	@Override
	public List<Flight> findAll() {
		return flightRespository.findAll();
	}

	@Override
	public Page<Flight> findAll(Pageable pageable) {
		return flightRespository.findAll(pageable);
	}

	@Override
	public List<Flight> findAll(Sort sort) {
		return flightRespository.findAll(sort);
	}

	@Override
	public List<Flight> findAllById(Iterable<String> ids) {
		return flightRespository.findAllById(ids);
	}

	@Override
	public Optional<Flight> findById(String id) {
		return flightRespository.findById(id);
	}

	@Override
	public <S extends Flight> List<S> saveAll(Iterable<S> entities) {
		return flightRespository.saveAll(entities);
	}

	@Override
	public void flush() {
		flightRespository.flush();
	}

	@Override
	public boolean existsById(String id) {
		return flightRespository.existsById(id);
	}

	@Override
	public <S extends Flight> S saveAndFlush(S entity) {
		return flightRespository.saveAndFlush(entity);
	}

	@Override
	public <S extends Flight> List<S> saveAllAndFlush(Iterable<S> entities) {
		return flightRespository.saveAllAndFlush(entities);
	}

	@Override
	public <S extends Flight> Page<S> findAll(Example<S> example, Pageable pageable) {
		return flightRespository.findAll(example, pageable);
	}

	@Override
	public <S extends Flight> long count(Example<S> example) {
		return flightRespository.count(example);
	}

	@Override
	public void deleteAllInBatch(Iterable<Flight> entities) {
		flightRespository.deleteAllInBatch(entities);
	}

	@Override
	public long count() {
		return flightRespository.count();
	}

	@Override
	public <S extends Flight> boolean exists(Example<S> example) {
		return flightRespository.exists(example);
	}

	@Override
	public void deleteById(String id) {
		flightRespository.deleteById(id);
	}

	@Override
	public void deleteAllByIdInBatch(Iterable<String> ids) {
		flightRespository.deleteAllByIdInBatch(ids);
	}

	@Override
	public void delete(Flight entity) {
		flightRespository.delete(entity);
	}

	@Override
	public <S extends Flight, R> R findBy(Example<S> example, Function<FetchableFluentQuery<S>, R> queryFunction) {
		return flightRespository.findBy(example, queryFunction);
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		flightRespository.deleteAllById(ids);
	}

	@Override
	public void deleteAllInBatch() {
		flightRespository.deleteAllInBatch();
	}

	@Override
	public void deleteAll(Iterable<? extends Flight> entities) {
		flightRespository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		flightRespository.deleteAll();
	}

	@Override
	public Flight getReferenceById(String id) {
		return flightRespository.getReferenceById(id);
	}

	@Override
	public <S extends Flight> List<S> findAll(Example<S> example) {
		return flightRespository.findAll(example);
	}

	@Override
	public <S extends Flight> List<S> findAll(Example<S> example, Sort sort) {
		return flightRespository.findAll(example, sort);
	}

}
