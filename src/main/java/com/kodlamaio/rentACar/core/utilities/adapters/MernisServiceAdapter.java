package com.kodlamaio.rentACar.core.utilities.adapters;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserCheckService;
import com.kodlamaio.rentACar.entities.concretes.User;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;
@Service
public class MernisServiceAdapter implements UserCheckService{
	
	
	public boolean CheckIfRealPerson(User user) {
		try {
			KPSPublicSoapProxy kpsPublicSoapProxy= new KPSPublicSoapProxy();
			boolean isValid =kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(user.getTcNo()),user.getFirstName().toUpperCase(),
					user.getLastName().toUpperCase(),user.getDateOfBirth());
			return isValid;
		} catch (Exception e) {
			
			System.out.println("Giris bilgileri dogru degil");
		}
		return false;
	}
}
