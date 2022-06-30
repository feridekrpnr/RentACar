package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.IndividualInvoiceService;
import com.kodlamaio.rentACar.business.requests.individualinvoices.CreateIndividualInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.individualinvoices.StateUpdateIndividualInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.individualinvoices.GetAllIndividualInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.individualinvoices.ReadIndividualInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.IndividualInvoice;

@RestController
@RequestMapping("/api/individualinvoices")
public class IndividualInvoicesController {

	private IndividualInvoiceService individualInvoiceService;
	
	
	public IndividualInvoicesController(IndividualInvoiceService individualInvoiceService) {
		super();
		this.individualInvoiceService = individualInvoiceService;
	}

	@PostMapping("/add")
	public Result addIndividualInvoice(@RequestBody CreateIndividualInvoiceRequest createIndividualInvoiceRequest) {
		return individualInvoiceService.addIndividualInvoice(createIndividualInvoiceRequest);
	}
	
	@PostMapping("/cancel")
	public Result cancelIndividualInvoice(@RequestBody StateUpdateIndividualInvoiceRequest stateUpdateIndividualInvoiceRequest) {
		return individualInvoiceService.cancelIndividualInvoice(stateUpdateIndividualInvoiceRequest);
	}
	@PostMapping("/activate")
	public Result activateIndividualInvoice(@RequestBody StateUpdateIndividualInvoiceRequest stateUpdateIndividualInvoiceRequest) {
		return individualInvoiceService.activateIndividualInvoice(stateUpdateIndividualInvoiceRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllIndividualInvoicesResponse>> GetAll() {
		return individualInvoiceService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<IndividualInvoice> GetById(ReadIndividualInvoiceResponse readIndividualInvoiceResponse) {
		return individualInvoiceService.getById(readIndividualInvoiceResponse);
	}
}
