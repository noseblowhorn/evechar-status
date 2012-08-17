package eu.fizzystuff.android.utils;

import java.util.ArrayList;
import java.util.Map;

public class HttpUtils {
	public static String SerializeParamsGet(Map<String, String> params) {
		if (params == null) {
			return "";
		}
		
		ArrayList<String> keyValuePairs = new ArrayList<String>();
		
		for (Map.Entry<String, String>param : params.entrySet()) {
			keyValuePairs.add(param.getKey() + "=" + param.getValue());
		}
		
		return StringUtils.Join(keyValuePairs.toArray(new String[0]), "&");
	}
	
	public static String BuildUrl(String baseUrl, Map<String, String> params) {
		if ((params != null) && (params.size() > 0)) {
			String serializedParams = SerializeParamsGet(params);
			
			return baseUrl + "?" + serializedParams;
		} else {
			return baseUrl;
		}
	}
}
