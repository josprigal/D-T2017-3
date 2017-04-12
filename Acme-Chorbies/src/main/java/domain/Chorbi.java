package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Chorbi extends Actor {

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
		this.receivedChirps = new ArrayList<Chirp>();
		this.sentChirps = new ArrayList<Chirp>();
		this.sentLikes = new ArrayList<Likes>();
		this.receivedLikes = new ArrayList<Likes>();
	}

	private String description;
	private Date birth;
	private boolean banned;
	private String picture;
	private Relationship relationship;
	private Gender gender;

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

	private CreditCard creditCard;

	@OneToOne
	public CreditCard getCreditCard() {
		return this.creditCard;
	}

	public void setCreditCard(final CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	private Collection<Likes> sentLikes;
	private Collection<Likes> receivedLikes;
	private Collection<Chirp> sentChirps;
	private Collection<Chirp> receivedChirps;

	@ManyToMany
	public Collection<Likes> getSentLikes() {
		return this.sentLikes;
	}

	public void setSentLikes(final Collection<Likes> sentLikes) {
		this.sentLikes = sentLikes;
	}

	@ManyToMany
	public Collection<Likes> getReceivedLikes() {
		return this.receivedLikes;
	}

	public void setReceivedLikes(final Collection<Likes> receivedLikes) {
		this.receivedLikes = receivedLikes;
	}

	@ManyToMany
	public Collection<Chirp> getSentChirps() {
		return this.sentChirps;
	}

	public void setSentChirps(final Collection<Chirp> sentChirps) {
		this.sentChirps = sentChirps;
	}

	@ManyToMany
	public Collection<Chirp> getReceivedChirps() {
		return this.receivedChirps;
	}

	public void setReceivedChirps(final Collection<Chirp> receivedChirps) {
		this.receivedChirps = receivedChirps;
	}

	private Coordinates coordinates;
	private SearchTemplate searchTemplate;

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

}
