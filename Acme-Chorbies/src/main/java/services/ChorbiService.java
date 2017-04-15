
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChorbiRepository;
import security.Authority;
import domain.Chorbi;

@Service
@Transactional
public class ChorbiService {

	@Autowired
	ChorbiRepository	chorbiRepository;


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

	public void save(final Chorbi chorbi) {
		Assert.notNull(this.chorbiRepository);
		this.chorbiRepository.save(chorbi);
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

	public Double ratioNotCreditCard() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.ratioNotCreditCard();
	}

	public Double ratioLove() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.ratioLove();
	}

	public Double ratioFriendship() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.ratioFriendship();
	}

	public Double ratioActivities() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.ratioActivities();
	}

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

	public Integer numberOfChorbiesPerCity() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.numberOfChorbiesPerCity();
	}

	public Integer numberOfChorbiesPerCountry() {
		// TODO Auto-generated method stub
		return this.chorbiRepository.numberOfChorbiesPerCountry();
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

}
