package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.corporateinvoices.CreateCorporateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.corporateinvoices.StateUpdateCorporateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.corporateinvoices.GetAllCorporateInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.corporateinvoices.ReadCorporateInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.CorporateInvoice;

public interface CorporateInvoiceService {
	Result addCorporateInvoice(CreateCorporateInvoiceRequest createCorporateInvoiceRequest);
	Result cancelCorporateInvoice(StateUpdateCorporateInvoiceRequest stateUpdateCorporateInvoiceRequest);
	Result activateCorporateInvoice(StateUpdateCorporateInvoiceRequest stateUpdateCorporateInvoiceRequest);
	DataResult<List<GetAllCorporateInvoicesResponse>> getAll();
	DataResult<CorporateInvoice> getById(ReadCorporateInvoiceResponse readCorporateInvoiceResponse);
}
