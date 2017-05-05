
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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


	public EventService() {
		super();
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
	public void register(final Chorbi c) {

	}
}
