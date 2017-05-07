
package services;

import domain.Fee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.Assert;
import repositories.FeeRepository;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class FeeService {

	@Autowired
	private FeeRepository	feeRepository;
	@Autowired
	private ChorbiService	chorbiService;
	@Autowired
	private ManagerService	managerService;


	public FeeService() {
		super();
	}

	public Double calculateFee() {
		return null;
	}

	public Fee save(Fee fee){
		Assert.notNull(fee);

		return feeRepository.save(fee);
	}

    public Fee findFirst() {
		List<Fee> fees = feeRepository.findAll();
		Assert.isTrue(fees.size()>0);
		return fees.get(0);
    }
}
