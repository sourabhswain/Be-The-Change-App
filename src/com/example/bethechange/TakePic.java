package com.example.bethechange;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.sunny.bethechange.R;

public class TakePic extends Activity {

    private static final int REQUEST_IMAGE = 100;   
    private static final String TAG = "MainActivity";

    Bundle extras;
	String value;
    TextView tv;
    ImageView iv1,iv2,iv3,iv4;
    File destination1,destination2,destination3,destination4;
    String imagePath1,imagePath2,imagePath3,imagePath4;
    String name1,name2,name3,name4;
    int i=0;
    Button bEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.takepic);

        tv  = (TextView)  findViewById(R.id.textView1);
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        iv4 = (ImageView) findViewById(R.id.iv4);
        
                	
        name1 =   dateToString(new Date(),"yyyy-MM-dd-hh-mm-ss") + "1"; 
        name2 =   dateToString(new Date(),"yyyy-MM-dd-hh-mm-ss") + "2";
        name3 =   dateToString(new Date(),"yyyy-MM-dd-hh-mm-ss") + "3";
        name4 =   dateToString(new Date(),"yyyy-MM-dd-hh-mm-ss") + "4";
        
        destination1 = new File(Environment.getExternalStorageDirectory(), name1 + ".jpg");
        destination2 = new File(Environment.getExternalStorageDirectory(), name2 + ".jpg");       
        destination3 = new File(Environment.getExternalStorageDirectory(), name3 + ".jpg");
        destination4 = new File(Environment.getExternalStorageDirectory(), name4 + ".jpg");
        
        bEmail = (Button) findViewById(R.id.bCamEmail);
        
        extras = getIntent().getExtras();
		if(extras!=null)
			value=extras.getString("Button");
        
        iv1.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination1));
                startActivityForResult(intent, 1);

            }
        });
   
    
        iv2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination2));
                startActivityForResult(intent, 2);

            }
        });
    
        iv3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination3));
                startActivityForResult(intent, 3);

            }
        });
        
        iv4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(destination4));
                startActivityForResult(intent, 4);

            }
        });
        
        bEmail.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent e = new Intent(TakePic.this,GetCurrentLocation.class);
				e.putExtra("Button",value);
				e.putExtra("Path1",imagePath1);
				e.putExtra("Path2",imagePath2);
				e.putExtra("Path3",imagePath3);
				e.putExtra("Path4",imagePath4);
				startActivity(e);
				
			}
		});
        
 
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if( requestCode == 1 && resultCode == Activity.RESULT_OK ){
            try {
                FileInputStream in = new FileInputStream(destination1);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 10;
                imagePath1 = destination1.getAbsolutePath();
                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
                iv1.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else if( requestCode == 2 && resultCode == Activity.RESULT_OK ){
            try {
                FileInputStream in = new FileInputStream(destination2);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 10;
                imagePath2 = destination2.getAbsolutePath();
                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
                iv2.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }else if( requestCode == 3 && resultCode == Activity.RESULT_OK ){
            try {
                FileInputStream in = new FileInputStream(destination3);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 10;
                imagePath3 = destination3.getAbsolutePath();
                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
                iv3.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else if( requestCode == 4 && resultCode == Activity.RESULT_OK ){
            try {
                FileInputStream in = new FileInputStream(destination4);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 10;
                imagePath4 = destination4.getAbsolutePath();
                Bitmap bmp = BitmapFactory.decodeStream(in, null, options);
                iv4.setImageBitmap(bmp);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }else {
            tv.setText("Request cancelled");
        }
    }

    public String dateToString(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

}