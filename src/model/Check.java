package model;

import java.util.Date;

public class Check {
	private String Cname;
	private String Supcode;
	private String Comcode;
	private String Incode;
	private String Auditcode;	
	private double Finalprice;
	private double Finalprice1;
	private String Combewrite;
	private String Combewrite1;
	private   int Comnum;
	private int Comnum1;
	private int Isaudit;
	private int Paytime;
	private Date Uplatedate;
	private Date Intime;
	private Date Gooddate;
	private double Price;
    private String drawer;
    
    
    
    
	public String getdrawer() {
		return drawer;
	}
	public void setdrawer(String drawer) {
		this.drawer = drawer;
	}
	
    
    
    
	public String getComcode() {
		return Comcode;
	}
	public void setComcode(String Comcode) {
		this.Comcode = Comcode;
	}
	
	
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double Price) {
		this.Price = Price;
	}
	
	public String getSupcode() {
		return Supcode;
	}
	public void setSupcode(String Supcode) {
		this.Supcode = Supcode;
	}

	public String getIncode() {
		return Incode;
	}
	public void setIncode(String Incode) {
		this.Incode = Incode;
	}
	
	public String getAuditcode() {
		return Auditcode;
	}
	public void setAuditcode(String Auditcode) {
		this.Auditcode = Auditcode;
	}
	public int getIsaudit() {
		return Isaudit;
	}
	public void setIsaudit(int Isaudit) {
		this.Isaudit = Isaudit;
	}
	
	
	
	public int getComnum() {
		return Comnum;
	}
	public void setComnum(int Comnum) {
		this.Comnum = Comnum;
	}
	
	
	
	public int getPaytime() {
		return Paytime;
	}
	public void setPaytime(int Paytime) {
		this.Paytime = Paytime;
	}
	
	public int getComnum1() {
		return Comnum1;
	}
	public void setComnum1(int Comnum1) {
		this.Comnum1 = Comnum1;
	}
	
	
	
	public double getFinalprice() {
		return Finalprice;
	}
	public void setFinalprice(double Finalprice) {
		this.Finalprice = Finalprice;
	}	
	public String getCombewrite() {
		return Combewrite;
	}
	public void setCombewrite(String Combewrite) {
		this.Combewrite = Combewrite;
	}	
	public String getCombewrite1() {
		return Combewrite1;
	}
	public void setCombewrite1(String Combewrite1) {
		this.Combewrite1 = Combewrite1;
	}	
	
	
	
	public  double getFinalprice1() {
		return Finalprice1;
	}
	public void setFinalprice1( double Finalprice1) {
		this.Finalprice1 = Finalprice1;
	}	
	
	
	
	
	public Date getUplatedate() {
		return Uplatedate;
	}
	public void setUplatedate(Date Uplatedate) {
		this.Uplatedate = Uplatedate;
	}
	public Date getIntime() {
		return Intime;
	}
	public void setIntime(Date Intime) {
		this.Intime = Intime;
	}
	public String getCname() {
		return Cname;
	}
	public void setCname(String Cname) {
		this.Cname = Cname;
	}	
	public Date getGooddate() {
		return Gooddate;
	}
	public void setGooddate(Date Gooddate) {
		this.Gooddate = Gooddate;
	}
	
}
