
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
import domain.Chorbi;
import domain.Event;

@Service
@Transactional
public class EventService {

	@Autowired
	private EventRepository	eventRepository;
	@Autowired
	private ChorbiService	chorbiService;


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
		// TODO Auto-generated method stub
		final Collection<Event> listEvents = this.findAll();
		final Collection<Event> listAvailableEvents = new ArrayList<Event>();
		final Date actual = new Date(System.currentTimeMillis() - 1);
		for (final Event e : listEvents) {
			final boolean tiempo = e.getMoment().getMonth() - actual.getMonth() <= 1;
			if (tiempo && e.getSeats() != 0)
				listAvailableEvents.add(e);
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
}
