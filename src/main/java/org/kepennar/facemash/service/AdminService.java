package org.kepennar.facemash.service;

import org.kepennar.facemash.datatable.PageRequest;
import org.kepennar.facemash.datatable.PageResponse;
import org.kepennar.facemash.model.Element;

public interface AdminService {
	PageResponse<Element> getElementPage(PageRequest pageRequest);
}
