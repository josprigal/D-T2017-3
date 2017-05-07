
package domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
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
	private Manager manager;

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
	@Range(min = 0)
	public Integer getSeats() {
		return this.seats;
	}

	public void setSeats(final Integer seats) {
		this.seats = seats;
	}


	private List<Chorbi>	chorbies;


	@ManyToMany
	public List<Chorbi> getChorbies() {
		return this.chorbies;
	}

	public void setChorbies(final List<Chorbi> chorbies) {
		this.chorbies = chorbies;
	}

	@Transient
	public Boolean isAvailable(){
        Date date = new Date();

        return moment.after(date);
	}



    @Transient
    public Integer getSeatsAvailable(){
	    return seats - chorbies.size();
    }
    @Transient
	public Boolean isHighlighted(){
	    Date date = new Date();
        Calendar calMoment = Calendar.getInstance();
        calMoment.setTime(date);
        calMoment.add(Calendar.MONTH,1);

        return moment.before(calMoment.getTime()) && moment.after(date) && this.seats>0;
    }

    @ManyToOne

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}
