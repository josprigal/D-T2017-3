package services;

import java.util.Collection;

import domain.SearchTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ResultsRepository;
import domain.Results;

@Service
@Transactional
public class ResultsService {

	@Autowired
	ResultsRepository resultsRepository;

	public ResultsService() {
		super();
	}

	public Collection<Results> findAll() {
		Collection<Results> result;
		result = this.resultsRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Results findOne(final int id) {
		Assert.isTrue(id != 0);
		Results result;
		result = this.resultsRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Results results) {
		Assert.notNull(this.resultsRepository);
		this.resultsRepository.save(results);
	}

	public void delete(final Results results) {
		Assert.notNull(results);
		Assert.isTrue(results.getId() != 0);
		Assert.isTrue(this.resultsRepository.exists(results.getId()));
		this.resultsRepository.delete(results);
	}

	public Results findResultsBySearchTemplate(SearchTemplate searchTemplate){
		Assert.notNull(searchTemplate);
		Results results;
		results = resultsRepository.findResultsBySearchTemplate(searchTemplate);

		return results;
	}
}
