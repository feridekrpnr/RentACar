package com.kodlamaio.rentACar.core.utilities.adapters;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerCheckService;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;
@Service
public class MernisServiceAdapter implements IndividualCustomerCheckService {

	@Override
	public boolean CheckIfRealPerson(IndividualCustomer individualCustomer) throws NumberFormatException, RemoteException {
	KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
	boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(individualCustomer.getIdentityNumber()),
			individualCustomer.getFirstName().toUpperCase(), individualCustomer.getLastName().toUpperCase(), individualCustomer.getBirthYear());
	return result;
	}

	
	
	
}
