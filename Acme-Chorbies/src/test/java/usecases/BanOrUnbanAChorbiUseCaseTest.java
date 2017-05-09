
package usecases;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.AdministratorService;
import services.ChorbiService;
import utilities.AbstractTest;
import domain.Administrator;
import domain.Chorbi;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class BanOrUnbanAChorbiUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	AdministratorService	adminService;


	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * 
	 * Ban a chorbi, that is, to disable his or her account.
	 * Unban a chorbi, which means that his or her account is re-enabled
	 */

	@Test
	public void testBanUnban() {
		//Banear un chorbi y desbanearlo como admin
		super.authenticate("admin");
		final Administrator admin = this.adminService.findByPrincipal();
		Assert.notNull(admin);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll()) {
			c = ch;
			break;
		}

		c.setBanned(true); //BAN
		this.chorbiService.save(c);
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == c.getId())
				c = ch;
		Assert.isTrue(c.isBanned());

		c.setBanned(false); //UNBAN
		this.chorbiService.save(c);
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == c.getId())
				c = ch;
		Assert.isTrue(!c.isBanned());

		super.unauthenticate();
	}

	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		//Banear un chorbi y desbanearlo sin autentificacion
		super.authenticate(null);
		final Administrator admin = this.adminService.findByPrincipal();
		Assert.notNull(admin);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll()) {
			c = ch;
			break;
		}

		c.setBanned(true); //BAN
		this.chorbiService.save(c);
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == c.getId())
				c = ch;
		Assert.isTrue(c.isBanned());

		c.setBanned(false); //UNBAN
		this.chorbiService.save(c);
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == c.getId())
				c = ch;
		Assert.isTrue(!c.isBanned());

		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthority() {

		//Banear un chorbi y desbanearlo como chorbi
		super.authenticate("chorbi");
		final Administrator admin = this.adminService.findByPrincipal();
		Assert.notNull(admin);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll()) {
			c = ch;
			break;
		}

		c.setBanned(true); //BAN
		this.chorbiService.save(c);
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == c.getId())
				c = ch;
		Assert.isTrue(c.isBanned());

		c.setBanned(false); //UNBAN
		this.chorbiService.save(c);
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() == c.getId())
				c = ch;
		Assert.isTrue(!c.isBanned());

		super.unauthenticate();
	}

}
