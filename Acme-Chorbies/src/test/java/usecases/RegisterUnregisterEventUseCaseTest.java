
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
import services.ChorbiService;
import services.EventService;
import utilities.AbstractTest;
import domain.Event;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegisterUnregisterEventUseCaseTest extends AbstractTest {

	@Autowired
	ActorService	actorService;

	@Autowired
	ChorbiService	chorbiService;

	@Autowired
	EventService	eventService;

	@Autowired
	ChirpService	chirpService;


	/*
	 * An actor who is authenticated as a chorbi must be able to:
	 * Register to an event as long as there are enough seats available.
	 * Un-register from an event to which he or she's registered.
	 */

	@Test
	public void testRegisterUnregister() {
		//Registrarte a un evento autenticado como chorbie
		super.authenticate("chorbi");
		Event e = null;
		for (final Event ev : this.eventService.findAll()) {
			e = ev;
			break;
		}
		if (!e.getChorbies().contains(this.chorbiService.findByPrincipal())) {
			this.eventService.register(e);
			Assert.isTrue(e.getChorbies().contains(this.chorbiService.findByPrincipal()));
		}
		this.eventService.unregister(e);
		Assert.isTrue(!e.getChorbies().contains(this.chorbiService.findByPrincipal()));
		super.unauthenticate();
	}
	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		super.authenticate(null);
		Event e = null;
		for (final Event ev : this.eventService.findAll()) {
			e = ev;
			break;
		}
		if (e.getChorbies().contains(this.chorbiService.findByPrincipal())) {
			this.eventService.register(e);
			Assert.isTrue(e.getChorbies().contains(this.chorbiService.findByPrincipal()));
		}
		this.eventService.unregister(e);
		Assert.isTrue(!e.getChorbies().contains(this.chorbiService.findByPrincipal()));
		super.unauthenticate();
	}

}
