package com.example.test1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author Galen
 * Main screen, users add classes here
 */

public class MainActivity extends Activity
{
	private static int counter = 0;
    private TextView[] textViewArray;
    private String[] classNameArray;
	private EditText classInput, creditInput;
    private RelativeLayout relativeLayout;
    private RelativeLayout.LayoutParams params1;
    private LinearLayout.LayoutParams checkBoxParams;
    private int classCount = 10;
	private View textView;
	private String className;
	private CheckBox[] checkBoxArray;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Array of textviews representing list of classes
		textViewArray = new TextView[classCount];
		classNameArray = new String[classCount];
		checkBoxArray = new CheckBox[6];
        relativeLayout = (RelativeLayout)findViewById(R.id.info);
        final Button b1 = (Button) findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener()
        {
        	/**
        	 * Creates AlertDialog when "Add Class" button is pressed
        	 * to prompt user for class and credit input
        	 */
        	public void onClick(View v)
        	{
        		//LAYOUT OF THE ALERT DIALOG
            	LinearLayout alertLayout = new LinearLayout(MainActivity.this);
            	alertLayout.setOrientation(1);
            	//INPUT TEXT BOXES
            	classInput = new EditText(MainActivity.this);
            	classInput.setHint("Enter Class Name");
        		TextView checkBoxText = new TextView(MainActivity.this);
        		checkBoxText.setText("Select Number of Credits");
        		checkBoxText.setTextColor(Color.WHITE);
            	//LAYOUT OF THE CHECKBOXES
        		LinearLayout checkBoxLayout = new LinearLayout(MainActivity.this);
        		checkBoxLayout.setOrientation(0);
        		//CHECKBOXES
        		checkBoxArray[0] = new CheckBox (MainActivity.this);
        		checkBoxArray[1] = new CheckBox (MainActivity.this);
        		checkBoxArray[2] = new CheckBox (MainActivity.this);
        		checkBoxArray[3] = new CheckBox (MainActivity.this);
        		checkBoxArray[4] = new CheckBox (MainActivity.this);
        		checkBoxArray[5] = new CheckBox (MainActivity.this);
        		checkBoxArray[0].setText("1");
        		checkBoxArray[1].setText("2");
        		checkBoxArray[2].setText("3");
        		checkBoxArray[3].setText("4");
        		checkBoxArray[4].setText("5");
        		checkBoxArray[5].setText("6");
        		checkBoxLayout.addView(checkBoxArray[0]);
        		checkBoxLayout.addView(checkBoxArray[1]);
        		checkBoxLayout.addView(checkBoxArray[2]);
        		checkBoxLayout.addView(checkBoxArray[3]);
        		checkBoxLayout.addView(checkBoxArray[4]);
        		checkBoxLayout.addView(checkBoxArray[5]);
        		alertLayout.addView(classInput);
        		alertLayout.addView(checkBoxText);
        		alertLayout.addView(checkBoxLayout);
            	//ACTUAL ALERT DIALOG
            	AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            	alert.setTitle("Add New Class");
            	alert.setView(alertLayout);
            	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            	{
            		/**
            		 * OK button, creates the new textview in the textViewArray
            		 */
	            	public void onClick(DialogInterface dialog, int whichButton)
	            	{
	            	    Editable value = classInput.getText();
	            	    className = value.toString();
	      	    	    textViewArray[counter] = (createNewTextView(className) );
	      	    	    textViewArray[counter].setOnClickListener(new View.OnClickListener()
	      	            {
	      	    	    	/**
	      	    	    	 * Adds listener to textviews so they go to next screen when clicked
	      	    	    	 */
	      	    	    	public void onClick(View v)
	    	            	{
	    		    	    	//START NEW INTETNT, goes to next screen
	    						Bundle b = new Bundle();
	    						b.putString("class_name", className);
	    		    	    	Intent classesIntent = new Intent(MainActivity.this, Classes.class);
	    		    	    	classesIntent.putExtras(b);
	    		    	    	startActivity(classesIntent);
	    	            	}
	      	            });
	      	            params1 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	      	    	    if(counter > 0)
	      	    	    	params1.addRule(RelativeLayout.BELOW, textViewArray[counter-1].getId());
	      	    	    else
	      	    		    params1.addRule(RelativeLayout.BELOW, textViewArray[counter].getId());
	      	    	    relativeLayout.addView(textViewArray[counter], params1);
	      	    	    counter++;
	            	}
	            });

	            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
	            {
					public void onClick(DialogInterface dialog, int whichButton)
					{
					}
            	});
    	    	alert.show();
            }
        });
	}
	private TextView createNewTextView(String text)
	{
	    final TextView textView = new TextView(this);
	    textView.setText(text);
	    textView.setId((int)System.currentTimeMillis());
	    return textView;
	}
}
