package eu.fizzystuff.android.evechar;

import java.util.List;

import eu.fizzystuff.android.evechar.model.EveCharacter;

public interface EveCharacterStatusAndroidActivityCallbackProvider {
	void CharacterListCallback(List<EveCharacter> chars);
	void DisplayErrorMessage(String message);
}
