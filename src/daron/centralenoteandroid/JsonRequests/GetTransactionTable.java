package daron.centralenoteandroid.JsonRequests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import daron.centralenoteandroid.Model.Transaction;

public class GetTransactionTable extends AsyncTask<String, List<String>, List<Transaction>> {
	List<Transaction> transactionList = new ArrayList<Transaction>();
	 	
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	}

	
	protected List<Transaction> doInBackground(String... params) {
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
	            JSONArray item = new JSONArray(data);
	            /* Starts function which performs the parsing and stores
	               All PostItems in a List<PostItem> */
	            transactionList = parseJson(item);
	            return transactionList;
	        }
	    // Catches any errors from the url or JSONObject
	    } catch (ClientProtocolException clientExcep) {
	        clientExcep.printStackTrace();
	    } catch (IOException ioExcep) {
	        ioExcep.printStackTrace();
	    } catch (JSONException jsonExcep) {
	        jsonExcep.printStackTrace();
	    }
	        return null;
	}
	
	public static List<Transaction> parseJson(JSONArray item) {
		
	    List<Transaction> transactionList = new ArrayList<Transaction>();
	    if(item != null) {
	        try {
		        for(int counter = 0; counter < item.length(); counter++) {
		            JSONObject userItem = item.getJSONObject(counter);
		            Transaction transaction = new Transaction();
		            transaction.setId(userItem.get("idd").toString());
		            transaction.setDate(userItem.get("date").toString());
		            transaction.setDeleted(userItem.get("suppr").toString());
		            transaction.setReceiver(userItem.get("re").toString());
		            transaction.setEmitter(userItem.get("em").toString());
		            transaction.setAmount(userItem.get("mo").toString());
		            transaction.setComment(userItem.get("com").toString());
		            transaction.setIp(userItem.get("ip").toString());
		            transactionList.add(transaction);
		        }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	    return transactionList;
	}
	
	@Override
	protected void onPostExecute(List<Transaction> transactions) {
	}
	
}
