//package com.kodlamaio.rentACar.core.utilities.aspects;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.time.LocalDateTime;
//
//import javax.json.Json;
//import javax.json.JsonObject;
//
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import com.kodlamaio.rentACar.entities.concretes.LogType;
//@Aspect
//@Component
//public class Logging {
//	@Before("execution(* com.kodlamaio.rentACar.business.concretes.*.*(..))")
//    public void beforeLog(JoinPoint joinPoint) throws ClassNotFoundException {
//        LogType log = new LogType();
//
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        log.setDate(LocalDateTime.now());
//        log.setClassName(joinPoint.getTarget().getClass().getSimpleName());
//        log.setMethodName(signature.getMethod().getName());
//        log.setParameters(joinPoint.getArgs()[0].toString());
//
//        @SuppressWarnings("unused")
//        JsonObject jsonObject = Json.createObjectBuilder().add("date", log.getDate().toString())
//                .add("className", log.getClassName()).add("methodName", log.getMethodName())
//                .add("parameters", log.getParameters()).build();
//
//
//        File file = new File("C:\\logs\\operations.json");
//
//        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
//            bufferWriter.write(jsonObject.toString() + " , ");
//        } catch (IOException e) {
//            System.out.println("Unable to read file " + file.toString());
//        }
//    }
//}
