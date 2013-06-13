package org.kepennar.facemash.datatable.converter;

import org.kepennar.facemash.datatable.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * Convert a org.kepennar.facemash.datatable.PageRequest to a org.springframework.data.domain.Pageable
 * @author KEPENNAR
 *
 */
public class PageRequest2Pageable {
	
	
	public static Pageable convert(PageRequest pageRequest) {
		int pageNumber = pageRequest.getPageNumber();
		int pageSize = pageRequest.getiDisplayLength();
		Pageable pageable = null;
		if (pageRequest.getOrderBy() != null && pageRequest.getOrderSens() != null) {
			Sort sort = new Sort(Direction.fromString(pageRequest.getOrderSens().toString()), pageRequest.getOrderBy());
			pageable = new org.springframework.data.domain.PageRequest(pageNumber, pageSize, sort);
		} else {
			pageable = new org.springframework.data.domain.PageRequest(pageNumber, pageSize);
		}
		return pageable;
		
	}

}
