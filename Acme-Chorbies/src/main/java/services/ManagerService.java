
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.ManagerRepository;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	@Autowired
	ManagerRepository	managerRepository;


	public ManagerService() {
		super();
	}

	public Collection<Manager> listManagersMoreEventsCreated() {
		// TODO Auto-generated method stub
		return this.managerRepository.listManagersMoreEventsCreated();
	}

	public Collection<Manager> listManagersFees() {
		// TODO Auto-generated method stub
		return null;
	}
}
