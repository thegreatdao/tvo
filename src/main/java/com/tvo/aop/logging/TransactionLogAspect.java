package com.tvo.aop.logging;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionLogAspect
{
	private final Logger LOGGER = LoggerFactory.getLogger(TransactionLogAspect.class);
	
	@Around("execution(* com.tvo.scheduler.*.*(..))")
	public void logTransactions(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
	{
		Signature signature = proceedingJoinPoint.getSignature();
		LOGGER.info(signature + " invoked at --" + new Date());
		proceedingJoinPoint.proceed();
		LOGGER.info(signature + " ended at --" + new Date());
	}
}