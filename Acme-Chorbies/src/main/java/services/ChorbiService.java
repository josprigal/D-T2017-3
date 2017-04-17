
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiRepository;
import security.Authority;
import domain.Actor;
import domain.Chorbi;
import domain.Coordinates;
import domain.SearchTemplate;

@Service
@Transactional
public class ChorbiService {

	@Autowired
	ChorbiRepository		chorbiRepository;

	@Autowired
	ActorService			actorService;

	@Autowired
	SearchTemplateService	searchTemplateService;


	public ChorbiService() {
		super();
	}

	public Collection<Chorbi> findAll() {
		Collection<Chorbi> result;
		result = this.chorbiRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Chorbi findOne(final int id) {
		Assert.isTrue(id != 0);
		Chorbi result;
		result = this.chorbiRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Chorbi save(final Chorbi chorbi) {
		Assert.notNull(this.chorbiRepository);
		return this.chorbiRepository.save(chorbi);
	}

	public void delete(final Chorbi chorbi) {
		Assert.notNull(chorbi);
		Assert.isTrue(chorbi.getId() != 0);
		Assert.isTrue(this.chorbiRepository.exists(chorbi.getId()));
		this.chorbiRepository.delete(chorbi);
	}

	public void create(final Chorbi chorbi) {
		Assert.notNull(chorbi);
		final List<Authority> authorities = new ArrayList<Authority>();
		final Authority a = new Authority();
		a.setAuthority("CHORBI");
		authorities.add(a);
		chorbi.getUserAccount().setAuthorities(authorities);
		chorbi.setBanned(false);
		SearchTemplate searchTemplate = new SearchTemplate();
		searchTemplate = this.searchTemplateService.save(searchTemplate);
		chorbi.setSearchTemplate(searchTemplate);
		Assert.notNull(chorbi);
		this.chorbiRepository.save(chorbi);
	}

	public Integer minimunAgeChorbies() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.minimunAgeChorbies();
	}

	public Integer maximunAgeChorbies() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.maximunAgeChorbies();
	}

	public Double avgAgeChorbies() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.avgAgeChorbies();
	}

	//	public Double ratioNotCreditCard() {
	//		// TODO Auto-generated method stub
	//		return this.chorbiRepository.ratioNotCreditCard();
	//	}
	//
	//	public Double ratioLove() {
	//		// TODO Auto-generated method stub
	//		return this.chorbiRepository.ratioLove();
	//	}
	//
	//	public Double ratioFriendship() {
	//		// TODO Auto-generated method stub
	//		return this.chorbiRepository.ratioFriendship();
	//	}
	//
	//	public Double ratioActivities() {
	//		// TODO Auto-generated method stub
	//		return this.chorbiRepository.ratioActivities();
	//	}

	public Integer minLikesChorbi() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.minLikesChorbi();
	}

	public Integer maxLikesChorbi() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.maxLikesChorbi();
	}

	public Double avgLikesChorbi() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.avgLikesChorbi();
	}

	public Integer minChirpsChorbiSent() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.minChirpsChorbiSent();
	}

	public Integer maxChirpsChorbiSent() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.maxChirpsChorbiSent();
	}

	public Double avgChirpsChorbiSent() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.avgChirpsChorbiSent();
	}

	public Integer minChirpsChorbiReceived() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.minChirpsChorbiReceived();
	}

	public Double avgChirpsChorbiReceived() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.avgChirpsChorbiReceived();
	}

	public Integer maxChirpsChorbiReceived() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.maxChirpsChorbiReceived();
	}

	public Chorbi reconstruct(final Chorbi chorbi) {
		final Actor a = this.actorService.findActorByPrincipal();
		Assert.notNull(a);
		Assert.isTrue(a instanceof Chorbi);
		final Chorbi chor = (Chorbi) a;
		chor.setAge(chorbi.getAge());
		chor.setBirth(chorbi.getBirth());
		chor.setDescription(chorbi.getDescription());
		Coordinates coordinates = chor.getCoordinates();
		if (coordinates == null)
			coordinates = chorbi.getCoordinates();
		else {
			coordinates.setProvince(chorbi.getCoordinates().getProvince());
			coordinates.setState(chorbi.getCoordinates().getState());
			coordinates.setCity(chorbi.getCoordinates().getCity());
			coordinates.setCountry(chorbi.getCoordinates().getCountry());
		}
		chor.setCoordinates(coordinates);
		chor.setRelationship(chorbi.getRelationship());
		chor.setGender(chorbi.getGender());
		chor.setEmail(chorbi.getEmail());
		chor.setName(chorbi.getName());
		chor.setPhone(chorbi.getPhone());
		chor.setPicture(chorbi.getPicture());

		return this.save(chor);
	}

	public Chorbi findByPrincipal() {
		final Actor result = this.actorService.findActorByPrincipal();
		Assert.isTrue(result instanceof Chorbi);
		final Chorbi result2 = (Chorbi) result;
		Assert.notNull(result2);

		return result2;
	}

	public Map<String, Integer> numberOfChorbiesPerCity() {
		final Map<String, Integer> res = new HashMap<String, Integer>();
		for (final Chorbi c : this.findAll())
			if (res.get(c.getCoordinates().getCity()) == null)
				res.put(c.getCoordinates().getCity(), 1);
			else
				res.put(c.getCoordinates().getCity(), res.get(c.getCoordinates().getCity()) + 1);
		return res;
	}

	public Map<String, Integer> numberOfChorbiesPerCountry() {
		final Map<String, Integer> res = new HashMap<String, Integer>();
		for (final Chorbi c : this.findAll())
			if (res.get(c.getCoordinates().getCountry()) == null)
				res.put(c.getCoordinates().getCountry(), 1);
			else
				res.put(c.getCoordinates().getCountry(), res.get(c.getCoordinates().getCountry()) + 1);
		return res;
	}
	public Collection<Chorbi> listChorbiesNumberOfLikes() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.listChorbiesNumberOfLikes();
	}

	public Collection<Chorbi> listChorbiesMoreChirpsReceived() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.listChorbiesMoreChirpsReceived();
	}

	public Collection<Chorbi> listChorbiesMoreChirpsSent() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.listChorbiesMoreChirpsSent();
	}
	public Double ratioNotCreditCard() {
		Double res = 0.;
		if (!this.findAll().isEmpty())
			res = (double) this.chorbiRepository.chorbiNotCreditCard().size() / this.findAll().size();
		return res;
	}

	public Double ratioLove() {
		Double res = 0.;
		if (!this.findAll().isEmpty())
			res = (double) this.chorbiRepository.chorbiLove().size() / this.findAll().size();
		return res;
	}
	public Double ratioFriendship() {
		Double res = 0.;
		if (!this.findAll().isEmpty())
			res = (double) this.chorbiRepository.chorbiFriendship().size() / this.findAll().size();
		return res;
	}
	public Double ratioActivities() {
		Double res = 0.;
		if (!this.findAll().isEmpty())
			res = (double) this.chorbiRepository.chorbiActivities().size() / this.findAll().size();
		return res;
	}

}
