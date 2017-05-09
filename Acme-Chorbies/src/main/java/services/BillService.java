
package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BillRepository;
import domain.Bill;
import domain.Chorbi;
import domain.Fee;
import domain.Manager;

@Service
@Transactional
public class BillService {

	@Autowired
	BillRepository	billRepository;

	@Autowired
	FeeService		feeService;

	@Autowired
	ManagerService	managerService;

	@Autowired
	ChorbiService	chorbiService;


	public Bill save(final Bill b) {
		Assert.notNull(b);

		return this.billRepository.save(b);
	}
	public Collection<Bill> findAll() {
		Collection<Bill> result;
		result = this.billRepository.findAll();
		Assert.notNull(result);
		return result;
	}
	public void billNewEvent() {
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		final Fee fee = this.feeService.findFirst();
		final Bill bill = new Bill();
		bill.setAmount(fee.getFeeManagerEvent());
		bill.setMoment(new Date());
		bill.setReceiver(manager);

		this.save(bill);
	}

	public void billMonthlyChorbies() {
		final Collection<Chorbi> chorbis = this.chorbiService.findAll();
		final Fee fee = this.feeService.findFirst();
		for (final Chorbi e : chorbis) {
			final Bill bill = new Bill();
			bill.setAmount(fee.getFeeChorbieMonth());
			bill.setMoment(new Date());
			bill.setReceiver(e);

			this.save(bill);
		}

	}
}
