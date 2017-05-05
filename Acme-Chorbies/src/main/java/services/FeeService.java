
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.FeeRepository;

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

}
