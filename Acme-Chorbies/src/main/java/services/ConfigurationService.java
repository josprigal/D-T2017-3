
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {

	@Autowired
	ConfigurationRepository	configurationRepository;


	public ConfigurationService() {
		super();
	}

	public Collection<Configuration> findAll() {
		Collection<Configuration> result;
		result = this.configurationRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Configuration findOne(final int id) {
		Assert.isTrue(id != 0);
		Configuration result;
		result = this.configurationRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public Configuration save(final Configuration configuration) {
		Assert.notNull(this.configurationRepository);
		return this.configurationRepository.save(configuration);
	}

	public void delete(final Configuration configuration) {
		Assert.notNull(configuration);
		Assert.isTrue(configuration.getId() != 0);
		Assert.isTrue(this.configurationRepository.exists(configuration.getId()));
		this.configurationRepository.delete(configuration);
	}

	public void reconstruct(final Configuration configuration, final Configuration configuration1) {
		configuration1.setHours(configuration.getHours());
		configuration1.setMinutes(configuration.getMinutes());
		configuration1.setSeconds(configuration.getSeconds());

		this.save(configuration1);
	}
}
