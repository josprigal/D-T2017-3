package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Coordinates;

@Component
@Transactional
public class CoordinatesToStringConverter implements
		Converter<Coordinates, String> {

	@Override
	public String convert(final Coordinates coordinates) {

		String result;

		if (coordinates == null)
			result = null;
		else
			result = String.valueOf(coordinates.getId());
		return result;

	}
}
