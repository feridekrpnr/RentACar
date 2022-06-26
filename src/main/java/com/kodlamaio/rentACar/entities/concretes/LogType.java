package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class LogType {
	private LocalDateTime date;
	private String className;
	private String methodName;
	private String parameters;
}
