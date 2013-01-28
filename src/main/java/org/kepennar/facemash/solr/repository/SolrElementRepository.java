package org.kepennar.facemash.solr.repository;

import java.util.List;

import org.kepennar.facemash.model.Element;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolrElementRepository extends SolrCrudRepository<Element, String>{

	@Query("name:?0 OR description:?0 OR score:?1")
	List<Element> findByNameOrDescriptionOrScore(String term, int score);
}
