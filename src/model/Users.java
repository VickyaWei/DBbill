package model;

public class Users {
	private String LoginName;
	
	private String Password;
	private String Athority;
	
	public String getLoginName() {
		return LoginName;
	}
	public void setLoginName(String LoginName) {
		this.LoginName = LoginName;
	}
	
	public String getPassword() {
		return this.Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getAthority() {
		return this.Athority;
	}
	public void setAthority(String Athority) {
		this.Athority = Athority;
	}
}
