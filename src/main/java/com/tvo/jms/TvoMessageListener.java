package com.tvo.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TvoMessageListener implements MessageListener
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TvoMessageListener.class);

	public void onMessage(Message message)
	{
		try
		{
			int count = message.getIntProperty(TvoMessageProducer.MESSAGE_COUNT);

			if(message instanceof TextMessage)
			{
				TextMessage tm = (TextMessage) message;
				String msg = tm.getText();

				LOGGER.info("Processed message '{}'.  value={}", msg, count);

			}
		} catch (JMSException e)
		{
			LOGGER.error(e.getMessage(), e);
		}
	}

}
