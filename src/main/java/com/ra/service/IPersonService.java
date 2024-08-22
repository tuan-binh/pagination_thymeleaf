package com.ra.service;

import com.ra.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPersonService
{
	
	Page<Person> findAll(Pageable pageable,String search);
	
	void changeStatus(Long personId);
	
}
