package edu.ucsb.cs.cs185.hw4skeleton;


import java.util.Calendar;

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;
import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
public class KeepScoreActivity extends FragmentActivity{
	public static Context appContext;
	String gameinfo = new String("");
	
	WheelView wheelDateYear;
	WheelView wheelDateMonth;
	WheelView wheelDateDay;
	
	Spinner spinnerTeam1;
	Spinner spinnerTeam2;
	
	WheelView wheelScore1;
	WheelView wheelScore2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_keep_score);
		
		// This is used to update the maximum number of days in a month
		OnWheelChangedListener wheelListener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays();
            }
        };
       
		// Year
		wheelDateYear = (WheelView) findViewById(R.id.timePickerYear);
		wheelDateYear.setViewAdapter(new NumericWheelAdapter(this, 2012, 2013));
		wheelDateYear.setCurrentItem(1);
		wheelDateYear.addChangingListener(wheelListener);

		// Month
		wheelDateMonth = (WheelView) findViewById(R.id.timePickerMonth);
		wheelDateMonth.setViewAdapter(new ArrayWheelAdapter<String>(this, getResources().getStringArray(R.array.months_short)));
		wheelDateMonth.setCurrentItem(3);
		wheelDateMonth.addChangingListener(wheelListener);
		wheelDateMonth.setCyclic(true);

		// Day
		wheelDateDay = (WheelView) findViewById(R.id.timePickerDay);
		wheelDateDay.setViewAdapter(new NumericWheelAdapter(this, 1, 31, "%02d"));
		wheelDateDay.setCurrentItem(16);
		wheelDateDay.setCyclic(true);
		
		updateDays();
		
		// Teams
		spinnerTeam1 = (Spinner) findViewById(R.id.team1);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.teams, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTeam1.setAdapter(adapter);

		spinnerTeam2 = (Spinner) findViewById(R.id.team2);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
		        R.array.teams, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerTeam2.setAdapter(adapter2);
		
		// Scores
		wheelScore1 = (WheelView) findViewById(R.id.score1);
		wheelScore1.setViewAdapter(new NumericWheelAdapter(this, 0, 499, "%03d"));
		wheelScore1.setCurrentItem(0);

		wheelScore2 = (WheelView) findViewById(R.id.score2);
		wheelScore2.setViewAdapter(new NumericWheelAdapter(this, 0, 499, "%03d"));
		wheelScore2.setCurrentItem(0);
		
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_keep_score, menu);
		menu.getItem(0).setTitle(R.string.home);
		this.getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		SettingsFragment s = new SettingsFragment();
		HelpFragment h = new HelpFragment();
		
		switch (item.getItemId()) {
		case R.id.new_entry:
		    this.finish();
			return true;
		case R.id.settings:
			s.show(getSupportFragmentManager(), "SETTINGS");
			return true;
		case R.id.help:
			h.show(getSupportFragmentManager(), "HELP");
			return true;
		case android.R.id.home:
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	
	}
	
	public void onSet(View v)
	{
		//fix off by one
		int month = wheelDateMonth.getCurrentItem()+1;
		int day = wheelDateDay.getCurrentItem()+1;
		
		gameinfo = month + "/" + day + " -- "  + spinnerTeam1.getSelectedItem() + " (" + wheelScore1.getCurrentItem() +") vs. " + spinnerTeam2.getSelectedItem() + " (" + wheelScore2.getCurrentItem() +")";
        
		Intent i = new Intent();
		i.putExtra("gameinfo", new String(gameinfo));
		setResult(RESULT_OK, i);
		
	    wheelDateYear.setCurrentItem(0);
	    wheelDateMonth.setCurrentItem(3);
	    wheelDateDay.setCurrentItem(16);
	    spinnerTeam1.setSelection(0);
	    spinnerTeam2.setSelection(0);
	    wheelScore1.setCurrentItem(0);
	    wheelScore2.setCurrentItem(0);
	    
	    finish();
	}
	
	void updateDays()
    {
		//create string with game info
        // Get maximum number of days for the currently selected month
        // and update the wheelDateDay view by setting its view adapter.
        // hint: http://developer.android.com/reference/java/util/Calendar.html#getActualMaximum(int)
		
        Calendar calendar = Calendar.getInstance();
        calendar.set(wheelDateYear.getCurrentItem(), wheelDateMonth.getCurrentItem(), wheelDateDay.getCurrentItem());
        int max = calendar.getActualMaximum(Calendar.DATE);
        wheelDateDay.setViewAdapter(new NumericWheelAdapter(this, 1, max, "%02d"));
    }

}
