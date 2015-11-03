package project.savesatang;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.graphics.Color;

import project.savesatang.R.layout;

public class Income {
	private long inid;
	private long incategoryid;
	private long invalue;
	private String indetail;
	private String indate;
	private String incatype;
	private String incacategory;
	private String incaname;
	

	public long getINID() {
		return inid;
	}

	public void setINID(long inid) {
		this.inid = inid;
	}

	// ////////////////////////////////

	public long getINCATEGORYID() {
		return incategoryid;
	}

	public void setINCATEGORYID(long incategoryid) {
		this.incategoryid = incategoryid;
	}

	// ///////////////////////////////

	public long getINVALUE() {
		return invalue;
	}

	public void setINVALUE(long invalue) {
		this.invalue = invalue;
	}

	// ////////////////////////////////

	public String getINDETAIL() {
		return indetail;
	}

	public void setINDETAIL(String indetail) {
		this.indetail = indetail;
	}

	// /////////////////////////////////

	public String getINDATE() {
		return indate;
	}

	public void setINDATE(String indate) {
		this.indate = indate;
	}
	
	//////////////////////////////////////////
	
	public String getINCATYPE() {
		return incatype;
	}
	
	public void setINCATYPE(String incatype) {
		this.incatype = incatype;
	}
	
	//////////////////////////////////////////
	
	public String getINCACATEGORY() {
		return incacategory;
	}

	public void setINCACATEGORY(String incacategory) {
		this.incacategory = incacategory;
	}
	
	///////////////////////////////////////////
	
	public String getINCANAME() {
		return incaname;
	}	

	public void setINCANAME(String incaname) {
		this.incaname = incaname;
	}
	
	@Override
	public String toString() {
		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
   		String value1 = formatter.format(invalue);

		return " "+ indate + "  " + incacategory + "" + "\n" + " " + incaname + "   "+ value1;
		
	}
}
