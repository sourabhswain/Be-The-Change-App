package com.example.bethechange;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.sunny.bethechange.R;

public class MainActivity extends Activity {
	
/*Private Instance Variables.*/
	Button child,garbage,women,road;
	TextView text;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initializeVariables();
	}

/*Initializes the instance variables created.*/
	private void initializeVariables() {
		
		/*Links the XML buttons and text view with the variables created above.*/
		text=(TextView)findViewById(R.id.text);
		child = (Button)findViewById(R.id.Child);
		garbage = (Button)findViewById(R.id.Garbage);
		road= (Button)findViewById(R.id.Road);
		women=(Button)findViewById(R.id.Women);
		
		/*Sets the on click listeners to the buttons.*/
		child.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent c = new Intent(MainActivity.this,Options.class);
				c.putExtra("Button","child");
				startActivity(c);
			}
		});
		
		garbage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent g = new Intent(MainActivity.this,Options.class);
				g.putExtra("Button", "garbage");
				startActivity(g);
			}
		});
		
		road.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent r = new Intent(MainActivity.this,Options.class);
				r.putExtra("Button", "road");
				startActivity(r);
			}
		});
		
		women.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent w = new Intent(MainActivity.this,Options.class);
				w.putExtra("Button", "women");
				startActivity(w);
			}
		});
	}



}
