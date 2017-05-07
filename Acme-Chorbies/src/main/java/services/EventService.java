
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Chirp;
import domain.Chorbi;
import domain.Event;
import domain.Manager;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository	eventRepository;
	@Autowired
	private ChorbiService	chorbiService;
	@Autowired
	private ChirpService	chirpService;

	@Autowired
	private ManagerService	managerService;

	@Autowired
	BillService				billService;


	public EventService() {
		super();
	}

	public void save(final Event event) {
		Assert.notNull(this.eventRepository);
		this.eventRepository.save(event);
	}
	public Collection<Event> findAll() {
		Collection<Event> result;
		result = this.eventRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Event findOne(final int id) {
		Assert.isTrue(id != 0);
		Event result;
		result = this.eventRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Object listAvailableEvents() {

		final Collection<Event> listEvents = this.findAll();
		final Collection<Event> listAvailableEvents = new ArrayList<>();
		for (final Event e : listEvents)
			if (e.isHighlighted())
				listAvailableEvents.add(e);

		return listAvailableEvents;
	}
	public void register(final Event e) {
		Assert.isTrue(e.getSeats() != 0);
		Assert.isTrue(!(e.getChorbies().contains(this.chorbiService.findByPrincipal())));

		final List<Chorbi> chorbies = e.getChorbies();

		chorbies.add(this.chorbiService.findByPrincipal());
		e.setSeats(e.getSeats() - 1);
		e.setChorbies(chorbies);

		this.save(e);

	}

	public void unregister(final Event e) {
		Assert.isTrue(e.getChorbies().contains(this.chorbiService.findByPrincipal()));

		final List<Chorbi> chorbies = e.getChorbies();

		chorbies.remove(this.chorbiService.findByPrincipal());
		e.setSeats(e.getSeats() + 1);
		e.setChorbies(chorbies);

		this.save(e);

	}

	public void newEvent(final Event event) {
		Assert.notNull(event);
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		event.setManager(manager);
		this.billService.billNewEvent();

		this.save(event);
	}

	public void delete(final Event event) {
		Assert.notNull(event);

		this.eventRepository.delete(event);
	}

	public void editEvent(final Event event, final Event saved) {

		Assert.notNull(event);
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.isTrue(saved.getManager() == manager);
		//Notificacion chirp
		for (final Chorbi ch : event.getChorbies()) {
			Chirp chirp = new Chirp();
			chirp.setSent(new Date());
			chirp.setSubject("Event " + event.getTitle() + " has been edited.");
			chirp.setText("The manager " + manager.getName() + " has edited the event " + event.getTitle());
			chirp.setSender(manager);
			chirp.setRecipent(ch);
			chirp = this.chirpService.save(chirp);
			ch.getChirpsReceived().add(chirp);
			manager.getChirpsSents().add(chirp);
			this.managerService.save(manager);
			this.chorbiService.save(ch);
		}
		saved.setDescription(event.getDescription());
		saved.setMoment(event.getMoment());
		saved.setPicture(event.getPicture());
		saved.setTitle(event.getTitle());
		saved.setSeats(event.getSeats());

		this.save(saved);

	}

	public List<Chorbi> findAllChorbiesRelatedToManager() {
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		final List<Chorbi> result = this.eventRepository.findAllChorbiesRelatedToManager(manager);

		return result;
	}

	public void deleteChirp(final Event event) {
		//Aquí iría lo de crear un chirp y enviarselo a todos los usuarios del evento
		//Notificacion chirp
		final Manager manager = this.managerService.findByPrincipal();
		Assert.notNull(manager);
		for (final Chorbi ch : event.getChorbies()) {
			Chirp chirp = new Chirp();
			chirp.setSent(new Date());
			chirp.setSubject("Event " + event.getTitle() + " has been deleted.");
			chirp.setText("The manager " + manager.getName() + " has deleted the event " + event.getTitle());
			chirp.setSender(manager);
			chirp.setRecipent(ch);
			chirp = this.chirpService.save(chirp);
			ch.getChirpsReceived().add(chirp);
			manager.getChirpsSents().add(chirp);
			this.managerService.save(manager);
			this.chorbiService.save(ch);
		}
		this.delete(event);
	}
}
