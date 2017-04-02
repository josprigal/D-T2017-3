package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Component
@Transactional
public class StringToConfigurationConverter implements
		Converter<String, Configuration> {

	@Autowired
	ConfigurationRepository configurationRepository;

	@Override
	public Configuration convert(final String text) {

		Configuration result = null;

		try {

			result = (Configuration) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.configurationRepository
					.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
