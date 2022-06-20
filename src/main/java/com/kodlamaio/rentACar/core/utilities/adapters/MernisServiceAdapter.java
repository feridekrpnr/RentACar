package com.kodlamaio.rentACar.core.utilities.adapters;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CustomerCheckService;
import com.kodlamaio.rentACar.entities.concretes.Customer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements CustomerCheckService {

	public boolean CheckIfRealPerson(Customer customer) throws NumberFormatException, RemoteException {
		
			KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
			boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(customer.getTcNo()),
					customer.getFirstName().toUpperCase(), customer.getLastName().toUpperCase(), customer.getDateOfBirth());
			return result;
		
	
	}
}
