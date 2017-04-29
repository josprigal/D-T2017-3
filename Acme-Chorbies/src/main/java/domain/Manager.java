
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Manager extends Actor {

	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String				company;
	private String				vatNumber;
	private Collection<Event>	events;
	private CreditCard			creditCard;


	@NotBlank
	public String getCompany() {
		return this.company;
	}

	public void setCompany(final String company) {
		this.company = company;
	}
	@NotBlank
	public String getVatNumber() {
		return this.vatNumber;
	}

	public void setVatNumber(final String vatNumber) {
		this.vatNumber = vatNumber;
	}
	@OneToMany
	public Collection<Event> getEvents() {
		return this.events;
	}

	public void setEvents(final Collection<Event> events) {
		this.events = events;
	}
	@OneToOne
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

}
