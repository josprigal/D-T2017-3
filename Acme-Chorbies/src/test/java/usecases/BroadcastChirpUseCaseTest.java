
package usecases;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.ChirpService;
import services.ChorbiService;
import services.EventService;
import services.ManagerService;
import utilities.AbstractTest;
import domain.Chirp;
import domain.Chorbi;
import domain.Manager;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BroadcastChirpUseCaseTest extends AbstractTest {

	@Autowired
	ActorService	actorService;

	@Autowired
	ChorbiService	chorbiService;

	@Autowired
	ManagerService	managerService;

	@Autowired
	EventService	eventService;

	@Autowired
	ChirpService	chirpService;


	/*
	 * An actor who is authenticated as a manager must be able to:
	 * Broadcast a chirp to the chorbies who have registered to any of the events that he
	 * or she manages.
	 */

	@Test
	public void testBroadcast() {
		//Broadcast chirp como manager
		super.authenticate("manager");

		final Manager manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager != null);
		final List<Chorbi> chorbis = this.eventService.findAllChorbiesRelatedToManager();
		Chirp c = null;
		for (final Chirp ch : this.chirpService.findAll()) {
			c = ch;
			break;
		}
		final int size = this.chirpService.findAll().size();
		this.chirpService.broadcast(c);
		final int size2 = this.chirpService.findAll().size();

		Assert.isTrue(size2 == (size + chorbis.size()));
		super.unauthenticate();
	}
	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		super.authenticate(null);

		final Manager manager = this.managerService.findByPrincipal();
		Assert.isTrue(manager != null);
		final List<Chorbi> chorbis = this.eventService.findAllChorbiesRelatedToManager();
		Chirp c = null;
		for (final Chirp ch : this.chirpService.findAll()) {
			c = ch;
			break;
		}
		final int size = this.chirpService.findAll().size();
		this.chirpService.broadcast(c);
		final int size2 = this.chirpService.findAll().size();

		Assert.isTrue(size2 == (size + chorbis.size()));
		super.unauthenticate();
	}

}
