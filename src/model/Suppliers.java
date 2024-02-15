package model;


/**
 * TabGysinfo generated by MyEclipse - Hibernate Tools
 */

public class Suppliers  implements java.io.Serializable {


    // Fields    

     private String CID;
     private String Cname;
     private String Property;
     private String Scope;
     private String Caddress;
     private String Number;
     private String Sarea;
     private String Farea;
     private String Phone;
     private String Email;
     private String Stuff;
     private String Sphone;
     private String FA;
     private String RC;
     private String License;
     private String IDcard;
     private String Authorization;
     private String Stax;
     private String Ltax;
     private String Code;
     private String Yz;
    // Constructors

    /** default constructor */
    public Suppliers() {
    }

	/** minimal constructor */
    public Suppliers(String CID) {
        this.CID = CID;
    }
    
    /** full constructor */
    public Suppliers(String CID, String Cname, String Property, String Scope, String Caddress, String Number, String Sarea, String Farea, String Phone, String Email, String Stuff,
    		String Sphone, String FA, String RC, String License, String IDcard, String Authorization, String Stax, String Ltax, String Code,String Yz ) {
        this.CID = CID;
        this.Cname = Cname;
        this.Property = Property;
        this.Scope = Scope;
        this.Caddress = Caddress;
        this.Number = Number;
        this.Sarea = Sarea;
        this.Farea = Farea;
        this.Phone = Phone;
        this.Email = Email;
        this.Stuff = Stuff;
        this.Sphone = Sphone;
        this.FA = FA;
        this.RC = RC;
        this.License = License;
        this.IDcard = IDcard;
        this.Authorization = Authorization;
        this.Stax = Stax;
        this.Ltax = Ltax;
        this.Code = Code;
        this.Yz = Yz;
    }

   
    // Property accessors

    public String getId() {
        return this.CID;
    }
    
    public void setId(String CID) {
        this.CID = CID;
    }

    public String getName() {
        return this.Cname;
    }
    
    public void setName(String Cname) {
        this.Cname = Cname;
    }

    public String getProperty() {
        return this.Property;
    }
    
    public void setProperty(String Property) {
        this.Property = Property;
    }

    public String getScope() {
        return this.Scope;
    }
    
    public void setScope(String Scope) {
        this.Scope = Scope;
    }

    public String getCaddress() {
        return this.Caddress;
    }
    
    public void setCaddress(String Caddress) {
        this.Caddress = Caddress;
    }

    public String getNumber() {
        return this.Number;
    }
    
    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getSarea() {
        return this.Sarea;
    }
    
    public void setSarea(String Sarea) {
        this.Sarea = Sarea;
    }

    public String getFarea() {
        return this.Farea;
    }
    
    public void setFarea(String Farea) {
        this.Farea = Farea;
    }

    public String getPhone() {
        return this.Phone;
    }
    
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getStuff() {
        return this.Stuff;
    }
    
    public void setStuff(String Stuff) {
        this.Stuff = Stuff;
    }
    
    public String getSphone() {
        return this.Sphone;
    }
    
    public void setSphone(String Sphone) {
        this.Sphone = Sphone;
    }

    public String getEmail() {
        return this.Email;
    }
    
    public void setEmail(String Email) {
        this.Email = Email;
    }
   
    public String getFA() {
        return this.FA;
    }
    
    public void setFA(String FA) {
        this.FA = FA;
    }

    public String getRC() {
        return this.RC;
    }
    
    public void setRC(String RC) {
        this.RC = RC;
    }

    public String getLicense() {
        return this.License;
    }
    
    public void setLicense(String License) {
        this.License = License;
    }

    public String getIDcard() {
        return this.IDcard;
    }
    
    public void setIDcard(String IDcard) {
        this.IDcard = IDcard;
    }
    
    public String getAuthorization() {
        return this.Authorization;
    }
    
    public void setAuthorization(String Authorization) {
        this.Authorization = Authorization;
    }

    public String getStax() {
        return this.Stax;
    }
    
    public void setStax(String Stax) {
        this.Stax = Stax;
    }

    public String getLtax() {
        return this.Ltax;
    }
    
    public void setLtax(String Ltax) {
        this.Ltax = Ltax;
    }

    public String getCode() {
        return this.Code;
    }
    
    public void setCode(String Code) {
        this.Code = Code;
    }
    
    public String getYz() {
        return this.Yz;
    }
    
    public void setYz(String Yz) {
        this.Yz = Yz;
    }
}