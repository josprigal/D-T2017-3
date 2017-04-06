
package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Chirp extends DomainEntity {

	public Chirp() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String	attachments;
	private Date	sent;
	private String	subject;
	private String	text;


	@NotBlank
	@URL
	public String getAttachments() {
		return this.attachments;
	}

	public void setAttachments(final String attachments) {
		this.attachments = attachments;
	}
	@NotNull
	@Past
	public Date getSent() {
		return this.sent;
	}

	public void setSent(final Date sent) {
		this.sent = sent;
	}
	@NotBlank
	public String getSubject() {
		return this.subject;
	}

	public void setSubject(final String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getText() {
		return this.text;
	}

	public void setText(final String text) {
		this.text = text;
	}

}
