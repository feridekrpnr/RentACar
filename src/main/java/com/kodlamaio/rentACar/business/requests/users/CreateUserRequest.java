package com.kodlamaio.rentACar.business.requests.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

	private String firstName;
	

	private String lastName;
	

	private String tcNo;

	
	private String eMail;

	private String password;
}
