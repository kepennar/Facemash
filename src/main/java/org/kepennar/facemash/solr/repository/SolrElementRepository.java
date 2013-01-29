package org.kepennar.facemash.solr.repository;

import java.util.List;

import org.kepennar.facemash.model.Element;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolrElementRepository extends SolrCrudRepository<Element, String>{

	List<Element> findByNameOrDescriptionOrderByScoreDesc(String termName, String termDescription);
}
