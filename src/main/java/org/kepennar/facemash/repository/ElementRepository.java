package org.kepennar.facemash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.stereotype.Repository;

import org.kepennar.facemash.model.Element;

@Repository
public interface ElementRepository extends MongoRepository<Element, String>, QueryDslPredicateExecutor<Element> {
 
}
