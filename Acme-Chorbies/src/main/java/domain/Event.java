
package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Event extends DomainEntity {

	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String	title;
	private String	description;
	private Date	moment;
	private String	picture;
	private Integer	seats;


	@NotBlank
	public String getTitle() {
		return this.title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}
	@NotNull
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}
	@NotNull
	public Integer getSeats() {
		return this.seats;
	}

	public void setSeats(final Integer seats) {
		this.seats = seats;
	}


	private List<Chorbi>	chorbies;


	@ManyToMany()
	public List<Chorbi> getChorbies() {
		return this.chorbies;
	}

	public void setChorbies(final List<Chorbi> chorbies) {
		this.chorbies = chorbies;
	}

}
