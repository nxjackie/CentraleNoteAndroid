package daron.centralenoteandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class AddPersonActivity extends Activity {

	private Button ReturnHomeButton = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_person);
		
		ReturnHomeButton = (Button)findViewById(R.id.button_return_home_from_person);
	    ReturnHomeButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    		Intent i = new Intent(AddPersonActivity.this, DebtsTableActivity.class);
	    		startActivity(i);
	        }
	      });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_person, menu);
		return true;
	}

}
