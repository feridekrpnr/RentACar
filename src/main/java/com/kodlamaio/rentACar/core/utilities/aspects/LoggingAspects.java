//package com.kodlamaio.rentACar.core.utilities.aspects;
//
//import java.time.LocalDateTime;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import com.kodlamaio.rentACar.entities.concretes.LogType;
//
//@Aspect
//@Component // bagımsız bir sekilde yapmak icin
//public class LoggingAspects {
//	
////	  @AfterThrowing //hata fırlatırsa
////	  @AfterReturning //bir şey döndügünde
////	  @Around 
////	  @Before
////    JoinPoint :metodun nerede oldugunu bilir
////    ProceedingJoinPoint
////    field_get metodlara ersim vs saglar
////    method signature typecast yapıyor
//	
//	
//	  @Before("execution (* com.kodlamaio.rentACar.business.concretes..(..)) ")
//      public void beforeLog(JoinPoint joinPoint) {
//		 LogType log = new LogType ();
//		 MethodSignature signature =(MethodSignature)joinPoint.getSignature();
//		 System.out.println(signature.getClass());
//		 log.setDate(LocalDateTime.now());
//		 log.setClassName(joinPoint.getTarget().getClass().getSimpleName());
//		 log.setMethodName(signature.getMethod().getName());
//		 log.setParameters(joinPoint.getArgs()[0].toString());
//		   JsonObject jsonObject =Json.createObjectBuilder()
//		   .add("date", log.getDate().toString())
//		   .add("className", log.getClassName())
//		   .add("methodName", log.getMethodName())
//		   .add("parameters",log.getParameters())
//		   .build();
//		   
//    	  
//      }
//}
