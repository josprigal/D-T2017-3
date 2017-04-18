
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
import services.SearchTemplateService;
import utilities.AbstractTest;
import domain.Chorbi;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChangeTemplateUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	SearchTemplateService	searchTemplateService;


	/*
	 * An actor who is authenticated as a chorbi must be able to:
	 * 
	 * Change his or her search template.
	 */

	@Test
	public void testChangeProfile() {
		//Cambiar su template
		super.authenticate("chorbi");
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		final Chorbi c = chorbi;
		c.getSearchTemplate().setKeyword("Chorbi2222");
		Assert.isTrue(c.getId() == chorbi.getId());
		this.searchTemplateService.save(c.getSearchTemplate());
		this.chorbiService.save(c);
		Assert.isTrue(this.chorbiService.findByPrincipal().getSearchTemplate().getKeyword().equals("Chorbi2222"));
		Assert.isTrue(c.getId() == chorbi.getId());
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Intentar cambiar su template sin estar conectado
		super.authenticate(null);
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		final Chorbi c = chorbi;
		c.getSearchTemplate().setKeyword("Chorbi2222");
		Assert.isTrue(c.getId() == chorbi.getId());
		this.searchTemplateService.save(c.getSearchTemplate());

		this.chorbiService.save(c);
		Assert.isTrue(this.chorbiService.findByPrincipal().getSearchTemplate().getKeyword().equals("Chorbi2222"));
		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthority() {
		//Intentar cambiar una template que no es suya
		super.authenticate("chorbi");
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		Chorbi c = null;
		for (final Chorbi ch : this.chorbiService.findAll())
			if (ch.getId() != chorbi.getId()) {
				c = ch;
				break;
			}
		c.getSearchTemplate().setKeyword("Chorbi2222");
		Assert.isTrue(c.getId() == chorbi.getId());
		this.searchTemplateService.save(c.getSearchTemplate());

		this.chorbiService.save(c);
		Assert.isTrue(this.chorbiService.findByPrincipal().getSearchTemplate().getKeyword().equals("Chorbi2222"));
		super.unauthenticate();
	}

}
