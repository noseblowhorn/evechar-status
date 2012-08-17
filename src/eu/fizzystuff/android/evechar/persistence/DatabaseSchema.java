package eu.fizzystuff.android.evechar.persistence;

import java.util.HashMap;
import java.util.Map;

public final class DatabaseSchema {
	public static final String[] initialSchema = new String[] {
		"CREATE TABLE ApiKey (KeyId text, VerificationCode text);"
	};
	
	// A version -> statement list dictionary, containing differential upgrade scripts
	
	public static final Map<Integer, String[]> differentialSqlStatements = new HashMap<Integer, String[]>() {
		{
			put(2, new String[] { "CREATE TABLE Character (CharacterId int, Name text);" });
		}
	};
}
