package com.javacode.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
	private String flightNo;
	private String flightName;
	private String fromCity;
	private String toCity;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date flightDate;
	private String time;
	private String travelDuration;
	private String airportName;
	private Double ticketPrice;
	private String description;
	private Boolean isEdit = false;
}
