package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.LikesRepository;
import domain.Likes;

@Component
@Transactional
public class StringToLikesConverter implements Converter<String, Likes> {

	@Autowired
	LikesRepository likesRepository;

	@Override
	public Likes convert(final String text) {

		Likes result = null;

		try {

			result = (Likes) Class.forName(text).newInstance();

		} catch (final InstantiationException | IllegalAccessException e) {

			e.printStackTrace();

		} catch (final ClassNotFoundException e) {

			result = this.likesRepository.findOne(Integer.valueOf(text));

		}

		return result;

	}

}
