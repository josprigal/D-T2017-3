
package usecases;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import services.ActorService;
import services.ChorbiService;
import services.CreditCardService;
import services.SearchTemplateService;
import utilities.AbstractTest;
import domain.Chorbi;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchWithTemplateUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	SearchTemplateService	searchTemplateService;

	@Autowired
	CreditCardService		creditCardService;

	@Autowired
	private SessionFactory	sessionFactory;


	/*
	 * An actor who is authenticated as a chorbi must be able to:
	 * 
	 * Browse the results of his or her search template as long as he or she's registered a
	 * valid credit card. Note that the validity of the credit card must be checked every
	 * time the results of the search template are displayed. The results of search templates
	 * must be cached for at least 12 hours
	 */

	@Test
	public void testSearch() {
		//Buscar con su template
		super.authenticate("chorbi");
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);

		Assert.notNull(chorbi.getCreditCard());
		Assert.isTrue(this.creditCardService.isValid(chorbi.getCreditCard()));
		final Session session = this.sessionFactory.openSession();
		Assert.notNull(this.searchTemplateService.search(session));

		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNoAuthenticated() {
		//Buscar sin estar autentificado
		super.authenticate(null);
		final Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);

		Assert.notNull(chorbi.getCreditCard());
		Assert.isTrue(this.creditCardService.isValid(chorbi.getCreditCard()));
		Assert.notNull(this.searchTemplateService.search());

		super.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoCreditCard() {
		//Buscar con su template pero sin tarjeta
		super.authenticate("chorbi");
		Chorbi chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi);
		chorbi.setCreditCard(null);
		this.chorbiService.save(chorbi);
		chorbi = this.chorbiService.findByPrincipal();
		Assert.notNull(chorbi.getCreditCard());
		Assert.isTrue(this.creditCardService.isValid(chorbi.getCreditCard()));
		Assert.notNull(this.searchTemplateService.search());

		super.unauthenticate();
	}
}
