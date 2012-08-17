package eu.fizzystuff.android.evechar.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EveSQLiteOpenHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "EveDb.sqlite";

	public EveSQLiteOpenHelper(Context ctx) {
		super(ctx, DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		for (String statement: DatabaseSchema.initialSchema) {
			db.execSQL(statement);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// niemo¿noœæ wykonania skryptów ró¿nicowych z plików .sql œmierdzi niemytym kutasem...
		
		while (oldVersion < newVersion) {
			oldVersion++;
			
			String[] statementsToExecute = DatabaseSchema.differentialSqlStatements.get(oldVersion);
			
			for (String statement: statementsToExecute) {
				db.execSQL(statement);
			}
		}
	}

}
