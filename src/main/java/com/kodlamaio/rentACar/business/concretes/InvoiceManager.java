package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.StateUpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoices.GetAllInvoicesResponse;
import com.kodlamaio.rentACar.business.responses.invoices.ReadInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.RentalDetail;

@Service
public class InvoiceManager implements InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;
	@Autowired
	ModelMapperService modelMapperService;

	@Autowired
	RentalDetailRepository rentalDetailRepository;

	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		RentalDetail rentalDetail = this.rentalDetailRepository.findById(createInvoiceRequest.getRentalDetailId())
				.get();
		invoice.setRentalDetail(rentalDetail);
		if (!checkIfInvoicesNumber(invoice.getInvoiceNumber())) {
			invoice.setState(true);
			invoiceRepository.save(invoice);
			return new SuccessResult("Fatura eklendi");
		} else {

			return new ErrorResult("Fatura zaten var, numara kontrolü yap!");
		}
	}

	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		this.invoiceRepository.deleteById(deleteInvoiceRequest.getId());
		return new SuccessResult("deleted");
	}

	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<GetAllInvoicesResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<GetAllInvoicesResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoice, GetAllInvoicesResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<GetAllInvoicesResponse>>(response);
	}

	@Override
	public DataResult<Invoice> getById(ReadInvoiceResponse readInvoiceResponse) {
		Invoice invoiceToGet = this.modelMapperService.forResponse().map(readInvoiceResponse, Invoice.class);
		invoiceToGet = invoiceRepository.findById(readInvoiceResponse.getId());

		return new SuccessDataResult<Invoice>(invoiceToGet);
	}

	@Override
	public boolean checkIfInvoicesNumber(String invoiceNumber) {
		boolean state = false;
		List<Invoice> invoices = invoiceRepository.findAll();
		for (Invoice item : invoices) {
			if (item.getInvoiceNumber().equals(invoiceNumber) ) {
				state = true;
			}
		}
		return state;
	}

	@Override
	public Result cancelInvoice(StateUpdateInvoiceRequest stateUpdateInvoiceRequest) {
		Invoice invoice = modelMapperService.forRequest().map(stateUpdateInvoiceRequest, Invoice.class);
		if (checkIfInvoicesNumber(invoice.getInvoiceNumber())) {
			invoice.setState(false);
			invoiceRepository.save(invoice);
			return new SuccessResult("Fatura pasife geçilmiştir.");
		}
		return new ErrorResult("Silinecek bir fatura bulunamadı");
	}

}
