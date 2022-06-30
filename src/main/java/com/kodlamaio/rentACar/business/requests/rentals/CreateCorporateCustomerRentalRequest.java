package com.kodlamaio.rentACar.business.requests.rentals;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateCustomerRentalRequest {
	private int corporateCustomerId;
	private Date pickupDate;
	private Date returnDate;
	private String pickupCity;
	private String returnCity;
	private int totalDays;
	private int carId;
	
}
