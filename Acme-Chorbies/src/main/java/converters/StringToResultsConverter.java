package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ResultsRepository;
import domain.Results;

@Component
@Transactional
public class StringToResultsConverter implements Converter<String, Results> {

	@Autowired
	ResultsRepository resultsRepository;

	@Override
	public Results convert(final String text) {

		Results result = null;

		try {

			result = (Results) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.resultsRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
