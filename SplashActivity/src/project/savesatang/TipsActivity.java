package project.savesatang;

import android.os.Bundle;
import android.app.Activity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;

public class TipsActivity extends Activity implements OnClickListener {
	
	static String subday;
	static String subdate;
	static String subyear;
	static String dayly;
	static String date;
	static String yearly;
	static String picky1;
	static String picky2;
	static String subpicky1;
	static String subpicky2;
	/////////////
	String mMonth2;
	String mDay1;
	String mYear1;
	
	String cDay1;
	String cMonth2;
	String cYear1;
	
	String yDay1;
	String yMonth2;
	String yYear1;
	
	String p1Day1;
	String p1Month2;
	String p1Year1;
	
	String p2Day1;
	String p2Month2;
	String p2Year1;
	
	private int mYear;
	private int mMonth;
	private int mDay;
	
	private int mcYear;
	private int mcMonth;
	private int mcDay;
	
	private int myYear;
	private int myMonth;
	private int myDay;
	
	private int mp1Year;
	private int mp1Month;
	private int mp1Day;
	
	private int mp2Year;
	private int mp2Month;
	private int mp2Day;
	
	static final int DATE_DIALOG_ID = 0;
	static final int MONTH_DATE_DIALOG_ID = 1;
	static final int YEAR_DATE_DIALOG_ID = 2;
	static final int PICK1_DATE_DIALOG_ID = 4;
	static final int PICK2_DATE_DIALOG_ID = 5;
	
	Spinner fromList;
	ArrayAdapter<String> dataAdapter;
	TabHost mTabHost;
	long myData2;
	//ArrayList<HashMap<String, String>> MebmerList;
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.summary);
		//SQLiteHelper myDb = new SQLiteHelper(this);
		
		mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();
        
        mTabHost.addTab(mTabHost.newTabSpec("tab_test1").setIndicator("Day").setContent(R.id.tab1));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test2").setIndicator("Month").setContent(R.id.tab2));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test3").setIndicator("Year").setContent(R.id.tab3));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test4").setIndicator("All").setContent(R.id.tab4));
        mTabHost.addTab(mTabHost.newTabSpec("tab_test5").setIndicator("Pick").setContent(R.id.tab5));
        
        mTabHost.setCurrentTab(0);
      
        ShowSumIncomeAll();
        ShowSumIncomeMonth();
		ShowSumIncomeDay();
		ShowSumIncomeYear();
		ShowSumIncomePick();
		
	/*	fromList = (Spinner) findViewById(R.id.month_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		R.array.month_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		fromList.setAdapter(adapter);*/
		

		 Button add = (Button) findViewById(R.id.render);
		 Button addday = (Button) findViewById(R.id.renderday);
		 Button addyear = (Button) findViewById(R.id.renderyear);
		 Button addpick = (Button) findViewById(R.id.renderpick);
		 addpick.setOnClickListener(TipsActivity.this);
		 addyear.setOnClickListener(TipsActivity.this);
		 addday.setOnClickListener(TipsActivity.this);
		 add.setOnClickListener(TipsActivity.this);
		 
		 EditText month = (EditText) findViewById(R.id.monthtxt);
		 month.setFocusable(false);
		 month.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
			 showDialog(MONTH_DATE_DIALOG_ID);
			 }
		 });
		 EditText day = (EditText) findViewById(R.id.daytxt);
		 day.setFocusable(false);
		 day.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
			 showDialog(DATE_DIALOG_ID);
			 }
		 });
		 EditText year = (EditText) findViewById(R.id.yeartxt);
		 year.setFocusable(false);
		 year.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
			 showDialog(YEAR_DATE_DIALOG_ID);
			 }
		 });
		 
		 EditText pick1 = (EditText) findViewById(R.id.picktxt1);
		 pick1.setFocusable(false);
		 pick1.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
			 showDialog(PICK1_DATE_DIALOG_ID);
			 }
		 });
		 EditText pick2 = (EditText) findViewById(R.id.picktxt2);
		 pick2.setFocusable(false);
		 pick2.setOnClickListener(new View.OnClickListener() {
			 public void onClick(View v) {
			 showDialog(PICK2_DATE_DIALOG_ID);
			 }
		 });
	
		final Calendar c = Calendar.getInstance();
		 mYear = c.get(Calendar.YEAR);
		 mMonth = c.get(Calendar.MONTH);
		 mDay = c.get(Calendar.DAY_OF_MONTH);
		 
		 mcYear = c.get(Calendar.YEAR);
		 mcMonth = c.get(Calendar.MONTH);
		 mcDay = c.get(Calendar.DAY_OF_MONTH);
		 
		 myYear = c.get(Calendar.YEAR);
		 myMonth = c.get(Calendar.MONTH);
		 myDay = c.get(Calendar.DAY_OF_MONTH);
		 
		 mp1Year = c.get(Calendar.YEAR);
		 mp1Month = c.get(Calendar.MONTH);
		 mp1Day = c.get(Calendar.DAY_OF_MONTH);
		 
		 mp2Year = c.get(Calendar.YEAR);
		 mp2Month = c.get(Calendar.MONTH);
		 mp2Day = c.get(Calendar.DAY_OF_MONTH);
		 
		 //////////////////////////////////////////////////////////////
		//SET TEXT MON
		TextView datetxt = (TextView) findViewById(R.id.sumdatetxt);
		if (TipsActivity.getSubDate() == null){
			  Calendar calendardate = Calendar.getInstance();
			  int yeardate = calendardate.get(Calendar.YEAR);
			  int monthdate= calendardate.get(Calendar.MONTH);
			  	if(monthdate < 10){
					int month1 = monthdate + 1 ;
					String month2 = "0"+month1;
					datetxt.setText(yeardate + "-" + month2);
				}
				else if (monthdate >= 10){

					int month1 = monthdate + 1 ;
					datetxt.setText(yeardate + "-" + month1);
	
				}
		}
		else if (TipsActivity.getSubDate() != null) {
			 datetxt.setText(TipsActivity.getSubDate());
			 month.setText(TipsActivity.getSubDate());
		
		}
	
		/////////////////////////////////////////////////////////////////
		// SET TEXT DAY
		TextView daytxt = (TextView) findViewById(R.id.sumdaytxt5);
		if (TipsActivity.getSubDay() == null){
			  Calendar calendardate = Calendar.getInstance();
			  int yeardate = calendardate.get(Calendar.YEAR);
			  int monthdate= calendardate.get(Calendar.MONTH);
			  int dateday= calendardate.get(Calendar.DATE);
			  if(dateday < 10){
				  if(monthdate < 10){
				  	String day1 = "0"+dateday;
					int month1 = monthdate + 1 ;
					String month2 = "0"+month1;
					daytxt.setText(yeardate + "-" + month2 + "-" + day1);
				}
				  else if (monthdate >= 10){
					String day1 = "0"+dateday;
					int month1 = monthdate + 1 ;
					daytxt.setText(yeardate + "-" + month1 + "-" + day1);
				}
			  }
			  else if(dateday >= 10){
				  if(monthdate < 10){
					int month1 = monthdate + 1 ;
					String month2 = "0"+month1;
					daytxt.setText(yeardate + "-" + month2 + "-" + dateday);
				}
				  else if (monthdate >= 10){
					int month1 = monthdate + 1 ;
					daytxt.setText(yeardate + "-" + month1 + "-" + dateday);
				}
			  }
		}
		else if (TipsActivity.getSubDay() != null) {
			 daytxt.setText(TipsActivity.getSubDay());
			 day.setText(TipsActivity.getSubDay());
		}
		//////////////////////////////////////////////////////////////
		//SET TEXT YEAR
		TextView yeartxt4 = (TextView) findViewById(R.id.sumyeartxt5);
		if (TipsActivity.getSubYear() == null){
			 Calendar calendardate = Calendar.getInstance();
			  int yeardate = calendardate.get(Calendar.YEAR);
			  String year1 = String.valueOf(yeardate);																																																																																																																																																																							
			  yeartxt4.setText(year1);
		}
		else if (TipsActivity.getSubYear() != null) {
			yeartxt4.setText(TipsActivity.getSubYear());
			year.setText(TipsActivity.getSubYear());

		}
		
		
		
		TextView pictxt1 = (TextView) findViewById(R.id.pickdis1);
		TextView pictxt2 = (TextView) findViewById(R.id.pickdis2);
		if (TipsActivity.getSubPick1() == null || (TipsActivity.getSubPick2() == null )){
			  Calendar calendardate = Calendar.getInstance();
			  int yeardate = calendardate.get(Calendar.YEAR);
			  int monthdate= calendardate.get(Calendar.MONTH);
			  int dateday= calendardate.get(Calendar.DATE);
			  if(dateday < 10){
				  if(monthdate < 10){
				  	String day1 = "0"+dateday;
					int month1 = monthdate + 1 ;
					String month2 = "0"+month1;
					pictxt1.setText(yeardate + "-" + month2 + "-" + day1);
					pictxt2.setText(yeardate + "-" + month2 + "-" + day1);
				}
				  else if (monthdate >= 10){
					String day1 = "0"+dateday;
					int month1 = monthdate + 1 ;
					pictxt1.setText(yeardate + "-" + month1 + "-" + day1);
					pictxt2.setText(yeardate + "-" + month1 + "-" + day1);
				}
			  }
			  else if(dateday >= 10){
				  if(monthdate < 10){
					int month1 = monthdate + 1 ;
					String month2 = "0"+month1;
					pictxt1.setText(yeardate + "-" + month2 + "-" + dateday);
					pictxt2.setText(yeardate + "-" + month2 + "-" + dateday);
				}
				  else if (monthdate >= 10){
					int month1 = monthdate + 1 ;
					pictxt1.setText(yeardate + "-" + month1 + "-" + dateday);
					pictxt2.setText(yeardate + "-" + month1 + "-" + dateday);
				}
			  }
		}
		else if (TipsActivity.getSubPick1() != null || (TipsActivity.getSubPick2() != null )) {
			 pictxt1.setText(TipsActivity.getSubPick1());
			 pictxt2.setText(TipsActivity.getSubPick2());
			 pick1.setText(TipsActivity.getSubPick1());
			 pick2.setText(TipsActivity.getSubPick2());
		}
	
		
		 
		/*  toList = (Spinner) findViewById(R.id.toList);
		  dataAdapter = new ArrayAdapter<String>(this,
		    android.R.layout.simple_spinner_item, new ArrayList<String>());
		  dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  toList.setAdapter(dataAdapter);*/
	
		 }
	public static String getDate() {
		return date;
	}
	public static void setDate(String date) {
		TipsActivity.date = date;
	}
	public static String getDay() {
		return dayly;
	}
	public static void setDay(String dayly) {
		TipsActivity.dayly = dayly;
	}
	public static String getYear() {
		return yearly;
	}
	public static void setYear(String yearly) {
		TipsActivity.yearly = yearly;
	}
	
	
	
	public static String getPick1() {
		return picky1;
	}
	public static void setPick1(String picky1) {
		TipsActivity.picky1 = picky1;
	}
	public static String getPick2() {
		return picky2;
	}
	public static void setPick2(String picky2) {
		TipsActivity.picky2 = picky2;
	}

	
	
	
	
	public static String getSubPick1() {
	return subpicky1;
	}
	public static void setSubPick1(String subpicky1) {
	TipsActivity.subpicky1 = subpicky1;
	}
	
	public static String getSubPick2() {
	return subpicky2;
	}
	public static void setSubPick2(String subpicky2) {
	TipsActivity.subpicky2 = subpicky2;
	}
	
///////////////////////////////////////////
	// Month display
public static String getSubDate() {
return subdate;
}
public static void setSubDate(String subdate) {
TipsActivity.subdate = subdate;
}

///////////////////////////////////////////
/// Date display
public static String getSubDay() {
return subday;
}
public static void setSubDay(String subday) {
TipsActivity.subday = subday;
}

///////////////////////////////////////////
/// Date display
public static String getSubYear() {
return subyear;
}
public static void setSubYear(String subyear) {
TipsActivity.subyear = subyear;
}
	
	public void onClick(View v) {
		
		  switch (v.getId()) {
		  case R.id.render:
			  EditText month= (EditText) findViewById(R.id.monthtxt);
			  TextView datetxt = (TextView) findViewById(R.id.sumdatetxt);
		  

			  if (month.getText().toString().equals("")){
					
  				  Toast.makeText(TipsActivity.this,
  	        	    		"Please Click On Box And Choose Date",
  	        	    			Toast.LENGTH_LONG).show();	
  				  }
			  else {	
				  String datemonth = String.valueOf(month.getText().toString());
				  date = datemonth;
				  subdate = datemonth;
				  TipsActivity.setDate(date);
				  TipsActivity.setSubDate(subdate);
				  datetxt.setText(TipsActivity.getSubDate());
			 Log.d("tag","1" +" " + date);
		  }
		  
		  datetxt.setText(TipsActivity.getSubDate());
		  ShowSumIncomeMonth();
		   
		  break;
	         
		  
		  case R.id.renderday:
			  TextView daytxt5 = (TextView) findViewById(R.id.sumdaytxt5);
			  EditText day = (EditText) findViewById(R.id.daytxt);
			  Log.d("tag","msg" + day);
			  if (day.getText().toString().equals("")){
					
  				  Toast.makeText(TipsActivity.this,
  						"Please Click On Box And Choose Date",
  	        	    			Toast.LENGTH_LONG).show();	
  				  }
			  else {
				  String dateday = String.valueOf(day.getText().toString());
						dayly = dateday;
						subday = dateday;
						TipsActivity.setDay(dayly);
						TipsActivity.setSubDay(subday);
			  }
				  daytxt5.setText(TipsActivity.getSubDay());
				  ShowSumIncomeDay();
			  
	          	break;
	          
		  	case R.id.renderyear:
		  	TextView yeartxt5 = (TextView) findViewById(R.id.sumyeartxt5);
		  	  EditText year2 = (EditText) findViewById(R.id.yeartxt);
		  	  if (year2.getText().toString().equals("")){
					
  				  Toast.makeText(TipsActivity.this,
  						"Please Click On Box And Choose Date",
  	        	    			Toast.LENGTH_LONG).show();	
  				  }
		  	  else {
		  	  String year3 = year2.getText().toString();
		  	  yearly = year3 + "-" + "01" + "-" + "01";
		  	  subyear = year3;
		  	  Log.d("tag", "year" +" "+ yearly);
		  	  TipsActivity.setYear(yearly);
		  	  TipsActivity.setSubYear(subyear);
		  	  yeartxt5.setText(TipsActivity.getSubYear());
		  	  ShowSumIncomeYear();
		  	  }
		  	  break;
		  	
		  	case R.id.renderpick:
		  	  EditText pick1 = (EditText) findViewById(R.id.picktxt1);
		  	  EditText pick2 = (EditText) findViewById(R.id.picktxt2);
			  TextView pickd1 = (TextView) findViewById(R.id.pickdis1);
			  TextView pickd2 = (TextView) findViewById(R.id.pickdis2);
		  

			  if (pick1.getText().toString().equals("") || (pick2.getText().toString().equals("")))
					  {
					
  				  Toast.makeText(TipsActivity.this,
  	        	    		"Please Click On Box And Choose Date",
  	        	    			Toast.LENGTH_LONG).show();	
  				  }
			  else {	
				  String pickc1 = String.valueOf(pick1.getText().toString());
				  String pickc2 = String.valueOf(pick2.getText().toString());
				  picky1 = pickc1;
				  picky2 = pickc2;
				  
				  TipsActivity.setPick1(picky1);
				  TipsActivity.setPick2(picky2);
				  TipsActivity.setSubPick1(picky1);
				  TipsActivity.setSubPick2(picky2);
				  pickd1.setText(TipsActivity.getSubPick1());
				  pickd2.setText(TipsActivity.getSubPick2());
			 Log.d("tag","1" +" " + date);
		  }
			  pickd1.setText(TipsActivity.getSubPick1());
			  pickd2.setText(TipsActivity.getSubPick2());
			  ShowSumIncomePick();
		   
		  	break;
	      }
	}
	
	



	/*	ListView lisView1 = (ListView)findViewById(R.id.listmonth);
		SimpleAdapter sAdap;
	    sAdap = new SimpleAdapter(TipsActivity.this, MebmerList, R.layout.summarymonth_column,
	    new String[] {"Month","Income","Expense"}, new int[] {R.id.ColMonth,R.id.ColIncome,R.id.ColExpense});      
	        lisView1.setAdapter(sAdap);*/
	public void ShowSumIncomeMonth() {

		final SQLiteHelper myDb = new SQLiteHelper(this);
		long income = myDb.SumIncomeNow();   
		long necessary = myDb.SumExpenseNethis();
		long unnecessary = myDb.SumExpenseUnthis();
		long other = myDb.SumExpenseOtthis();
		long expense = myDb.SumExpenseNow();
		
		
	
       TextView sumin = (TextView)findViewById(R.id.sumIn);
       TextView sumadvice = (TextView)findViewById(R.id.sumAdvice);
       TextView exne = (TextView)findViewById(R.id.sumNe);
       TextView exun = (TextView)findViewById(R.id.sumUn);
       TextView exot = (TextView)findViewById(R.id.sumOt);
       TextView ex = (TextView)findViewById(R.id.all);
       TextView remain = (TextView)findViewById(R.id.rem);
       
      // 50 / 200 * 100
       
    
   				////////////////////////////////////// Income
    
				if(income == 0){
					sumin.setText("  " + 0);
					sumin.setTextColor(Color.parseColor("#000000"));
			}
				else if(income > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String inc1 = formatter.format(income);
					sumin.setText("  " + inc1);
				   	sumin.setTextColor(Color.parseColor("#006400"));
			}
		
			/////////////////////////////////// Necessary Expense

				if(necessary == 0){
				       	exne.setText("  " + 0 +"("+ 0  + "%" +")" );
				       	exne.setTextColor(Color.parseColor("#000000"));
				}
				else if(necessary > 0){
					double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String nes1 = formatter.format(necessary);
						exne.setText("  " + nes1 +"("+ ne  + "%" +")" );
						exne.setTextColor(Color.parseColor("#FF8C00"));
						}

				///////////////////////////////// Unnecessary Expense
					if (unnecessary == 0){
					       exun.setText("  " + 0 +"("+ 0  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#000000"));
					}
					else if(unnecessary > 0){
						double unpercent = unnecessary * 100 / expense;
					       String un = String.format("%.2f", unpercent);
					   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					   		String unn1 = formatter.format(unnecessary);
					       exun.setText("  " + unn1 +"("+ un  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#FF0000"));
					}
				
				/////////////////////////////////////// Other Expense
					if(other == 0){
					       exot.setText("  " + 0 +"("+ 0  + "%" +")" );
						exot.setTextColor(Color.parseColor("#000000"));
					}
					else if(other > 0){
						double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						double unpercent = unnecessary * 100 / expense;
						String un = String.format("%.2f", unpercent);
						double ne1 = Double.parseDouble(ne);
						double un1 = Double.parseDouble(un);
						double ot1 = 100 - (ne1 + un1);
					    String ot = String.format("%.2f", ot1);
					    //double otpercent = other * 100 / expense;
					   	NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String ote1 = formatter.format(other);
					       exot.setText("  " + ote1 +"("+ ot  + "%" +")" );
						exot.setTextColor(Color.parseColor("#555555"));
					}

					////////////////////// Total Expense
					if (expense == 0){
						ex.setText("  " + 0 );
						ex.setTextColor(Color.parseColor("#000000"));
					}
					else if(expense > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String exp1 = formatter.format(expense);
						ex.setText("  " + exp1 );
						ex.setTextColor(Color.parseColor("#FF0000"));
					}
              //////////////////////////// Remain
					double rem = income - expense;
					if (rem > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#006400"));
				}
				else if(rem < 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#FF0000"));
				}
				else if(rem == 0){
						remain.setText("  " + 0 );
						remain.setTextColor(Color.parseColor("#000000"));
				}	
				
				long deposite = income * 10 / 100;
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
				String dep = formatter.format(deposite);
		//		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
		//		Long.parseLong(dep);
		//		formatter.format(dep);
				sumadvice.setText("  " + dep);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void ShowSumIncomeAll() {

		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		long income = myDb.SumIncome();   
		long necessary = myDb.SumExpenseNe();
		long unnecessary = myDb.SumExpenseUn();
		long other = myDb.SumExpenseOt();
		long expense = myDb.SumExpense();
	
       TextView sumin = (TextView)findViewById(R.id.sumIns);
       TextView sumadvice = (TextView)findViewById(R.id.sumAdvices);
       TextView exne = (TextView)findViewById(R.id.sumNes);
       TextView exun = (TextView)findViewById(R.id.sumUns);
       TextView exot = (TextView)findViewById(R.id.sumOts);
       TextView ex = (TextView)findViewById(R.id.alls);
       TextView remain = (TextView)findViewById(R.id.rems);
       
      // 50 / 200 * 100
       
    
   				////////////////////////////////////// Income
    
				if(income == 0){
					sumin.setText("  " + 0);
					sumin.setTextColor(Color.parseColor("#000000"));
			}
				else if(income > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String inc1 = formatter.format(income);
					sumin.setText("  " + inc1);
				   	sumin.setTextColor(Color.parseColor("#006400"));
			}
		
			/////////////////////////////////// Necessary Expense

				if(necessary == 0){
				       	exne.setText("  " + 0 +"("+ 0  + "%" +")" );
				       	exne.setTextColor(Color.parseColor("#000000"));
				}
				else if(necessary > 0){
					double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String nes1 = formatter.format(necessary);
						exne.setText("  " + nes1 +"("+ ne  + "%" +")" );
						exne.setTextColor(Color.parseColor("#FF8C00"));
						}

				///////////////////////////////// Unnecessary Expense
					if (unnecessary == 0){
					       exun.setText("  " + 0 +"("+ 0  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#000000"));
					}
					else if(unnecessary > 0){
						double unpercent = unnecessary * 100 / expense;
					       String un = String.format("%.2f", unpercent);
					   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					   		String unn1 = formatter.format(unnecessary);
					       exun.setText("  " + unn1 +"("+ un  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#FF0000"));
					}
				
				/////////////////////////////////////// Other Expense
					if(other == 0){
					       exot.setText("  " + 0 +"("+ 0  + "%" +")" );
						exot.setTextColor(Color.parseColor("#000000"));
					}
					else if(other > 0){
						double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						double unpercent = unnecessary * 100 / expense;
						String un = String.format("%.2f", unpercent);
						double ne1 = Double.parseDouble(ne);
						double un1 = Double.parseDouble(un);
						double ot1 = 100 - (ne1 + un1);
						String ot = String.format("%.2f", ot1);
					//	double otpercent = other * 100 / expense;
					   	NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String ote1 = formatter.format(other);
					       exot.setText("  " + ote1 +"("+ ot  + "%" +")" );
						exot.setTextColor(Color.parseColor("#555555"));
					}

					////////////////////// Total Expense
					if (expense == 0){
						ex.setText("  " + 0 );
						ex.setTextColor(Color.parseColor("#000000"));
					}
					else if(expense > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String exp1 = formatter.format(expense);
						ex.setText("  " + exp1 );
						ex.setTextColor(Color.parseColor("#FF0000"));
					}
              //////////////////////////// Remain
					double rem = income - expense;
					if (rem > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#006400"));
				}
				else if(rem < 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#FF0000"));
				}
				else if(rem == 0){
						remain.setText("  " + 0 );
						remain.setTextColor(Color.parseColor("#000000"));
				}	
				
				long deposite = income * 10 / 100;
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
				String dep = formatter.format(deposite);
		//		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
		//		Long.parseLong(dep);
		//		formatter.format(dep);
				sumadvice.setText("  " + dep);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public void ShowSumIncomeDay() {

		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		long income = myDb.SumIncomeDay();   
		long necessary = myDb.SumExpenseNeDay();
		long unnecessary = myDb.SumExpenseUnDay();
		long other = myDb.SumExpenseOtDay();
		long expense = myDb.SumExpenseDay();
	
       TextView sumin = (TextView)findViewById(R.id.sumInd);
       TextView sumadvice = (TextView)findViewById(R.id.sumAdviced);
       TextView exne = (TextView)findViewById(R.id.sumNed);
       TextView exun = (TextView)findViewById(R.id.sumUnd);
       TextView exot = (TextView)findViewById(R.id.sumOtd);
       TextView ex = (TextView)findViewById(R.id.alld);
       TextView remain = (TextView)findViewById(R.id.remd);
       
      // 50 / 200 * 100
       
    
   				////////////////////////////////////// Income
    
				if(income == 0){
					sumin.setText("  " + 0);
					sumin.setTextColor(Color.parseColor("#000000"));
			}
				else if(income > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String inc1 = formatter.format(income);
					sumin.setText("  " + inc1);
				   	sumin.setTextColor(Color.parseColor("#006400"));
			}
		
			/////////////////////////////////// Necessary Expense

				if(necessary == 0){
				       	exne.setText("  " + 0 +"("+ 0  + "%" +")" );
				       	exne.setTextColor(Color.parseColor("#000000"));
				}
				else if(necessary > 0){
					double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String nes1 = formatter.format(necessary);
						exne.setText("  " + nes1 +"("+ ne  + "%" +")" );
						exne.setTextColor(Color.parseColor("#FF8C00"));
						}

				///////////////////////////////// Unnecessary Expense
					if (unnecessary == 0){
					       exun.setText("  " + 0 +"("+ 0  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#000000"));
					}
					else if(unnecessary > 0){
						double unpercent = unnecessary * 100 / expense;
					       String un = String.format("%.2f", unpercent);
					   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					   		String unn1 = formatter.format(unnecessary);
					       exun.setText("  " + unn1 +"("+ un  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#FF0000"));
					}
				
				/////////////////////////////////////// Other Expense
					if(other == 0){
					       exot.setText("  " + 0 +"("+ 0  + "%" +")" );
						exot.setTextColor(Color.parseColor("#000000"));
					}
					else if(other > 0){
						double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						double unpercent = unnecessary * 100 / expense;
						String un = String.format("%.2f", unpercent);
						double ne1 = Double.parseDouble(ne);
						double un1 = Double.parseDouble(un);
						double ot1 = 100 - (ne1 + un1);
						String ot = String.format("%.2f", ot1);
					   	NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String ote1 = formatter.format(other);
					       exot.setText("  " + ote1 +"("+ ot  + "%" +")" );
						exot.setTextColor(Color.parseColor("#555555"));
					}

					////////////////////// Total Expense
					if (expense == 0){
						ex.setText("  " + 0 );
						ex.setTextColor(Color.parseColor("#000000"));
					}
					else if(expense > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String exp1 = formatter.format(expense);
						ex.setText("  " + exp1 );
						ex.setTextColor(Color.parseColor("#FF0000"));
					}
              //////////////////////////// Remain
					double rem = income - expense;
					if (rem > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#006400"));
				}
				else if(rem < 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#FF0000"));
				}
				else if(rem == 0){
						remain.setText("  " + 0 );
						remain.setTextColor(Color.parseColor("#000000"));
				}	
				
				long deposite = income * 10 / 100;
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
				String dep = formatter.format(deposite);
		//		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
		//		Long.parseLong(dep);
		//		formatter.format(dep);
				sumadvice.setText("  " + dep);
	}
	
	
	public void ShowSumIncomeYear() {

		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		long income = myDb.SumIncomeYear();   
		long necessary = myDb.SumExpenseNeYear();
		long unnecessary = myDb.SumExpenseUnYear();
		long other = myDb.SumExpenseOtYear();
		long expense = myDb.SumExpenseYear();
	
       TextView sumin = (TextView)findViewById(R.id.sumIny);
       TextView sumadvice = (TextView)findViewById(R.id.sumAdvicey);
       TextView exne = (TextView)findViewById(R.id.sumNey);
       TextView exun = (TextView)findViewById(R.id.sumUny);
       TextView exot = (TextView)findViewById(R.id.sumOty);
       TextView ex = (TextView)findViewById(R.id.ally);
       TextView remain = (TextView)findViewById(R.id.remy);
       
      // 50 / 200 * 100
       
    
   				////////////////////////////////////// Income
    
				if(income == 0){
					sumin.setText("  " + 0);
					sumin.setTextColor(Color.parseColor("#000000"));
			}
				else if(income > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String inc1 = formatter.format(income);
					sumin.setText("  " + inc1);
				   	sumin.setTextColor(Color.parseColor("#006400"));
			}
		
			/////////////////////////////////// Necessary Expense

				if(necessary == 0){
				       	exne.setText("  " + 0 +"("+ 0  + "%" +")" );
				       	exne.setTextColor(Color.parseColor("#000000"));
				}
				else if(necessary > 0){
					double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String nes1 = formatter.format(necessary);
						exne.setText("  " + nes1 +"("+ ne  + "%" +")" );
						exne.setTextColor(Color.parseColor("#FF8C00"));
						}

				///////////////////////////////// Unnecessary Expense
					if (unnecessary == 0){
					       exun.setText("  " + 0 +"("+ 0  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#000000"));
					}
					else if(unnecessary > 0){
						double unpercent = unnecessary * 100 / expense;
					       String un = String.format("%.2f", unpercent);
					   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					   		String unn1 = formatter.format(unnecessary);
					       exun.setText("  " + unn1 +"("+ un  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#FF0000"));
					}
				
				/////////////////////////////////////// Other Expense
					if(other == 0){
					       exot.setText("  " + 0 +"("+ 0  + "%" +")" );
						exot.setTextColor(Color.parseColor("#000000"));
					}
					else if(other > 0){
						double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						double unpercent = unnecessary * 100 / expense;
						String un = String.format("%.2f", unpercent);
						double ne1 = Double.parseDouble(ne);
						double un1 = Double.parseDouble(un);
						double ot1 = 100 - (ne1 + un1);
						String ot = String.format("%.2f", ot1);
					   	NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String ote1 = formatter.format(other);
					       exot.setText("  " + ote1 +"("+ ot  + "%" +")" );
						exot.setTextColor(Color.parseColor("#555555"));
					}

					////////////////////// Total Expense
					if (expense == 0){
						ex.setText("  " + 0 );
						ex.setTextColor(Color.parseColor("#000000"));
					}
					else if(expense > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String exp1 = formatter.format(expense);
						ex.setText("  " + exp1 );
						ex.setTextColor(Color.parseColor("#FF0000"));
					}
              //////////////////////////// Remain
					double rem = income - expense;
					if (rem > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#006400"));
				}
				else if(rem < 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#FF0000"));
				}
				else if(rem == 0){
						remain.setText("  " + 0 );
						remain.setTextColor(Color.parseColor("#000000"));
				}	
				
				long deposite = income * 10 / 100;
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
				String dep = formatter.format(deposite);
		//		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
		//		Long.parseLong(dep);
		//		formatter.format(dep);
				sumadvice.setText("  " + dep);
	}
	public void ShowSumIncomePick() {

		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		long income = myDb.SumIncomePick();   
		long necessary = myDb.SumExpenseNePick();
		long unnecessary = myDb.SumExpenseUnPick();
		long other = myDb.SumExpenseOtPick();
		long expense = myDb.SumExpensePick();
	
       TextView sumin = (TextView)findViewById(R.id.sumInp);
       TextView sumadvice = (TextView)findViewById(R.id.sumAdvicep);
       TextView exne = (TextView)findViewById(R.id.sumNep);
       TextView exun = (TextView)findViewById(R.id.sumUnp);
       TextView exot = (TextView)findViewById(R.id.sumOtp);
       TextView ex = (TextView)findViewById(R.id.allp);
       TextView remain = (TextView)findViewById(R.id.remp);
       
      // 50 / 200 * 100
       
    
   				////////////////////////////////////// Income
    
				if(income == 0){
					sumin.setText("  " + 0);
					sumin.setTextColor(Color.parseColor("#000000"));
			}
				else if(income > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String inc1 = formatter.format(income);
					sumin.setText("  " + inc1);
				   	sumin.setTextColor(Color.parseColor("#006400"));
			}
		
			/////////////////////////////////// Necessary Expense

				if(necessary == 0){
				       	exne.setText("  " + 0 +"("+ 0  + "%" +")" );
				       	exne.setTextColor(Color.parseColor("#000000"));
				}
				else if(necessary > 0){
					double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String nes1 = formatter.format(necessary);
						exne.setText("  " + nes1 +"("+ ne  + "%" +")" );
						exne.setTextColor(Color.parseColor("#FF8C00"));
						}

				///////////////////////////////// Unnecessary Expense
					if (unnecessary == 0){
					       exun.setText("  " + 0 +"("+ 0  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#000000"));
					}
					else if(unnecessary > 0){
						double unpercent = unnecessary * 100 / expense;
					       String un = String.format("%.2f", unpercent);
					   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					   		String unn1 = formatter.format(unnecessary);
					       exun.setText("  " + unn1 +"("+ un  + "%" +")" );
					       exun.setTextColor(Color.parseColor("#FF0000"));
					}
				
				/////////////////////////////////////// Other Expense
					if(other == 0){
					       exot.setText("  " + 0 +"("+ 0  + "%" +")" );
						exot.setTextColor(Color.parseColor("#000000"));
					}
					else if(other > 0){
						double nepercent = necessary * 100 / expense;
						String ne = String.format("%.2f", nepercent);
						double unpercent = unnecessary * 100 / expense;
						String un = String.format("%.2f", unpercent);
						double ne1 = Double.parseDouble(ne);
						double un1 = Double.parseDouble(un);
						double ot1 = 100 - (ne1 + un1);
						String ot = String.format("%.2f", ot1);
					   	NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String ote1 = formatter.format(other);
					       exot.setText("  " + ote1 +"("+ ot  + "%" +")" );
						exot.setTextColor(Color.parseColor("#555555"));
					}

					////////////////////// Total Expense
					if (expense == 0){
						ex.setText("  " + 0 );
						ex.setTextColor(Color.parseColor("#000000"));
					}
					else if(expense > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String exp1 = formatter.format(expense);
						ex.setText("  " + exp1 );
						ex.setTextColor(Color.parseColor("#FF0000"));
					}
              //////////////////////////// Remain
					double rem = income - expense;
					if (rem > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#006400"));
				}
				else if(rem < 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String re1 = formatter.format(rem);
						remain.setText("  " + re1 );
						remain.setTextColor(Color.parseColor("#FF0000"));
				}
				else if(rem == 0){
						remain.setText("  " + 0 );
						remain.setTextColor(Color.parseColor("#000000"));
				}	
				
				long deposite = income * 10 / 100;
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
				String dep = formatter.format(deposite);
		//		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
		//		Long.parseLong(dep);
		//		formatter.format(dep);
				sumadvice.setText("  " + dep);
	}
	   private void updateCurrentDate() {
		   EditText day = (EditText) findViewById(R.id.daytxt);
		   String stringday = cYear1 + "-" + cMonth2 + "-" + cDay1;
	    	day.setText(stringday);
	    	
	    	Log.d("","date" + stringday);
	    }
	    
	    private DatePickerDialog.OnDateSetListener mDateSetListener =
	            new DatePickerDialog.OnDateSetListener() {

	                public void onDateSet(DatePicker view, int year, 
	                                      int monthOfYear, int dayOfMonth) {
	                    mcYear = year;
	                    mcMonth = monthOfYear;
	                    mcDay = dayOfMonth;
	                    if(mDay < 10){
	                    	cDay1 = "0"+mcDay;
	      				  if(mcMonth < 10){
	      						int mMonth1 = mcMonth + 1 ;
	      						 cMonth2 = "0"+mMonth1;
	      						 cYear1 = String.valueOf(mcYear);
	      					}
	      					else if (mcMonth >= 10){
	      						int mMonth1 = mcMonth + 1 ;
	      						cMonth2 = String.valueOf(mMonth1);
	      						cYear1 = String.valueOf(mcYear);
	      					}
	      			  }
	      				  else if(mcDay >= 10){
	      					 cDay1 = String.valueOf(mcDay);
	      					  if(mcMonth < 10){
	      						int mMonth1 = mMonth + 1 ;
	      						cMonth2 = "0"+mMonth1;
	      						cYear1 = String.valueOf(mcYear);
	      					}
	      					  else if (mcMonth >= 10){
	      						int mMonth1 = mcMonth + 1 ;
	      						cMonth2 = String.valueOf(mMonth1);
	      						cYear1 = String.valueOf(mcYear);
	      					}
	      			  }
	                    updateCurrentDate();
	                }
	     };
	     
	     
	     private void updateCurrentDate1() {
			   EditText month = (EditText) findViewById(R.id.monthtxt);
			   String stringday = mYear1 + "-" + mMonth2;
		    	month.setText(stringday);
		    	
		    	Log.d("","date" + stringday);
		    }
		    
		    private DatePickerDialog.OnDateSetListener mDateSetListener1 =
		            new DatePickerDialog.OnDateSetListener() {

		                public void onDateSet(DatePicker view, int year, 
		                                      int monthOfYear, int dayOfMonth) {
		                    mYear = year;
		                    mMonth = monthOfYear;
		      				  if(mMonth < 10){
		      						int mMonth1 = mMonth + 1 ;
		      						 mMonth2 = "0"+mMonth1;
		      						mYear1 = String.valueOf(mYear);
		      					}
		      					else if (mMonth >= 10){
		      						int mMonth1 = mMonth + 1 ;
		      						mMonth2 = String.valueOf(mMonth1);
		      						mYear1 = String.valueOf(mYear);
		      					}
		                    updateCurrentDate1();
		                }
		     };
		     
		     
		     
		     
		     private void updateCurrentDate2() {
				   EditText year = (EditText) findViewById(R.id.yeartxt);
				   String stringday = String.valueOf(yYear1);
			    	year.setText(stringday);
			    	
			    	Log.d("","date" + stringday);
			    }
			    
			    private DatePickerDialog.OnDateSetListener mDateSetListener2 =
			            new DatePickerDialog.OnDateSetListener() {

			                public void onDateSet(DatePicker view, int year, 
			                                      int monthOfYear, int dayOfMonth) {
			                    yYear1 = String.valueOf(year);
			                    updateCurrentDate2();
			                }
			     };
			     
			    
			     
			     
			     
			     
			     
			     private void updateCurrentDatePick1() {
					   EditText pickdate1 = (EditText) findViewById(R.id.picktxt1);
					   String stringday = p1Year1 + "-" + p1Month2 + "-" + p1Day1;
				    	pickdate1.setText(stringday);
				    	
				    	Log.d("","date" + stringday);
				    }
				    
				    private DatePickerDialog.OnDateSetListener mDatePick1SetListener =
				            new DatePickerDialog.OnDateSetListener() {

				                public void onDateSet(DatePicker view, int year, 
				                                      int monthOfYear, int dayOfMonth) {
				                    mp1Year = year;
				                    mp1Month = monthOfYear;
				                    mp1Day = dayOfMonth;
				                    if(mp1Day < 10){
				                    	p1Day1 = "0"+mp1Day;
				      				  if(mp1Month < 10){
				      						int mMonth1 = mp1Month + 1 ;
				      						 p1Month2 = "0"+mMonth1;
				      						p1Year1 = String.valueOf(mp1Year);
				      					}
				      					else if (mp1Month >= 10){
				      						int mMonth1 = mp1Month + 1 ;
				      						p1Month2 = String.valueOf(mMonth1);
				      						p1Year1 = String.valueOf(mp1Year);
				      					}
				      			  }
				      				  else if(mp1Day >= 10){
				      					 p1Day1 = String.valueOf(mp1Day);
				      					  if(mp1Month < 10){
				      						int mMonth1 = mp1Month + 1 ;
				      						p1Month2 = "0"+mMonth1;
				      						p1Year1 = String.valueOf(mp1Year);
				      					}
				      					  else if (mp1Month >= 10){
				      						int mMonth1 = mp1Month + 1 ;
				      						p1Month2 = String.valueOf(mMonth1);
				      						p1Year1 = String.valueOf(mp1Year);
				      					}
				      			  }
				                    updateCurrentDatePick1();
				                }
				     };
				     
				     
				     
				     
				     
				     
				     private void updateCurrentDatePick2() {
						   EditText pickdate2 = (EditText) findViewById(R.id.picktxt2);
						   String stringday = p2Year1 + "-" + p2Month2 + "-" + p2Day1;
					    	pickdate2.setText(stringday);
					    	
					    	Log.d("","date" + stringday);
					    }
					    
					    private DatePickerDialog.OnDateSetListener mDatePick2SetListener =
					            new DatePickerDialog.OnDateSetListener() {

					                public void onDateSet(DatePicker view, int year, 
					                                      int monthOfYear, int dayOfMonth) {
					                    mp2Year = year;
					                    mp2Month = monthOfYear;
					                    mp2Day = dayOfMonth;
					                    if(mp2Day < 10){
					                    	p2Day1 = "0"+mp2Day;
					      				  if(mp2Month < 10){
					      						int mMonth1 = mp2Month + 1 ;
					      						 p2Month2 = "0"+mMonth1;
					      						p2Year1 = String.valueOf(mp2Year);
					      					}
					      					else if (mp2Month >= 10){
					      						int mMonth1 = mp2Month + 1 ;
					      						p2Month2 = String.valueOf(mMonth1);
					      						p2Year1 = String.valueOf(mp2Year);
					      					}
					      			  }
					      				  else if(mp2Day >= 10){
					      					 p2Day1 = String.valueOf(mp2Day);
					      					  if(mp2Month < 10){
					      						int mMonth1 = mp2Month + 1 ;
					      						p2Month2 = "0"+mMonth1;
					      						p2Year1 = String.valueOf(mp2Year);
					      					}
					      					  else if (mp2Month >= 10){
					      						int mMonth1 = mp2Month + 1 ;
					      						p2Month2 = String.valueOf(mMonth1);
					      						p2Year1 = String.valueOf(mp2Year);
					      					}
					      			  }
					                    updateCurrentDatePick2();
					                }
					     };
	@Override
	    protected Dialog onCreateDialog(int id) {
	        switch (id) {
	        case DATE_DIALOG_ID:
	            return new DatePickerDialog(this,
	                        mDateSetListener,
	                        mcYear, mcMonth, mcDay);
	        case MONTH_DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                        mDateSetListener1,
                        mYear, mMonth, mDay);
	        case YEAR_DATE_DIALOG_ID:
	            return new DatePickerDialog(this,
	                        mDateSetListener2,
	                        myYear, myMonth, myDay);
	        case PICK1_DATE_DIALOG_ID:
	            return new DatePickerDialog(this,
	                        mDatePick1SetListener,
	                        mp1Year, mp1Month, mp1Day);
	        case PICK2_DATE_DIALOG_ID:
	            return new DatePickerDialog(this,
	            		mDatePick2SetListener,
	                        mp2Year, mp2Month, mp2Day);
	        }
	        return null;
	    }
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.backmenu, menu);
        return true;
    }
	
	@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        if(item.getItemId() == R.id.Back){
				finish();
	        }
	        return super.onOptionsItemSelected(item);
	    }
	
}
