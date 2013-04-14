package daron.centralenoteandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SharedPreferences prefs = getPreferences(MODE_PRIVATE); 
		String restoredText = prefs.getString("centraleNoteCircles", null);
		
		String circles;
		
		
		if (restoredText == null) 
		{
			circles = "";
		}
		else
		{
			circles = restoredText;
		}
		
		/*
		To store in centraleNoteCircles
		
		SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
 		editor.putString("centraleNoteCircles", "mon texte");
		 */
		
		
		
		Intent i = new Intent(this, DebtsTableActivity.class);
		startActivity(i); 
		
		
		
		
		
		
	}
}