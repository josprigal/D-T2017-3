
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

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
	}


	private String			description;
	private Date			birth;
	private boolean			banned;
	private String			picture;
	private Relationship	relationship;
	private Gender			gender;


	@NotNull
	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@NotNull
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

}
