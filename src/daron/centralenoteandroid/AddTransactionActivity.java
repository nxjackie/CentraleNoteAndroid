package daron.centralenoteandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import daron.centralenoteandroid.JsonRequests.GetDebtsTable;
import daron.centralenoteandroid.Model.User;

public class AddTransactionActivity extends Activity {

	private Button ReturnHomeButton = null;
	private Button AddProfiteurButton = null;
	private	ScrollView ProfiteurScrollView = null;
	private List<Spinner> spinnerList = new ArrayList<Spinner>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner_host);
		hydrateUsersSpinner(spinner);
		
		

		AddProfiteurButton = (Button)findViewById(R.id.button_add_profiteur);
		AddProfiteurButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	TableLayout TableLayoutProfiteur = (TableLayout)findViewById(R.id.tableLayoutProfiteur);
	  
	        	TableRow tr = new TableRow(v.getContext());
				Spinner spinner = new Spinner(v.getContext());
				hydrateUsersSpinner(spinner);
				spinner.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 10f));
				tr.addView(spinner);
				EditText debt = new EditText(v.getContext());
				debt.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 10f));
				debt.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL);
				tr.addView(debt);
				Button delete = new Button(v.getContext());
				delete.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f));
				delete.setText("x");
				tr.addView(delete);
				TableLayoutProfiteur.addView(tr);
	        }
	      });
		
		ReturnHomeButton = (Button)findViewById(R.id.button_return_home_from_transaction);
	    ReturnHomeButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    		Intent i = new Intent(AddTransactionActivity.this, DebtsTableActivity.class);
	    		startActivity(i);
	        }
	      });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_transaction, menu);
		return true;
	}
	
	private void hydrateUsersSpinner(Spinner spinner) {

	}
	
	private String findIdByUsername(String name) {
		try {
			List<User> users = new GetDebtsTable(AddTransactionActivity.this).execute("http://centralenote.campus.ecp.fr/api/user.php").get();
			
			for (User user : users) {
				if (user.getName() == name)
					return user.getId();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "0";
	}

}
