
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import domain.Actor;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	@Autowired
	ManagerRepository	managerRepository;

	@Autowired
	ActorService		actorService;


	public ManagerService() {
		super();
	}
	public Manager save(final Manager manager) {
		Assert.notNull(this.managerRepository);
		return this.managerRepository.save(manager);
	}
	public Collection<Manager> listManagersMoreEventsCreated() {
		// TODO Auto-generated method stub
		return this.managerRepository.listManagersMoreEventsCreated();
	}

	public Collection<Manager> listManagersFees() {
		// TODO Auto-generated method stub
		return null;
	}

	public Manager findByPrincipal() {
		final Actor result = this.actorService.findActorByPrincipal();
		if (!(result instanceof Manager))
			return null;

		return (Manager) result;
	}
}
