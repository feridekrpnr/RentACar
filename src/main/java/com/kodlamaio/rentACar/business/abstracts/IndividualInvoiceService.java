package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.individualinvoices.CreateIndividualInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.individualinvoices.StateUpdateIndividualInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.individualinvoices.GetAllIndividualInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.individualinvoices.ReadIndividualInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.IndividualInvoice;

public interface IndividualInvoiceService {
	Result addIndividualInvoice(CreateIndividualInvoiceRequest createIndividualInvoiceRequest);
	Result cancelIndividualInvoice(StateUpdateIndividualInvoiceRequest stateUpdateIndividualInvoiceRequest);
	Result activateIndividualInvoice(StateUpdateIndividualInvoiceRequest stateUpdateIndividualInvoiceRequest);
	DataResult<List<GetAllIndividualInvoicesResponse>> getAll();
	DataResult<IndividualInvoice> getById(ReadIndividualInvoiceResponse readIndividualInvoiceResponse);
}
