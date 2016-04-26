package com.posts.utils.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(public * * (..))")
	public void publicMethods() 
	{
		
	}

	@Before("publicMethods() && execution(public * com.posts.services.data..* (..))")
	public void logBefore(JoinPoint joinPoint) 
	{
		System.out.println("logBefore() : " + joinPoint.getSignature());
	}
	
}
