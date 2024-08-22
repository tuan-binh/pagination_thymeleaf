package com.ra.controller;

import com.ra.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping
public class PersonController
{
	
	@Autowired
	private IPersonService personService;
	
	@GetMapping
	public String index(
			  @RequestParam(value = "page", defaultValue = "0") int page,
			  @RequestParam(value = "size", defaultValue = "2") int size,
			  @RequestParam(value = "sort", defaultValue = "id") String sort,
			  @RequestParam(value = "order", defaultValue = "ASC") String order,
//			  @PageableDefault(page = 0,size = 3,sort = "id",direction = Sort.Direction.ASC) Pageable pageable,
			  @RequestParam(value = "search", defaultValue = "") String search,
			  Model model
	)
	{
		// PageRequest
		
		Pageable pageable;
		if (order.equals("ASC"))
		{
			pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
		}
		else
		{
			pageable = PageRequest.of(page, size, Sort.by(sort).descending());
		}
		model.addAttribute("search", search);
		model.addAttribute("persons", personService.findAll(pageable,search));
		return "persons";
	}
	
	@GetMapping("/{personId}/status")
	public String changeStatus(@PathVariable Long personId)
	{
		personService.changeStatus(personId);
		return "redirect:/";
	}
}
