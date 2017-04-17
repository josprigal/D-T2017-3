
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	@Autowired
	BannerRepository	bannerRepository;


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

	public Banner save(final Banner banner) {
		Assert.notNull(banner);
		return this.bannerRepository.save(banner);
	}

	public void delete(final Banner banner) {
		Assert.notNull(banner);
		Assert.isTrue(banner.getId() != 0);
		Assert.isTrue(this.bannerRepository.exists(banner.getId()));
		this.bannerRepository.delete(banner);
	}
	public Banner getPrincipal() {
		return this.bannerRepository.findAll().get(0);
	}

	public Banner changeBanner(final String image) {
		final Banner banner = this.getPrincipal();
		banner.setUrl(image);
		return this.save(banner);
	}

}
