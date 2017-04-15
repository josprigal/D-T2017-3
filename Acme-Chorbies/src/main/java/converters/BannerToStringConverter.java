package converters;

import domain.Banner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class BannerToStringConverter implements Converter<Banner, String> {

	public String convert(final Banner chirp) {

		String result;

		if (chirp == null)
			result = null;
		else
			result = String.valueOf(chirp.getId());
		return result;

	}
}
