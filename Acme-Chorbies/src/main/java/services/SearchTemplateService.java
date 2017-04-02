package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;
import domain.SearchTemplate;

@Service
@Transactional
public class SearchTemplateService {

	@Autowired
	SearchTemplateRepository searchTemplateRepository;

	public SearchTemplateService() {
		super();
	}

	public Collection<SearchTemplate> findAll() {
		Collection<SearchTemplate> result;
		result = this.searchTemplateRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public SearchTemplate findOne(final int id) {
		Assert.isTrue(id != 0);
		SearchTemplate result;
		result = this.searchTemplateRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final SearchTemplate searchTemplate) {
		Assert.notNull(this.searchTemplateRepository);
		this.searchTemplateRepository.save(searchTemplate);
	}

	public void delete(final SearchTemplate searchTemplate) {
		Assert.notNull(searchTemplate);
		Assert.isTrue(searchTemplate.getId() != 0);
		Assert.isTrue(this.searchTemplateRepository.exists(searchTemplate
				.getId()));
		this.searchTemplateRepository.delete(searchTemplate);
	}

}
