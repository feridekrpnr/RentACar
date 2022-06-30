package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CorporateInvoiceService;
import com.kodlamaio.rentACar.business.requests.corporateinvoices.CreateCorporateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.corporateinvoices.StateUpdateCorporateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.corporateinvoices.GetAllCorporateInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.corporateinvoices.ReadCorporateInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.CorporateInvoice;

@RestController
@RequestMapping("/api/corporateinvoices")
public class CorporateInvoicesController {
	
	private CorporateInvoiceService corporateInvoiceService;

	public CorporateInvoicesController(CorporateInvoiceService corporateInvoiceService) {
		super();
		this.corporateInvoiceService = corporateInvoiceService;
	}
	
	@PostMapping("/add")
	public Result addCorporateInvoice(@RequestBody CreateCorporateInvoiceRequest createCorporateInvoiceRequest) {
		return corporateInvoiceService.addCorporateInvoice(createCorporateInvoiceRequest);
	}
	
	@PostMapping("/cancel")
	public Result cancelCorporateInvoice(@RequestBody StateUpdateCorporateInvoiceRequest stateUpdateCorporateInvoiceRequest) {
		return corporateInvoiceService.cancelCorporateInvoice(stateUpdateCorporateInvoiceRequest);
	}
	@PostMapping("/activate")
	public Result activateCorporateInvoice(@RequestBody StateUpdateCorporateInvoiceRequest stateUpdateCorporateInvoiceRequest) {
		return corporateInvoiceService.activateCorporateInvoice(stateUpdateCorporateInvoiceRequest);
	}
	
	@GetMapping("/getAll")
	public DataResult<List<GetAllCorporateInvoicesResponse>> GetAll() {
		return corporateInvoiceService.getAll();
	}

	@GetMapping("/getById")
	public DataResult<CorporateInvoice> GetById(ReadCorporateInvoiceResponse readCorporateInvoiceResponse) {
		return corporateInvoiceService.getById(readCorporateInvoiceResponse);
	}
	
}
