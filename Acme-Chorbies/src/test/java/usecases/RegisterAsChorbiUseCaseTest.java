
package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.ChorbiService;
import utilities.AbstractTest;
import domain.Actor;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegisterAsChorbiUseCaseTest extends AbstractTest {

	@Autowired
	ActorService	actorService;

	@Autowired
	ChorbiService	chorbiService;


	/*
	 * - A user who is not authenticated must be able to:
	 * 
	 * Register to the system as a chorbi. As of the time of registering, a chorbi is not required
	 * to provide a credit card. No person under 18 is allowed to register to the system.
	 */

	@Test
	public void testRegister() {
		//Registrarse como un chorbi sin estar autentificado
		super.authenticate(null);
		final Actor a = this.actorService.findActorByPrincipal();
		Assert.isNull(a);

		this.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Intentar postear un offer sin estar autenticado
		this.authenticate(null);

		this.unauthenticate();
	}

}
