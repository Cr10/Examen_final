package com.example.aci570_db;

import com.example.aci570_db.db.MyAppDataSourceList4;
import com.example.aci570_db.model.Molist4;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class List4Item extends Activity {

	private MyAppDataSourceList4 dslist4;
    private Molist4 ItemsToUpdate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list4_item);
            // Show the Up button in the action bar.
            setupActionBar();
            
            dslist4 = new MyAppDataSourceList4(this);
            dslist4.open();
    
            Intent i = this.getIntent();
    
	     if(i.hasExtra(Lista2.EXTRA_ITEMS))
	     	{
    	 	Molist4 ls4 = (Molist4) i.getSerializableExtra(Lista2.EXTRA_ITEMS);
             
    	 			EditText firstNameField = (EditText) this.findViewById(R.id.fieldnameitem);
    	 			firstNameField.setText(ls4.getNameList4());
                    
                	EditText lastNameField = (EditText) this.findViewById(R.id.fieldvaloritem);
                    lastNameField.setText(ls4.getValorList4());
                    
                    EditText emailField = (EditText) this.findViewById(R.id.fieldcantidad);
                    emailField.setText(ls4.getCantidadList4());
                    
                    Button saveButton = (Button) this.findViewById(R.id.buttonadditem);
                    saveButton.setText("Update");
                    
                    Button deleteButton = (Button) this.findViewById(R.id.buttondelete);
                    deleteButton.setVisibility(Button.VISIBLE);
                    
                    this.setTitle("Update Items");
                    
                    this.ItemsToUpdate = ls4;
		     }
		     else
		     {
    	 			Button saveButton = (Button) this.findViewById(R.id.buttonadditem);
    	 			saveButton.setText("Create");
             
		       
		             
		            this.setTitle("Create Items");
		             
		            this.ItemsToUpdate = null;
     }
    }

    /**
     * Set up the {@link android.app.ActionBar}.
     */
    		private void setupActionBar() {

            getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.lista_item, menu);
            return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
            case android.R.id.home:
                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. Use NavUtils to allow users
                    // to navigate up one level in the application structure. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                    //
                    NavUtils.navigateUpFromSameTask(this);
                    return true;
            }
            return super.onOptionsItemSelected(item);
    }
    
    public void onSaveButtonClicked4(View view) {
            EditText NameField = (EditText) this.findViewById(R.id.fieldnameitem);
            String firstName = NameField.getText().toString();
            
            EditText valorField = (EditText) this.findViewById(R.id.fieldvaloritem);
            String valor = valorField.getText().toString();
            
            EditText cantidadField = (EditText) this.findViewById(R.id.fieldcantidad);
            String cantidad = cantidadField.getText().toString();
            
            if(firstName.isEmpty() || valor.isEmpty() || cantidad.isEmpty())
            {
                    Toast.makeText(this, "Complete the form before saving", Toast.LENGTH_LONG).show();
                    return;
            }
            
            Molist4 ls4 = null;
            
            if(this.ItemsToUpdate != null)
            {
            		ls4 = dslist4.updateItems(this.ItemsToUpdate, firstName, valor, cantidad);
            }
            else
            {
            	ls4 = dslist4.createItems(firstName, valor, cantidad);
            }
            
            Intent i = new Intent();
            i.putExtra(List4.EXTRA_ITEMS, ls4);
            i.putExtra(List4.EXTRA_REMOVE_ITEMS, false);
            this.setResult(RESULT_OK, i);
            
            this.finish();
    }
    
    public void onDeleteButtonClicked(View view) {
            
    		Molist4 ls4 = dslist4.deleteList4(this.ItemsToUpdate);
            
            Intent i = new Intent();
            i.putExtra(List4.EXTRA_ITEMS, ls4);
            i.putExtra(List4.EXTRA_REMOVE_ITEMS, true);
            this.setResult(RESULT_OK, i);
            
            this.finish();
    }
    
    @Override
    protected void onResume() {
    	dslist4.open();
            super.onResume();
    }

    @Override
    protected void onPause() {
    	dslist4.close();
            super.onPause();
    }
    
    public void onBackClick2(View view) {
		Intent i = new Intent(this, Lista2.class);
		this.startActivity(i);
 
		
	}
    
    
    
}
