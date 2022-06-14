package com.kodlamaio.rentACar.business.requests.maintenances;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMaintenanceRequest {
	private int id;
	private Date dateSent;
	private Date dateReturn;
	private int carId;
}
