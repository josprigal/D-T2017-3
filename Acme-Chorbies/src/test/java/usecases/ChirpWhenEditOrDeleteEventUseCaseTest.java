
package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.ChirpService;
import services.EventService;
import services.ManagerService;
import utilities.AbstractTest;
import domain.Event;
import domain.Manager;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChirpWhenEditOrDeleteEventUseCaseTest extends AbstractTest {

	@Autowired
	ActorService	actorService;

	@Autowired
	ManagerService	managerService;

	@Autowired
	EventService	eventService;

	@Autowired
	ChirpService	chirpService;


	/*
	 * Every change that is carried out to an event must be automatically chirped to the chorbies who have registered to that event. The author of the chirp must be the manager who organ-ises the event. Note that an event may be deleted, even if there are
	 * registered chorbies: in such cases, they get chirped and unregistered immediately.
	 */

	@Test
	public void testChirp() {
		//Editar evento para que se envien los chirps
		super.authenticate("manager");
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Event event = null;
		int size = this.chirpService.findAll().size();
		for (final Event ev : manager.getEvents()) {
			event = ev;
			break;
		}
		if (event != null) {
			this.eventService.editEvent(event, event);
			Assert.isTrue(this.chirpService.findAll().size() == (size + event.getChorbies().size()));
			size = this.chirpService.findAll().size();
			final int size2 = (size + event.getChorbies().size());
			this.eventService.deleteChirp(event);
			Assert.isTrue(this.chirpService.findAll().size() == size2);
		}
		super.unauthenticate();
	}
	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		super.authenticate(null);
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Event event = null;
		int size = manager.getChirpsSents().size();
		for (final Event ev : manager.getEvents()) {
			event = ev;
			break;
		}
		if (event != null) {
			this.eventService.editEvent(event, event);
			Assert.isTrue(manager.getChirpsSents().size() == (size + event.getChorbies().size()));
			size = manager.getChirpsSents().size();
			final int size2 = (size + event.getChorbies().size());
			this.eventService.deleteChirp(event);
			Assert.isTrue(manager.getChirpsSents().size() == size2);
		}
		super.unauthenticate();
	}

}
