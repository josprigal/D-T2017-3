
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiRepository;
import security.Authority;
import utilities.MapUtil;
import domain.Actor;
import domain.Chirp;
import domain.Chorbi;
import domain.Coordinates;
import domain.Likes;
import domain.SearchTemplate;

@Service
@Transactional
public class ChorbiService {

	@Autowired
	ChorbiRepository		chorbiRepository;

	@Autowired
	ChirpService			chirpService;

	@Autowired
	LikesService			likesService;

	@Autowired
	ActorService			actorService;

	@Autowired
	SearchTemplateService	searchTemplateService;

	@Autowired
	CoordinatesService		coordinatesService;


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
		Coordinates coordinates = new Coordinates();
		if (chorbi.getCoordinates() != null)
			coordinates = chorbi.getCoordinates();
		coordinates = this.coordinatesService.save(coordinates);
		chorbi.setCoordinates(coordinates);
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

	public Integer minLikesChorbi() {
		// TODO Auto-generated method stub
		Integer result = Integer.MAX_VALUE;
		for (final Chorbi c : this.findAll())
			if (this.receivedLikes(c).size() < result)
				result = this.receivedLikes(c).size();
		return result;
	}

	public Integer maxLikesChorbi() {
		// TODO Auto-generated method stub
		Integer result = 0;
		for (final Chorbi c : this.findAll())
			if (this.receivedLikes(c).size() > result)
				result = this.receivedLikes(c).size();
		return result;
	}

	public Double avgLikesChorbi() {
		// TODO Auto-generated method stub
		Double number = 0.;
		for (final Chorbi c : this.findAll())
			number += this.receivedLikes(c).size();
		return number / this.findAll().size();
	}

	public Integer minChirpsChorbiSent() {
		// TODO Auto-generated method stub
		Integer result = Integer.MAX_VALUE;
		for (final Chorbi c : this.findAll())
			if (this.sentChirps(c).size() < result)
				result = this.sentChirps(c).size();
		return result;
	}

	public Integer maxChirpsChorbiSent() {
		// TODO Auto-generated method stub
		Integer result = 0;
		for (final Chorbi c : this.findAll())
			if (this.sentChirps(c).size() > result)
				result = this.sentLikes(c).size();
		return result;
	}

	public Double avgChirpsChorbiSent() {
		// TODO Auto-generated method stub
		Double number = 0.;
		for (final Chorbi c : this.findAll())
			number += this.sentChirps(c).size();
		return number / this.findAll().size();
	}

	public Integer minChirpsChorbiReceived() {
		// TODO Auto-generated method stub
		Integer result = Integer.MAX_VALUE;
		for (final Chorbi c : this.findAll())
			if (this.receivedChirps(c).size() < result)
				result = this.receivedChirps(c).size();
		return result;
	}

	public Double avgChirpsChorbiReceived() {
		// TODO Auto-generated method stub
		Double number = 0.;
		for (final Chorbi c : this.findAll())
			number += this.receivedChirps(c).size();
		return number / this.findAll().size();

	}

	public Integer maxChirpsChorbiReceived() {
		// TODO Auto-generated method stub
		Integer result = 0;
		for (final Chorbi c : this.findAll())
			if (this.receivedChirps(c).size() > result)
				result = this.receivedLikes(c).size();
		return result;
	}

	public Chorbi reconstruct(final Chorbi chorbi) {
		final Actor a = this.actorService.findActorByPrincipal();
		Assert.notNull(a);
		Assert.isTrue(a instanceof Chorbi);
		final Chorbi chor = (Chorbi) a;
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
		final List<Chorbi> chorbies = (List<Chorbi>) this.findAll();
		Collections.sort(chorbies, new Comparator<Chorbi>() {

			@Override
			public int compare(final Chorbi lhs, final Chorbi rhs) {
				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				return ChorbiService.this.receivedLikes(lhs).size() > ChorbiService.this.receivedLikes(rhs).size() ? -1 : (ChorbiService.this.receivedLikes(lhs).size() > ChorbiService.this.receivedLikes(rhs).size()) ? 1 : 0;
			}
		});
		return chorbies;
	}

	public Collection<Chorbi> listChorbiesMoreChirpsReceived() {
		// TODO Auto-generated method stub
		final List<Chorbi> chorbies = (List<Chorbi>) this.findAll();
		Collections.sort(chorbies, new Comparator<Chorbi>() {

			@Override
			public int compare(final Chorbi lhs, final Chorbi rhs) {
				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				return ChorbiService.this.receivedChirps(lhs).size() > ChorbiService.this.receivedChirps(rhs).size() ? -1 : (ChorbiService.this.receivedChirps(lhs).size() > ChorbiService.this.receivedChirps(rhs).size()) ? 1 : 0;
			}
		});
		return chorbies;
	}

	public Collection<Chorbi> listChorbiesMoreChirpsSent() {
		// TODO Auto-generated method stub
		final List<Chorbi> chorbies = (List<Chorbi>) this.findAll();
		Collections.sort(chorbies, new Comparator<Chorbi>() {

			@Override
			public int compare(final Chorbi lhs, final Chorbi rhs) {
				// -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
				return ChorbiService.this.sentChirps(lhs).size() > ChorbiService.this.sentChirps(rhs).size() ? -1 : (ChorbiService.this.sentChirps(lhs).size() > ChorbiService.this.sentChirps(rhs).size()) ? 1 : 0;
			}
		});
		return chorbies;
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

	public Collection<Chirp> sentChirps(final Chorbi principal) {
		final Collection<Chirp> all = this.chirpService.findAll();
		final Collection<Chirp> sentChirps = new ArrayList<Chirp>();
		for (final Chirp c : all)
			if (c.getSender().getId() == principal.getId())
				sentChirps.add(c);
		return sentChirps;
	}
	public Collection<Chirp> receivedChirps(final Chorbi principal) {
		final Collection<Chirp> all = this.chirpService.findAll();
		final Collection<Chirp> receivedChirps = new ArrayList<Chirp>();
		for (final Chirp c : all)
			if (c.getRecipent().getId() == principal.getId())
				receivedChirps.add(c);
		return receivedChirps;
	}
	public Collection<Likes> sentLikes(final Chorbi principal) {
		final Collection<Likes> all = this.likesService.findAll();
		final Collection<Likes> sentLikes = new ArrayList<Likes>();
		for (final Likes c : all)
			if (c.getRecipent().getId() == principal.getId())
				sentLikes.add(c);
		return sentLikes;
	}
	public Collection<Likes> receivedLikes(final Chorbi principal) {
		final Collection<Likes> all = this.likesService.findAll();
		final Collection<Likes> receivedLikes = new ArrayList<Likes>();
		for (final Likes c : all)
			if (c.getRecipent().getId() == principal.getId())
				receivedLikes.add(c);
		return receivedLikes;
	}

	public Collection<Chorbi> listChorbiesMoreEventsRegistered() {
		return this.chorbiRepository.listChorbiesMoreEventsRegistered();
	}

	public Collection<Chorbi> listChorbiesFees() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Chorbi> listChorbiesAvgStars() {
		final Map<Chorbi, Double> mapa = new HashMap<Chorbi, Double>();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		chorbies = this.findAll();
		Collection<Likes> likes = new ArrayList<Likes>();
		likes = this.likesService.findAll();
		for (final Chorbi ch : chorbies) {
			double stars = 0;
			int numberLikes = 0;
			for (final Likes li : likes)
				if (li.getRecipent().getId() == (ch.getId())) {
					numberLikes++;
					stars += li.getStars();
				}
			mapa.put(ch, (stars / numberLikes));
		}
		return MapUtil.sortByValue(mapa).keySet();
	}
	public Double avgStarsChorbi() {
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		chorbies = this.findAll();
		Collection<Likes> likes = new ArrayList<Likes>();
		likes = this.likesService.findAll();
		double stars = 0;
		int numberLikes = 0;
		for (final Chorbi ch : chorbies)
			for (final Likes li : likes)
				if (li.getRecipent().getId() == (ch.getId())) {
					numberLikes++;
					stars += li.getStars();
				}
		return (stars / numberLikes);
	}

	public Double maxStarsChorbi() {
		final Map<Chorbi, Double> mapa = new HashMap<Chorbi, Double>();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		chorbies = this.findAll();
		Collection<Likes> likes = new ArrayList<Likes>();
		likes = this.likesService.findAll();
		Double maxAvgStars = 0.;
		for (final Chorbi ch : chorbies) {
			double stars = 0;
			int numberLikes = 0;
			for (final Likes li : likes)
				if (li.getRecipent().getId() == (ch.getId())) {
					numberLikes++;
					stars += li.getStars();
				}
			if (maxAvgStars < (stars / numberLikes))
				maxAvgStars = (stars / numberLikes);
		}
		return maxAvgStars;
	}

	public Double minStarsChorbi() {
		final Map<Chorbi, Double> mapa = new HashMap<Chorbi, Double>();
		Collection<Chorbi> chorbies = new ArrayList<Chorbi>();
		chorbies = this.findAll();
		Collection<Likes> likes = new ArrayList<Likes>();
		likes = this.likesService.findAll();
		Double minAvgStars = 4.;
		for (final Chorbi ch : chorbies) {
			double stars = 0;
			int numberLikes = 0;
			for (final Likes li : likes)
				if (li.getRecipent().getId() == (ch.getId())) {
					numberLikes++;
					stars += li.getStars();
				}
			if (minAvgStars > (stars / numberLikes))
				minAvgStars = (stars / numberLikes);
		}
		return minAvgStars;
	}
}
