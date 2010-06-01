package com.tvo.dao;

import lombok.Data;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Component
@Data
public class PropertyPlaceHolder
{
	@Value("${hongli.li.name}")
	private String message;
}
