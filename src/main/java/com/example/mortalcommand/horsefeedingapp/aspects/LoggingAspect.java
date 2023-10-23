package com.example.mortalcommand.horsefeedingapp.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.example.mortalcommand.horsefeedingapp.presentation.StableController.*(..))")
    public void allStableControllerMethods() {}

//    @Before("allStableControllerMethods()")
    @Before("execution(* com.example.mortalcommand.horsefeedingapp.presentation.StableController.readStables())")
    public void logEntry() {
        System.out.println("Start Aspect");
    }
    @After("execution(* com.example.mortalcommand.horsefeedingapp.presentation.StableController.readStables())")
    public void logExit() {
        System.out.println("Start Aspect");
    }
}
