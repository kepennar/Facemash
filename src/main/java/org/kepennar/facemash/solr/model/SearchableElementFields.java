package org.kepennar.facemash.solr.model;

import org.springframework.data.solr.core.query.Field;

public enum SearchableElementFields implements Field {
	
	ID(SearchableElement.ID),
	FAMILY(SearchableElement.FAMILY),
	NAME(SearchableElement.NAME),
	DESCRIPTION(SearchableElement.DESCRIPTION),
	SCORE(SearchableElement.SCORE);

	
	private final String fieldName;

	private SearchableElementFields(String fieldName) {
		this.fieldName = fieldName;
	}
	@Override
	public String getName() {
		return fieldName;
	}
	

}
