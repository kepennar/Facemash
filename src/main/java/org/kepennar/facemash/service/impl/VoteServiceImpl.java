package org.kepennar.facemash.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.kepennar.facemash.model.Element;
import org.kepennar.facemash.repository.ElementRepository;
import org.kepennar.facemash.service.VoteService;
import org.kepennar.facemash.solr.repository.SolrElementRepository;
import org.kepennar.facemash.util.CacheUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("voteService")
@Transactional(readOnly= true)
public class VoteServiceImpl implements VoteService {


	@Inject @Named("cacheUtil")
	private CacheUtil cacheUtil;
	
	@Inject @Named("elementRepository")
	private ElementRepository elementRepository;

    @Inject @Named("solrElementRepository")
    private SolrElementRepository solrElementRepository;
	
	@Override
	@Transactional
	public void vote(String winnerId, String loserId) {
		
		Element winner = elementRepository.findOne(winnerId);
		Element loser = elementRepository.findOne(loserId);
		
		int winnerScore = winner.getScore();
		int loserScore = loser.getScore();
		
		winner.play();
		loser.play();
		
		winner.setScore(++winnerScore);
		loser.setScore(--loserScore);
		
		elementRepository.save(winner);
		solrElementRepository.save(winner);
		elementRepository.save(loser);
		
		cacheUtil.play();
	}

	
	

}
