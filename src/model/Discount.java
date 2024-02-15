package model;

import java.sql.Date;

public class Discount {
	private String Incode;
	private String Supcode;
	private String Finalprice;
	private String Comcode;
	private String Verification;
	private Date Gooddate;
	private int paytime;
	
	public String getIncode() {
		return Incode;
	}
	public void setIncode(String Incode) {
		this.Incode = Incode;
	}
	
	public String getSupcode() {
		return Supcode;
	}
	public void setSupcode(String Supcode) {
		this.Supcode = Supcode;
	}
	
	public String getFinalprice() {
		return Finalprice;
	}
	public void setFinalprice(String Finalprice) {
		this.Finalprice = Finalprice;
	}
	
	public String getComcode() {
		return Comcode;
	}
	public void setComcode(String Comcode) {
		this.Comcode = Comcode;
	}
	
	public Date getGooddate() {
		return Gooddate;
	}
	public void setGooddate(Date Gooddate) {
		this.Gooddate = Gooddate;
	}
	
	public int getpaytime() {
		return paytime;
	}
	public void setpaytime(int paytime) {
		this.paytime = paytime;
	}
	
	public String getVerification() {
		return Verification;
	}
	public void setVerification(String Verification) {
		this.Verification = Verification;
	}
}
