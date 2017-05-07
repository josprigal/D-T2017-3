
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import domain.Chorbi;
import domain.Event;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;

@Service
@Transactional
public class ChirpService {

	@Autowired
	ChirpRepository	chirpRepository;

	@Autowired
	private EventService eventService;

	@Autowired
	private ManagerService managerService;


	public ChirpService() {
		super();
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> result;
		result = this.chirpRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Chirp findOne(final int id) {
		Assert.isTrue(id != 0);
		Chirp result;
		result = this.chirpRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Chirp save(final Chirp chirp) {
		Assert.notNull(this.chirpRepository);
		return this.chirpRepository.save(chirp);
	}

	public void delete(final Chirp chirp) {
		Assert.notNull(chirp);
		Assert.isTrue(chirp.getId() != 0);
		Assert.isTrue(this.chirpRepository.exists(chirp.getId()));
		this.chirpRepository.delete(chirp);
	}

    public void broadcast(Chirp chirp) {
		Manager manager = managerService.findByPrincipal();
		List<Chorbi> chorbis = eventService.findAllChorbiesRelatedToManager();
		for (Chorbi e:chorbis){
			Chirp nc = new Chirp();
			nc.setSent(new Date());
			nc.setText(chirp.getText());
			nc.setSubject(chirp.getSubject());
			nc.setAttachments(chirp.getAttachments());
			nc.setRecipent(e);
			nc.setSender(manager);
			save(nc);
		}
    }
}
