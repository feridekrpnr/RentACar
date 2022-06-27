package com.kodlamaio.rentACar.business.requests.additionalservices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAdditionalServiceRequest {
	private int id;
	private int day;
	private int additionalItemId;
	private double totalPrice;
	private Date sendDate;
	private Date returnDate;
}
