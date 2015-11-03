package project.savesatang;


import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.app.Activity;

public class TopexActivity extends Activity {
	ArrayList<HashMap<String, String>> TopList;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top);

		SQLiteHelper myDb = new SQLiteHelper(this);
		TopList= myDb.Selecttopex();   
	
		 ListView lisView1 = (ListView)findViewById(R.id.listtop); 
		 SimpleAdapter sAdap;
	        sAdap = new SimpleAdapter(TopexActivity.this, TopList, R.layout.topex_column,
	                new String[] {"excaname", "exvalue"}, new int[] {R.id.Colexca, R.id.Colvalue});  
	        lisView1.setAdapter(sAdap);
	
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
	
