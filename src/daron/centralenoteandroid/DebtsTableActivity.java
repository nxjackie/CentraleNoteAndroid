package daron.centralenoteandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class DebtsTableActivity extends Activity {
	
	private Button AddPersonButton = null;
	private Button AddTransactionButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debts_table);
		
		// Fill Table
		TableLayout tl=(TableLayout)findViewById(R.id.tableLayoutMain);    
		TableRow tr1 = new TableRow(this);
		TextView textview = new TextView(this);
		textview.setText("Coucou");
		tr1.addView(textview);
		tl.addView(tr1);
		
		AddPersonButton = (Button)findViewById(R.id.button_add_person);
		AddTransactionButton = (Button)findViewById(R.id.button_add_transaction);
		
	    AddPersonButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    		Intent i = new Intent(DebtsTableActivity.this, AddPersonActivity.class);
	    		startActivity(i);
	        }
	      });
	    
	    AddTransactionButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    		Intent i = new Intent(DebtsTableActivity.this, AddTransactionActivity.class);
	    		startActivity(i);
	        }
	      });
		
		
		
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.debts_table, menu);
		return true;
	}

}