package edu.ucsb.cs.cs185.hw4skeleton;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HelpFragment extends DialogFragment {
	  @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        View v = inflater.inflate(R.layout.help, container, false);
	        getDialog().setTitle("HELP");
	        
	        
	        Button b = (Button) v.findViewById(R.id.donehelp);
	        b.setOnClickListener(new OnClickListener(){
	        	@Override
				public void onClick(View v) {
	        	 getDialog().dismiss();
	        	}
	        });
	        
	        return v;
	        
	  }
	  
}
