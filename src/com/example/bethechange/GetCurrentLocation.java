package com.example.bethechange;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.sunny.bethechange.R;

public class GetCurrentLocation extends Activity {

	Context context;
	Bundle extras;
	TextView textview;
	Button returnLocation;
	String stringLatitude,stringLongitude,country,city,postalCode,addressLine;
	String btnSelected,img1,img2,img3,img4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context=this;
		setContentView(R.layout.location);
		intializeVar();
		
		// check if GPS enabled
		GPSTracker gpsTracker = new GPSTracker(this);
		
		if (gpsTracker.canGetLocation())
		{
			stringLatitude = String.valueOf(gpsTracker.latitude);
			textview = (TextView)findViewById(R.id.fieldLatitude);
			textview.setText(stringLatitude);
			
			stringLongitude = String.valueOf(gpsTracker.longitude);
			textview = (TextView)findViewById(R.id.fieldLongitude);
			textview.setText(stringLongitude);
			
			country = gpsTracker.getCountryName(this);
			textview = (TextView)findViewById(R.id.fieldCountry);
			textview.setText(country);
			
			city = gpsTracker.getLocality(this);
			textview = (TextView)findViewById(R.id.fieldCity);
			textview.setText(city);
			
			postalCode = gpsTracker.getPostalCode(this);
			textview = (TextView)findViewById(R.id.fieldPostalCode);
			textview.setText(postalCode);
			
			addressLine = gpsTracker.getAddressLine(this);
			textview = (TextView)findViewById(R.id.fieldAddressLine);
			textview.setText(addressLine);
		}
		else
		{
			// can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
			gpsTracker.showSettingsAlert();
		}
	}

	private void intializeVar() {
		// TODO Auto-generated method stub
		Toast.makeText(context, "If all the location fields are not available,go back and press the Get Location button again !", Toast.LENGTH_LONG).show();
		extras = getIntent().getExtras();
		if(extras!=null){
			btnSelected=extras.getString("Button");
			img1=extras.getString("Path1");
			img2=extras.getString("Path2");
			img3=extras.getString("Path3");
			img4=extras.getString("path4");
		}
		
		returnLocation=(Button) findViewById(R.id.bReturnLocation);
		returnLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent p = new Intent(GetCurrentLocation.this,NewEmail.class);
				p.putExtra("Button", btnSelected);
				p.putExtra("Path1",img1);
				p.putExtra("Path2",img2);
				p.putExtra("Path3",img3);
				p.putExtra("Path4",img4);
				p.putExtra("Latitude", stringLatitude);
				p.putExtra("Longitude", stringLongitude);
				p.putExtra("Country", country);
				p.putExtra("City", city);
				p.putExtra("PostalCode", postalCode);
				p.putExtra("Address", addressLine); 
				startActivity(p);
				//Toast.makeText(getBaseContext(),"Details returned. PRESS BACK BUTTON",Toast.LENGTH_LONG).show();
			}
		});
	}



}
