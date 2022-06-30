package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.CorporateInvoice;

public interface CorporateInvoiceRepository extends JpaRepository<CorporateInvoice, Integer>{

}
