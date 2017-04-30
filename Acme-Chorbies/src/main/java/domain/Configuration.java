
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	public Configuration() {
		super();
		this.minutes = 0;
		this.seconds = 0;
		this.hours = 0;
	}


	private Integer	hours;
	private Integer	minutes;
	private Integer	seconds;


	@Range(min = 12)
	public Integer getHours() {
		return this.hours;
	}

	public void setHours(final Integer hours) {
		this.hours = hours;
	}

	@Range(min = 0, max = 60)
	public Integer getMinutes() {
		return this.minutes;
	}

	public void setMinutes(final Integer minutes) {
		this.minutes = minutes;
	}

	@Range(min = 0, max = 60)
	public Integer getSeconds() {
		return this.seconds;
	}

	public void setSeconds(final Integer seconds) {
		this.seconds = seconds;
	}
}
