
package services;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.BillRepository;
import repositories.ChirpRepository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BillService {

	@Autowired
	BillRepository billRepository;

	@Autowired
    FeeService feeService;

	@Autowired
    ManagerService managerService;

	@Autowired
    ChorbiService chorbiService;

	public Bill save(Bill b){
		Assert.notNull(b);

		return billRepository.save(b);
	}

	public void billNewEvent(){
        Manager manager = managerService.findByPrincipal();
        Assert.notNull(manager);
        Fee fee = feeService.findFirst();
        Bill bill = new Bill();
        bill.setAmount(fee.getFeeManagerEvent());
        bill.setMoment(new Date());
        bill.setReceiver(manager);

        save(bill);
    }


    public void billMonthlyChorbies(){
        Collection<Chorbi> chorbis = chorbiService.findAll();
        Fee fee = feeService.findFirst();
        for(Chorbi e: chorbis){
            Bill bill = new Bill();
            bill.setAmount(fee.getFeeChorbieMonth());
            bill.setMoment(new Date());
            bill.setReceiver(e);


            save(bill);
        }

    }
}
