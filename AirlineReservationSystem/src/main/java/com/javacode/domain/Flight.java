package com.javacode.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flights")
public class Flight implements Serializable {
	@Id
	@Column(length = 5)
	private String flightNo;

	@Column(columnDefinition = "varchar(20) not null")
	private String flightName;

	@Column(columnDefinition = "varchar(30) not null")
	private String fromCity;

	@Column(columnDefinition = "varchar(30) not null")
	private String toCity;

	@Temporal(TemporalType.DATE)
	private Date flightDate;

	@Column(columnDefinition = "varchar(20) not null")
	private String time;

	@Column(columnDefinition = "varchar(20) not null")
	private String travelDuration;

	@Column(columnDefinition = "varchar(20) not null")
	private String airportName;

	@Column(nullable = false)
	private Double ticketPrice;

	@Column(columnDefinition = "varchar(500) not null")
	private String description;
}
