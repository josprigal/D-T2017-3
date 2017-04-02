package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.CreditCard;

@Service
@Transactional
public class CreditCardService {

	@Autowired
	CreditCardRepository creditCardRepository;

	public CreditCardService() {
		super();
	}

	public Collection<CreditCard> findAll() {
		Collection<CreditCard> result;
		result = this.creditCardRepository.findAll();
		Assert.notNull(result);
		return result;
	}

	public CreditCard findOne(final int id) {
		Assert.isTrue(id != 0);
		CreditCard result;
		result = this.creditCardRepository.findOne(id);
		Assert.notNull(result);
		return result;
	}

	public void save(final CreditCard creditCard) {
		Assert.notNull(this.creditCardRepository);
		this.creditCardRepository.save(creditCard);
	}

	public void delete(final CreditCard creditCard) {
		Assert.notNull(creditCard);
		Assert.isTrue(creditCard.getId() != 0);
		Assert.isTrue(this.creditCardRepository.exists(creditCard.getId()));
		this.creditCardRepository.delete(creditCard);
	}

}