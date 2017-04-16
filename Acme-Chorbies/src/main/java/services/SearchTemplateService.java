package services;

import java.util.*;

import domain.*;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;

@Service
@Transactional
public class SearchTemplateService {

	@Autowired
	SearchTemplateRepository searchTemplateRepository;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private ChorbiService chorbiService;

	@Autowired
    private ResultsService resultsService;

	@Autowired
    private ConfigurationService configurationService;

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
		return searchTemplateRepository.save(searchTemplate);
    }

	public void delete(final SearchTemplate searchTemplate) {
		Assert.notNull(searchTemplate);
		Assert.isTrue(searchTemplate.getId() != 0);
		Assert.isTrue(this.searchTemplateRepository.exists(searchTemplate
				.getId()));
		this.searchTemplateRepository.delete(searchTemplate);
	}


	public List<Chorbi> search(){
		Chorbi chorbi = chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
        SearchTemplate searchTemplate = chorbi.getSearchTemplate();
		List<Chorbi> result = checkIfCached(chorbi.getSearchTemplate());
		if(result==null) {
            Session session = sessionFactory.getCurrentSession();
            Criteria criteria = session.createCriteria(Chorbi.class);
            if (searchTemplate.getAge() != null) {
                Integer minAge = searchTemplate.getAge() - 5;
                Integer maxAge = searchTemplate.getAge() + 5;
                criteria.add(Restrictions.between("age", minAge, maxAge));
            }
            if (searchTemplate.getGender() != null) {
                criteria.add(Restrictions.eq("gender", searchTemplate.getGender()));
            }
            if (searchTemplate.getRelationship() != null) {
                criteria.add(Restrictions.eq("relationship", searchTemplate.getRelationship()));
            }
            if (searchTemplate.getCoordinates() != null) {
                criteria.add(Restrictions.eq("coordinates", searchTemplate.getCoordinates()));
            }
            result = criteria.list();
            Results results = new Results();
            results.setChorbis(result);
            results.setSearchTemplate(searchTemplate);

            resultsService.save(results);

        }
        return result;
	}

    private List<Chorbi> checkIfCached(SearchTemplate searchTemplate) {
        Results results = resultsService.findResultsBySearchTemplate(searchTemplate);
        if(results==null){
            return null;
        }
        Configuration configuration = new ArrayList<>(configurationService.findAll()).get(0);
        Integer seconds = configuration.getSeconds();
        Integer hours = configuration.getHours();
        Integer minutes = configuration.getMinutes();
        Date nowDate = new Date();
        Calendar calcached = Calendar.getInstance();
        calcached.setTime(results.getMoment());
        calcached.add(Calendar.MINUTE,minutes);
        calcached.add(Calendar.HOUR,hours);
        calcached.add(Calendar.SECOND, seconds);
        Date dateCache = calcached.getTime();
        if(dateCache.after(nowDate)){
            return null;
        }else{
            return new ArrayList<>(results.getChorbis());
        }
    }

    public SearchTemplate createIfNotExists(Chorbi chorbi) {
	    SearchTemplate searchTemplate = chorbi.getSearchTemplate();
	    if(searchTemplate==null){
	        searchTemplate = new SearchTemplate();
            searchTemplate = save(searchTemplate);
            chorbi.setSearchTemplate(searchTemplate);
            chorbiService.save(chorbi);
        }

        return searchTemplate;
    }

    public void reconstruct(SearchTemplate searchTemplate) {
        Chorbi chorbi = chorbiService.findByPrincipal();
        Assert.notNull(chorbi);
        Assert.notNull(searchTemplate);
        SearchTemplate chsearchTemplate = chorbi.getSearchTemplate();
        chsearchTemplate.setAge(searchTemplate.getAge());
        Coordinates coordinates = chsearchTemplate.getCoordinates();
        if(coordinates==null){
            coordinates =  searchTemplate.getCoordinates();
        }
        coordinates.setCity(searchTemplate.getCoordinates().getCity());
        coordinates.setCountry(searchTemplate.getCoordinates().getCountry());
        coordinates.setState(searchTemplate.getCoordinates().getState());
        coordinates.setProvince(searchTemplate.getCoordinates().getProvince());
        chsearchTemplate.setCoordinates(coordinates);
        chsearchTemplate.setGender(searchTemplate.getGender());
        chsearchTemplate.setKeyword(searchTemplate.getKeyword());
        chsearchTemplate.setRelationship(searchTemplate.getRelationship());

        save(chsearchTemplate);
    }
}
