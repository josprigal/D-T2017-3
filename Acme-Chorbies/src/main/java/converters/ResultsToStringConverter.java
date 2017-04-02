package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Results;

@Component
@Transactional
public class ResultsToStringConverter implements Converter<Results, String> {

	@Override
	public String convert(final Results results) {

		String result;

		if (results == null)
			result = null;
		else
			result = String.valueOf(results.getId());
		return result;

	}
}
