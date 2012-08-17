package eu.fizzystuff.android.utils;

public class StringUtils {
	private StringUtils() {
		
	}
	
	public static String Join(String[] strings, String separator) {
		if (strings == null) {
			return "";
		}
		if (separator == null) {
			throw new IllegalArgumentException("The 'separator' parameter cannot be null.");
		}
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0;i < strings.length;i++) {
			sb.append(strings[i]);
			if (i < strings.length - 1) {
				sb.append(separator);
			}
		}
		
		return sb.toString();
	}
}
