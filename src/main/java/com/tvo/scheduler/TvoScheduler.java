package com.tvo.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TvoScheduler
{
	@Scheduled(fixedDelay=5000)
	public void print()
	{
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
}
