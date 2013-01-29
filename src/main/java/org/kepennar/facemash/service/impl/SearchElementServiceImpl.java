package org.kepennar.facemash.service.impl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.repository.ElementRepository;
import org.kepennar.facemash.service.SearchElementService;
import org.kepennar.facemash.solr.repository.SolrElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

@Service("searchElementService")
public class SearchElementServiceImpl implements SearchElementService {

	private static final Function<Element, String> elementsIds = new ElementsIdsFunction();
	
	
	@Autowired private SolrElementRepository solrElementRepository;
	@Autowired private ElementRepository elementRepository;
	
	@Override
	public List<Element> searchElement(String term) {
		List<Element> elementListFounded = solrElementRepository.findByNameOrDescriptionOrderByScoreDesc(term, term);
		Collection<String> ids = Collections2.transform(elementListFounded, elementsIds);
		
		return Lists.newArrayList(elementRepository.findAll(ids));
	}
	
	private static class ElementsIdsFunction implements Function<Element, String> {
		@Override
		@Nullable
		public String apply(@Nullable Element elem) {
			return elem != null ? elem.getId() : null;
		}
	}

}
