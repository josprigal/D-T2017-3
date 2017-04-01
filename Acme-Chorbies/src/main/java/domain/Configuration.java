
package domain;

import java.sql.Time;

public class Configuration extends DomainEntity {

	public Configuration() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String	banner;
	private Time	cacheTime;


	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	public Time getCacheTime() {
		return this.cacheTime;
	}

	public void setCacheTime(final Time cacheTime) {
		this.cacheTime = cacheTime;
	}

}
