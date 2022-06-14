package com.javacode.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javacode.domain.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

}
