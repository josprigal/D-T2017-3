
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Fee extends DomainEntity {

	private Double	feeChorbieMonth;
	private Double	feeManagerEvent;


	@NotNull
	public Double getFeeChorbieMonth() {
		return this.feeChorbieMonth;
	}

	public void setFeeChorbieMonth(final Double feeChorbieMonth) {
		this.feeChorbieMonth = feeChorbieMonth;
	}

	@NotNull
	public Double getFeeManagerEvent() {
		return this.feeManagerEvent;
	}

	public void setFeeManagerEvent(final Double feeManagerEvent) {
		this.feeManagerEvent = feeManagerEvent;
	}

}
