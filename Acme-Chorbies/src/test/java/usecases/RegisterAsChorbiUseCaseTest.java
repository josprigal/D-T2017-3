
package usecases;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountRepository;
import services.ActorService;
import services.ChorbiService;
import utilities.AbstractTest;
import domain.Actor;
import domain.Chorbi;
import domain.Chorbi.Gender;
import domain.Chorbi.Relationship;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegisterAsChorbiUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	UserAccountRepository	userAccountRepository;


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
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(1980, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}

	@Test
	public void testRegister2() {
		//Registrarse como un chorbi sin estar autentificado
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(1995, Calendar.FEBRUARY, 14).getTime();

		c.setBirth(d);
		c.setDescription("Desc 2");
		c.setName("Name 2");
		c.setSurname("Surname 2");
		c.setRelationship(Relationship.LOVE);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.MALE);
		c.setEmail("chorbi123@hotmail.com");
		c.setPhone("958233473");
		c.setPicture("http://www.imgur.com/2");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAuthenticated() {
		//Intentar registrarse como chorbi estando autentificado
		this.authenticate("admin");
		final Actor a = this.actorService.findActorByPrincipal();
		Assert.isNull(a);
		final int size1 = this.chorbiService.findAll().size(); //Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(1980, Calendar.FEBRUARY, 11).getTime();
		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing

		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		this.unauthenticate();
	}
	@Test(expected = IllegalArgumentException.class)
	public void testNoAge() {
		//Registrarse como un chorbi sin tener 18 anios
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}

	@Test(expected = ConstraintViolationException.class)
	public void testNoName() {
		//Registrarse como un chorbi sin tener nombre
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void testNoSurName() {
		//Registrarse como un chorbi sin tener apellidos
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void testNoRelationship() {
		//Registrarse como un chorbi sin tener relacion
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void testNoGender() {
		//Registrarse como un chorbi sin tener genero
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setEmail("chorbi1234@hotmail.com");
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void testNoEmail() {
		//Registrarse como un chorbi sin tener email
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setPhone("958235473");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}
	@Test(expected = ConstraintViolationException.class)
	public void testNoPhone() {
		//Registrarse como un chorbi sin tener telefono
		super.authenticate(null);
		final int size1 = this.chorbiService.findAll().size();//Listing
		final Chorbi c = new Chorbi();
		final Date d = new GregorianCalendar(2001, Calendar.FEBRUARY, 11).getTime();

		c.setBirth(d);
		c.setDescription("Desc 1");
		c.setName("Name 1");
		c.setSurname("Surname 1");
		c.setRelationship(Relationship.ACTIVITIES);
		for (final UserAccount u : this.userAccountRepository.findAll()) {
			c.setUserAccount(u);
			break;
		}
		c.setGender(Gender.FEMALE);
		c.setEmail("chorbi1234@hotmail.com");
		c.setPicture("http://www.imgur.com");
		this.chorbiService.create(c);
		final int size2 = this.chorbiService.findAll().size();//Listing
		Assert.isTrue(c.getAge() >= 18);
		Assert.isTrue(size1 < size2);
		super.unauthenticate();
	}

}
