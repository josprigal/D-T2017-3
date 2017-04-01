
package domain;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

public class Results extends DomainEntity {

	public Results() {
		super();
		// TODO Auto-generated constructor stub
	}


	private Date	moment;


	@NotNull
	@Past
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

}
