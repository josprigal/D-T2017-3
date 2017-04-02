package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ChorbiRepository;
import domain.Chorbi;

@Component
@Transactional
public class StringToChorbiConverter implements Converter<String, Chorbi> {

	@Autowired
	ChorbiRepository chorbiRepository;

	@Override
	public Chorbi convert(final String text) {

		Chorbi result = null;

		try {

			result = (Chorbi) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.chorbiRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
