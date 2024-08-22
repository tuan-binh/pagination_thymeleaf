package com.ra.service.impl;

import com.ra.model.Person;
import com.ra.repository.IPersonRepository;
import com.ra.service.IPersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements IPersonService
{
	@Autowired
	private IPersonRepository personRepository;
	
	@Override
	public Page<Person> findAll(Pageable pageable, String search)
	{
		Page<Person> personPage;
		if (search.isEmpty())
		{
			personPage = personRepository.findAll(pageable);
		}
		else
		{
			personPage = personRepository.findAllByNameContainsOrAddressContains(search,search,pageable);
		}
		return personPage;
	}
}
