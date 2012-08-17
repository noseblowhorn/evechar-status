package eu.fizzystuff.android.evechar.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import eu.fizzystuff.android.evechar.EveCharacterStatusAndroidActivityCallbackProvider;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.model.EveCharacter;

public class EveOnlineRESTfulWebservice implements IEveOnlineWebservice {

	private ConnectivityManager connectivityManager;
	private EveCharacterStatusAndroidActivityCallbackProvider callbackProvider;
	
	public EveOnlineRESTfulWebservice(ConnectivityManager connectivityMgr) {
		connectivityManager = connectivityMgr;
	}
	
	public void Characters(ApiKey apiKey,
			EveCharacterStatusAndroidActivityCallbackProvider callback) {
		
		String url = "https://api.eveonline.com/account/Characters.xml.aspx?keyID="
				+ apiKey.getKeyId() + "&vCode=" + "2PXAlt7o2CZbRGt3Npch7IIuP11aP04m828lcmgToWX1DMofZWr42Lm0IckqM7ni";
		
		callbackProvider = callback;
		
		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected()) {
			new CharactersExecutor().execute(url);
		} else {
			callback.DisplayErrorMessage("No Internet connection available.");
		}
	}

	private void CharactersCallback(List<EveCharacter> chars,
			EveCharacterStatusAndroidActivityCallbackProvider callback) {
		callback.CharacterListCallback(chars);
	}

	private class CharactersExecutor extends AsyncTask {

		@Override
		protected Object doInBackground(Object... params) {
			try {
                return downloadUrl((String)params[0]);
            } catch (Exception e) {
                return "Unable to retrieve web page. URL may be invalid.";
            }
		}
		
		@Override
        protected void onPostExecute(Object result) {
            callbackProvider.DisplayErrorMessage(result.toString());
        }
		
		private String downloadUrl(String myurl) throws IOException {
		    InputStream is = null;
		    // Only display the first 500 characters of the retrieved
		    // web page content.
		    int len = 500;
		        
		    try {
		        URL url = new URL(myurl);
		        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		        conn.setReadTimeout(10000 /* milliseconds */);
		        conn.setConnectTimeout(15000 /* milliseconds */);
		        conn.setRequestMethod("GET");
		        conn.setDoInput(true);
		        // Starts the query
		        conn.connect();
		        int response = conn.getResponseCode();
		        is = conn.getInputStream();

		        // Convert the InputStream into a string
		        String contentAsString = readIt(is, len);
		        return contentAsString;
		        
		    // Makes sure that the InputStream is closed after the app is
		    // finished using it.
		    } finally {
		        if (is != null) {
		            is.close();
		        } 
		    }
		}
		
		public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
		    Reader reader = null;
		    reader = new InputStreamReader(stream, "UTF-8");        
		    char[] buffer = new char[len];
		    reader.read(buffer);
		    return new String(buffer);
		}

	}

}
