package com.ra.controller;

import com.ra.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/persons")
public class PersonController
{
	
	@Autowired
	private IPersonService personService;
	
	@GetMapping
	public ResponseEntity<?> index(
//			  @RequestParam(value = "page", defaultValue = "0") int page,
//			  @RequestParam(value = "size", defaultValue = "2") int size,
//			  @RequestParam(value = "sort", defaultValue = "id") String sort,
//			  @RequestParam(value = "order", defaultValue = "ASC") String order,
			  @PageableDefault(page = 0,size = 3,sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
			  @RequestParam(value = "search", defaultValue = "") String search
	)
	{
		// PageRequest
		
//		Pageable pageable;
//		if (order.equals("ASC"))
//		{
//			pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
//		}
//		else
//		{
//			pageable = PageRequest.of(page, size, Sort.by(sort).descending());
//		}
		
//		return new ResponseEntity<>("body", HttpStatus.OK);
		return ResponseEntity.ok().body(personService.findAll(pageable,search));
	}
	
	@GetMapping("/{personId}/status")
	public String changeStatus(@PathVariable Long personId)
	{
		personService.changeStatus(personId);
		return "redirect:/";
	}
}
