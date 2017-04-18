
package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;
import domain.Chorbi;
import domain.Configuration;
import domain.Coordinates;
import domain.Results;
import domain.SearchTemplate;

@Service
@Transactional
public class SearchTemplateService {

	@Autowired
	SearchTemplateRepository		searchTemplateRepository;

	@Autowired
	private SessionFactory			sessionFactory;

	@Autowired
	private ChorbiService			chorbiService;

	@Autowired
	private ResultsService			resultsService;

	@Autowired
	private ConfigurationService	configurationService;


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

	public SearchTemplate save(final SearchTemplate searchTemplate) {
		Assert.notNull(this.searchTemplateRepository);
		return this.searchTemplateRepository.save(searchTemplate);
	}

	public void delete(final SearchTemplate searchTemplate) {
		Assert.notNull(searchTemplate);
		Assert.isTrue(searchTemplate.getId() != 0);
		Assert.isTrue(this.searchTemplateRepository.exists(searchTemplate.getId()));
		this.searchTemplateRepository.delete(searchTemplate);
	}

	public List<Chorbi> search() {
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		final SearchTemplate searchTemplate = chorbi.getSearchTemplate();
		List<Chorbi> result = this.checkIfCached(chorbi.getSearchTemplate());
		if (result == null) {
			final Session session = this.sessionFactory.getCurrentSession();
			final Criteria criteria = session.createCriteria(Chorbi.class);
			if (searchTemplate.getAge() != null) {
				final Integer minAge = searchTemplate.getAge() - 5;
				final Integer maxAge = searchTemplate.getAge() + 5;
				criteria.add(Restrictions.between("age", minAge, maxAge));
			}
			if (searchTemplate.getGender() != null)
				criteria.add(Restrictions.eq("gender", searchTemplate.getGender()));
			if (searchTemplate.getRelationship() != null)
				criteria.add(Restrictions.eq("relationship", searchTemplate.getRelationship()));
			if (searchTemplate.getCoordinates() != null)
				criteria.add(Restrictions.eq("coordinates", searchTemplate.getCoordinates()));
			result = criteria.list();
			final Results results = new Results();
			results.setChorbis(result);
			results.setSearchTemplate(searchTemplate);

			this.resultsService.save(results);

		}
		return result;
	}

	public List<Chorbi> search(final Session session) {
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		final SearchTemplate searchTemplate = chorbi.getSearchTemplate();
		List<Chorbi> result = this.checkIfCached(chorbi.getSearchTemplate());
		if (result == null) {
			final Criteria criteria = session.createCriteria(Chorbi.class);
			if (searchTemplate.getAge() != null) {
				final Integer minAge = searchTemplate.getAge() - 5;
				final Integer maxAge = searchTemplate.getAge() + 5;
				criteria.add(Restrictions.between("age", minAge, maxAge));
			}
			if (searchTemplate.getGender() != null)
				criteria.add(Restrictions.eq("gender", searchTemplate.getGender()));
			if (searchTemplate.getRelationship() != null)
				criteria.add(Restrictions.eq("relationship", searchTemplate.getRelationship()));
			if (searchTemplate.getCoordinates() != null)
				criteria.add(Restrictions.eq("coordinates", searchTemplate.getCoordinates()));
			result = criteria.list();
			final Results results = new Results();
			results.setChorbis(result);
			results.setSearchTemplate(searchTemplate);

			this.resultsService.save(results);

		}
		return result;
	}

	private List<Chorbi> checkIfCached(final SearchTemplate searchTemplate) {
		final Results results = this.resultsService.findResultsBySearchTemplate(searchTemplate);
		if (results == null)
			return null;
		final Configuration configuration = new ArrayList<>(this.configurationService.findAll()).get(0);
		final Integer seconds = configuration.getSeconds();
		final Integer hours = configuration.getHours();
		final Integer minutes = configuration.getMinutes();
		final Date nowDate = new Date();
		final Calendar calcached = Calendar.getInstance();
		calcached.setTime(results.getMoment());
		calcached.add(Calendar.MINUTE, minutes);
		calcached.add(Calendar.HOUR, hours);
		calcached.add(Calendar.SECOND, seconds);
		final Date dateCache = calcached.getTime();
		if (dateCache.after(nowDate))
			return null;
		else
			return new ArrayList<>(results.getChorbis());
	}

	public SearchTemplate createIfNotExists(final Chorbi chorbi) {
		SearchTemplate searchTemplate = chorbi.getSearchTemplate();
		if (searchTemplate == null) {
			searchTemplate = new SearchTemplate();
			searchTemplate = this.save(searchTemplate);
			chorbi.setSearchTemplate(searchTemplate);
			this.chorbiService.save(chorbi);
		}

		return searchTemplate;
	}

	public void reconstruct(final SearchTemplate searchTemplate) {
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Assert.notNull(searchTemplate);
		final SearchTemplate chsearchTemplate = chorbi.getSearchTemplate();
		chsearchTemplate.setAge(searchTemplate.getAge());
		Coordinates coordinates = chsearchTemplate.getCoordinates();
		if (coordinates == null)
			coordinates = searchTemplate.getCoordinates();
		coordinates.setCity(searchTemplate.getCoordinates().getCity());
		coordinates.setCountry(searchTemplate.getCoordinates().getCountry());
		coordinates.setState(searchTemplate.getCoordinates().getState());
		coordinates.setProvince(searchTemplate.getCoordinates().getProvince());
		chsearchTemplate.setCoordinates(coordinates);
		chsearchTemplate.setGender(searchTemplate.getGender());
		chsearchTemplate.setKeyword(searchTemplate.getKeyword());
		chsearchTemplate.setRelationship(searchTemplate.getRelationship());

		this.save(chsearchTemplate);
	}
}
