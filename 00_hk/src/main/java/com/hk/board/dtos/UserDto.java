package com.hk.board.dtos;

import java.io.Serializable;
import java.util.Date;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String TID;
	private String TPASSWORD;
	private String TNAME;
	private String TADDRESS;
	private String TPHONE;
	private String TEMAIL;
	private String TENABLED;
	private String TROLE;
	private Date REGDATE;
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserDto(String tID, String tPASSWORD, String tNAME, String tADDRESS, String tPHONE, String tEMAIL,
			String tENABLED, String tROLE, Date rEGDATE) {
		super();
		TID = tID;
		TPASSWORD = tPASSWORD;
		TNAME = tNAME;
		TADDRESS = tADDRESS;
		TPHONE = tPHONE;
		TEMAIL = tEMAIL;
		TENABLED = tENABLED;
		TROLE = tROLE;
		REGDATE = rEGDATE;
	}
	
	 public UserDto(String id, String name, String password, String phone, String address, String email) {
	        this.TID = id;
	        this.TNAME = name;
	        this.TPASSWORD = password;
	        this.TADDRESS = address;
	        this.TEMAIL = email;
	        this.TPHONE = phone;
	    }
	 
	 public UserDto(String id, String address, String email) {
	        this.TID = id;
	        this.TADDRESS = address;
	        this.TEMAIL = email;
	    }
	 

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public String getTPASSWORD() {
		return TPASSWORD;
	}

	public void setTPASSWORD(String tPASSWORD) {
		TPASSWORD = tPASSWORD;
	}

	public String getTNAME() {
		return TNAME;
	}

	public void setTNAME(String tNAME) {
		TNAME = tNAME;
	}

	public String getTADDRESS() {
		return TADDRESS;
	}

	public void setTADDRESS(String tADDRESS) {
		TADDRESS = tADDRESS;
	}

	public String getTPHONE() {
		return TPHONE;
	}

	public void setTPHONE(String tPHONE) {
		TPHONE = tPHONE;
	}

	public String getTEMAIL() {
		return TEMAIL;
	}

	public void setTEMAIL(String tEMAIL) {
		TEMAIL = tEMAIL;
	}

	public String getTENABLED() {
		return TENABLED;
	}

	public void setTENABLED(String tENABLED) {
		TENABLED = tENABLED;
	}

	public String getTROLE() {
		return TROLE;
	}

	public void setTROLE(String tROLE) {
		TROLE = tROLE;
	}

	public Date getREGDATE() {
		return REGDATE;
	}

	public void setREGDATE(Date rEGDATE) {
		REGDATE = rEGDATE;
	}

	@Override
	public String toString() {
		return "UserDto [TID=" + TID + ", TPASSWORD=" + TPASSWORD + ", TNAME=" + TNAME + ", TADDRESS=" + TADDRESS
				+ ", TPHONE=" + TPHONE + ", TEMAIL=" + TEMAIL + ", TENABLED=" + TENABLED + ", TROLE=" + TROLE
				+ ", REGDATE=" + REGDATE + "]";
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

	
}
