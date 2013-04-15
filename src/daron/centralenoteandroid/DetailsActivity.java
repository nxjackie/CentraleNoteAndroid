package daron.centralenoteandroid;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import android.widget.Toast;
import daron.centralenoteandroid.JsonRequests.GetStringFromUrl;
import daron.centralenoteandroid.JsonRequests.GetTransactionTable;
import daron.centralenoteandroid.Model.Transaction;



public class DetailsActivity extends Activity {
	
	private Button BackToMain = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		
		
		BackToMain = (Button)findViewById(R.id.back_to_main);

	    BackToMain.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	    		Intent i = new Intent(DetailsActivity.this, DebtsTableActivity.class);
	    		startActivity(i);
	        }
	      });
		
		
		Intent intent = getIntent();
		String id = intent.getStringExtra("id");
		
		try {
			
			String name = new GetStringFromUrl().execute("http://centralenote.campus.ecp.fr/api/name.php?n=" + id).get();
			
			TextView text = (TextView) findViewById(R.id.detail_name);
			text.setText(name);
			
			
			List<Transaction> transactions = new GetTransactionTable().execute("http://centralenote.campus.ecp.fr/api/details.php?n="+id).get();
			
			// Fill Table
			TableLayout tl = (TableLayout)findViewById(R.id.tableLayoutDetails);
			for (Transaction transaction : transactions) {
				TableRow tr = new TableRow(this);
				
				TextView dateTransaction = new TextView(this);
				dateTransaction.setText(transaction.getDate());
				
				TextView emitterTransaction = new TextView(this);
				emitterTransaction.setText(transaction.getEmitter());
				
				TextView receiverTransaction = new TextView(this);
				receiverTransaction.setText(transaction.getReceiver());
				
				TextView amountTransaction = new TextView(this);
				amountTransaction.setText(transaction.getAmount());
				
				TextView commentTransaction = new TextView(this);
				commentTransaction.setText(transaction.getComment());
				
				tr.addView(dateTransaction);
				tr.addView(emitterTransaction);
				tr.addView(receiverTransaction);
				tr.addView(amountTransaction);
				tr.addView(commentTransaction);
				
				tl.addView(tr);
				
				final Transaction transaction2 = transaction;
				
				tr.setOnClickListener(new View.OnClickListener() {
			        @Override
			        public void onClick(View v) {
			        	
						
			        	Toast.makeText(getApplicationContext(), "Date : " + transaction2.getDate() + "\n Emetteur : " + transaction2.getEmitter() + "\n Beneficiaire : " + transaction2.getReceiver() + "\n Montant : " + transaction2.getAmount() + "\n Commentaire : " + transaction2.getComment() + "\n Ip : " + transaction2.getIp(), Toast.LENGTH_LONG).show();
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
		getMenuInflater().inflate(R.menu.details, menu);
		return true;
	}

}
