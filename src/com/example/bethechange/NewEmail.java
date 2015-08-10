package com.example.bethechange;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.sunny.bethechange.R;

public class NewEmail extends Activity{

	private static final String CHILD_EMAIL = "complaints.ncpcr@gmail.com";
	private static final String ROAD_EMAIL  = "grievance@sanjoghelpline.in";
	private static final String GARBAGE_EMAIL= "grievance@sanjoghelpline.in";
	private static final String WOMEN_EMAIL =  "complaintcell-ncw@nic.in";
	
	
	Context context;
	TextView tv,tvNewEmail,tvNewEmailAd,tvNewSubject,tvNewContent,tvNewLocation,tvLocation;
	EditText etNewSubject,etNewContent;
	String newSubject,newContent,newLocation,mail;
	Button newSend;
	Bundle extras;
	String[] images;
	String gpsLocation,revContent,btnSelected,image1,image2,image3,image4,latitude,longitude,country,city,postalCode,address;
	ArrayList<Uri> newUri;
	int i = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		context = this;
		setContentView(R.layout.newemail);
		initializeVariables();
	}

	private void initializeVariables() {
		// TODO Auto-generated method stub
	
		tv = (TextView) findViewById(R.id.tvNew);
		tvNewEmail = (TextView) findViewById(R.id.tvNewEmail);
		tvNewEmailAd = (TextView) findViewById(R.id.tvNewEmailAd);
		tvNewSubject = (TextView) findViewById(R.id.tvNewSubject);
		tvNewContent = (TextView) findViewById(R.id.tvNewContent);
		tvLocation   = (TextView) findViewById(R.id.tvLocation);
		
		etNewSubject = (EditText) findViewById(R.id.NewSubject);
		etNewContent = (EditText) findViewById(R.id.NewContent);
		
		newSend = (Button) findViewById(R.id.bNewSend);
	
		
		extras = getIntent().getExtras();
		if(extras!=null){
			btnSelected=extras.getString("Button");
			
			
			//Sets the E-mail field according to the value received by the Bundle.
			if(btnSelected.equalsIgnoreCase("child")){
				tvNewEmailAd.setText(CHILD_EMAIL);
				mail=CHILD_EMAIL;
			}else if(btnSelected.equalsIgnoreCase("garbage")){
				tvNewEmailAd.setText(GARBAGE_EMAIL);
				mail=GARBAGE_EMAIL;
			}else if(btnSelected.equalsIgnoreCase("road")){
				tvNewEmailAd.setText(ROAD_EMAIL);
				mail=ROAD_EMAIL;
			}else if(btnSelected.equalsIgnoreCase("women")){
				tvNewEmailAd.setText(WOMEN_EMAIL);
				mail=WOMEN_EMAIL;
			}
			
			image1=extras.getString("Path1");
			image2=extras.getString("Path2");
			image3=extras.getString("Path3");
			image4=extras.getString("path4");
			
			
			latitude=extras.getString("Latitude");
			longitude=extras.getString("Longitude");
			country=extras.getString("Country");
			city=extras.getString("City");
			postalCode=extras.getString("PostalCode");
			address=extras.getString("Address");
		
		
		}
	
		gpsLocation = "Longitude:" + longitude+"\n"+"Latitude :" + latitude +"\n"+"Country:"+country+"\n"+"City:"+city+"\n"+"Postal Code:"+postalCode+"\n"+"Address:"+address;
		
		
		newSend.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("null")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				convertEditTextToString();
				revContent = newContent + "\n\n" + gpsLocation;
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
				emailIntent.setType("text/plain");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{mail});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,newSubject);
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,revContent);
				
				newUri = new ArrayList<Uri>();
				
			
				if (image1 != null){
					File file1= new File(image1);
					Uri u1 = Uri.fromFile(file1);
					newUri.add(u1);
				}
				if (image2 != null){
					File file2= new File(image2);
					Uri u2 = Uri.fromFile(file2);
					newUri.add(u2);
				}
				if (image3 != null){
					File file3= new File(image3);
					Uri u3 = Uri.fromFile(file3);
					newUri.add(u3);
				}
				if (image4 != null){
					File file4= new File(image4);
					Uri u4 = Uri.fromFile(file4);
					newUri.add(u4);
				}
				
				
				emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, newUri);
				startActivity(emailIntent);
			}
		});
	}
	
	public String[] BitMapToString(Bitmap bitmap[]){
		int i=0;
		 String[] temp= new String[2000];
		while(bitmap[i] !=null){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap[i].compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        temp[i]=Base64.encodeToString(b, Base64.DEFAULT);
        i++;
		}
        return temp;
  }
	
	private boolean convertEditTextToString(){
		
		newSubject = etNewSubject.getText().toString();
		newContent = etNewContent.getText().toString();
	
		if(!newSubject.equals("") && !newContent.equals("") ){
	         return true;
	  } else {
	       Toast.makeText(context, "Please fill out all edit text boxes", Toast.LENGTH_LONG).show();
	       return false;
	   }
		
	}

}
