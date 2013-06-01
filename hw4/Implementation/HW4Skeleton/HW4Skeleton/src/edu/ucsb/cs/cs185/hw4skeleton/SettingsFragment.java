package edu.ucsb.cs.cs185.hw4skeleton;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SettingsFragment extends DialogFragment {
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View v = inflater.inflate(R.layout.settings, container, false);
	        getDialog().setTitle("SETTINGS");
	        
	        Button b = (Button) v.findViewById(R.id.done);
	        b.setOnClickListener(new OnClickListener(){
	        	@Override
				public void onClick(View v) {
	        	 getDialog().dismiss();
	        	}
	        });
	        
	        return v;
	        
	  }
}
