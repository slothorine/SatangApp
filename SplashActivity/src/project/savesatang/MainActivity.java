package project.savesatang;

import android.net.Uri;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Color;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 
		ShowAll();
		
		
		final Button btn1 = (Button) findViewById(R.id.income_button);

		btn1.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent newActivity = new Intent(MainActivity.this,
						IncomeActivity.class);
				startActivity(newActivity);
				// TODO Auto-generated method stub

				ShowAll();
			}
		});
		final Button btn2 = (Button) findViewById(R.id.expense_button);

		btn2.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent newActivity = new Intent(MainActivity.this,
						ExpenseActivity.class);
				startActivity(newActivity);
				// TODO Auto-generated method stub

			
				ShowAll();
				
			}
		});
		
		final Button btnhowto = (Button) findViewById(R.id.topex);
		
		btnhowto.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				Intent newActivity = new Intent(MainActivity.this,
						TopexActivity.class);
				startActivity(newActivity);
				// TODO Auto-generated method stub

			
				ShowAll();
		
		}
	});
		final Button btnchart = (Button) findViewById(R.id.chart_button);

		btnchart.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent newActivity = new Intent(MainActivity.this,
						ChartActivity.class);
				startActivity(newActivity);
				// TODO Auto-generated method stub

			
				ShowAll();
			}
		});
	}
		//////////////////////////////////////////Remain
		public void ShowAll() {

			final SQLiteHelper myDb = new SQLiteHelper(this);
			
			long Income = myDb.SumIncomeDayMain(); 
			long NeEx = myDb.SumExpenseNeDayMain();
			long UnEx = myDb.SumExpenseUnDayMain();
			long OtEx= myDb.SumExpenseOtDayMain();
		
			TextView textsum = (TextView)findViewById(R.id.sumIncome);  
			TextView textne = (TextView)findViewById(R.id.sumExpenseNe);  
			TextView textun = (TextView)findViewById(R.id.sumExpenseUn);  
			TextView textot = (TextView)findViewById(R.id.sumExpenseOt);  
			TextView textremain = (TextView)findViewById(R.id.Remain);  
			
			String sum = String.valueOf(Income);
			String ne = String.valueOf(NeEx);
			String un = String.valueOf(UnEx);
			String ot = String.valueOf(OtEx);
		
			
			////////////////////////////////////// Income
			if(sum == null){
				Income = 0;
				textsum.setText("  " + Income);
				textsum.setTextColor(Color.parseColor("#000000"));
			}
			else if(sum != null){
				if(Income == 0){
				textsum.setText("  " + Income);
				textsum.setTextColor(Color.parseColor("#000000"));
				}
				else if(Income > 0){
				NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
				String Income1 = formatter.format(Income);
				textsum.setText("  " + Income1);
				textsum.setTextColor(Color.parseColor("#006400"));
				}
			} 
			
			/////////////////////////////////// Necessary Expense
			if(ne == null){
				NeEx = 0; 
				textne.setText("  " + NeEx);
				textne.setTextColor(Color.parseColor("#000000"));
			}
			else if(ne != null){
				if(NeEx == 0){
					textne.setText("  " + NeEx);
					textne.setTextColor(Color.parseColor("#000000"));
				}
				else if(NeEx > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String NeEx1 = formatter.format(NeEx);
					textne.setText("  " + NeEx1);
					textne.setTextColor(Color.parseColor("#FF8C00"));
				}
			}
			
			/////////////////////////////////// Unnecessary Expense
			if(un == null){
				UnEx = 0;
				textun.setText("  " + UnEx);
				textun.setTextColor(Color.parseColor("#000000"));
			}
			else if(un != null){
				if (UnEx == 0){
					textun.setText("  " + UnEx);
					textun.setTextColor(Color.parseColor("#000000"));
				}
				else if(UnEx > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String UnEx1 = formatter.format(UnEx);
					textun.setText("  " + UnEx1);
					textun.setTextColor(Color.parseColor("#FF0000"));
				}
			} 
		
			////////////////////////////////// Other Expense
			if(ot == null){
				OtEx = 0;
				textot.setText("  " + OtEx);
				textot.setTextColor(Color.parseColor("#000000"));	
			}
			else if(ot != null){
				if(OtEx == 0){
					textot.setText("  " + OtEx);
					textot.setTextColor(Color.parseColor("#000000"));
				}
				else if(OtEx > 0){
					NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
					String OtEx1 = formatter.format(OtEx);
					textot.setText("  " + OtEx1);
					textot.setTextColor(Color.parseColor("#555555"));
				}
			} 
			
		/*	textsum.setText("Total Income = " + Income);
			textne.setText("Total Income = " + NeEx);
			textun.setText("Total Income = " + UnEx);
			textot.setText("Total Income = " + OtEx); */
			
			
			//////////////////////////////////////// Remain
			long remain = Income - (NeEx + UnEx + OtEx);	
			if (remain > 0){
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String summary = formatter.format(remain);
					//	String summary = String.valueOf(remain);
						textremain.setText("  " + summary);
						textremain.setTextColor(Color.parseColor("#006400"));
				}
				else if(remain < 0){
					//String summary = String.valueOf(remain);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String summary = formatter.format(remain);
						textremain.setText("  " + summary);
						textremain.setTextColor(Color.parseColor("#FF0000"));
				}
				else if(remain == 0){
					//	String summary = String.valueOf(remain);
						NumberFormat formatter = new DecimalFormat("###,###,###,###,###,###");
						String summary = formatter.format(remain);
						textremain.setText("  " + summary);
						textremain.setTextColor(Color.parseColor("#000000"));
				}	
			}
		
		//	String sum = String.valueOf(myData);
		//	String exp = String.valueOf(myData2);
			
		/*	if(sum == null && exp == null){ 
			   textview.setText("Remain = " + 0);
			   textview.setTextColor(Color.parseColor("#000000"));
			}
			else if(sum != null && exp == null){
				long exp1 = 0;
				long remain = myData - exp1;
				String summary = String.valueOf(remain);
			    textview.setText("Remain = " + summary);
			    textview.setTextColor(Color.parseColor("#32CD32"));
			}
			else if(sum == null & exp != null){
				long sum1 = 0;
				long remain = sum1 - myData2;
				String summary = String.valueOf(remain);
		    	textview.setText("Remain = " + summary);
		    	textview.setTextColor(Color.parseColor("#FF0000"));
			}
			
			} 

		///////////////////////////////////////////////////Income
	/*      public void ShowSumIncome() {

	  		final SQLiteHelper myDb = new SQLiteHelper(this);
	  		
	  		long myData = myDb.SumIncome();   
	  		
	  		String sum = String.valueOf(myData);
	  		
	  		if(sum == null){
	  		TextView textview = (TextView)findViewById(R.id.sumIncome);  
	        textview.setText("Total Income = " + 0);}
	  		
	  		else if(sum != null){
	  			TextView textview = (TextView)findViewById(R.id.sumIncome);  
		        textview.setText("Total Income = " + sum);}
	  		} */
	      ////////////////////////////////////////////////////Expense
	/*      public void ShowSumExpense() {

	  		final SQLiteHelper myDb = new SQLiteHelper(this);
	  		
	  		long myData = myDb.SumExpense();   
	  		
	  		String exp = String.valueOf(myData);
	  		
	  		if(exp == null){
	  		TextView textview = (TextView)findViewById(R.id.sumExpense);  
	        textview.setText("Total Expense = " + 0);}
	  		
	  		else if(exp != null){
	  			TextView textview = (TextView)findViewById(R.id.sumExpense);  
		        textview.setText("Total Expense = " + exp);}
	      } */
	/* @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	} */
		
		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
			 super.onCreateOptionsMenu(menu);
			MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.activity_main, menu);
		/*	 MenuItem menu0 = menu.add(0, 0, 0, "Action Item 0");
			 {

			  menu0.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
			 }
			 menu.add(0, 1, 1, "Action Item 1");
			 menu.add(0, 2, 2, "Action Item 2");
			 menu.add(0, 3, 3, "Action Item 3");
			 menu.add(0, 4, 4, "Action Item 4");*/

	        return true;
	    }
		private void CopyAssetsbrochure() {
	        AssetManager assetManager = getAssets();
	        String[] files = null;
	        try 
	        {
	            files = assetManager.list("");
	        } 
	        catch (IOException e)
	        {
	            Log.e("tag", e.getMessage());
	        }
	        for(int i=0; i<files.length; i++)
	        {
	            String fStr = files[i];
	            if(fStr.equalsIgnoreCase("Satangguide.pdf"))
	            {
	                InputStream in = null;
	                OutputStream out = null;
	                try 
	                {
	                  in = assetManager.open(files[i]);
	                  out = new FileOutputStream("/sdcard/" + files[i]);
	                  copyFile(in, out);
	                  in.close();
	                  in = null;
	                  out.flush();
	                  out.close();
	                  out = null;
	                  break;
	                } 
	                catch(Exception e)
	                {
	                    Log.e("tag", e.getMessage());
	                } 
	            }
	        }
	    }

	 private void copyFile(InputStream in, OutputStream out) throws IOException {
	        byte[] buffer = new byte[1024];
	        int read;
	        while((read = in.read(buffer)) != -1){
	          out.write(buffer, 0, read);
	        }
	    }
		 @Override
		    public boolean onOptionsItemSelected(MenuItem item) {
			 if(item.getItemId() == R.id.userguide){
				 File fileBrochure = new File("/sdcard/Satangguide.pdf");
				    if (!fileBrochure.exists())
				    {
				         CopyAssetsbrochure();
				    } 

				    /** PDF reader code */
				    File file = new File("/sdcard/Satangguide.pdf");        

				    	Intent intent = new Intent(Intent.ACTION_VIEW);
				    	intent.setDataAndType(Uri.fromFile(file),"application/pdf");
				    	intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				    
				    	try 
				    	{
				        getApplicationContext().startActivity(intent);
				    	} 
				    	catch (ActivityNotFoundException e) 
				    	{
				         Toast.makeText(MainActivity.this, "NO Pdf Viewer", Toast.LENGTH_SHORT).show();
				    	}
				   /*Intent viewIntent = new Intent("android.intent.action.VIEW", 
						 Uri.parse("https://docs.google.com/file/d/0B63uf2S7XkLUcVhLOFhKdjMzTkU/edit"));
				 startActivity(viewIntent); */
				    
			 }
			 if(item.getItemId() == R.id.aboutme){
					Intent newActivity = new Intent(MainActivity.this,
							About.class);
					startActivity(newActivity);
			 }
		        if(item.getItemId() == R.id.tips){
		       
		        	Intent newActivity = new Intent(MainActivity.this,
							TipsActivity.class);
					startActivity(newActivity);
					// TODO Auto-generated method stub
		        }
		        if(item.getItemId() == R.id.searchinc){
				       
		        	Intent newActivity = new Intent(MainActivity.this,
		        			 SearchIncomeActivity.class);
					startActivity(newActivity);
					// TODO Auto-generated method stub
		        }
		       if(item.getItemId() == R.id.searchex){
				       
		        	Intent newActivity = new Intent(MainActivity.this,
		        			 SearchExpenseActivity.class);
					startActivity(newActivity);
					// TODO Auto-generated method stub
		        }
		        return super.onOptionsItemSelected(item);
		    }
		 @Override
			protected void onResume() {
			 ShowAll();
				super.onResume();
			}
}
