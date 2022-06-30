package com.kodlamaio.rentACar.business.requests.rentals;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIndividualCustomerRentalRequest {
	private int id;
	private Date pickupDate;
	private Date returnDate;
	private String pickupCity;
	private String returnCity;
	private int totalDays;
	private double totalPrice;
	private int carId;
	private int additionalServiceId;
	private int individualCustomerId;
}
