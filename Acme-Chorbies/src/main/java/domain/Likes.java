
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Likes extends DomainEntity {

	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}


	private Date	moment;
	private String	comment;
	private Chorbi	sender;
	private Chorbi	recipent;


	@OneToOne
	public Chorbi getSender() {
		return this.sender;
	}

	public void setSender(final Chorbi sender) {
		this.sender = sender;
	}
	@OneToOne
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
