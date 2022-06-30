package com.kodlamaio.rentACar.business.requests.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateUpdateInvoiceRequest {
	private int id;
	private String invoiceNumber;
	private boolean state;
	private int rentalId;

}
