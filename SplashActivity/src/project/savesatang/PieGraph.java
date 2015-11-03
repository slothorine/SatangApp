package project.savesatang;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import android.os.Bundle;
import android.widget.Toast;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;

import project.savesatang.SQLiteHelper;

public class PieGraph extends Activity {
	
	
	public Intent getIntent(Context context) {
		final SQLiteHelper dx = new SQLiteHelper(context);
	
		int val1 = (int) dx.SumIncome();
		int val2 = (int) dx.SumExpenseNe();
		int val3 = (int) dx.SumExpenseUn();
		int val4 = (int) dx.SumExpenseOt();
		
		
		int[] values = {val1, val2, val3, val4}; 
		
		CategorySeries series = new CategorySeries("Pie Graph");
		int k = 0;
		String [] i = {"Income","Necessary Expense","Unnecessary Expense","Other Expense"};
		for (int value : values) {
		series.add(i[k++], value);
	}

		int[] colors = new int[] { Color.GREEN, Color.YELLOW, Color.RED, Color.LTGRAY};

		DefaultRenderer renderer = new DefaultRenderer();
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
		}
		renderer.setChartTitle("Summary");
		renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.BLACK);
		renderer.setChartTitleTextSize(20);
		renderer.setLabelsTextSize(15);
		renderer.setLabelsColor(Color.WHITE);
		renderer.setZoomButtonsVisible(true);

		Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Pie");
		return intent;
	}
}
