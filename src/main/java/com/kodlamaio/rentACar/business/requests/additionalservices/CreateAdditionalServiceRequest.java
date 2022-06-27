package com.kodlamaio.rentACar.business.requests.additionalservices;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdditionalServiceRequest {
	private int id;
	private int day;
	private int additionalItemId;
	private Date sendDate;
	private Date returnDate;
	

}
