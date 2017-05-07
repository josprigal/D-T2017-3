package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import domain.Chorbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LikesRepository;
import domain.Likes;

import javax.print.attribute.standard.Chromaticity;

@Service
@Transactional
public class LikesService {

	@Autowired
	LikesRepository likesRepository;

	@Autowired
	ChorbiService chorbiService;

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

    public void like(Likes likes) {
		Chorbi principal = chorbiService.findByPrincipal();
		Assert.isTrue(likes.getRecipent().getId() != principal.getId());
		Assert.isNull(likesRepository.findChorbiByLikes(likes.getRecipent(),principal));
		likes.setSender(principal);
		save(likes);
    }

    public void dislike(Chorbi chorbi){
        Chorbi principal = chorbiService.findByPrincipal();
        Assert.isTrue(chorbi.getId() != principal.getId());
        Assert.notNull(likesRepository.findChorbiByLikes(chorbi,principal));
        Likes likes = likesRepository.findLikesByChorbies(chorbi,principal);

        delete(likes);
    }

    public Collection<Chorbi> findChorbiesLikedPrincipal(Chorbi chorbi){
    	Assert.notNull(chorbi);
    	Collection<Chorbi> result = likesRepository.findChorbiesLikedPrincipal(chorbi);
    	Assert.notNull(result);

    	return result;
	}
}
