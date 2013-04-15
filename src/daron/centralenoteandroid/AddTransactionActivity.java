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
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.util.Log;

import daron.centralenoteandroid.JsonRequests.AddTransaction;
import daron.centralenoteandroid.JsonRequests.GetDebtsTable;
import daron.centralenoteandroid.Model.User;

public class AddTransactionActivity extends Activity {

	private EditText CommentEditText = null;
	private Button ReturnHomeButton = null;
	private Button AddProfiteurButton = null;
	private Button AddTransactionButton = null;
	private List<TableRow> rowList = new ArrayList<TableRow>();
	private List<User> users;
	
	private class TransactionOnClickListener implements OnClickListener {
		private List<TableRow> _rowList = new ArrayList<TableRow>();
		private User _host;
		private String _comment;
		
		public TransactionOnClickListener(User host, String comment, List<TableRow> rowList) {
			_host = host;
			_comment = comment;
			_rowList = rowList;
		}
		
		@Override
		public void onClick(View v) {
			Log.v("hello_comment", _comment);
			List<User> profiteurs = new ArrayList<User>();
			List<String> debt = new ArrayList<String>();
			for (TableRow row : rowList) {
				Spinner spinner = (Spinner)row.getChildAt(0);
				EditText editText = (EditText)row.getChildAt(1);
				Log.v("hello", row.toString());
				profiteurs.add(findUserByUsername(spinner.getSelectedItem().toString()));
				debt.add(editText.getText().toString());
			}
			AddTransaction t = new AddTransaction(v.getContext(), _host, _comment, profiteurs, debt);
			t.execute("http://centralenote.campus.ecp.fr/api/add_transaction.php");
			Intent i = new Intent(AddTransactionActivity.this, DebtsTableActivity.class);
			startActivity(i); 
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		try {
			users = new GetDebtsTable(AddTransactionActivity.this).execute("http://centralenote.campus.ecp.fr/api/user.php").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setContentView(R.layout.activity_add_transaction);
		
		Spinner spinner = (Spinner)findViewById(R.id.spinner_host);
		hydrateUsersSpinner(spinner);
		
		CommentEditText = (EditText)findViewById(R.id.editText_comment);
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
				rowList.add(tr);
				TableLayoutProfiteur.addView(tr);
	        }
	      });
		
		AddTransactionButton = (Button)findViewById(R.id.button_add_transaction);
		AddTransactionButton.setOnClickListener(new TransactionOnClickListener(findUserByUsername(spinner.getSelectedItem().toString()), CommentEditText.getText().toString(), rowList));
		
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
		List<String> spinnerArray = new ArrayList<String>();
		for (User user : users) {
			spinnerArray.add(user.getName());
		}
	    ArrayAdapter spinnerArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, spinnerArray);
	    spinner.setAdapter(spinnerArrayAdapter);	

	}
	
	private User findUserByUsername(String name) {
		for (User user : users) {
			if (user.getName() == name)
				return user;
		}
		return null;
	}

}
