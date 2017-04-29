
package domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Transient;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	public enum BrandName {
		VISA, MASTERCARD, DISCOVER, DINNERS, AMEX;

		@Override
		public String toString() {
			return super.toString();
		}
	}


	public CreditCard() {
		super();

	}


	private BrandName	brandName;
	private String		holderName;
	private String		number;
	private Integer		expirationMonth;
	private Integer		expirationYear;
	private Integer		cvvCode;


	@NotNull
	@Enumerated(EnumType.STRING)
	public BrandName getBrandName() {
		return this.brandName;
	}

	public void setBrandName(final BrandName brandName) {
		this.brandName = brandName;
	}

	@NotBlank
	public String getHolderName() {
		return this.holderName;
	}

	public void setHolderName(final String holderName) {
		this.holderName = holderName;
	}

	@CreditCardNumber
	public String getNumber() {
		return this.number;
	}

	public void setNumber(final String number) {
		this.number = number;
	}

	@Range(min = 1, max = 12)
	@NotNull
	public Integer getExpirationMonth() {
		return this.expirationMonth;
	}

	public void setExpirationMonth(final Integer expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	@NotNull
	public Integer getExpirationYear() {
		return this.expirationYear;
	}

	public void setExpirationYear(final Integer expirationYear) {
		this.expirationYear = expirationYear;
	}

	@Range(min = 100, max = 999)
	@NotNull
	public Integer getCvvCode() {
		return this.cvvCode;
	}

	public void setCvvCode(final Integer cvvCode) {
		this.cvvCode = cvvCode;
	}


	private Chorbi	chorbi;


	@OneToOne
	public Chorbi getChorbi() {
		return this.chorbi;
	}

	public void setChorbi(final Chorbi chorbi) {
		this.chorbi = chorbi;
	}

	@Transient
	public boolean checkValidity() {
		final Date now = new Date();
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		final int monthnow = calendar.get(Calendar.MONTH);
		final int yearnow = calendar.get(Calendar.YEAR);
		return yearnow == this.expirationYear && monthnow < this.expirationMonth || (yearnow < this.expirationYear);
	}

}
