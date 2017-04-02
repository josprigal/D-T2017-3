package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.SearchTemplateRepository;
import domain.SearchTemplate;

@Component
@Transactional
public class StringToSearchTemplateConverter implements
		Converter<String, SearchTemplate> {

	@Autowired
	SearchTemplateRepository searchTemplateRepository;

	@Override
	public SearchTemplate convert(final String text) {

		SearchTemplate result = null;

		try {

			result = (SearchTemplate) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.searchTemplateRepository.findOne(Integer
					.valueOf(text));

		}

		return result;

	}

}
