package org.kepennar.facemash.service;

import java.util.List;

import org.kepennar.facemash.model.Element;

public interface SearchElementService {

	List<Element> searchElement(String term);
	void registerElement(Element pElement);
}
