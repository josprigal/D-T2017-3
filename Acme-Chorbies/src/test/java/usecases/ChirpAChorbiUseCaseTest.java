
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
import services.ChirpService;
import services.ChorbiService;
import utilities.AbstractTest;
import domain.Chirp;
import domain.Chorbi;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChirpAChorbiUseCaseTest extends AbstractTest {

	@Autowired
	ActorService	actorService;

	@Autowired
	ChorbiService	chorbiService;

	@Autowired
	ChirpService	chirpService;


	/*
	 * - An actor who is authenticated as a chorbi must be able to:
	 * 
	 * Chirp to another chorbi.
	 * 
	 * Erase any of the chirps that he or she's got or sent, which requires previous confirmation.
	 */

	@Test
	public void testChirp() {
		//Dar un chirp a otro chorbi como chorbi y luego lo borramos
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
		final int size1 = this.chirpService.findAll().size();
		Chirp chirp = new Chirp(); //Aqui creamos un chirp
		chirp.setAttachments("http://www.imgur.com");
		chirp.setText("Random");
		chirp.setSubject("subject random");
		chirp.setSent(new Date(System.currentTimeMillis() - 1));
		chirp.setRecipent(c);
		chirp.setSender(chorbi);
		chirp = this.chirpService.save(chirp);
		final int size2 = this.chirpService.findAll().size();

		Assert.isTrue(size2 > size1); //¿Se ha creado?

		Assert.isTrue(chirp.getSender().getId() == chorbi.getId() || chirp.getRecipent().getId() == chorbi.getId()); //Comprobamos que o el sender o el recipient sea el user logueado

		this.chirpService.delete(chirp);
		final int size3 = this.chirpService.findAll().size();
		Assert.isTrue(size2 > size3); //¿Se ha borrado?
		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		///Dar un chirp a otro chorbi sin estar autentificado 
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
		final int size1 = this.chirpService.findAll().size();
		Chirp chirp = new Chirp(); //Aqui creamos un chirp

		chirp.setAttachments("http://www.imgur.com");
		chirp.setText("Random");
		chirp.setSubject("subject random");
		chirp.setSent(new Date(System.currentTimeMillis() - 1));
		chirp.setRecipent(c);
		chirp.setSender(chorbi);
		chirp = this.chirpService.save(chirp);
		final int size2 = this.chirpService.findAll().size();

		Assert.isTrue(size2 > size1); //¿Se ha creado?

		Assert.isTrue(chirp.getSender().getId() == chorbi.getId() || chirp.getRecipent().getId() == chorbi.getId()); //Comprobamos que o el sender o el recipient sea el user logueado

		this.chirpService.delete(chirp);
		final int size3 = this.chirpService.findAll().size();
		Assert.isTrue(size2 > size3); //¿Se ha borrado?
		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAutoChirp() {
		//Intentar darse autochirp
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
		final int size1 = this.chirpService.findAll().size();
		Chirp chirp = new Chirp(); //Aqui creamos un chirp

		chirp.setAttachments("http://www.imgur.com");
		chirp.setText("Random");
		chirp.setSubject("subject random");
		chirp.setSent(new Date(System.currentTimeMillis() - 1));
		chirp.setRecipent(c);
		chirp.setSender(chorbi);
		chirp = this.chirpService.save(chirp);
		final int size2 = this.chirpService.findAll().size();

		Assert.isTrue(size2 > size1); //¿Se ha creado?

		Assert.isTrue(chirp.getSender().getId() == chorbi.getId() || chirp.getRecipent().getId() == chorbi.getId()); //Comprobamos que o el sender o el recipient sea el user logueado

		this.chirpService.delete(chirp);
		final int size3 = this.chirpService.findAll().size();
		Assert.isTrue(size2 > size3); //¿Se ha borrado?
		super.unauthenticate();
	}

}
