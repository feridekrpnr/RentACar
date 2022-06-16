package com.kodlamaio.rentACar.core.utilities.adapters;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserCheckService;
import com.kodlamaio.rentACar.entities.concretes.User;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements UserCheckService {

	public boolean CheckIfRealPerson(User user) throws NumberFormatException, RemoteException {
		
			KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();
			boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(user.getTcNo()),
					user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getDateOfBirth());
			return result;
		
	
	}
}
