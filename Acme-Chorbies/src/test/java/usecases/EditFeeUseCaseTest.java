
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
import services.ChirpService;
import services.ChorbiService;
import services.EventService;
import services.FeeService;
import services.ManagerService;
import utilities.AbstractTest;
import domain.Administrator;
import domain.Fee;

@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class EditFeeUseCaseTest extends AbstractTest {

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


	/*
	 * An actor who is authenticated as an administrator must be able to:
	 * Change the fee that is charged to managers and chorbies. (Note that they need not
	 * be the same.)
	 */

	@Test
	public void testChange() {
		//Change fee admin
		super.authenticate("admin");

		final Administrator admin = this.adminService.findByPrincipal();
		Assert.isTrue(admin != null);
		Fee fee1 = this.feeService.findFirst();
		Assert.notNull(fee1);

		fee1.setFeeChorbieMonth(10.);
		fee1.setFeeManagerEvent(12.);
		this.feeService.save(fee1);
		fee1 = this.feeService.findFirst();
		Assert.isTrue(fee1.getFeeChorbieMonth() == 10.);
		Assert.isTrue(fee1.getFeeManagerEvent() == 12.);
		super.unauthenticate();
	}
	@Test(expected = NullPointerException.class)
	public void testNoAuthenticated() {
		super.authenticate(null);

		final Administrator admin = this.adminService.findByPrincipal();
		Assert.isTrue(admin != null);
		Fee fee1 = this.feeService.findFirst();
		Assert.notNull(fee1);

		fee1.setFeeChorbieMonth(10.);
		fee1.setFeeManagerEvent(12.);
		this.feeService.save(fee1);
		fee1 = this.feeService.findFirst();
		Assert.isTrue(fee1.getFeeChorbieMonth() == 10.);
		Assert.isTrue(fee1.getFeeManagerEvent() == 12.);
		super.unauthenticate();
	}

}
