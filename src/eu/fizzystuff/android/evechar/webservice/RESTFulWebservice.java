package eu.fizzystuff.android.evechar.webservice;

import java.util.Map;

import eu.fizzystuff.android.utils.HttpUtils;

import android.os.AsyncTask;

public class RESTFulWebservice extends AsyncTask {
	private IWebserviceCallback callback;
	
	public void Execute(String baseUrl, Map<String, String> params, IWebserviceCallback cb) {
		callback = cb;
		
		String url = HttpUtils.BuildUrl(baseUrl, params);
		
		this.execute(url);
	}

	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    protected void onPostExecute(Object result) {
        if (callback == null) {
        	throw new IllegalStateException("No callback object present after webservice method execution");
        }
        
        callback.OnXmlReceived((String) result);
    }
}
