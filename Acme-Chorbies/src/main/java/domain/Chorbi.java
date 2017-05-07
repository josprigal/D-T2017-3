
package domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chorbi extends CreditCardUser {

	public enum Gender {
		MALE, FEMALE;

		@Override
		public String toString() {
			return super.toString();
		}
	}

	public enum Relationship {
		ACTIVITIES, FRIENDSHIP, LOVE;

		@Override
		public String toString() {
			return super.toString();
		}
	}


	public Chorbi() {
		super();
		// TODO Auto-generated constructor stub
		this.banned = false;

	}


	private String				description;
	private Date				birth;
	private boolean				banned;
	private String				picture;
	private Relationship		relationship;
	private Gender				gender;
	private Integer				age;
	private Collection<Likes>	userLikedYou;
	private Collection<Likes>	likedUsers;



	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getBirth() {
		return this.birth;

	}

	public void setBirth(final Date birth) {
		this.birth = birth;
		this.age = this.calculateAge();
	}

	@NotBlank
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	@NotNull
	public Relationship getRelationship() {
		return this.relationship;
	}

	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}

	@NotNull
	public Gender getGender() {
		return this.gender;
	}

	public void setGender(final Gender gender) {
		this.gender = gender;
	}

	@NotNull
	public boolean isBanned() {
		return this.banned;
	}

	public void setBanned(final boolean banned) {
		this.banned = banned;
	}




	private Coordinates		coordinates;
	private SearchTemplate	searchTemplate;


	@OneToOne
	public Coordinates getCoordinates() {
		return this.coordinates;
	}

	public void setCoordinates(final Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	@OneToOne
	public SearchTemplate getSearchTemplate() {
		return this.searchTemplate;
	}

	public void setSearchTemplate(final SearchTemplate searchTemplate) {
		this.searchTemplate = searchTemplate;
	}

	public Integer getAge() {
		return this.age;
	}
	public void setAge(final Integer age) {
		this.age = age;
	}

	public Integer calculateAge() {
		final LocalDate birthdate = new LocalDate(this.birth);          //Birth date
		final LocalDate now = new LocalDate();                    //Today's date
		final Period period = new Period(birthdate, now, PeriodType.yearMonthDay());
		//Now access the values as below
		return period.getYears();
	}

	@OneToMany(mappedBy = "recipent")
	public Collection<Likes> getUserLikedYou() {
		return this.userLikedYou;
	}

	public void setUserLikedYou(final Collection<Likes> userLikedYou) {
		this.userLikedYou = userLikedYou;
	}

	@OneToMany(mappedBy = "sender")
	public Collection<Likes> getLikedUsers() {
		return this.likedUsers;
	}

	public void setLikedUsers(final Collection<Likes> likedUsers) {
		this.likedUsers = likedUsers;
	}




	private List<Event>	events;


	@ManyToMany(mappedBy = "chorbies")
	public List<Event> getEvents() {
		return this.events;
	}

	public void setEvents(final List<Event> events) {
		this.events = events;
	}

}
