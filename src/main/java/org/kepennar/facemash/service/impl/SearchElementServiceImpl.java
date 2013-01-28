package org.kepennar.facemash.service.impl;

import java.util.List;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.service.SearchElementService;
import org.kepennar.facemash.solr.repository.SolrElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("searchElementService")
public class SearchElementServiceImpl implements SearchElementService {

	@Autowired private SolrElementRepository solrElementRepository;
	
	@Override
	public List<Element> searchElement(String term) {
		//TEST
		int t =0;
		return solrElementRepository.findByNameOrDescriptionOrScore(term, t);
	}

}
