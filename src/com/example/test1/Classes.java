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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
/**
 * 
 * @author Galen
 * This is the second screen, users add categories like Exams or Homework
 * and their corresponding weighting percentage
 */

public class Classes extends Activity
{
	private static int counter = 0;
    private TextView[] textViewArray;
    private TextView classText;
	private EditText categoryInput, weightingInput, assignmentInput;
	private EditText scoreInput;
    private RelativeLayout relativeLayout;
    private RelativeLayout.LayoutParams params1;
    private int textViewCount = 10;
	private String categoryName, assignmentName;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test2);
		/**
		 * FETCHES THE CLASS NAME FROM PREVIOUS ACTIVITY/SCREEN
		 */
		Bundle b = this.getIntent().getExtras();
		String className = b.getString("class_name");
		classText = (TextView) findViewById(R.id.class_name1);
		classText.setText(className);
		classText.setTextSize(40.0f);
		classText.setId((int)System.currentTimeMillis());
		//Array of textviews representing list of classes
		textViewArray = new TextView[textViewCount];
        relativeLayout = (RelativeLayout)findViewById(R.id.classesLayout);

        final Button b1 = (Button) findViewById(R.id.AddCategoryButton);
        b1.setOnClickListener(new View.OnClickListener()
        {
        	/**
        	 * Creates AlertDialog when "Add Category" button is pressed
        	 * to prompt user for class and credit input
        	 */
        	public void onClick(View v)
        	{
            	AlertDialog.Builder alert = new AlertDialog.Builder(Classes.this);
            	categoryInput = new EditText(Classes.this);
            	alert.setTitle("Add New Category");
            	alert.setMessage("Enter Category (e.g Exams, Homeworks)");
            	alert.setView(categoryInput);
            	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            	{
	            	public void onClick(DialogInterface dialog, int whichButton)
	            	{
	            	    Editable value = categoryInput.getText();
	            	    categoryName = value.toString();
	      	    	    textViewArray[counter] = (createNewCategory(categoryName) );
	      	            params1 = new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
	      	    	    if(counter > 0)
	      	    	    	params1.addRule(RelativeLayout.BELOW, textViewArray[counter-1].getId());
	      	    	    else
	      	    		    params1.addRule(RelativeLayout.BELOW, classText.getId());
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
	private TextView createNewCategory(String text)
	{
	    final TextView textView = new TextView(this);
	    textView.setText(text);
	    textView.setBackgroundColor(Color.LTGRAY);
	    textView.setId((int)System.currentTimeMillis());
	    textView.setOnClickListener(new View.OnClickListener()
        {
        	/**
        	 * Creates AlertDialog when Category textView is pressed
        	 * to prompt user to add an assignment and score as percentage
        	 */
        	public void onClick(View v)
        	{
            	AlertDialog.Builder alert = new AlertDialog.Builder(Classes.this);
            	assignmentInput = new EditText(Classes.this);
            	alert.setTitle("Add New Assignment");
            	alert.setMessage("Enter Assignment (e.g Midterm 1)");
            	alert.setView(assignmentInput);
            	alert.setPositiveButton("Ok", new DialogInterface.OnClickListener()
            	{
	            	public void onClick(DialogInterface dialog, int whichButton)
	            	{
	            		Editable value = assignmentInput.getText();
	            		assignmentName = value.toString();
	            		TextView assignmentText = new TextView(Classes.this);
	            		assignmentText.setText(assignmentName);
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
	    return textView;
	}
}
