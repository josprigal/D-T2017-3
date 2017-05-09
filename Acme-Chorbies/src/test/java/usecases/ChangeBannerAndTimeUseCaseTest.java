
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
import services.BannerService;
import services.ChorbiService;
import services.ConfigurationService;
import utilities.AbstractTest;
import domain.Administrator;
import domain.Configuration;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ChangeBannerAndTimeUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	AdministratorService	adminService;

	@Autowired
	BannerService			bannerService;

	@Autowired
	ConfigurationService	configurationService;


	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * 
	 * Change the banners that are displayed on the welcome page and the time that the
	 * results of search templates are cached. The time must be expressed in hours,
	 * minutes, and seconds
	 */

	@Test
	public void testChangeBanner() {
		//Cambiar banner como admin
		super.authenticate("admin");
		final Administrator a = this.adminService.findByPrincipal();
		Assert.notNull(a);
		final String s = "http://img.clipartall.com/clipart-banner-tumundografico-banner-clip-art-8326_2675.png";
		this.bannerService.changeBanner(s);
		Assert.isTrue(this.bannerService.getPrincipal().getUrl().equalsIgnoreCase(s));

		this.unauthenticate();
	}

	@Test
	public void testChangeTiempo() {
		//Cambiar tiempo como admin
		super.authenticate("admin");
		final Administrator a = this.adminService.findByPrincipal();
		Assert.notNull(a);
		Configuration c = null;
		for (final Configuration con : this.configurationService.findAll()) {
			c = con;
			break;
		}
		c.setHours(50);
		c = this.configurationService.save(c);
		Assert.isTrue(c.getHours() == 50);
		this.unauthenticate();
	}

	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		//Cambiar banner sin estar autentificado

		super.authenticate(null);
		final Administrator a = this.adminService.findByPrincipal();
		Assert.notNull(a);
		final String s = "http://img.clipartall.com/clipart-banner-tumundografico-banner-clip-art-8326_2675.png";
		this.bannerService.changeBanner(s);
		Assert.isTrue(this.bannerService.getPrincipal().getUrl().equalsIgnoreCase(s));
		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testAsChorbi() {
		//Cambiar banner como chorbi
		super.authenticate("chorbi");
		final Administrator a = this.adminService.findByPrincipal();
		Assert.notNull(a);
		final String s = "http://img.clipartall.com/clipart-banner-tumundografico-banner-clip-art-8326_2675.png";
		this.bannerService.changeBanner(s);
		Assert.isTrue(this.bannerService.getPrincipal().getUrl().equalsIgnoreCase(s));

		this.unauthenticate();
	}

}
