package daron.centralenoteandroid.JsonRequests;


import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;


public class GetStringFromUrl extends AsyncTask<String, String, String> {
	 
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	}
	
	@Override
	protected String doInBackground(String... params) {
	    StringBuilder url = new StringBuilder(params[0]);
	    HttpGet geturl = new HttpGet(url.toString());
	    HttpClient client = new DefaultHttpClient();
	    HttpResponse response;
	    try {
	        response = client.execute(geturl);
	        int status = response.getStatusLine().getStatusCode();
	        if (status == 200) {
	            HttpEntity entity = response.getEntity();
	            String data = EntityUtils.toString(entity);
	            return data;
	        }
	    // Catches any errors from the url
	    } catch (ClientProtocolException clientExcep) {
	        clientExcep.printStackTrace();
	    } catch (IOException ioExcep) {
	        ioExcep.printStackTrace();
	    }
	        return null;
	}
	
}



