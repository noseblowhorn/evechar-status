package eu.fizzystuff.android.evechar.persistence;

import java.util.List;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import eu.fizzystuff.android.evechar.model.ApiKey;

public class SQLiteAPIKeyRepository extends AbstractSQLiteRepository<ApiKey> implements IAPIKeyRepository {
	
	public SQLiteAPIKeyRepository(SQLiteDatabase database) {
		super(database);
	}
	
	public List<ApiKey> GetAll() {
		return super.GetAll("SELECT RowId, KeyId, VerificationCode FROM ApiKey", null);
	}

	public void AddKey(ApiKey key) {
		ContentValues vals = new ContentValues();
		vals.put("KeyId", key.getKeyId());
		vals.put("VerificationCode", key.getVCode());

		db.insertOrThrow("ApiKey", null, vals);
	}

	public void RemoveKey(ApiKey key) {
		db.delete("ApiKey", "KeyId=?", new String[] { key.getKeyId() });
	}

	@Override
	protected ApiKey RowToPOJO(Cursor c) {
		ApiKey key = new ApiKey();
		key.setRowId(c.getInt(0));
		key.setKeyId(c.getString(1));
		key.setVCode(c.getString(2));
		
		return key;
	}

	public void UpdateKey(ApiKey key) {
		ContentValues vals = new ContentValues();
		vals.put("KeyId", key.getKeyId());
		vals.put("VerificationCode", key.getVCode());
		
		db.update("ApiKey", vals, "RowId=?", new String[] { Integer.toString(key.getRowId()) });
	}

	public ApiKey GetById(int id) {
		return GetById("SELECT RowId, KeyId, VerificationCode FROM ApiKey WHERE RowId=?", id);
	}

}
