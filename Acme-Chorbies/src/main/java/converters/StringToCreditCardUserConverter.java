
package converters;

import domain.Actor;
import domain.CreditCard;
import domain.CreditCardUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.ActorRepository;
import repositories.CreditCardUserRepository;

@Component
@Transactional
public class StringToCreditCardUserConverter implements Converter<String, CreditCardUser> {

	@Autowired
	CreditCardUserRepository creditCardUserRepository;


	@Override
	public CreditCardUser convert(final String text) {

		CreditCardUser result = null;

		try {

			result = (CreditCardUser) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.creditCardUserRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
