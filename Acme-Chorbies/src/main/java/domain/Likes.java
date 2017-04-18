
package domain;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Likes extends DomainEntity {

	public Likes() {
		super();
		moment = new Date();
	}


	private Date	moment;
	private String	comment;
	private Chorbi	sender;
	private Chorbi	recipent;


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
