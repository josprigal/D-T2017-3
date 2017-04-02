package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CoordinatesRepository;
import domain.Coordinates;

@Component
@Transactional
public class StringToCoordinatesConverter implements
		Converter<String, Coordinates> {

	@Autowired
	CoordinatesRepository coordinatesRepository;

	@Override
	public Coordinates convert(final String text) {

		Coordinates result = null;

		try {

			result = (Coordinates) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.coordinatesRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
