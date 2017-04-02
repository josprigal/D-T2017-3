package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	@Autowired
	AdministratorRepository administratorRepository;

	public AdministratorService() {
		super();
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> result;
		result = this.administratorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Administrator findOne(final int id) {
		Assert.isTrue(id != 0);
		Administrator result;
		result = this.administratorRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Administrator administrator) {
		Assert.notNull(this.administratorRepository);
		this.administratorRepository.save(administrator);
	}

	public void delete(final Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(this.administratorRepository.exists(administrator.getId()));
		this.administratorRepository.delete(administrator);
	}

}
