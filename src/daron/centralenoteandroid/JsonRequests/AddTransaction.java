package daron.centralenoteandroid.JsonRequests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import daron.centralenoteandroid.Model.User;

public class AddTransaction extends AsyncTask<String, List<String>, String> {

//	List<User> userList = new ArrayList<User>();
	private ProgressDialog _dialog;
	private Context _context;
	private User _host;
	private String _comment;
	private List<User> _profiteurs;
	private List<String> _debt;
	
    public AddTransaction(Context activity, User host, String comment, List<User> profiteurs, List<String> debt) {
        _context = activity;
        _dialog = new ProgressDialog(_context);
        _host = host;
        _comment = comment;
        _profiteurs = profiteurs;
        _debt = debt; 
    }
	 
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	    _dialog.setMessage("Chargement en cours...");
	    _dialog.show();
	}
	
	@Override
	protected String doInBackground(String... params) {
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost(params[0]);
	    
	    try {
	        // Add your data
	        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
	        nameValuePairs.add(new BasicNameValuePair("nom_avanceur", _host.getId()));
	        nameValuePairs.add(new BasicNameValuePair("com", _comment));
	        for (int i = 0; i < _profiteurs.size(); i++) {
	        	nameValuePairs.add(new BasicNameValuePair("nom_beneficiaire_" + _profiteurs.get(i).getId(), _debt.get(i)));
	        }
	        Log.v("hello", nameValuePairs.toString());
	        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	        // Execute HTTP Post Request
	        HttpResponse response = httpclient.execute(httppost);
	        return "0";

	    } catch (ClientProtocolException e) {
	        return "1";
	    } catch (IOException e) {
	        return "1";
	    }
	    
	}
	
	@Override
	protected void onPostExecute(String returnCode) {
		_dialog.dismiss();
		
	}
}
	