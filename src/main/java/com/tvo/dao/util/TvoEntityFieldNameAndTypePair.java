package com.tvo.dao.util;

import java.io.Serializable;

import lombok.Data;

@Data
public class TvoEntityFieldNameAndTypePair implements Serializable
{
	private static final long serialVersionUID = 4089788462220700913L;
	private String key;
	private Class<?> type;
}
