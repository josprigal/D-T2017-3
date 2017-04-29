
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Likes extends DomainEntity {

	public Likes() {
		super();
		this.moment = new Date();
	}


	private Date	moment;
	private String	comment;
	private Chorbi	sender;
	private Chorbi	recipent;
	private Integer	stars;


	@Range(min = 0, max = 3)
	public Integer getStars() {
		return this.stars;
	}

	public void setStars(final Integer stars) {
		this.stars = stars;
	}

	@ManyToOne
	public Chorbi getSender() {
		return this.sender;
	}

	public void setSender(final Chorbi sender) {
		this.sender = sender;
	}
	@ManyToOne
	public Chorbi getRecipent() {
		return this.recipent;
	}

	public void setRecipent(final Chorbi recipent) {
		this.recipent = recipent;
	}

	@NotNull
	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

}
