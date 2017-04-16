
package domain;

import org.hibernate.validator.constraints.Range;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.PROPERTY)
public class Configuration extends DomainEntity {

	public Configuration() {
		super();
		minutes = 0;
		seconds = 0;
		hours = 0;
	}


	private Integer hours;
	private Integer minutes;
	private Integer seconds;


	@Range(min = 12)
	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}

	@Range(min = 0, max = 60)
	public Integer getMinutes() {
		return minutes;
	}

	public void setMinutes(Integer minutes) {
		this.minutes = minutes;
	}

    @Range(min = 0, max = 60)
	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}
}
