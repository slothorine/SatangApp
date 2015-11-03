package project.savesatang;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Expense {
	private long exid;
	private long excategoryid;
	private long exvalue;
	private String exdetail;
	private String exdate;
	private String excatype;
	private String excacategory;
	private String excaname;
	

	public long getEXID() {
		return exid;
	}

	public void setEXID(long exid) {
		this.exid = exid;
	}

	// ////////////////////////////////

	public long getEXCATEGORYID() {
		return excategoryid;
	}

	public void setEXCATEGORYID(long excategoryid) {
		this.excategoryid = excategoryid;
	}

	// ///////////////////////////////

	public long getEXVALUE() {
		return exvalue;
	}

	public void setEXVALUE(long exvalue) {
		this.exvalue = exvalue;
	}

	// ////////////////////////////////

	public String getEXDETAIL() {
		return exdetail;
	}

	public void setEXDETAIL(String exdetail) {
		this.exdetail = exdetail;
	}

	// /////////////////////////////////

	public String getEXDATE() {
		return exdate;
	}

	public void setEXDATE(String exdate) {
		this.exdate = exdate;
	}
	
	//////////////////////////////////////////
	
	public String getEXCATYPE() {
		return excatype;
	}
	
	public void setEXCATYPE(String excatype) {
		this.excatype = excatype;
	}
	
	//////////////////////////////////////////
	
	public String getEXCACATEGORY() {
		return excacategory;
	}

	public void setEXCACATEGORY(String excacategory) {
		this.excacategory = excacategory;
	}
	
	///////////////////////////////////////////
	
	public String getEXCANAME() {
		return excaname;
	}	

	public void setEXCANAME(String excaname) {
		this.excaname = excaname;
	}
	
	
	@Override
	public String toString() {
		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
   		String value2 = formatter.format(exvalue);
		return " " + exdate + "  " + excacategory + " " +"\n" + " " + excaname + "   " + value2 ;
	}
}
