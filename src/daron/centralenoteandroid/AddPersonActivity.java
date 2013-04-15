package daron.centralenoteandroid;

import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import daron.centralenoteandroid.JsonRequests.GetStringFromUrl;

public class AddPersonActivity extends Activity {

	private Button ReturnHomeButton = null;
	private Button AddNewNameButton = null;
	
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
	    
	    AddNewNameButton = (Button)findViewById(R.id.add_new_user);
	    AddNewNameButton.setOnClickListener(new View.OnClickListener(){
	    	 @Override
		        public void onClick(View v) {
	    		 
	    		 EditText tonEdit = (EditText)findViewById(R.id.name_new_user);
	    		 String name_new_user = tonEdit.getText().toString();
	    			
	    		 
	    		 String answer = "0";
		    		 try {
						answer = new GetStringFromUrl().execute("http://centralenote.campus.ecp.fr/api/add_user.php?n=" + name_new_user).get();
												
					} catch (InterruptedException e) {
						// Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// Auto-generated catch block
						e.printStackTrace();
					}
		    		 
			    	 if(Integer.parseInt(answer) == 1){
			    		 Toast.makeText(getApplicationContext(), getString(R.string.nom_ajoute), Toast.LENGTH_LONG).show();
			    	 }
			    	 else{
			    		 Toast.makeText(getApplicationContext(), getString(R.string.nom_non_ajoute), Toast.LENGTH_LONG).show();
			    	 }
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
