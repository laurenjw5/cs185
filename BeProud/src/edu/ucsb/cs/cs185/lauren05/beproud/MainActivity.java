package edu.ucsb.cs.cs185.lauren05.beproud;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<String> entry = null;
	ArrayList<String> value = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		entry = new ArrayList<String>();
		value = new ArrayList<String>();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addNewEntry(View v){
		EditText e = (EditText) findViewById(R.id.body);
		
		if(!e.getText().toString().matches("") && e.getText() != null)
			entry.add(e.getText().toString());
		
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		final AlertDialog.Builder alertDeleteEditBuilder = new AlertDialog.Builder(this);
		
		
		final CharSequence[] items = new String[entry.size()];
		for(int i=0; i<entry.size(); i++)
		{
			if (entry.get(i) != null && entry.get(i) != "")
			{
				items[i] = entry.get(i).toString();
			}
		}	
		
		
		// set title
		alertDialogBuilder.setTitle("List of Accomplishments");
		// set dialog message
					alertDialogBuilder
					.setItems(items, new DialogInterface.OnClickListener() {

						  @Override
						  public void onClick(DialogInterface dialog, final int which) {
						//Toast.makeText(getApplicationContext(), "U clicked "+items[which], Toast.LENGTH_LONG).show();
							  alertDeleteEditBuilder
								.setCancelable(false)
								.setMessage(items[which])
								.setPositiveButton("Exit",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										dialog.cancel();
									}
								})
								.setNegativeButton("Delete",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
										entry.remove(which);
									}
								});
				 
								// create alert dialog
								AlertDialog alertDialog = alertDeleteEditBuilder.create();
				 
								// show it
								alertDialog.show();
						  }
						  
						  
//						//NEED TO DO: EDIT BUTTON!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  
//							.setPositiveButton("Edit",new DialogInterface.OnClickListener() {
//							public void onClick(DialogInterface dialog,int id) {
//								// if this button is clicked, close
//								// current activity
//								MainActivity.this.finish();
//							}
//						  })
						  
						  
						})
						.setCancelable(false)
						.setPositiveButton("Add Entry",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, close
								// current activity
								MainActivity.this.finish();
							}
						  })
						.setNegativeButton("Exit",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								// if this button is clicked, just close
								// the dialog box and do nothing
								dialog.cancel();
							}
						});
		
	

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();
		 
		// show it
		alertDialog.show();
		
	}

	public static class LineEditText extends EditText{
  // we need this constructor for LayoutInflater
    	
   public LineEditText(Context context, AttributeSet attrs) {
   super(context, attrs);
    mRect = new Rect();
    mPaint = new Paint();
    mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    mPaint.setColor(Color.BLUE);
    //this.setPadding(100, this.getPaddingTop(), this.getPaddingRight(), this.getPaddingBottom());
    
  }
   
   
  	private Rect mRect;
    private Paint mPaint; 
    private Paint redPaint;
     
     @Override
     protected void onDraw(Canvas canvas) {
   
         int height = getHeight();
         int line_height = getLineHeight();

         int count = height / line_height;

         if (getLineCount() > count)
             count = getLineCount();

         Rect r = mRect;
         Paint paint = mPaint;
         int baseline = getLineBounds(0, r);

         for (int i = 0; i < count; i++) {

             canvas.drawLine(r.left, baseline + 1, r.right, baseline + 1, paint);
             baseline += getLineHeight();
             
             

         super.onDraw(canvas);
     }
     //draw binder holes...
     //mPaint.setColor(Color.BLACK);
     //canvas.drawCircle(30, 90, 15, mPaint);
     //canvas.drawCircle(30, 350, 15, mPaint);
     //canvas.drawCircle(30, 610, 15, mPaint);
     //super.onDraw(canvas);
         
 }
}

}
