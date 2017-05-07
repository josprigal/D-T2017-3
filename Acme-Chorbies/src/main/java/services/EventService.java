
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import domain.Chirp;
import domain.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EventRepository;
import domain.Chorbi;
import domain.Event;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository	eventRepository;
	@Autowired
	private ChorbiService	chorbiService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	BillService billService;

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

		Collection<Event> listEvents = this.findAll();
		Collection<Event> listAvailableEvents = new ArrayList<>();
		for (Event e : listEvents) {
			if(e.isHighlighted()){
				listAvailableEvents.add(e);
			}
		}

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

    public void newEvent(Event event) {
		Assert.notNull(event);
		Manager manager = managerService.findByPrincipal();
		Assert.notNull(manager);
		event.setManager(manager);
		billService.billNewEvent();

		save(event);
    }

	public void delete(Event event) {
		Assert.notNull(event);

		eventRepository.delete(event);
	}

	public void editEvent(Event event, Event saved) {

	    //Aquí iría lo de crear un chirp cuando se ha cambiado un evento

		Assert.notNull(event);
		Manager manager = managerService.findByPrincipal();
		Assert.notNull(manager);
		Assert.isTrue(saved.getManager()==manager);
		saved.setDescription(event.getDescription());
		saved.setMoment(event.getMoment());
		saved.setPicture(event.getPicture());
		saved.setTitle(event.getTitle());
		saved.setSeats(event.getSeats());

		save(saved);

	}

    public List<Chorbi> findAllChorbiesRelatedToManager() {
		Manager manager = managerService.findByPrincipal();
		Assert.notNull(manager);
		List<Chorbi> result = eventRepository.findAllChorbiesRelatedToManager(manager);


		return result;
    }

    public void deleteChirp(Event event) {
	    //Aquí iría lo de crear un chirp y enviarselo a todos los usuarios del evento

        delete(event);
    }
}
