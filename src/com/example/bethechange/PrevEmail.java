package com.example.bethechange;

import java.io.File;
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.sunny.bethechange.R;

public class PrevEmail extends Activity{
	
	/*Constants section*/
	private static final String CHILD_EMAIL = "complaints.ncpcr@gmail.com";
	private static final String ROAD_EMAIL  = "grievance@sanjoghelpline.in";
	private static final String GARBAGE_EMAIL= "grievance@sanjoghelpline.in";
	private static final String WOMEN_EMAIL =  "complaintcell-ncw@nic.in";
	
	Context context;
	TextView tv,tvEmail,tvEmailAd,tvSubject,tvContent,tvLocation;
	EditText etSubject,etContent,etLocation;
	String subject,content,location,mail,revContent;
	Button send;
	String btnSelected;
	String[] imgPath;
	Bundle extras;
	ArrayList<Uri> uris;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//remove the below code
		context = this;
		setContentView(R.layout.prevemail);
		intializeVar();
	}

	private void intializeVar() {
		
		// Links xml to java
		tv = (TextView) findViewById(R.id.tv);
		tvEmail = (TextView) findViewById(R.id.tvEmail);
		tvEmailAd = (TextView) findViewById(R.id.tvEmailAd);
		tvSubject = (TextView) findViewById(R.id.tvSubject);
		tvContent = (TextView) findViewById(R.id.tvContent);
		tvLocation = (TextView) findViewById(R.id.tvLocation);
		
		etSubject = (EditText) findViewById(R.id.Subject);
		etContent = (EditText) findViewById(R.id.Content);
		etLocation= (EditText) findViewById(R.id.Location);
		
		send = (Button) findViewById(R.id.bSend);
	
		// IMPORTANT
		//The following code Gets the value of the button that was selected in the Options class using the key "Button"
		//By using the value of the button , we can determine which e-mail address to use for the 
		//problem type chosen by the user.
		//The value of the button selected is retrieved from the Bundle package that has been passed between the classes 
		//till it reaches here. The String is retrieved by using the method getString(KEY) ,here key = "Button".
		
		extras = getIntent().getExtras();
		if(extras!=null){
			btnSelected=extras.getString("Button");
			imgPath=extras.getStringArray("Path");
			
			//Sets the E-mail field according to the value received by the Bundle.
			if(btnSelected.equalsIgnoreCase("child")){
				tvEmailAd.setText(CHILD_EMAIL);
				mail=CHILD_EMAIL;
			}else if(btnSelected.equalsIgnoreCase("garbage")){
				tvEmailAd.setText(GARBAGE_EMAIL);
				mail=GARBAGE_EMAIL;
			}else if(btnSelected.equalsIgnoreCase("road")){
				tvEmailAd.setText(ROAD_EMAIL);
				mail=ROAD_EMAIL;
			}else if(btnSelected.equalsIgnoreCase("women")){
				tvEmailAd.setText(WOMEN_EMAIL);
				mail=WOMEN_EMAIL;
			}
			
		}	
		
	
		
		send.setOnClickListener(new View.OnClickListener() {
			
			@SuppressWarnings("null")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				convertEditTextToString();
				revContent = content + "\n" + location;
				Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
				emailIntent.setType("text/plain");
				emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] {mail});
				emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,subject);
				emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,revContent);
				
				uris = new ArrayList<Uri>();
				
				if(imgPath!= null){
				for(String file : imgPath) {
					File fileIn = new File(file);
					Uri u = Uri.fromFile(fileIn);
					uris.add(u);
				}
				}
				emailIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
				context.startActivity(Intent.createChooser(emailIntent, "Send Email.."));
			}
		});
	
	}
	
	
	//The following function is for converting the editText contents into Strings ,so that they can be passed to the mail.
		private boolean convertEditTextToString(){
			
			subject = etSubject.getText().toString();
			content = etContent.getText().toString();
			location= etLocation.getText().toString();
	
			if(!subject.equals("") && !content.equals("") && !location.equals("")){
		         return true;
		  } else {
		       Toast.makeText(context, "Please fill out all edit text boxes", Toast.LENGTH_LONG).show();
		       return false;
		   }
			
		}
	
	
}
