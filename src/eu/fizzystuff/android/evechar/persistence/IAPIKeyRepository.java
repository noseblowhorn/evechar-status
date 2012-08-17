package eu.fizzystuff.android.evechar.persistence;

import java.util.List;

import eu.fizzystuff.android.evechar.model.ApiKey;

public interface IAPIKeyRepository {
	List<ApiKey> GetAll();
	ApiKey GetById(int id);
	void AddKey(ApiKey key);
	void RemoveKey (ApiKey key);
	void UpdateKey (ApiKey key);
}
