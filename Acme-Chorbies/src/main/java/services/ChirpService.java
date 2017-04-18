
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;

@Service
@Transactional
public class ChirpService {

	@Autowired
	ChirpRepository	chirpRepository;


	public ChirpService() {
		super();
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> result;
		result = this.chirpRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Chirp findOne(final int id) {
		Assert.isTrue(id != 0);
		Chirp result;
		result = this.chirpRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Chirp save(final Chirp chirp) {
		Assert.notNull(this.chirpRepository);
		return this.chirpRepository.save(chirp);
	}

	public void delete(final Chirp chirp) {
		Assert.notNull(chirp);
		Assert.isTrue(chirp.getId() != 0);
		Assert.isTrue(this.chirpRepository.exists(chirp.getId()));
		this.chirpRepository.delete(chirp);
	}

}
