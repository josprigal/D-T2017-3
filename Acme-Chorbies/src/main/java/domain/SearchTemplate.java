
package domain;

import domain.Chorbi.Gender;
import domain.Chorbi.Relationship;

public class SearchTemplate extends DomainEntity {

	public SearchTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}


	private Relationship	relationship;
	private Gender			gender;
	private Integer			age;
	private String			keyword;


	public Relationship getRelationship() {
		return this.relationship;
	}

	public void setRelationship(final Relationship relationship) {
		this.relationship = relationship;
	}

	public Gender getGender() {
		return this.gender;
	}

	public void setGender(final Gender gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(final Integer age) {
		this.age = age;
	}

	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(final String keyword) {
		this.keyword = keyword;
	}

}
