package daron.centralenoteandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPersonActivity extends Activity {

	private Button ReturnHomeButton = null;
	private Button AddNewNameButton = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_person);
		
		EditText tonEdit = (EditText)findViewById(R.id.name_new_user);
		String name_new_user = tonEdit.getText().toString();
		
		
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
	    		 
	    		 
	    		 
		    		/*
		    		 * 
		    		 * Ici executer la page http://centralenote.campus.ecp.fr/api/add_user.php?n='nomdeluser'
		    		 * avec 'nomdeluser' ayant pour valeur name_new_user
		    		 *  
		    		 *  En suite faire un retour sous forme de message (bulle) en  fonction du contenu
		    		 *  Si 1 : le nom 'nomdeluser' a bien été ajouté
		    		 *  Si 0 : le nom 'nomdeluser' existe déjà
		    		 *  
		    		 */
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
