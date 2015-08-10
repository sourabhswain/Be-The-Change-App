package com.example.bethechange;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.sunny.bethechange.R;

public class Options extends Activity{

	Button prevPic,newPic;
	TextView options;
	Bundle extras;
	String value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.options);
		initializeVariables();
	}

	private void initializeVariables() {
		// TODO Auto-generated method stub
		prevPic=(Button) findViewById(R.id.prevPic);
		newPic=(Button) findViewById(R.id.newPic);
		options=(TextView) findViewById(R.id.options);
		
		//The following code receives the Bundle sent from the MainActivity Class and saves it 
		//in the string named value.
		extras = getIntent().getExtras();
		if(extras!=null){
			value=extras.getString("Button");
			}
		
		prevPic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p = new Intent(Options.this,BrowsePicture.class);
				p.putExtra("Button",value);
				startActivity(p);
			}
		});
		  
		newPic.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent n = new Intent(Options.this,TakePic.class);
				n.putExtra("Button",value);
				startActivity(n);
			}
		});
	}
	

}
