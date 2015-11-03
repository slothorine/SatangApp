package project.savesatang;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class HowtoActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_howto);
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
