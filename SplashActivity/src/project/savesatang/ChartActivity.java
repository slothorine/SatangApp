package project.savesatang;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.view.View;

public class ChartActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chart);
	}
    public void lineGraphHandler (View view)
    {
    	LineGraph line = new LineGraph();
    	Intent lineIntent = line.getIntent(this);
        startActivity(lineIntent);
    }
    
    public void barGraphHandler (View view)
    {
    	BarGraph bar = new BarGraph();
    	Intent lineIntent = bar.getIntent(this);
        startActivity(lineIntent);
    }
    
    public void pieGraphHandler (View view)
    {
    	PieGraph pie = new PieGraph();
    	Intent lineIntent = pie.getIntent(this);
        startActivity(lineIntent);
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
