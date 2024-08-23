package com.ra.repository;

import com.ra.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
// crudRepository
// PagingAndSortingRepository
// JpaRepository

@Transactional
public interface IPersonRepository extends JpaRepository<Person, Long>
{
	
	// JPQL
	@Query(value = "SELECT p FROM Person p where p.name like concat('%',:search,'%') or p.address like concat('%',:search,'%')")
	Page<Person> findAllByNameContainsOrAddressContains(@Param("search") String name, Pageable pageable);
	
	@Query("update Person p set p.status = (not p.status) where p.id = :personId")
	@Modifying // thêm sửa xóa tự custom viết query thì sẽ phải dùng
	void updateStatus(@Param("personId") Long personId);

}
