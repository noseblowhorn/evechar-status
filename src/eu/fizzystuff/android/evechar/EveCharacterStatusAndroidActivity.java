package eu.fizzystuff.android.evechar;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.model.EveCharacter;
import eu.fizzystuff.android.evechar.webservice.EveOnlineRESTfulWebservice;
import eu.fizzystuff.android.evechar.webservice.IEveOnlineWebservice;

public class EveCharacterStatusAndroidActivity extends Activity implements EveCharacterStatusAndroidActivityCallbackProvider {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void GetAllCharacters(View view) {
    	IEveOnlineWebservice webService = new EveOnlineRESTfulWebservice((ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE));
    	ApiKey key = GetApiKey();
    	
        webService.Characters(key, this);
    }
    
    private ApiKey GetApiKey() {
    	EditText keyIdText = (EditText)findViewById(R.id.KeyId);
    	String keyId = keyIdText.getText().toString();
    	EditText vCodeText = (EditText)findViewById(R.id.VCode);
    	String vCode = vCodeText.getText().toString();
    	
    	ApiKey key = new ApiKey();
    	key.setKeyId(keyId);
    	key.setVCode(vCode);
    	
    	return key;
    }
    
    public void CharacterListCallback(List<EveCharacter> chars)
    {
    	StringBuilder sb = new StringBuilder();
    	for (EveCharacter c : chars) {
    		sb.append(c.getName());
    	}
    	
    	TextView outputLabel = (TextView)findViewById(R.id.CharNamesLabel);
    	outputLabel.setText(sb.toString());
    }
    
    public void DisplayErrorMessage(String message) {
    	TextView outputLabel = (TextView)findViewById(R.id.CharNamesLabel);
    	outputLabel.setText(message);
    }
}