package com.kodlamaio.rentACar.business.responses.rentals;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllRentalsResponse {
	private int id;
	private int carId;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private int additionalServiceId;
}
