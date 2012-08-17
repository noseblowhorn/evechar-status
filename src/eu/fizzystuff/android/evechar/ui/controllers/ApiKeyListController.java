package eu.fizzystuff.android.evechar.ui.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import eu.fizzystuff.android.evechar.R;
import eu.fizzystuff.android.evechar.model.ApiKey;
import eu.fizzystuff.android.evechar.services.APIKeyService;
import eu.fizzystuff.android.evechar.ui.activities.ApiKeyDetailsActivity;
import eu.fizzystuff.android.evechar.ui.activities.ApiKeyListActivity;

public class ApiKeyListController implements OnItemClickListener, OnItemLongClickListener {

	private ApiKeyListActivity context;
	
	public ApiKeyListController(ApiKeyListActivity ctx) {
		context = ctx;
	}
	
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		context.setResult(((ApiKey)parent.getItemAtPosition(position)).getRowId());
		context.finish();
	}
	
	public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position,
			long id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		
		builder.setTitle(context.getString(R.string.dialog_choose_action));
		
		String[] options = new String[] { context.getString(R.string.dialog_option_edit),
										  context.getString(R.string.dialog_option_delete)};
		
		builder.setItems(options, new OnClickListener() {

			public void onClick(DialogInterface dialog, int which) {
				if (which == 0) {
					Intent intent = new Intent(context, ApiKeyDetailsActivity.class);
					intent.putExtra("Context", ApiKeyDetailsActivity.EditContext.UPDATE.toString());
					intent.putExtra("RowId", ((ApiKey)parent.getItemAtPosition(position)).getRowId());
					
					context.startActivity(intent);
				} else if (which == 1) {
					APIKeyService service = new APIKeyService(context);
					service.DeleteApiKey((ApiKey)parent.getItemAtPosition(position));
					context.RefreshList();
				}
			}
			
		});
		
		AlertDialog alert = builder.create();
		alert.show();
		
		return true;
	}

}
