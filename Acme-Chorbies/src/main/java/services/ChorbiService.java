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
	ChorbiRepository chorbiRepository;

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
		Assert.notNull(chorbi);
		this.chorbiRepository.save(chorbi);
	}

}
