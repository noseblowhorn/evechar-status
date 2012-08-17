package eu.fizzystuff.android.evechar.ui.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import eu.fizzystuff.android.evechar.R;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.services.APIKeyService;
import eu.fizzystuff.android.evechar.ui.controllers.ApiKeyListController;

public class ApiKeyListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.api_key_list);
		
		ListView listView = (ListView) findViewById(R.id.api_key_list_List1);
		listView.setItemsCanFocus(false);
		
		ApiKeyListController controller = new ApiKeyListController(this);
		listView.setOnItemClickListener(controller);
		listView.setOnItemLongClickListener(controller);
		
		RefreshList();
	}
	
	public void AddApiKeyClick(View view) {
		Intent intent = new Intent(this, ApiKeyDetailsActivity.class);
		intent.putExtra("Context", ApiKeyDetailsActivity.EditContext.INSERT.toString());
		startActivity(intent);
	}
	
	public void RefreshList() {
		APIKeyService service = new APIKeyService(this);
		List<ApiKey> keys = service.GetAll();
		ListView listView = (ListView) findViewById(R.id.api_key_list_List1);
		ListAdapter adapter = new ArrayAdapter<ApiKey>(this, R.layout.api_key_list_row, keys);
		listView.setAdapter(adapter);
	}

	
	
}
