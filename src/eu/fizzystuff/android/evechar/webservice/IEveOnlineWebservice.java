package eu.fizzystuff.android.evechar.webservice;

import java.util.List;

import eu.fizzystuff.android.evechar.EveCharacterStatusAndroidActivityCallbackProvider;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.model.EveCharacter;

public interface IEveOnlineWebservice {
	void Characters(ApiKey apiKey, EveCharacterStatusAndroidActivityCallbackProvider callback);
}
