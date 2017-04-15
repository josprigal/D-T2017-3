package services;

import domain.Actor;
import domain.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import repositories.ActorRepository;
import repositories.BannerRepository;

import java.util.Collection;

@Service
@Transactional
public class BannerService {

	@Autowired
	BannerRepository bannerRepository;
	public BannerService() {
		super();
	}

	public Collection<Banner> findAll() {
		Collection<Banner> result;
		result = this.bannerRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public Banner findOne(final int id) {
		Assert.isTrue(id != 0);
		Banner result;
		result = this.bannerRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}


	public void save(final Banner banner) {
		Assert.notNull(banner);
		this.bannerRepository.save(banner);
	}

	public void delete(final Banner banner) {
		Assert.notNull(banner);
		Assert.isTrue(banner.getId() != 0);
		Assert.isTrue(this.bannerRepository.exists(banner.getId()));
		this.bannerRepository.delete(banner);
	}



	
}
