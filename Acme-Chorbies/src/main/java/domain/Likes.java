
package domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class Likes extends DomainEntity {

	public Likes() {
		super();
		// TODO Auto-generated constructor stub
	}


	private Date	moment;
	private String	comment;


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
