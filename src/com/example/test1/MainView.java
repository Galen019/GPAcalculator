package com.example.test1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainView extends Activity
{

	private static int counter = 0;
    private TextView[] textViewArray;
    private TextView classText;
	private EditText categoryInput, weightingInput, assignmentInput;
	private EditText scoreInput;
    private RelativeLayout relativeLayout;
    private RelativeLayout.LayoutParams params1;
    private int textViewCount = 10;
	private String categoryName;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_view);
	}
}
