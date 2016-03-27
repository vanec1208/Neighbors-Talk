package program_movil.neighborstalk;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.view.View.OnTouchListener;

public class App extends Activity {
	
	protected Button Event;
	protected Button Alert;
	protected Button Setting;
	protected Button Logout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Event = (Button)findViewById(R.id.events);
		Alert = (Button)findViewById(R.id.alerts);
		Setting = (Button)findViewById(R.id.settings);
		Logout= (Button)findViewById(R.id.logout);

		
		Event.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if(arg1.getAction() == MotionEvent.ACTION_UP){
								Event.setBackgroundColor(Color.rgb(255, 255, 255));
								Intent intent = new Intent(App.this, Events.class);
								startActivity(intent);
								return true;
						}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
								Event.setBackgroundColor(Color.rgb(192,192 , 192));
								return false;
						}else{
								return false;
								}
							}
					});
		 
		Alert.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_UP){
						Alert.setBackgroundColor(Color.rgb(255, 255, 255));
						Intent intent = new Intent (App.this, Alerts.class);
						startActivity(intent);
						return true;
				}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
						Alert.setBackgroundColor(Color.rgb(192,192 , 192));
						return false;
				}else{
						return false;
						}
					}
			});

		Setting.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if(arg1.getAction() == MotionEvent.ACTION_UP){
								Setting.setBackgroundColor(Color.rgb(255, 255, 255));
								Intent intent = new Intent (App.this, Setting.class);
								startActivity(intent);	
								return true;
						}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
								Setting.setBackgroundColor(Color.rgb(192,192 , 192));
								return false;
						}else{
								return false;
								}
							}
					});
		
		
		
		Logout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_UP){
						Logout.setBackgroundColor(Color.rgb(255, 255, 255));
						Intent intent = new Intent (App.this, MainActivity.class);
						startActivity(intent);
						finish();
						MyappGV.username_now="";
						MyappGV.password_now="";
						MyappGV.birthday_now="";
						MyappGV.city_now="";
						MyappGV.neighbor_now="";
						MyappGV.gender_now="";
						MyappGV.email_now="";
						MyappGV.name_now="";
						return true;
				}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
						Logout.setBackgroundColor(Color.rgb(192,192 , 192));
						return false;
				}else{
						return false;
						}
					}
			});

	}	


}
