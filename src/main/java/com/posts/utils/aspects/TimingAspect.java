package com.posts.utils.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimingAspect {

	@Pointcut("execution(public * * (..))")
	public void publicMethods() 
	{
		
	}
	
	@Pointcut("@annotation(com.posts.utils.aspects.Timed)")
	public void findTimedAnnotations() {}

	@Around("publicMethods() && findTimedAnnotations()")
	public Object logBefore(ProceedingJoinPoint pjp) throws Throwable
	{
		StopWatch watch = new StopWatch();
		
		watch.start();
		
		Object o = pjp.proceed();
		
		watch.stop();
		
		System.out.format("[ [%s] = %f ms]", pjp.getSignature(), watch.getTotalTimeSeconds());
		
		return o;
	}
	
}
