package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.StateUpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.invoices.ReadInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.entities.concretes.Invoice;

import lombok.var;
@RestController
@RequestMapping("/api/invoices")
public class InvoicesController {

	@Autowired
	InvoiceService invoiceService; 
	
	@PostMapping("/add")
	private Result add(@RequestBody CreateInvoiceRequest createInvoiceRequest ) {
		var result = invoiceService.add(createInvoiceRequest);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		} else {
			return new ErrorResult(result.getMessage());
		}
		
	}
	@PostMapping("/cancel")
	public Result add(@RequestBody StateUpdateInvoiceRequest stateUpdateInvoiceRequest) {
		var result = invoiceService.cancelInvoice(stateUpdateInvoiceRequest);
		if (result.isSuccess()) {
			return new SuccessResult(result.getMessage());
		} else {
			return new ErrorResult(result.getMessage());
		}

	}
	@GetMapping("/getById")
	private DataResult<Invoice> getById(ReadInvoiceResponse readInvoiceResponse) {
		return this.invoiceService.getById(readInvoiceResponse);
	}
	
	@GetMapping("/getAll")
	private DataResult<List<GetAllInvoicesResponse>> getAll() {
		return this.invoiceService.getAll();
	}
}
