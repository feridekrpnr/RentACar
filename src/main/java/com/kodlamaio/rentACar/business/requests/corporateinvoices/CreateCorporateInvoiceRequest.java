package com.kodlamaio.rentACar.business.requests.corporateinvoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCorporateInvoiceRequest {

	private int id;
	private int invoiceNumber;
	private double totalPrice;
	private boolean state;
	private int rentalId;
	private int additionalServiceId;
	private int corporateCustomerId;
	
}