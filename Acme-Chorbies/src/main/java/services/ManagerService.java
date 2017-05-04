
package services;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.Manager;

@Service
@Transactional
public class ManagerService {

	public ManagerService() {
		super();
	}

	public Collection<Manager> listManagersMoreEventsCreated() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<Manager> listManagersFees() {
		// TODO Auto-generated method stub
		return null;
	}
}
