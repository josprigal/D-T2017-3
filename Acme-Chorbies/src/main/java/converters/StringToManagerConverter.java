package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.ManagerRepository;
import domain.Manager;

@Component
@Transactional
public class StringToManagerConverter implements Converter<String, Manager> {

	@Autowired
	ManagerRepository managerRepository;

	@Override
	public Manager convert(final String text) {

		Manager result = null;

		try {

			result = (Manager) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.managerRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
