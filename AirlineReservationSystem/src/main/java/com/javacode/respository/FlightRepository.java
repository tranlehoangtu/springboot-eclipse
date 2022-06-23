package com.javacode.respository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacode.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {
	
	List<Flight> findByFlightNameContaining(String name);
	List<Flight> findByFlightNoContaining(String id);
	List<Flight> findByFlightNameContainingAndFlightNoContaining(String name, String id);
	
	Page<Flight> findByFlightNameContaining(String name, Pageable pageable);
	Page<Flight> findByFlightNoContaining(String id, Pageable pageable);
	Page<Flight> findByFlightNameContainingAndFlightNoContaining(String name, String id, Pageable pageable);
}
