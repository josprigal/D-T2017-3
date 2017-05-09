
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
import services.BillService;
import services.ChirpService;
import services.ChorbiService;
import services.EventService;
import services.FeeService;
import services.ManagerService;
import utilities.AbstractTest;
import domain.Administrator;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RunProcessUseCaseTest extends AbstractTest {

	@Autowired
	ActorService			actorService;

	@Autowired
	ChorbiService			chorbiService;

	@Autowired
	ManagerService			managerService;

	@Autowired
	AdministratorService	adminService;

	@Autowired
	EventService			eventService;

	@Autowired
	FeeService				feeService;

	@Autowired
	ChirpService			chirpService;

	@Autowired
	BillService				billService;


	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * Run a process to update the total monthly fees that the chorbies would have to pay.
	 * Recall that chorbies must not be aware of the simulation.
	 */

	@Test
	public void testRun() {
		//Run Process
		super.authenticate("admin");

		final Administrator admin = this.adminService.findByPrincipal();
		Assert.isTrue(admin != null);
		final int size = this.billService.findAll().size();
		this.billService.billMonthlyChorbies();
		final int size2 = this.billService.findAll().size();
		Assert.isTrue(size2 == (size + this.chorbiService.findAll().size()));
		super.unauthenticate();
	}
	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		super.authenticate(null);

		final Administrator admin = this.adminService.findByPrincipal();
		Assert.isTrue(admin != null);
		final int size = this.billService.findAll().size();
		this.billService.billMonthlyChorbies();
		final int size2 = this.billService.findAll().size();
		Assert.isTrue(size2 == (size + this.chorbiService.findAll().size()));
		super.unauthenticate();
	}

}
