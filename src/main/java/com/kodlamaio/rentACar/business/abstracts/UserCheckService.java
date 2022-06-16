package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.entities.concretes.User;

public interface UserCheckService {
	boolean CheckIfRealPerson(User user) throws NumberFormatException, RemoteException;
}
