package org.kepennar.facemash.controller;

import javax.inject.Inject;

import org.kepennar.facemash.datatable.PageRequest;
import org.kepennar.facemash.datatable.PageResponse;
import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.model.security.Role;
import org.kepennar.facemash.service.AdminService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Secured(Role.ADMIN)
@RequestMapping(value = "/admin")
public class AdminController {

	@Inject
	private AdminService adminService;

	
	
    @RequestMapping(value="search", method = RequestMethod.POST)
    @ResponseBody
    public PageResponse<Element> search( @RequestBody PageRequest pageRequest) {	
    	return adminService.getElementPage(pageRequest);
    }
}
