package com.kodlamaio.rentACar.business.requests.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {
    private int id;
	private String invoiceNumber;
	private int rentalId;
	private double totalPrice;
	private boolean state;
}
