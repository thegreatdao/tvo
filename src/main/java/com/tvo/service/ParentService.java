package com.tvo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tvo.entity.Parent;
import com.tvo.entity.Parents;

@Service
public class ParentService
{
	public Parents getParents()
	{
		List<Parent> parents = new ArrayList<Parent>();
		Parent parent = new Parent();
		parent.setId(1);
		parent.setName("first parent");
		Parent parent2 = new Parent();
		parent2.setId(2);
		parent2.setName("second parent");
		parents.add(parent);
		parents.add(parent2);
		return new Parents(parents);
	}
}
