package com.tvo.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@Component
public class TvoMessageProducer
{
	private static final Logger LOGGER = LoggerFactory.getLogger(TvoMessageProducer.class);

	protected static final String MESSAGE_COUNT = "messageCount";

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@PostConstruct
	public void generateMessage() throws JmsException
	{
		for (int i = 0; i < 100; i++)
		{
			final int index = i;
			final String text = "Message number is " + i + ".";

			jmsTemplate.send(new MessageCreator()
			{
				public Message createMessage(Session session) throws JMSException
				{
					TextMessage message = session.createTextMessage(text);
					message.setIntProperty(MESSAGE_COUNT, index);

					LOGGER.info("Sending message: " + text);

					return message;
				}
			});
		}

	}
}
