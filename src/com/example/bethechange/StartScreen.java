package com.example.bethechange;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import com.sunny.bethechange.R;

public class StartScreen extends Activity{
	
	MediaPlayer bSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.startscreen);
		bSong = MediaPlayer.create(StartScreen.this,R.raw.startscreen);
		bSong.start();
		Thread timer = new Thread() {
			public void run() {
				try{
					sleep(3000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openMenu = new Intent("com.example.bethechange.MAINACTIVITY");
					startActivity(openMenu);
				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		bSong.release();
		finish();
	}
	
}
