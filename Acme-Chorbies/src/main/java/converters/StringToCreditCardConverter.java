package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Component
@Transactional
public class StringToCreditCardConverter implements
		Converter<String, CreditCard> {

	@Autowired
	CreditCardRepository creditCardRepository;

	@Override
	public CreditCard convert(final String text) {

		CreditCard result = null;

		try {

			result = (CreditCard) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.creditCardRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
