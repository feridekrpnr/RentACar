package com.kodlamaio.rentACar.business.responses.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCustomersResponse {
	private int id;
	private String firstName;
	private String lastName;
	private String tcNo;
	private String eMail;
	private String password;
	
}
