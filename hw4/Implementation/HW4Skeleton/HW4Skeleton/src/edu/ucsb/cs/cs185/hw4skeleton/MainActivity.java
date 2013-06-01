package edu.ucsb.cs.cs185.hw4skeleton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity{
	private ArrayAdapter arrayAdapter = null;
	private ListView lv = null;
	private ArrayList<String> array = null;
	static final String KEY = "key";

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		
		if (savedInstanceState != null) {
			// Restore value of members from saved state
			 array = savedInstanceState.getStringArrayList(KEY);
			} else {
			// Probably initialize members with default values for a new instance
			array = new ArrayList<String>();
			}
		
		//to remove the sharedPreferences for the TA
		//getSharedPreferences("state",0).edit().clear();
		
		setContentView(R.layout.list);
		
		lv = (ListView) findViewById(R.id.listView);
		arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
		lv.setAdapter(arrayAdapter);
		
		//LoadPreferences();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_keep_score, menu);
        return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		SettingsFragment s = new SettingsFragment();
		HelpFragment h = new HelpFragment();
		
		switch (item.getItemId()) {
		case R.id.new_entry:
		    Intent request = new Intent(MainActivity.this, KeepScoreActivity.class);
		    startActivityForResult(request, 0);
			return true;
		case R.id.settings:
			s.show(getSupportFragmentManager(), "SETTINGS");
			return true;
		case R.id.help:
			h.show(getSupportFragmentManager(), "HELP");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		//check which activity gives data
	     switch(requestCode){
	     //checking for our KeepScoreActivity using request code
	     case 0:
	      //check whether result comes with RESULT_OK (That mean no problem in result)
	      if(resultCode == RESULT_OK){
	      
	       //then get the team info that returned from KeepScoreActivity  
	       String team_data= data.getExtras().getString("gameinfo");  
	       
	       //then populate the current view
	       array.add(team_data);
	       
	       arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
	       lv.setAdapter(arrayAdapter);
	       finishActivity(requestCode);
	       
	       //Toast.makeText(getApplicationContext(), "in onActivityResult", Toast.LENGTH_SHORT).show();
	      }
	     }
	}

	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	// Save the user's current game state
	//Toast.makeText(getApplicationContext(), "in onSaveInstanceState"+array.toString(), Toast.LENGTH_SHORT).show();
	
	savedInstanceState.putStringArrayList(KEY, array);
	// Always call the superclass so it can save the view hierarchy state
	super.onSaveInstanceState(savedInstanceState);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Always call the superclass so it can restore the view hierarchy
		super.onRestoreInstanceState(savedInstanceState);
		//Toast.makeText(getApplicationContext(), "in onRestoreInstanceState", Toast.LENGTH_SHORT).show();
		// Restore state members from saved instance
		 array = savedInstanceState.getStringArrayList(KEY);
		}

	@Override
	public void onBackPressed()
	{
		SavePreferences();
		super.onBackPressed();
	}
	
	private void SavePreferences(){
		
		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		Set<String> s = new HashSet<String>();
		if(!array.isEmpty())
		for(int i=0;i<array.size();i++)
			s.add(array.get(i));
		editor.putStringSet("state", s);
		editor.commit();
	    //Toast.makeText(getApplicationContext(), "array"+array.toString(), Toast.LENGTH_LONG).show();
		
	}
	
	private void LoadPreferences(){
	    SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
	    Set<String> s = null;
	    s = sharedPreferences.getStringSet("state", null);
	    if(!s.isEmpty())
	    for(int i=0; i<s.size(); i++)
	    	array = new ArrayList<String>(s);
	   }

	
} 

