//package com.kodlamaio.rentACar.core.utilities.aspects;
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import ch.qos.logback.classic.Logger;
//
//@Aspect 
//@Component //yönetimi spirnge devretmek icin
//public class LoggingAspect {
//	
////	    @Before("pointcut")
////        public void beforeLog() {
////        	System.out.println("before brand manager deleteById");
////        }
////	    @After("pointcut")
////        public void afterLog() {
////        	System.out.println("before brand manager deleteById");
////        }
////	    
////	    @Pointcut("execution(com.kodlamaio.rentACar.entities.concretes.BrandManager.deleteById(int))")
////	    public void pointcut() { } //dummy method
//	    
////	    @Before("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))")
////	    public void beforeLog(JoinPoint joinPoint) {
////	    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
////	    	System.out.println("before ");
////	    	System.out.println(JoinPoint.FIELD_GET);
////	    	System.out.println(signature.getParameterNames()[0]);
////	    }
//	    
////	    @Before("execution(* com.kodlamaio.rentACar.business.concretes.BrandManager.*.checkIfBrandExistByName(String))")
////	    public void beforeLog(JoinPoint joinPoint) {
////	    	MethodSignature signature = (MethodSignature) joinPoint.getSignature();
////	    	System.out.println("before ");
////	    	System.out.println(JoinPoint.FIELD_GET);
////	    	System.out.println(signature.getParameterNames()[0]);
////	    }
//	 
////	    @Around("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))")
////	    public void beforeLog(ProceedingJoinPoint proceedingJoinPoint) {
////	    	 try {
////				System.out.println("before logging");
////				proceedingJoinPoint.proceed();
////				System.out.println("after logging");
////			} catch ( Throwable e) {
////				
////				
////		System.out.println("after logging");			}
////	    }
////	    
////	    
//	
//}
