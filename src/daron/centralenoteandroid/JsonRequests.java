package daron.centralenoteandroid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;



public class JsonRequests {
	
	public static String getContent(String url){
		String result = null;
		
		URL url_objet = new URL(url);
	    URLConnection yc = url_ogject.openConnection();
	    BufferedReader in = new BufferedReader(
	                            new InputStreamReader(
	                            yc.getInputStream()));
	    String inputLine;

	    while ((inputLine = in.readLine()) != null) {
	  //What I have to write here?
	    }
        
		
	return result;
	}
}
