package com.kodlamaio.rentACar.business.requests.customers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCustomerRequest {
	private int id;
	private String firstName;
	private String lastName;
	private String tcNo;
	private String eMail;
	private String password;
}
