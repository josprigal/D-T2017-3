
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Results extends DomainEntity {

	public Results() {
		super();
		this.moment = new Date();
	}


	private Date				moment;
	private Collection<Chorbi>	chorbis;
	private SearchTemplate		searchTemplate;


	@NotNull
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@OneToMany
	public Collection<Chorbi> getChorbis() {
		return this.chorbis;
	}

	public void setChorbis(final Collection<Chorbi> chorbis) {
		this.chorbis = chorbis;
	}

	@OneToOne
	public SearchTemplate getSearchTemplate() {
		return this.searchTemplate;
	}

	public void setSearchTemplate(final SearchTemplate searchTemplate) {
		this.searchTemplate = searchTemplate;
	}
}
