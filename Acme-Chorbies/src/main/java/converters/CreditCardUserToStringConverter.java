
package converters;

import domain.Actor;
import domain.CreditCardUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class CreditCardUserToStringConverter implements Converter<CreditCardUser, String> {

	@Override
	public String convert(final CreditCardUser actor) {

		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());
		return result;

	}
}
