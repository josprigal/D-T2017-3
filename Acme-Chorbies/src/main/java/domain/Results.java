
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

@Entity
@Access(AccessType.PROPERTY)
public class Results extends DomainEntity {

	public Results() {
		super();
		moment = new Date();
	}


	private Date	moment;
	private Collection<Chorbi> chorbis;
	private SearchTemplate searchTemplate;


	@NotNull
	public Date getMoment() {
		return this.moment;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	@OneToMany
	public Collection<Chorbi> getChorbis() {
		return chorbis;
	}

	public void setChorbis(Collection<Chorbi> chorbis) {
		this.chorbis = chorbis;
	}

	@OneToOne
    public SearchTemplate getSearchTemplate() {
        return searchTemplate;
    }

    public void setSearchTemplate(SearchTemplate searchTemplate) {
        this.searchTemplate = searchTemplate;
    }
}
