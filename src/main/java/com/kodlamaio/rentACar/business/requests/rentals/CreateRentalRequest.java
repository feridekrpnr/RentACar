package com.kodlamaio.rentACar.business.requests.rentals;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRentalRequest {
	private int id;
	private int carId;
	private Date pickupDate;
	private Date returnDate;
	private int totalDays;
	private String pickupCity;
	private String returnCity;
	private int userId;

	
	
	

}
