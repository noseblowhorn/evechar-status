package eu.fizzystuff.android.evechar.persistence;

import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractSQLiteRepository<T> {
	
	protected SQLiteDatabase db;
	
	public AbstractSQLiteRepository(SQLiteDatabase database) {
		db = database;
	}
	
	protected T GetById(String sql, int id) {
		Cursor cursor = null;
		T result;
		
		try {
			cursor = db.rawQuery(sql, new String[] { Integer.toString(id) });
			
			if (cursor.getCount() == 0) {
				throw new SQLException("The query returned no results.");
			} else if (cursor.getCount() > 1) {
				throw new SQLException("The query returned more than one result.");
			}
			
			cursor.moveToFirst();
			
			result = RowToPOJO(cursor);
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		
		return result;
	}
	
	protected List<T> GetAll(String sql, String[] params) {
		Cursor cursor = null;
		ArrayList<T> list = new ArrayList<T>();
		
		try {
			cursor = db.rawQuery(sql, params);
			
			if (cursor.isBeforeFirst()) {
				cursor.moveToFirst();
			}
			
			while(!cursor.isAfterLast()) {
				T obj = RowToPOJO(cursor);
				list.add(obj);
				
				cursor.moveToNext();
			}
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		
		return list;
	}
	
	protected abstract T RowToPOJO(Cursor c);
}
