 package project.savesatang;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;

public class IncomeActivity extends ListActivity { 
	final static String TAG = "SQLite";
	private IncomeDataSource datasource;
	List<Income> values;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);
		ShowSumIncome();

		// //////////////////////////////////////////////////////////////////////
		// ปุุ่ม Back
	/*	final Button btn1 = (Button) findViewById(R.id.back_button);
		btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(IncomeActivity.this,
						MainActivity.class);
				startActivity(newActivity);
				// TODO Auto-generated method stub

			}
		}); */
			///////////////////////////////////////////////////////////////////
    // ปุ่ม Add
		final Button add_income = (Button) findViewById(R.id.add_button);
		add_income.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				addNewIncome(); // /////// เรียกใช้ class addNewIncome
				// TODO Auto-generated method stub
			}
		});
		datasource = new IncomeDataSource(this);
		datasource.open();
		ShowAllIncome();
		ShowSumIncome();
		// // เรียใช้ class ShowAllIncome
	}

	// /////////////////////////////////////////////////// class ShowAllIncome
	public void ShowAllIncome() {
		values = datasource.getAllIncome();
		ArrayAdapter<Income> adapter = new ArrayAdapter<Income>(this,
				android.R.layout.simple_list_item_1, values);
		
		setListAdapter(adapter);
	}
	public void ShowSumIncome() {

		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		long myData = myDb.SumIncomeNow();   
	
        // spinner dialog income
       TextView textview = (TextView)findViewById(R.id.textInSum);  
       
     //  double value_value = Double.parseDouble(value.getText().toString());
       
       	long sum = Long.valueOf(myData);
   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
   		String sum1 = formatter.format(sum);
      
   		textview.setText("Income This Month = " + sum1);
      
       
      // textview.setText(sum);
        
	//final SQLiteHelper myDb = new SQLiteHelper(this);
		
	//	final Cursor myData = myDb.SelectAllData2();  
		
	 // double myData = Double.parseDouble(myDb.toString()); */
		
		//String test = "1";
		
		//System.out.println("2");
		
		//TextView textsum = (TextView) findViewById(R.id.textInSum);
	     
	//	Toast.makeText(IncomeActivity.this,"Your Income =" + myData, Toast.LENGTH_LONG).show();
		
}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		showIncomeDetail(position); // View book by id
	}

	// ////////////////////////////////////////////////////////// Show Income
	// Detail
	public void showIncomeDetail(final int id) {
		@SuppressWarnings("unchecked")
		final ArrayAdapter<Income> adapter = (ArrayAdapter<Income>) getListAdapter();
		final Income income = (Income) getListAdapter().getItem(id);
		
		final Dialog dialog = new Dialog(IncomeActivity.this);
		dialog.setContentView(R.layout.detail_income);
		dialog.setTitle("Income Detail");
		dialog.setCancelable(true);
		
		TextView txt_category = (TextView) dialog.findViewById(R.id.textIncomeCategory);
		TextView txt_Detail = (TextView) dialog.findViewById(R.id.textDetail);
		TextView txt_Value = (TextView) dialog.findViewById(R.id.textValue);
		TextView txt_Date = (TextView) dialog.findViewById(R.id.textDate);
		
		txt_category.setText("Category :\t" + "" + income.getINCANAME());
		txt_Detail.setText("Detail :\t" + "" + income.getINDETAIL());
		
		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
   		String value1 = formatter.format(income.getINVALUE());
		
   		txt_Value.setText("Value :\t" + "" + value1);
		txt_Date.setText("Date :\t" + "" + income.getINDATE());
		
		//edit income
		Button button_edit = (Button) dialog.findViewById(R.id.Edit_button);
		button_edit.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if (getListAdapter().getCount() > 0) {
						editIncome(income);
						dialog.dismiss();
					}
				}
		});
		
		//delete income
		Button button_delete = (Button) dialog.findViewById(R.id.Delete_button);
		button_delete.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if (getListAdapter().getCount() > 0) {
						datasource.deleteIncome(income); // delete income
						adapter.remove(income);
						dialog.dismiss();
						Toast.makeText(IncomeActivity.this, "Delete data succeed.", Toast.LENGTH_LONG).show();
					}
					ShowSumIncome();
				}
		});
		// close dialog
		Button button_cancel = (Button) dialog.findViewById(R.id.Close_button);
		button_cancel.setOnClickListener(new OnClickListener() { 
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	// ////////////////////////////////////////////////////////// Edit Income
	public void editIncome(final Income income) {
		final Dialog dialog = new Dialog(IncomeActivity.this);
		dialog.setContentView(R.layout.add_income);
		dialog.setTitle("Edit Income");
		dialog.setCancelable(true);
		
		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		final Cursor myData = myDb.SelectAllData();   
		
		startManagingCursor(myData);
		
        // spinner dialog income
        final Spinner spin = (Spinner)dialog.findViewById(R.id.spinner1);  
        
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(IncomeActivity.this, R.layout.income_column, myData
        		,new String[] {"cacategory","caname","caid","catype"}
				,new int[] {R.id.ColCate, R.id.ColName}); 
        
        spin.setAdapter(adapter);
  /*      String ItemID5 = myData.getString(myData.getColumnIndex("caname"));*/
        
       	Integer selectsp = (int) income.getINCATEGORYID() - 1;
        	spin.setSelection(selectsp); 
        
        
      /*  int selectsp = (int) income.getINCATEGORYID();
        
        spin.setSelection((int) adapter.getItemId(selectsp)); */
        
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				String ItemID = myData.getString(myData.getColumnIndex("caname"));
        	    Toast.makeText(IncomeActivity.this,
        	    		"Your Selected : " + ItemID,
        	    			Toast.LENGTH_SHORT).show();	
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
	      	    Toast.makeText(IncomeActivity.this,
        	    		"Your Selected : -",
        	    			Toast.LENGTH_SHORT).show();	
			}
        });
		
		final EditText detail = (EditText) dialog.findViewById(R.id.editTextDetail);
		final EditText value = (EditText) dialog.findViewById(R.id.editTextValue);
		final DatePicker date = (DatePicker) dialog.findViewById(R.id.dateIncome);
	//	final Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
		
		String date1 = income.getINDATE();
		
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		Date txtdate2 = null;
			try {
				txtdate2 = df2.parse(date1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	/*	DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
		Date txtdate1 = new Date();
		try {
			 txtdate1 = df3.parse(txtdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	
			
		@SuppressWarnings("deprecation")
		Integer dateMonth = txtdate2.getMonth();
		@SuppressWarnings("deprecation")
		Integer dateYear = txtdate2.getYear() + 1900;
		@SuppressWarnings("deprecation")
		Integer dateDay = txtdate2.getDate();
	/*	if(dateYear == 000){dateYear = 2000;}if(dateYear == 001){dateYear = 2001;}
		if(dateYear == 002){dateYear = 2002;}if(dateYear == 003){dateYear = 2003;}
		if(dateYear == 004){dateYear = 2004;}if(dateYear == 005){dateYear = 2005;}
		if(dateYear == 006){dateYear = 2006;}if(dateYear == 007){dateYear = 2007;}
		if(dateYear == 008){dateYear = 2008;}if(dateYear == 009){dateYear = 2009;}
		if(dateYear == 010){dateYear = 2010;}if(dateYear == 011){dateYear = 2011;}
		if(dateYear == 112){dateYear = 2012;}if(dateYear == 113){dateYear = 2013;}
		if(dateYear == 000){dateYear = 2000;}if(dateYear == 001){dateYear = 2001;}
		
	//	if(dateYear = 008){dateYear = 2008;}if(dateYear == 001){dateYear = 2001;} /*
		txt1.setText(String.valueOf(dateYear));
		txt2.setText(String.valueOf(txtdate));
		txt3.setText(String.valueOf(txtdate2));
	

	//	Integer dateMonth = date.getMonth();
	//	Integer dateDate = date.getDayOfMonth();
		
		
	/*	StringBuilder sb = new StringBuilder();
		sb.append(dateMonth + 1).append("-").append(dateDate).append("-").append(dateYear).append("");
		
		String datetime = sb.toString();
		
	//	datetime.(String.valueOf(income.getINDATE())); */
		
   		

		value.setText(String.valueOf(income.getINVALUE()));
		detail.setText(income.getINDETAIL());
		date.init(dateYear, dateMonth, dateDay, null);
		// setup button
		Button button_save = (Button) dialog.findViewById(R.id.save_button);
		button_save.setOnClickListener(new OnClickListener() {
			@SuppressWarnings("deprecation")
			public void onClick(View v) {
				
		        
				////////////////////// spinner category
				//Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
				final String ItemID = myData.getString(myData.getColumnIndex("caid"));
				final String ItemID2 = myData.getString(myData.getColumnIndex("catype"));
				final String ItemID3 = myData.getString(myData.getColumnIndex("cacategory"));
				final String ItemID4 = myData.getString(myData.getColumnIndex("caname"));
				/////////////////// textbox ช่องรายละเอียด
				EditText detail = (EditText) dialog.findViewById(R.id.editTextDetail);

				/////////////////// textbox ช่องจำนวนเงิน
				final EditText value = (EditText) dialog.findViewById(R.id.editTextValue);

				/////////////////// DatePicker
				DatePicker date = (DatePicker) dialog.findViewById(R.id.dateIncome);
				final Integer dateYear = date.getYear();
				final Integer dateMonth = date.getMonth();
				final Integer dateDate = date.getDayOfMonth();
				final Integer dateMonth1 = dateMonth + 1;
				final String value_detail = detail.getText().toString();
				final int value_spinner = Integer.parseInt(ItemID.toString());
				final String value_spinner2 = ItemID2.toString();
				final String value_spinner3 = ItemID3.toString();
				final String value_spinner4 = ItemID4.toString();
				//String value_spinner = spinner.getItemAtPosition().toString();
				
				if (value.getText().toString().equals("")){
					
  				  Toast.makeText(IncomeActivity.this,
  	        	    		"Please enter number in value's space",
  	        	    			Toast.LENGTH_LONG).show();	
				}
				
  					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 
					///////////////////////////////////////////////////////////// If month Date < 10
				
				else if(dateDate < 10 && dateMonth1 < 10 )
  				  {
					final long value_value = Long.parseLong(value.getText().toString());
					String dateDate1 = "0"+dateDate;
					String dateMonth2 = "0"+dateMonth1;
					StringBuilder sb = new StringBuilder();
					sb.append(dateYear.toString()).append("-").append(dateMonth2.toString()).append("-").append(dateDate1.toString()).append("");
					final String dateStr = sb.toString();
					
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
			   		String sum1 = formatter.format(value_value);
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
			        // Setting Dialog Title
			        alertDialog.setTitle("Confirm");
			        // Setting Dialog Message
			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
			        						"Detail : " + value_detail + "\n" +
			        						"Value : " + sum1 + "\n" +
			        						"Date : " + dateStr);
			        // Setting Positive "Yes" Button
			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog1,int which) {
			             	income.setINCATEGORYID(value_spinner);
			            	income.setINCATYPE(value_spinner2);
			            	income.setINCACATEGORY(value_spinner3);
			            	income.setINCANAME(value_spinner4);
			            	income.setINDATE(dateStr);
			            	income.setINDETAIL(value_detail);
			            	income.setINVALUE(value_value);
			            	datasource.updateIncome(income);
			            	ShowSumIncome();
			            	ShowAllIncome();
			            	dialog.cancel();
			            }
			        });
  					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
  						public void onClick(DialogInterface dialog, int which) {
		            dialog.cancel();
		            }
		        });
  					
		        // Showing Alert Message
		        alertDialog.show();
  			} 
  		
				
				
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 
					///////////////////////////////////////////////////////////// If date< 10

				else if(dateDate < 10)
				{
					final long value_value = Long.parseLong(value.getText().toString());
					String dateDate1 = "0"+dateDate;
					StringBuilder sb = new StringBuilder();
					sb.append(dateYear.toString()).append("-").append(dateMonth1.toString()).append("-").append(dateDate1.toString()).append("");
					final String dateStr = sb.toString();
					
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
			   		String sum1 = formatter.format(value_value);
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
			        // Setting Dialog Title
			        alertDialog.setTitle("Confirm");
			        // Setting Dialog Message
			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
			        						"Detail : " + value_detail + "\n" +
			        						"Value : " + sum1 + "\n" +
			        						"Date : " + dateStr);
			        // Setting Positive "Yes" Button
			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog1,int which) {
			             	income.setINCATEGORYID(value_spinner);
			            	income.setINCATYPE(value_spinner2);
			            	income.setINCACATEGORY(value_spinner3);
			            	income.setINCANAME(value_spinner4);
			            	income.setINDATE(dateStr);
			            	income.setINDETAIL(value_detail);
			            	income.setINVALUE(value_value);
			            	datasource.updateIncome(income);
			            	ShowSumIncome();
			            	ShowAllIncome();
			            	dialog.cancel();
			            }
			        });
  					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
  						public void onClick(DialogInterface dialog, int which) {
		            dialog.cancel();
		            }
		        });

		        // Showing Alert Message
		        alertDialog.show();
  			}
  		
				
				
				
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 			
					///////////////////////////////////////////////////////////// 
					///////////////////////////////////////////////////////////// If month < 10

				else if(dateMonth1 < 10 )
				{
					final long value_value = Long.parseLong(value.getText().toString());
					String dateMonth2 = "0"+dateMonth1;
					StringBuilder sb = new StringBuilder();
					sb.append(dateYear.toString()).append("-").append(dateMonth2.toString()).append("-").append(dateDate.toString()).append("");
					final String dateStr = sb.toString();
					
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
			   		String sum1 = formatter.format(value_value);
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
			        // Setting Dialog Title
			        alertDialog.setTitle("Confirm");
			        // Setting Dialog Message
			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
			        						"Detail : " + value_detail + "\n" +
			        						"Value : " + sum1 + "\n" +
			        						"Date : " + dateStr);
			        // Setting Positive "Yes" Button
			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			            public void onClick(DialogInterface dialog1,int which) {
			             	income.setINCATEGORYID(value_spinner);
			            	income.setINCATYPE(value_spinner2);
			            	income.setINCACATEGORY(value_spinner3);
			            	income.setINCANAME(value_spinner4);
			            	income.setINDATE(dateStr);
			            	income.setINDETAIL(value_detail);
			            	income.setINVALUE(value_value);
			            	datasource.updateIncome(income);
			            	ShowSumIncome();
			            	ShowAllIncome();
			            	dialog.cancel();
			            }
			        });
  					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
  						public void onClick(DialogInterface dialog, int which) {
		            dialog.cancel();
		            }
		        });
		 

		        // Showing Alert Message
		        alertDialog.show();
  			}
  		
				

				
				///////////////////////////////////////////////////////////// 			
				///////////////////////////////////////////////////////////// 			
				///////////////////////////////////////////////////////////// 			
				///////////////////////////////////////////////////////////// 			
				///////////////////////////////////////////////////////////// 
				///////////////////////////////////////////////////////////// If month date > 10
				
				
				else if(dateDate >= 10 && dateMonth1 >= 10){
				final long value_value = Long.parseLong(value.getText().toString());
				StringBuilder sb = new StringBuilder();
				sb.append(dateYear.toString()).append("-")
				.append(dateMonth1.toString()).append("-")
				.append(dateDate.toString()).append("");
				final String dateStr = sb.toString();
				
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
		   		String sum1 = formatter.format(value_value);
				AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
		        // Setting Dialog Title
		        alertDialog.setTitle("Confirm");
		        // Setting Dialog Message
		        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
		        						"Detail : " + value_detail + "\n" +
		        						"Value : " + sum1 + "\n" +
		        						"Date : " + dateStr);
		        // Setting Positive "Yes" Button
		        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog1,int which) {
		       
	
		            	income.setINCATEGORYID(value_spinner);
		            	income.setINCATYPE(value_spinner2);
		            	income.setINCACATEGORY(value_spinner3);
		            	income.setINCANAME(value_spinner4);
		            	income.setINDATE(dateStr);
		            	income.setINDETAIL(value_detail);
		            	income.setINVALUE(value_value);
		            	datasource.updateIncome(income);
		            	ShowSumIncome();
		            	ShowAllIncome();
		            	dialog.cancel();
		            }
		        });
					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
	            dialog.cancel();
	            }
	        });
					
	        // Showing Alert Message
	        alertDialog.show();
				}
	            }
		});
		Button button_cancel = (Button) dialog.findViewById(R.id.cancel_button);
		button_cancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});
dialog.show();
}
	// ///////////////////////////////////////////// Class addNewIncome
	public void addNewIncome() {
		
		final Dialog dialog = new Dialog(IncomeActivity.this);
		dialog.setContentView(R.layout.add_income);
		dialog.setTitle("Add Income");
		dialog.setCancelable(true);
		
		
		final SQLiteHelper myDb = new SQLiteHelper(this);
		
		final Cursor myData = myDb.SelectAllData();   
		startManagingCursor(myData);
		
        // spinner dialog income
        final Spinner spin = (Spinner)dialog.findViewById(R.id.spinner1);  
        
        SimpleCursorAdapter adapter;
        adapter = new SimpleCursorAdapter(IncomeActivity.this, R.layout.income_column, myData
        		,new String[] {"cacategory","caname","caid","catype"}
				,new int[] {R.id.ColCate, R.id.ColName}); 
        
        spin.setAdapter(adapter);
        
        spin.setOnItemSelectedListener(new OnItemSelectedListener() {

			public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
					int position, long id) {
				// TODO Auto-generated method stub
				String ItemID = myData.getString(myData.getColumnIndex("caname"));
        	    Toast.makeText(IncomeActivity.this,
        	    		"Your Selected : " + ItemID,
        	    			Toast.LENGTH_SHORT).show();	
			}

			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
	      	    Toast.makeText(IncomeActivity.this,
        	    		"Your Selected : -",
        	    			Toast.LENGTH_SHORT).show();	
			}
        });

        ////////////////////////////////////////////////////Add button Dialog Income			
        		Button button_save = (Button) dialog.findViewById(R.id.save_button);
        		button_save.setOnClickListener(new OnClickListener() {
        			@SuppressWarnings("deprecation")
					public void onClick(View v) {
        				
        		        
        				////////////////////// spinner category
        				//Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
        				final String ItemID = myData.getString(myData.getColumnIndex("caid"));
        				final String ItemID2 = myData.getString(myData.getColumnIndex("catype"));
        				final String ItemID3 = myData.getString(myData.getColumnIndex("cacategory"));
        				final String ItemID4 = myData.getString(myData.getColumnIndex("caname"));
        				/////////////////// textbox ช่องรายละเอียด
        				EditText detail = (EditText) dialog.findViewById(R.id.editTextDetail);

        				/////////////////// textbox ช่องจำนวนเงิน
        				final EditText value = (EditText) dialog.findViewById(R.id.editTextValue);

        				/////////////////// DatePicker
        				DatePicker date = (DatePicker) dialog.findViewById(R.id.dateIncome);
        				final Integer dateYear = date.getYear();
        				final Integer dateMonth = date.getMonth();
        				final Integer dateDate = date.getDayOfMonth();
        				final Integer dateMonth1 = dateMonth + 1;
        				
        				final String value_detail = detail.getText().toString();
        				final int value_spinner = Integer.parseInt(ItemID.toString());
        				final String value_spinner2 = ItemID2.toString();
        				final String value_spinner3 = ItemID3.toString();
        				final String value_spinner4 = ItemID4.toString();
        				//String value_spinner = spinner.getItemAtPosition().toString();
        				
        				if (value.getText().toString().equals("")){
        					
          				  Toast.makeText(IncomeActivity.this,
          	        	    		"Please enter number in value's space",
          	        	    			Toast.LENGTH_LONG).show();	
        				}
        				
          					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 
        					///////////////////////////////////////////////////////////// If month Date < 10
        				
        				else if(dateDate < 10 && dateMonth1 < 10 )
          				  {
        					final long value_value = Long.parseLong(value.getText().toString());
        					String dateDate1 = "0"+dateDate;
        					String dateMonth2 = "0"+dateMonth1;
        					StringBuilder sb = new StringBuilder();
        					sb.append(dateYear.toString()).append("-").append(dateMonth2.toString()).append("-").append(dateDate1.toString()).append("");
        					final String dateStr = sb.toString();
        					
        					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
        			   		String sum1 = formatter.format(value_value);
        					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
        			        // Setting Dialog Title
        			        alertDialog.setTitle("Confirm");
        			        // Setting Dialog Message
        			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
        			        						"Detail : " + value_detail + "\n" +
        			        						"Value : " + sum1 + "\n" +
        			        						"Date : " + dateStr);
        			        // Setting Positive "Yes" Button
        			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
        			            public void onClick(DialogInterface dialog1,int which) {
        			       
        			            	@SuppressWarnings("unchecked")
        			        ArrayAdapter<Income> adapter = (ArrayAdapter<Income>) getListAdapter();
        			        Income income = new Income();
        			        income.setINCATEGORYID(value_spinner);
        			        income.setINCATYPE(value_spinner2);
          					income.setINCACATEGORY(value_spinner3);
          					income.setINCANAME(value_spinner4);
          					income.setINDATE(dateStr);
          					income.setINDETAIL(value_detail);
          					income.setINVALUE(value_value);
          					income = datasource.insertIncome(income);
          					adapter.add(income);
          					ShowSumIncome();
          					ShowAllIncome();
          					 dialog.cancel();
        			            }
        			        });
          					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
          						public void onClick(DialogInterface dialog, int which) {
  			            dialog.cancel();
  			            }
  			        });
  			 

  			        // Showing Alert Message
  			        alertDialog.show();
          			}
          		
        				
        				
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 
        					///////////////////////////////////////////////////////////// If date< 10

        				else if(dateDate < 10)
        				{
        					final long value_value = Long.parseLong(value.getText().toString());
        					String dateDate1 = "0"+dateDate;
        					StringBuilder sb = new StringBuilder();
        					sb.append(dateYear.toString()).append("-").append(dateMonth1.toString()).append("-").append(dateDate1.toString()).append("");
        					final String dateStr = sb.toString();
        					
        					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
        			   		String sum1 = formatter.format(value_value);
        					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
        			        // Setting Dialog Title
        			        alertDialog.setTitle("Confirm");
        			        // Setting Dialog Message
        			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
        			        						"Detail : " + value_detail + "\n" +
        			        						"Value : " + sum1 + "\n" +
        			        						"Date : " + dateStr);
        			        // Setting Positive "Yes" Button
        			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
        			            public void onClick(DialogInterface dialog1,int which) {
        			       
        			            	@SuppressWarnings("unchecked")
        			        ArrayAdapter<Income> adapter = (ArrayAdapter<Income>) getListAdapter();
        			        Income income = new Income();
        			        income.setINCATEGORYID(value_spinner);
        			        income.setINCATYPE(value_spinner2);
          					income.setINCACATEGORY(value_spinner3);
          					income.setINCANAME(value_spinner4);
          					income.setINDATE(dateStr);
          					income.setINDETAIL(value_detail);
          					income.setINVALUE(value_value);
          					income = datasource.insertIncome(income);
          					adapter.add(income);
          					ShowSumIncome();
          					ShowAllIncome();
          					 dialog.cancel();
        			            }
        			        });
          					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
          						public void onClick(DialogInterface dialog, int which) {
  			            dialog.cancel();
  			            }
  			        });
  			 

  			        // Showing Alert Message
  			        alertDialog.show();
          			}
          		
        				
        				
        				
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 			
        					///////////////////////////////////////////////////////////// 
        					///////////////////////////////////////////////////////////// If month < 10

        				else if(dateMonth1 < 10 )
        				{
        					final long value_value = Long.parseLong(value.getText().toString());
        					String dateMonth2 = "0"+dateMonth1;
        					StringBuilder sb = new StringBuilder();
        					sb.append(dateYear.toString()).append("-").append(dateMonth2.toString()).append("-").append(dateDate.toString()).append("");
        					final String dateStr = sb.toString();
        					
        			   		NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
        			   		String sum1 = formatter.format(value_value);
        					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
        			        // Setting Dialog Title
        			        alertDialog.setTitle("Confirm");
        			        // Setting Dialog Message
        			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
        			        						"Detail : " + value_detail + "\n" +
        			        						"Value : " + sum1 + "\n" +
        			        						"Date : " + dateStr);
        			        // Setting Positive "Yes" Button
        			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
        			            public void onClick(DialogInterface dialog1,int which) {
        			       
        			            	@SuppressWarnings("unchecked")
        			        ArrayAdapter<Income> adapter = (ArrayAdapter<Income>) getListAdapter();
        			        Income income = new Income();
        			        income.setINCATEGORYID(value_spinner);
        			        income.setINCATYPE(value_spinner2);
          					income.setINCACATEGORY(value_spinner3);
          					income.setINCANAME(value_spinner4);
          					income.setINDATE(dateStr);
          					income.setINDETAIL(value_detail);
          					income.setINVALUE(value_value);
          					income = datasource.insertIncome(income);
          					adapter.add(income);
          					ShowSumIncome();
          					ShowAllIncome();
          					 dialog.cancel();
        			            }
        			        });
          					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
          						public void onClick(DialogInterface dialog, int which) {
  			            dialog.cancel();
  			            }
  			        });
  			 

  			        // Showing Alert Message
  			        alertDialog.show();
          			}
          		
        				

        				
        				///////////////////////////////////////////////////////////// 			
        				///////////////////////////////////////////////////////////// 			
        				///////////////////////////////////////////////////////////// 			
        				///////////////////////////////////////////////////////////// 			
        				///////////////////////////////////////////////////////////// 
        				///////////////////////////////////////////////////////////// If month date > 10
        				
        				
        				else if(dateDate >= 10 && dateMonth1 >= 10){
        				final long value_value = Long.parseLong(value.getText().toString());
        				StringBuilder sb = new StringBuilder();
        				sb.append(dateYear.toString()).append("-")
        				.append(dateMonth1.toString()).append("-")
        				.append(dateDate.toString()).append("");
        				final String dateStr = sb.toString();
    					
        				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
    			   		String sum1 = formatter.format(value_value);
    					AlertDialog.Builder alertDialog = new AlertDialog.Builder(IncomeActivity.this);
    			        // Setting Dialog Title
    			        alertDialog.setTitle("Confirm");
    			        // Setting Dialog Message
    			        alertDialog.setMessage(	"Category : " + ItemID4 + "\n" +
    			        						"Detail : " + value_detail + "\n" +
    			        						"Value : " + sum1 + "\n" +
    			        						"Date : " + dateStr);
    			        // Setting Positive "Yes" Button
    			        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
    			            public void onClick(DialogInterface dialog1,int which) {
    			       
    			            	@SuppressWarnings("unchecked")
    			        ArrayAdapter<Income> adapter = (ArrayAdapter<Income>) getListAdapter();
    			        Income income = new Income();
    			        income.setINCATEGORYID(value_spinner);
    			        income.setINCATYPE(value_spinner2);
      					income.setINCACATEGORY(value_spinner3);
      					income.setINCANAME(value_spinner4);
      					income.setINDATE(dateStr);
      					income.setINDETAIL(value_detail);
      					income.setINVALUE(value_value);
      					income = datasource.insertIncome(income);
      					adapter.add(income);
      					ShowSumIncome();
      					ShowAllIncome();
      					 dialog.cancel();
    			            }
    			        });
      					alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
      						public void onClick(DialogInterface dialog, int which) {
			            dialog.cancel();
			            }
			        });
			 

			        // Showing Alert Message
			        alertDialog.show();
      			}
        				
        	            }
        		});
        		Button button_cancel = (Button) dialog.findViewById(R.id.cancel_button);
        		button_cancel.setOnClickListener(new OnClickListener() {
        			public void onClick(View v) {
        				dialog.cancel();
        			}
        		});
		dialog.show();
	}
	@Override
	protected void onResume() {
		datasource.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
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
