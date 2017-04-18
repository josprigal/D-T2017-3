package services;

import java.util.*;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SearchTemplateRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class SearchTemplateService {

	@Autowired
	SearchTemplateRepository searchTemplateRepository;


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


	public Collection<Chorbi> search(){
		Chorbi chorbi = chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
        SearchTemplate searchTemplate = chorbi.getSearchTemplate();
        List<Chorbi> result = new ArrayList<>();
		List<Chorbi> cachedList = checkIfCached(chorbi.getSearchTemplate());
		if(cachedList==null) {
		    Integer min = null;
		    Integer max = null;
            if (searchTemplate.getAge() != null) {
                min = searchTemplate.getAge()-5;
                max = searchTemplate.getAge()+5;
            }
            result.addAll(searchTemplateRepository.search(min,max,searchTemplate.getGender(),searchTemplate.getRelationship()
            ,searchTemplate.getCoordinates()));

            Results results = (searchTemplate.getResults()==null)?  new Results() : searchTemplate.getResults();
            results.setChorbis(result);
            results.setSearchTemplate(searchTemplate);
            results.setMoment(new Date());
            resultsService.save(results);
            return result;
        }else{
		    return cachedList;
        }
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
        if(dateCache.before(nowDate)){
            System.out.println("before");
            return null;
        }else{
            System.out.println("after");
            return new ArrayList<>(results.getChorbis());
        }
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
