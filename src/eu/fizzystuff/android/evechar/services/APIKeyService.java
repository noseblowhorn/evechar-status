package eu.fizzystuff.android.evechar.services;

import java.util.List;

import android.content.Context;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.persistence.EveSQLiteOpenHelper;
import eu.fizzystuff.android.evechar.persistence.IAPIKeyRepository;
import eu.fizzystuff.android.evechar.persistence.SQLiteAPIKeyRepository;

public class APIKeyService {
	
	private Context ctx;
	private IAPIKeyRepository repository;
	
	public APIKeyService(Context context) {
		ctx = context;
		repository = GetRepository();
	}
	
	public void AddApiKey(ApiKey apiKey) {
		repository.AddKey(apiKey);
	}
	
	public void UpdateApiKey(ApiKey key) {
		repository.UpdateKey(key);
	}
	
	public List<ApiKey> GetAll() {
		IAPIKeyRepository keyRepository = GetRepository();
		
		List<ApiKey> keys = keyRepository.GetAll();
		
		return keys;
	}
	
	public ApiKey GetById(int id) {
		IAPIKeyRepository keyRepository = GetRepository();
		
		return keyRepository.GetById(id);
	}
	
	public void DeleteApiKey(ApiKey key) {
		IAPIKeyRepository keyRepository = GetRepository();
		
		keyRepository.RemoveKey(key);
	}

	private IAPIKeyRepository GetRepository() {
		EveSQLiteOpenHelper dataSource = new EveSQLiteOpenHelper(ctx);
		IAPIKeyRepository keyRepository = new SQLiteAPIKeyRepository(dataSource.getWritableDatabase());
		return keyRepository;
	}
}
