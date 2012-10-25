package org.kepennar.facemash.service;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.mvc.model.Fight;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ElementService {

	Element createElement(String name, String description, MultipartFile file);
	
	Page<Element> getTopRated(int pageNumber, int pageSize);
	
	Page<Element> getWorstRated(int pageNumber, int pageSize);

	Element getWinner();
	Fight getFight();
	int getTotalPlayed();
}
