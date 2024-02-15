package model;

import java.sql.Date;

public class Order {
	
		private String Comcode;
		private String Combewrite;
		private String Comnum;
		private Double Comprice;
		private Double Sprice;
		private Double Disprice;
		private Double Finalprice;
		private Date Gooddate;
		private int Paytime;
		private Date uplatedate;
		private String Supcode;
		private String Verification;
		private String staff;
		
		
		
		public String getComcode() {
			return Comcode;
		}
		public void setComcode(String Comcode) {
			this.Comcode = Comcode;
		}
		
		public String getCombewrite() {
			return this.Combewrite;
		}

		public void setCombewrite(String Combewrite) {
			this.Combewrite = Combewrite;
		}
		public String getComnum() {
			return this.Comnum;
		}
		public void setComnum(String Comnum) {
			this.Comnum = Comnum;
		}
		public Double getComprice() {
			return Comprice;
		}
		public void setComprice(Double Comprice) {
			this.Comprice = Comprice;
		}
		
		public Double getSprice() {
			return this.Sprice;
		}

		public void setSprice(Double Sprice) {
			this.Sprice = Sprice;
		}
		public Double getDisprice() {
			return this.Disprice;
		}
		public void setDisprice(Double Disprice) {
			this.Disprice = Disprice;
		}
		public Double getFinalprice() {
			return Finalprice;
		}
		public void setFinalprice(Double Finalprice) {
			this.Finalprice = Finalprice;
		}
		
		public Date getGooddate() {
			return this.Gooddate;
		}

		public void setGooddate(Date Gooddate) {
			this.Gooddate = Gooddate;
		}
		public int getPaytime() {
			return this.Paytime;
		}
		public void setPaytime(int Paytime) {
			this.Paytime = Paytime;
		}
		public Date getuplatedate() {
			return uplatedate;
		}
		public void setuplatedate(Date uplatedate) {
			this.uplatedate = uplatedate;
		}
		
		public String getSupcode() {
			return this.Supcode;
		}

		public void setSupcode(String Supcode) {
			this.Supcode = Supcode;
		}
		
		public String getstaff() {
			return this.staff;
		}
		public void setstaff(String staff) {
			this.staff = staff;
		}
		
		public String getVerification() {
			return this.Verification;
		}
		public void setVerification(String Verification) {
			this.Verification = Verification;
		}
}
