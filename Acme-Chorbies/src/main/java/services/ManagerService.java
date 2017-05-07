
package services;

import java.util.Collection;

import domain.Actor;
import domain.Chorbi;
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

	@Autowired
	ActorService actorService;


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

    public Manager findByPrincipal() {
		final Actor result = actorService.findActorByPrincipal();
		if(!(result instanceof Manager)){
			return null;
		}

		return (Manager) result;
	}
}
