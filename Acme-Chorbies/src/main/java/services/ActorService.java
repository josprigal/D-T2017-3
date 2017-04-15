package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import domain.Actor;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ActorService {

	@Autowired
	ActorRepository actorRepository;
	public ActorService() {
		super();
	}

	public Collection<Actor> findAll() {
		Collection<Actor> result;
		result = this.actorRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Actor findOne(final int id) {
		Assert.isTrue(id != 0);
		Actor result;
		result = this.actorRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

//	public Actor findByPrincipal() {
//		Actor result = actorRepository.findByUserAccountId(LoginService
//				.getPrincipal().getId());
//		Assert.notNull(result);
//
//		return result;
//	}

	public void save(final Actor actor) {
		Assert.notNull(this.actorRepository);
		this.actorRepository.save(actor);
	}

	public void delete(final Actor actor) {
		Assert.notNull(actor);
		Assert.isTrue(actor.getId() != 0);
		Assert.isTrue(this.actorRepository.exists(actor.getId()));
		this.actorRepository.delete(actor);
	}



	 public Actor findActorByPrincipal() {
	 final UserAccount userAccount = LoginService.getPrincipal();
	 return this.actorRepository.findActorByUserAccount(userAccount);
	 }

	
}
