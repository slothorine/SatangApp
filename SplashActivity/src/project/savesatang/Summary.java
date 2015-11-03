package project.savesatang;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import android.graphics.Color;

import project.savesatang.R.layout;

public class Summary {
	private long sumid;
	private long sumcategoryid;
	private long sumexvalue;
	private long suminvalue;
	private String sumdate;
	private String sumcatype;
	private String sumcacategory;
	private String sumcaname;
	

	public long getSUMID() {
		return sumid;
	}

	public void setSUMID(long sumid) {
		this.sumid = sumid;
	}

	// ////////////////////////////////

	public long getSUMCATEGORYID() {
		return sumcategoryid;
	}

	public void setSUMCATEGORYID(long sumcategoryid) {
		this.sumcategoryid = sumcategoryid;
	}
	// ///////////////////////////////

	public long getSUMEXVALUE() {
		return sumexvalue;
	}

	public void setSUMEXVALUE(long sumexvalue) {
		this.sumexvalue = sumexvalue;
	}
	// ///////////////////////////////

	public long getSUMINVALUE() {
		return suminvalue;
	}

	public void setSUMINVALUE(long suminvalue) {
		this.suminvalue = suminvalue;
	}

	// /////////////////////////////////

	public String getSUMDATE() {
		return sumdate;
	}

	public void setSUMDATE(String  sumdate) {
		this. sumdate =  sumdate;
	}
	
	//////////////////////////////////////////
	
	public String getSUMCATYPE() {
		return  sumcatype;
	}
	
	public void setSUMCATYPE(String sumcatype) {
		this. sumcatype =  sumcatype;
	}
	
	//////////////////////////////////////////
	
	public String getSUMCACATEGORY() {
		return sumcacategory;
	}

	public void setSUMCACATEGORY(String  sumcacategory) {
		this. sumcacategory =  sumcacategory;
	}
	
	///////////////////////////////////////////
	
	public String getSUMCANAME() {
		return  sumcaname;
	}	

	public void setSUMCANAME(String sumcaname) {
		this. sumcaname =  sumcaname;
	}
	
	/*@Override
	public String toString() {
		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
   		String value1 = formatter.format(invalue);

		return " "+ indate + "  " + incacategory + "" + "\n" + " " + incaname + "   "+ value1;
		
	}*/
}
