package com.ra.repository;

import com.ra.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// crudRepository
// PagingAndSortingRepository
// JpaRepository


public interface IPersonRepository extends JpaRepository<Person, Long>
{
	
	Page<Person> findAllByNameContainsOrAddressContains(String name, String address, Pageable pageable);
	
}
