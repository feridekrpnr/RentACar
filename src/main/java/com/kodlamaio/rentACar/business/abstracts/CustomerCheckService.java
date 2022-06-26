package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.entities.concretes.Customer;

public interface CustomerCheckService {
	
	boolean CheckIfRealPerson(Customer customer) throws NumberFormatException, RemoteException;
}
