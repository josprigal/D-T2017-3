package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CoordinatesRepository;
import domain.Coordinates;

@Service
@Transactional
public class CoordinatesService {

	@Autowired
	CoordinatesRepository coordinatesRepository;

	public CoordinatesService() {
		super();
	}

	public Collection<Coordinates> findAll() {
		Collection<Coordinates> result;
		result = this.coordinatesRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Coordinates findOne(final int id) {
		Assert.isTrue(id != 0);
		Coordinates result;
		result = this.coordinatesRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Coordinates coordinates) {
		Assert.notNull(this.coordinatesRepository);
		this.coordinatesRepository.save(coordinates);
	}

	public void delete(final Coordinates coordinates) {
		Assert.notNull(coordinates);
		Assert.isTrue(coordinates.getId() != 0);
		Assert.isTrue(this.coordinatesRepository.exists(coordinates.getId()));
		this.coordinatesRepository.delete(coordinates);
	}

}
