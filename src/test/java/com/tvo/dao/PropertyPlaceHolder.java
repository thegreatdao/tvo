package com.tvo.dao;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class PropertyPlaceHolder
{
	@Value("${hongli.li.name}")
	private String message;
}
