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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import daron.centralenoteandroid.Model.User;

public class GetDebtsTable extends AsyncTask<String, List<String>, List<User>> {

	List<User> userList = new ArrayList<User>();
	private ProgressDialog _dialog;
	private Context _context;
	
    public GetDebtsTable(Activity activity) {
        _context = activity;
        _dialog = new ProgressDialog(_context);
    }
	 
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	    _dialog.setMessage("Chargement en cours...");
	    _dialog.show();
	}
	
	@Override
	protected List<User> doInBackground(String... params) {
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
	            userList = parseJson(item);
	            return userList;
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
	
	public static List<User> parseJson(JSONArray item) {
		
	    List<User> userList = new ArrayList<User>();
	    if(item != null) {
	        try {
		        for(int counter = 0; counter < item.length(); counter++) {
		            JSONObject userItem = item.getJSONObject(counter);
		            User user = new User();
		            user.setId(userItem.get("id").toString());
		            user.setName(userItem.get("nom").toString());
		            user.setDebt(userItem.get("etat").toString());
		            userList.add(user);
		        }
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    }
	    return userList;
	}
	
	@Override
	protected void onPostExecute(List<User> users) {
		_dialog.dismiss();
	}
}
	