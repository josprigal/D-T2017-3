
package usecases;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.ChorbiService;
import services.LikesService;
import utilities.AbstractTest;
import domain.Chorbi;
import domain.Likes;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class LikeAChorbiUseCaseTest extends AbstractTest {

	@Autowired
	ActorService	actorService;

	@Autowired
	ChorbiService	chorbiService;

	@Autowired
	LikesService	likeService;


	/*
	 * An actor who is authenticated as a chorbi must be able to:
	 * 
	 * Like another chorbi; a like may be cancelled at any time
	 */

	@Test
	public void testLike() {
		//Dar un like a otro chorbi como chorbi
		super.authenticate("chorbi");
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() != chorbi.getId()) {
				c = ch;
				break;
			}
		Assert.isTrue(c.getId() != chorbi.getId());
		final int size1 = this.likeService.findAll().size();
		final Likes like = new Likes();
		like.setComment("Like pa ti");
		like.setMoment(new Date(System.currentTimeMillis() - 1));
		like.setRecipent(c);
		like.setSender(chorbi);
		this.likeService.save(like);
		final int size2 = this.likeService.findAll().size();
		Assert.isTrue(size2 > size1);
		super.unauthenticate();
	}
	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		//Dar un like a otro chorbi sin login
		super.authenticate(null);
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() != chorbi.getId()) {
				c = ch;
				break;
			}
		Assert.isTrue(c.getId() != chorbi.getId());
		final int size1 = this.likeService.findAll().size();
		final Likes like = new Likes();
		like.setComment("Like pa ti");
		like.setMoment(new Date(System.currentTimeMillis() - 1));
		like.setRecipent(c);
		like.setSender(chorbi);
		this.likeService.save(like);
		final int size2 = this.likeService.findAll().size();
		Assert.isTrue(size2 > size1);
		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAutoLike() {
		//Intentar darse autolike
		super.authenticate("chorbi");
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == chorbi.getId()) {
				c = ch;
				break;
			}
		Assert.isTrue(c.getId() != chorbi.getId());
		final int size1 = this.likeService.findAll().size();
		final Likes like = new Likes();
		like.setComment("Like pa ti");
		like.setMoment(new Date(System.currentTimeMillis() - 1));
		like.setRecipent(c);
		like.setSender(chorbi);
		this.likeService.save(like);
		final int size2 = this.likeService.findAll().size();
		Assert.isTrue(size2 > size1);
		super.unauthenticate();
	}

}
