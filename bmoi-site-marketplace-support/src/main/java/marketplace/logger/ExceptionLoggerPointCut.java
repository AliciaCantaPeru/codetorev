package marketplace.logger;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package marketplace.logger;
//
//import lombok.extern.log4j.Log4j2;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
///**
// *
// * @author Martin Pilar <mpilarcastillejo@gmail.com>
// */
//@Aspect
//@Log4j2
//@Component
//public class ExceptionLoggerPointCut {
//
//    @Pointcut("bean(*Controller)")
//    private void controllerBean() {
//    }
//
//    @Pointcut("bean(*ServiceImpl)")
//    private void serviceBean() {
//    }
//
//    @Pointcut("bean(*RepositoryImpl)")
//    private void repositoryBean() {
//    }
//
//    @Pointcut("execution(public void *())")
//    private void publicMethodActionListener() {
//    }
//
//    @Pointcut("execution(public * *(..))")
//    private void publicMethod() {
//    }
//
//    @Pointcut("execution(private * *(..))")
//    private void privateMethod() {
//    }
//
//    @Around("(controllerBean() || serviceBean() || repositoryBean())&& (publicMethodActionListener()|| publicMethod() || privateMethod())")
//    private Object controlGeneralLog(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startTime = System.currentTimeMillis();
//        Object o = joinPoint.proceed();
//        long timeTaken = System.currentTimeMillis() - startTime;
//        log.info("demore {}", timeTaken);
//        return o;
//    }
//
//    @AfterThrowing(pointcut = "(controllerBean() )", throwing = "e")
//    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
//
//        log.error(getStackTrace(e, joinPoint));
//    }
//
//    private static String getStackTrace(Throwable e, JoinPoint joinPoint) {
//        String NL = System.getProperty("line.separator");
//        StringBuilder builder = new StringBuilder();
//        builder.append("\n ")
//                .append("error --->  ")
//                .append(e.getMessage())
//                .append("\n ");
//        builder.append("causa --->  ")
//                .append(e.getCause())
//                .append(NL);
//        StackTraceElement[] elements = e.getStackTrace();
//        for (StackTraceElement s : elements) {
//            if (s.getFileName().contains("<generated>")) {
//                break;
//            }
//            builder.append("\tat ")
//                    .append(s.getClassName())
//                    .append(".")
//                    .append(s.getMethodName())
//                    .append("(")
//                    .append(s.getFileName())
//                    .append(":")
//                    .append(s.getLineNumber())
//                    .append(")")
//                    .append(NL);
//        }
//        return builder.toString();
//    }
//
////
////    @Before("execution(* com.solmit.*.*(..))")
////    public void antes(JoinPoint joinPoint) {
////        //Advice
////        log.info(" Check for user access ");
////        log.info(" Allowed execution for {}", joinPoint);
////    }
//}
