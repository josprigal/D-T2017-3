package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.EventRepository;
import domain.Event;

@Component
@Transactional
public class StringToEventConverter implements Converter<String, Event> {

	@Autowired
	EventRepository eventRepository;

	@Override
	public Event convert(final String text) {

		Event result = null;

		try {

			result = (Event) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.eventRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
