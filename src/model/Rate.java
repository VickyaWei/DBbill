package model;

import java.sql.Date;

public class Rate {
	private Double Rate1;
	private Double Rate2;
	private Double Rate3;
	private Date Updatedate;
	
	public Double getRate1() {
		return Rate1;
	}
	public void setRate1(Double Rate1) {
		this.Rate1 = Rate1;
	}
	
	public Double getRate2() {
		return Rate2;
	}
	public void setRate2(Double Rate2) {
		this.Rate2 = Rate2;
	}
	
	public Double getRate3() {
		return Rate3;
	}
	public void setRate3(Double Rate3) {
		this.Rate3 = Rate3;
	}
	
	public Date getUpdatedate() {
		return Updatedate;
	}
	public void setUpdatedate(Date Updatedate) {
		this.Updatedate = Updatedate;
	}
}
