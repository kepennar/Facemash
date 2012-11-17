package org.kepennar.facemash.controller;
import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.service.ElementService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController {

	@Inject @Named("elementService")
	private ElementService elementService;
	

	

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Element processUpload(
			@RequestParam("file") MultipartFile file,
			@RequestParam("name") String name, 
			@RequestParam("description") String description) {
		
		return elementService.createElement(name, description, file);
	
	}
}

