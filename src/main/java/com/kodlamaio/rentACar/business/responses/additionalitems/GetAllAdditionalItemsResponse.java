package com.kodlamaio.rentACar.business.responses.additionalitems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAllAdditionalItemsResponse {
	private int id;
	private String name;
	private String description;
	
}
