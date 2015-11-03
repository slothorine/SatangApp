package project.savesatang;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.widget.ArrayAdapter;
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

public class ExpenseActivity extends ListActivity { 
	final static String TAG = "SQLite";
	private ExpenseDataSource datasource;
	List<Expense> values2;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
		ShowRemain();
		// //////////////////////////////////////////////////////////////////////
		// ปุุ่ม Back
	/*	final Button btn1 = (Button) findViewById(R.id.back_button2);
		btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent newActivity = new Intent(ExpenseActivity.this,
						MainActivity.class);
				startActivity(newActivity);
				// TODO Auto-generated method stub

			}
		}); */
	//////////////////////////////////////////////////////////////////
		// ปุ่ม Add
		final Button add_expense = (Button) findViewById(R.id.add_button2);
		add_expense.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				addNewExpense(); // /////// เรียกใช้ class addNewIncome
				// TODO Auto-generated method stub
			}
		});
		datasource = new ExpenseDataSource(this);
		datasource.open();
		ShowAllExpense();
		ShowRemain();// // เรียใช้ class ShowAllIncome
		}
	// /////////////////////////////////////////////////// class ShowAllIncome
		public void ShowAllExpense() {
			values2 = datasource.getAllExpense();
			ArrayAdapter<Expense> adapter = new ArrayAdapter<Expense>(this,
					android.R.layout.simple_list_item_1, values2);
			setListAdapter(adapter);
		}
		
		public void ShowRemain() {

			final SQLiteHelper myDb = new SQLiteHelper(this);
			
			//long myData = myDb.SumIncome(); 
			long myData2 = myDb.SumExpenseNow();
		
	     
	       TextView textview = (TextView)findViewById(R.id.textRemain);  
	       
	     // long remain = myData - myData2;
	       
	      if(myData2 >= 0)
	      {
	      long summary = Long.valueOf(myData2);
	      NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
	      String sumex = formatter.format(summary);
	      textview.setText("Expense This Month = " + sumex);
	   //   textview.setTextColor(Color.parseColor("#32CD32"));
	      }
	  /*    else if(myData2 < 0) {
	    	  
	      String summary = String.valueOf(myData2);
    	  textview.setText("Remain = " + summary);
    	  textview.setTextColor(Color.parseColor("#FF0000"));
	    	  
	      } */
	      }

		@Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			showExpenseDetail(position); // View book by id
		}
		
		// ////////////////////////////////////////////////////////// Show Income
		// Detail
		public void showExpenseDetail(final int id) {
			@SuppressWarnings("unchecked")
			final ArrayAdapter<Expense> adapter = (ArrayAdapter<Expense>) getListAdapter();
			final Expense expense = (Expense) getListAdapter().getItem(id);
			
			final Dialog dialog = new Dialog(ExpenseActivity.this);
			dialog.setContentView(R.layout.detail_expense);
			dialog.setTitle("Expense Detail");
			dialog.setCancelable(true);
			
			TextView txt_category = (TextView) dialog.findViewById(R.id.textExpenseCategory);
			TextView txt_Detail = (TextView) dialog.findViewById(R.id.textDetail2);
			TextView txt_Value = (TextView) dialog.findViewById(R.id.textValue2);
			TextView txt_Date = (TextView) dialog.findViewById(R.id.textDate2);
			
			txt_category.setText("Category :\t" + "" + expense.getEXCANAME());
			txt_Detail.setText("Detail :\t" + "" + expense.getEXDETAIL());
			
			NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
	   		String value2 = formatter.format(expense.getEXVALUE());
			
	   		txt_Value.setText("Value :\t" + "" + value2);
			txt_Date.setText("Date :\t" + "" + expense.getEXDATE());
			
			//edit income
			Button button_edit = (Button) dialog.findViewById(R.id.Edit_button2);
			button_edit.setOnClickListener(new OnClickListener() {
					public void onClick(View v) {
						if (getListAdapter().getCount() > 0) {
							editExpense(expense);
							dialog.dismiss();
						}
					}
			});
			//delete income
			Button button_delete = (Button) dialog.findViewById(R.id.Delete_button2);
			button_delete.setOnClickListener(new OnClickListener() {
				
					public void onClick(View v) {
						
						if (getListAdapter().getCount() > 0) {
							datasource.deleteExpense(expense); // delete income
							adapter.remove(expense);
							dialog.dismiss();
							Toast.makeText(ExpenseActivity.this, "Delete data succeed.", Toast.LENGTH_LONG).show();
						}
						ShowRemain();
					}
			});
			// close dialog
			Button button_cancel = (Button) dialog.findViewById(R.id.Close_button2);
			button_cancel.setOnClickListener(new OnClickListener() { 
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			dialog.show();
		}
		// ////////////////////////////////////////////////////////// Edit Income
		public void editExpense(final Expense expense) {
			final Dialog dialog = new Dialog(ExpenseActivity.this);
			dialog.setContentView(R.layout.add_expense);
			dialog.setTitle("Edit Expense");
			dialog.setCancelable(true);
			
			final SQLiteHelper myDb = new SQLiteHelper(this);
	
			final Cursor myData = myDb.SelectAllData2();   
			startManagingCursor(myData);
			
	        // spinner dialog expense
	        final Spinner spin = (Spinner)dialog.findViewById(R.id.spinner2);  
	        
	        SimpleCursorAdapter adapter;
	        adapter = new SimpleCursorAdapter(ExpenseActivity.this, R.layout.expense_column, myData
	        		,new String[] {"cacategory","caname","caid","catype"}
    				,new int[] {R.id.ColCate2, R.id.ColName2}); 
	        
	        spin.setAdapter(adapter);
	   
	    	Integer selectsp = (int) expense.getEXCATEGORYID() - 3;
        	spin.setSelection(selectsp);
	        
	        spin.setOnItemSelectedListener(new OnItemSelectedListener() {
	        	public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
						int position, long id) {
					// TODO Auto-generated method stub
					String ItemID = myData.getString(myData.getColumnIndex("caname"));
	        	    Toast.makeText(ExpenseActivity.this,
	        	    		"Your Selected : " + ItemID,
	        	    			Toast.LENGTH_SHORT).show();	
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
		      	    Toast.makeText(ExpenseActivity.this,
	        	    		"Your Selected : -",
	        	    			Toast.LENGTH_SHORT).show();	
				}
	        });
			
			final EditText detail = (EditText) dialog.findViewById(R.id.editTextDetail2);
			final EditText value = (EditText) dialog.findViewById(R.id.editTextValue2);
			final DatePicker date = (DatePicker) dialog.findViewById(R.id.dateExpense2);
			String date1 = expense.getEXDATE();
			
			DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
			Date txtdate2 = null;
				try {
					txtdate2 = df2.parse(date1);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				@SuppressWarnings("deprecation")
				Integer dateMonth = txtdate2.getMonth();
				@SuppressWarnings("deprecation")
				Integer dateYear = txtdate2.getYear() + 1900;
				@SuppressWarnings("deprecation")
				Integer dateDay = txtdate2.getDate();
				
				date.init(dateYear, dateMonth, dateDay, null);

	        
		value.setText(String.valueOf(expense.getEXVALUE()));
		detail.setText(expense.getEXDETAIL());
		
		// setup button
		Button button_save = (Button) dialog.findViewById(R.id.save_button2);
		button_save.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {

				////////////////////// spinner category
				//Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
				final String ItemID = myData.getString(myData.getColumnIndex("caid"));
				final String ItemID2 = myData.getString(myData.getColumnIndex("catype"));
				final String ItemID3 = myData.getString(myData.getColumnIndex("cacategory"));
				final String ItemID4 = myData.getString(myData.getColumnIndex("caname"));
				/////////////////// textbox ช่องรายละเอียด
				EditText detail = (EditText) dialog.findViewById(R.id.editTextDetail2);

				/////////////////// textbox ช่องจำนวนเงิน
				EditText value = (EditText) dialog.findViewById(R.id.editTextValue2);
	
				/////////////////// DatePicker
				DatePicker date = (DatePicker) dialog.findViewById(R.id.dateExpense2);
				final Integer dateYear = date.getYear();
				final Integer dateMonth = date.getMonth();
				final Integer dateDate = date.getDayOfMonth();
				final Integer dateMonth1 = dateMonth + 1;
				
				final String value_detail = detail.getText().toString();
				final int value_spinner = Integer.parseInt(ItemID.toString());
				final String value_spinner2 = ItemID2.toString();
				final String value_spinner3 = ItemID3.toString();
				final String value_spinner4 = ItemID4.toString();
				
				if (value.getText().toString().equals("")){
					
    				  Toast.makeText(ExpenseActivity.this,
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
  					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
  			       
  			      		expense.setEXCATEGORYID(value_spinner);
  						expense.setEXCATYPE(value_spinner2);
  						expense.setEXCACATEGORY(value_spinner3);
  						expense.setEXCANAME(value_spinner4);
  						expense.setEXDETAIL(value_detail);
  						expense.setEXVALUE(value_value);
  						expense.setEXDATE(dateStr);
  						datasource.updateExpense(expense);
  						ShowAllExpense();
  						ShowRemain();
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
  					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
  			       
  			      		expense.setEXCATEGORYID(value_spinner);
  						expense.setEXCATYPE(value_spinner2);
  						expense.setEXCACATEGORY(value_spinner3);
  						expense.setEXCANAME(value_spinner4);
  						expense.setEXDETAIL(value_detail);
  						expense.setEXVALUE(value_value);
  						expense.setEXDATE(dateStr);
  						datasource.updateExpense(expense);
  						ShowAllExpense();
  						ShowRemain();
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
  					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
  			       
  			      		expense.setEXCATEGORYID(value_spinner);
  						expense.setEXCATYPE(value_spinner2);
  						expense.setEXCACATEGORY(value_spinner3);
  						expense.setEXCANAME(value_spinner4);
  						expense.setEXDETAIL(value_detail);
  						expense.setEXVALUE(value_value);
  						expense.setEXDATE(dateStr);
  						datasource.updateExpense(expense);
  						ShowAllExpense();
  						ShowRemain();
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
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
			       
			        		expense.setEXCATEGORYID(value_spinner);
							expense.setEXCATYPE(value_spinner2);
							expense.setEXCACATEGORY(value_spinner3);
							expense.setEXCANAME(value_spinner4);
							expense.setEXDETAIL(value_detail);
							expense.setEXVALUE(value_value);
							expense.setEXDATE(dateStr);
							datasource.updateExpense(expense);
							ShowAllExpense();
							ShowRemain();
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
  		Button button_cancel = (Button) dialog.findViewById(R.id.cancel_button2);
  		button_cancel.setOnClickListener(new OnClickListener() {
  			public void onClick(View v) {
  				dialog.cancel();
  			}
  		});
	dialog.show();
}
			
			
			// ///////////////////////////////////////////// Class addNewIncome
		public void addNewExpense() {
			
			final Dialog dialog = new Dialog(ExpenseActivity.this);
			dialog.setContentView(R.layout.add_expense);
			dialog.setTitle("Add Expense");
			dialog.setCancelable(true);
			
			final SQLiteHelper myDb = new SQLiteHelper(this);
			
			final Cursor myData = myDb.SelectAllData2();   
			startManagingCursor(myData);
			
	        // spinner dialog income
	        final Spinner spin = (Spinner)dialog.findViewById(R.id.spinner2);  
	        
	        SimpleCursorAdapter adapter;
	        adapter = new SimpleCursorAdapter(ExpenseActivity.this, R.layout.expense_column, myData
	        		,new String[] {"cacategory","caname","caid","catype"}
	        		,new int[] {R.id.ColCate2, R.id.ColName2}); 
	        
	        spin.setAdapter(adapter);
	        
	        spin.setOnItemSelectedListener(new OnItemSelectedListener() {

				public void onItemSelected(AdapterView<?> arg0, View selectedItemView,
						int position, long id) {
					// TODO Auto-generated method stub
					String ItemID = myData.getString(myData.getColumnIndex("caname"));
	        	    Toast.makeText(ExpenseActivity.this,
	        	    		"Your Selected : " + ItemID,
	        	    			Toast.LENGTH_SHORT).show();	
				}

				public void onNothingSelected(AdapterView<?> arg0) {
					// TODO Auto-generated method stub
		      	    Toast.makeText(ExpenseActivity.this,
	        	    		"Your Selected : -",
	        	    			Toast.LENGTH_SHORT).show();	
				}
	        });

	        ////////////////////////////////////////////////////Add button Dialog Income			
	        		Button button_save = (Button) dialog.findViewById(R.id.save_button2);
	        		button_save.setOnClickListener(new OnClickListener() {
	        			
	        			public void onClick(View v) {
	      
	        				////////////////////// spinner category
	        				//Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner1);
	        				final String ItemID = myData.getString(myData.getColumnIndex("caid"));
	        				final String ItemID2 = myData.getString(myData.getColumnIndex("catype"));
	        				final String ItemID3 = myData.getString(myData.getColumnIndex("cacategory"));
	        				final String ItemID4 = myData.getString(myData.getColumnIndex("caname"));
	        				/////////////////// textbox ช่องรายละเอียด
	        				EditText detail = (EditText) dialog.findViewById(R.id.editTextDetail2);

	        				/////////////////// textbox ช่องจำนวนเงิน
	        				EditText value = (EditText) dialog.findViewById(R.id.editTextValue2);
	        	
	        				/////////////////// DatePicker
	        				DatePicker date = (DatePicker) dialog.findViewById(R.id.dateExpense2);
	        				final Integer dateYear = date.getYear();
	        				final Integer dateMonth = date.getMonth();
	        				final Integer dateDate = date.getDayOfMonth();
	        				final Integer dateMonth1 = dateMonth + 1;
	        				
	        				final String value_detail = detail.getText().toString();
	        				final int value_spinner = Integer.parseInt(ItemID.toString());
	        				final String value_spinner2 = ItemID2.toString();
	        				final String value_spinner3 = ItemID3.toString();
	        				final String value_spinner4 = ItemID4.toString();
	        				
	        				if (value.getText().toString().equals("")){
	        					
	            				  Toast.makeText(ExpenseActivity.this,
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
	          					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
	    		        				ArrayAdapter<Expense> adapter = (ArrayAdapter<Expense>) getListAdapter();
	    		        				Expense expense = new Expense();
	    		        				expense.setEXCATEGORYID(value_spinner);
	    		        				expense.setEXCATYPE(value_spinner2);
	    		        				expense.setEXCACATEGORY(value_spinner3);
	    		        				expense.setEXCANAME(value_spinner4);
	    		        				expense.setEXDATE(dateStr);
	    		        				expense.setEXDETAIL(value_detail);
	    		        				expense.setEXVALUE(value_value);
	    		        				expense = datasource.insertExpense(expense);
	    		        				adapter.add(expense);
	    		        				ShowRemain();
	    		        				ShowAllExpense();
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
	          					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
	    		        				ArrayAdapter<Expense> adapter = (ArrayAdapter<Expense>) getListAdapter();
	    		        				Expense expense = new Expense();
	    		        				expense.setEXCATEGORYID(value_spinner);
	    		        				expense.setEXCATYPE(value_spinner2);
	    		        				expense.setEXCACATEGORY(value_spinner3);
	    		        				expense.setEXCANAME(value_spinner4);
	    		        				expense.setEXDATE(dateStr);
	    		        				expense.setEXDETAIL(value_detail);
	    		        				expense.setEXVALUE(value_value);
	    		        				expense = datasource.insertExpense(expense);
	    		        				adapter.add(expense);
	    		        				ShowRemain();
	    		        				ShowAllExpense();
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
	          					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
	    		        				ArrayAdapter<Expense> adapter = (ArrayAdapter<Expense>) getListAdapter();
	    		        				Expense expense = new Expense();
	    		        				expense.setEXCATEGORYID(value_spinner);
	    		        				expense.setEXCATYPE(value_spinner2);
	    		        				expense.setEXCACATEGORY(value_spinner3);
	    		        				expense.setEXCANAME(value_spinner4);
	    		        				expense.setEXDATE(dateStr);
	    		        				expense.setEXDETAIL(value_detail);
	    		        				expense.setEXVALUE(value_value);
	    		        				expense = datasource.insertExpense(expense);
	    		        				adapter.add(expense);
	    		        				ShowRemain();
	    		        				ShowAllExpense();
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
	      					AlertDialog.Builder alertDialog = new AlertDialog.Builder(ExpenseActivity.this);
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
			        				ArrayAdapter<Expense> adapter = (ArrayAdapter<Expense>) getListAdapter();
			        				Expense expense = new Expense();
			        				expense.setEXCATEGORYID(value_spinner);
			        				expense.setEXCATYPE(value_spinner2);
			        				expense.setEXCACATEGORY(value_spinner3);
			        				expense.setEXCANAME(value_spinner4);
			        				expense.setEXDATE(dateStr);
			        				expense.setEXDETAIL(value_detail);
			        				expense.setEXVALUE(value_value);
			        				expense = datasource.insertExpense(expense);
			        				adapter.add(expense);
			        				ShowRemain();
			        				ShowAllExpense();
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
	          		Button button_cancel = (Button) dialog.findViewById(R.id.cancel_button2);
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

