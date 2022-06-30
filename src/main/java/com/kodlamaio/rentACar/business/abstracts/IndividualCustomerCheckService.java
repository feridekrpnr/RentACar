package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

public interface IndividualCustomerCheckService {
	
	boolean CheckIfRealPerson(IndividualCustomer individualCustomer) throws NumberFormatException, RemoteException;
}
