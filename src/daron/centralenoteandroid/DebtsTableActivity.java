package daron.centralenoteandroid;

import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;
import daron.centralenoteandroid.JsonRequests.GetDebtsTable;
import daron.centralenoteandroid.Model.User;

public class DebtsTableActivity extends Activity {

	private Button AddPersonButton = null;
	private Button AddTransactionButton = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debts_table);

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

	    try {
			List<User> users = new GetDebtsTable(DebtsTableActivity.this).execute("http://centralenote.campus.ecp.fr/api/user.php").get();
			
			// Fill Table
			TableLayout tl = (TableLayout)findViewById(R.id.tableLayoutMain);
			for (User user : users) {
				TableRow tr = new TableRow(this);
				TextView nameTextView = new TextView(this);
				nameTextView.setText(user.getName());
				nameTextView.setTextSize(20);
				TextView debtTextView = new TextView(this);
				debtTextView.setText(user.getDebt());
				debtTextView.setTextSize(20);
				tr.addView(nameTextView);
				tr.addView(debtTextView);
				tl.addView(tr);
				final String idu = user.getId();
				
				tr.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View v) { 
			    		Intent i = new Intent(DebtsTableActivity.this, DetailsActivity.class);
			    		i.putExtra("id", idu);
			    		startActivity(i);
			        }
			      });
				
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.debts_table, menu);
		return true;
	}

}
