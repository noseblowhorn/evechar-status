package eu.fizzystuff.android.evechar.ui.activities;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import eu.fizzystuff.android.evechar.R;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.services.APIKeyService;

public class ApiKeyDetailsActivity extends Activity {
	
	private EditContext editContext;
	private int rowId;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.api_key_details);
        
        if (getIntent().getExtras().isEmpty()) {
        	throw new IllegalArgumentException("No edit context fount in intent.");
        }
        
        editContext = EditContext.valueOf(getIntent().getStringExtra("Context"));
        
        if (editContext == EditContext.UPDATE) {
        	APIKeyService service = new APIKeyService(this);
        	ApiKey key = service.GetById(getIntent().getIntExtra("RowId", -1));
        	
        	rowId = key.getRowId();
        	((EditText)findViewById(R.id.KeyId)).setText(key.getKeyId());
        	((EditText)findViewById(R.id.VCode)).setText(key.getVCode());
        }
        
        
    }
	
	public void SaveApiKeyClick(View view) {
		EditText keyIdText = (EditText)findViewById(R.id.KeyId);
    	String keyId = keyIdText.getText().toString();
    	EditText vCodeText = (EditText)findViewById(R.id.VCode);
    	String vCode = vCodeText.getText().toString();
    	
    	ApiKey key = new ApiKey();
    	key.setKeyId(keyId);
    	key.setVCode(vCode);
    	
    	APIKeyService service = new APIKeyService(this);
    	
    	if (editContext == EditContext.INSERT) {
    		service.AddApiKey(key);
    	} else if (editContext == EditContext.UPDATE) {
    		key.setRowId(rowId);
    		service.UpdateApiKey(key);
    	}
    	
    	Intent intent = new Intent(this, ApiKeyListActivity.class);
    	startActivity(intent);
	}
	
	public void ImportFromQRClick(View view) {
		IntentIntegrator integrator = new IntentIntegrator(this);
		integrator.initiateScan();
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		IntentResult scanResult = IntentIntegrator.parseActivityResult(
				requestCode, resultCode, intent);
		EditText vCodeText = (EditText) findViewById(R.id.VCode);
		vCodeText.setText(scanResult.getContents());
	}
	
	
	
	public enum EditContext {
		INSERT, UPDATE
	}
}
