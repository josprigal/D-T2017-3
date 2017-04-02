package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LikesRepository;
import domain.Likes;

@Service
@Transactional
public class LikesService {

	@Autowired
	LikesRepository likesRepository;

	public LikesService() {
		super();
	}

	public Collection<Likes> findAll() {
		Collection<Likes> result;
		result = this.likesRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Likes findOne(final int id) {
		Assert.isTrue(id != 0);
		Likes result;
		result = this.likesRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final Likes likes) {
		Assert.notNull(this.likesRepository);
		this.likesRepository.save(likes);
	}

	public void delete(final Likes likes) {
		Assert.notNull(likes);
		Assert.isTrue(likes.getId() != 0);
		Assert.isTrue(this.likesRepository.exists(likes.getId()));
		this.likesRepository.delete(likes);
	}

}
