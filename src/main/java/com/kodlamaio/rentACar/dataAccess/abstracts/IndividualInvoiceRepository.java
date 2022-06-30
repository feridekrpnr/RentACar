package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.IndividualInvoice;

public interface IndividualInvoiceRepository extends JpaRepository<IndividualInvoice, Integer>{

}
