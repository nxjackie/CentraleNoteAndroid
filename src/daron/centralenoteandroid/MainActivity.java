package daron.centralenoteandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences settings = getSharedPreferences("CentraleNote", 0);
		
		String cercles = settings.getString("Cercles", null);
		
		
		
		Intent i = new Intent(this, LoginActivity.class);
		startActivity(i); 
		
		
		
		
		
		
	}
}