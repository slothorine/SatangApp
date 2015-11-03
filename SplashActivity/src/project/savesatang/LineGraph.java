package project.savesatang;


import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.SeriesSelection;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import project.savesatang.SQLiteHelper;

public class LineGraph extends Activity {
	
/*	public static SQLiteDatabase dbSqlite;


	public static XYMultipleSeriesDataset getDemoDataset(String title) {
		
		SQLiteHelper myDb = new SQLiteHelper(this);
	    Cursor cursor = myDb.sumIncome();
	    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	    TimeSeries series = new TimeSeries("Line1");
	    TimeSeries series2 = new TimeSeries(title);

	    cursor.moveToFirst(); 
	    while (!cursor.isAfterLast()) {
	        int date = cursor.getInt((Integer) cursor.getColumnIndexOrThrow("date"));

	        int weight = cursor.getInt((Integer) cursor.getColumnIndexOrThrow("weight"));
	        series2.add(weight, date);
	        cursor.moveToNext();
	                "Cursoren flyttet til neste element");
	    }
	            "There were no values in the cursor.");
	    cursor.close();

	    dataset.addSeries(series);

	    dataset.addSeries(series2);

	    return dataset;
	} */
	
	

	public Intent getIntent(Context context) {
		final SQLiteHelper dx = new SQLiteHelper(context);
		
		int in1 = (int) dx.SumIncome01();
		int in2 = (int) dx.SumIncome02();
		int in3 = (int) dx.SumIncome03();
		int in4 = (int) dx.SumIncome04();
		int in5 = (int) dx.SumIncome05();
		int in6 = (int) dx.SumIncome06();
		int in7 = (int) dx.SumIncome07();
		int in8 = (int) dx.SumIncome08();
		int in9 = (int) dx.SumIncome09();
		int in10 = (int) dx.SumIncome10();
		int in11 = (int) dx.SumIncome11();
		int in12 = (int) dx.SumIncome12();
		
		int ex1 = (int) dx.SumExpense01();
		int ex2 = (int) dx.SumExpense02();
		int ex3 = (int) dx.SumExpense03();
		int ex4 = (int) dx.SumExpense04();
		int ex5 = (int) dx.SumExpense05();
		int ex6 = (int) dx.SumExpense06();
		int ex7 = (int) dx.SumExpense07();
		int ex8 = (int) dx.SumExpense08();
		int ex9 = (int) dx.SumExpense09();
		int ex10 = (int) dx.SumExpense10();
		int ex11 = (int) dx.SumExpense11();
		int ex12 = (int) dx.SumExpense12();

	//	Calendar calendar = Calendar.getInstance();
	//	int year = calendar.get(Calendar.YEAR);
			int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }; 
	        int[] Income =  { in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12 };
	        int[] Expense =  { ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12};
	        TimeSeries series = new TimeSeries("Income"); 
	        TimeSeries series2 = new TimeSeries("Expense"); 
	        for(int i=0; i < x.length;i++){
	        	series.add(x[i], Income[i]);
	        	series2.add(x[i],Expense[i]);
	        }
		// Our first data
	//	String [] x = {"January","February","March","April","May","June","July","August","September","October","November","December"};
/*		int k = 0;
		int[] x = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }; // x values!
		int[] values =  { in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12 }; // y values!
		TimeSeries series = new TimeSeries("Income"); 
		/*for( int i = 0; i < x.length; i++)
		{
			series.add(x[k++], y[i]);
		}*/
	/*	for (int value : values) {
			series.add(x[k++], value);
		}
		// Our second data
	/*	int[] x2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 }; // x values!
		int[] y2 =  { ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ex10, ex11, ex12}; // y values!
		TimeSeries series2 = new TimeSeries("Expense"); 
		for( int i = 0; i < x2.length; i++)
		{
			series2.add(x2[i], y2[i]);
		}*/
		
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series);
		dataset.addSeries(series2);
		
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer(); // Holds a collection of XYSeriesRenderer and customizes the graph
		XYSeriesRenderer renderer = new XYSeriesRenderer(); // This will be used to customize line 1
		XYSeriesRenderer renderer2 = new XYSeriesRenderer(); // This will be used to customize line 2
		
		mRenderer.setChartTitle("Year Summary");
		mRenderer.setXTitle("Month");
		mRenderer.setYTitle("Value");
		mRenderer.setZoomButtonsVisible(true);
		mRenderer.setApplyBackgroundColor(true);
        mRenderer.setBackgroundColor(Color.BLACK);
        mRenderer.setChartTitleTextSize(20);
		// Customization time for line 1!
		renderer.setColor(Color.GREEN);
		renderer.setPointStyle(PointStyle.X);
		renderer.setFillPoints(true);
		renderer.setLineWidth(2);
        renderer.setDisplayChartValues(true);
		// Customization time for line 2!
		renderer2.setColor(Color.RED);
		renderer2.setPointStyle(PointStyle.X);
		renderer2.setFillPoints(true);
		renderer2.setLineWidth(2);
        renderer2.setDisplayChartValues(true);
        
        mRenderer.addSeriesRenderer(renderer);
		mRenderer.addSeriesRenderer(renderer2);
        
		Intent intent = ChartFactory.getLineChartIntent(context, dataset, mRenderer, "Line");
		return intent;
		
	}

}
