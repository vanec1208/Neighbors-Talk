package program_movil.neighborstalk;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Alerts extends Activity {
	
	final Context context = this;
	protected Button Create;
	protected Button Home;
	protected EditText description;
	protected Button createA;
	protected Button cancelA;
	protected ImageButton updateAlert;
	protected String list_Alerts = "";
	protected LinearLayout linearLayout;
	protected ArrayList <listAlerts> listalerts; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_alerts);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		StrictMode.enableDefaults();
		
		
		Create = (Button)findViewById(R.id.createAlert);
		Home = (Button)findViewById(R.id.homeAlert);
		updateAlert = (ImageButton)findViewById(R.id.updateAlert);
		
		list_Alerts = Rest.GET("http://secure-plains-4968.herokuapp.com/alerts.json");
		arrayAlerts(list_Alerts, "neighborhood_id", MyappGV.neighborhood_id_now);
		
		linearLayout = (LinearLayout)findViewById(R.id.allAlerts);
		linearLayout.removeAllViews();
		
		for(int i = 0; i < listalerts.size(); i++){
			LinearLayout alertMyNeigh = new LinearLayout(this);
			alertMyNeigh.setOrientation(LinearLayout.VERTICAL);
			
			String name = "";
			String username = "";
			
			for(int j = 0; j < LoginUser.listusers.size(); j ++){
				if(listalerts.get(i).getUser_id().equals(LoginUser.listusers.get(j).getId())){
					name = LoginUser.listusers.get(j).getName();
					username = LoginUser.listusers.get(j).getUserName();
				}
			}
			
			Spannable wordtoSpan = new SpannableString("$" + username);        
			wordtoSpan.setSpan(new ForegroundColorSpan(Color.rgb(0, 153, 51)), 0, username.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			
			TextView descriptionAlert = new TextView(this);
			descriptionAlert.setTextSize(18);
			descriptionAlert.append(name + " ");
			descriptionAlert.append(wordtoSpan);
			descriptionAlert.append("\n" + listalerts.get(i).getDescription() + "\n" + "\n");
			alertMyNeigh.addView(descriptionAlert);
			linearLayout.addView(alertMyNeigh);
		}
		
		createAlert();
		
		Home.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if(arg1.getAction() == MotionEvent.ACTION_UP){
								Home.setBackgroundColor(Color.rgb(0, 153, 51));
								Intent intent = new Intent(Alerts.this, App.class);
								startActivity(intent);
								return true;
						}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
								Home.setBackgroundColor(Color.rgb(0, 204, 51));
								return false;
						}else{
								return false;
								}
							}
					});
		
		 
		updateAlert.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_UP){
						updateAlert.setBackgroundColor(Color.rgb(0, 153, 51));
						Intent intent = getIntent();
						finish();
						startActivity(intent);
						return true;
				}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
						updateAlert.setBackgroundColor(Color.rgb(0, 204, 51));
						return false;
				}else{
						return false;
						}
					}
			});
		
	}
	
	public void createAlert(){
		
			Create.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View arg0, MotionEvent arg1) {
					if(arg1.getAction() == MotionEvent.ACTION_UP){
						Create.setBackgroundColor(Color.rgb(0, 153, 51));
						LayoutInflater lay = LayoutInflater.from(context);
						View promptsView = lay.inflate(R.layout.create_alert, null);
						final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
						alertDialogBuilder.setView(promptsView);
						alertDialogBuilder.setCancelable(false);
						
						description = (EditText)promptsView.findViewById(R.id.descriptionA);
						
						createA = (Button)promptsView.findViewById(R.id.safeAlert);
						cancelA = (Button)promptsView.findViewById(R.id.cancelA);
						
						final AlertDialog alertDialog = alertDialogBuilder.create();
						createA.setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View arg0, MotionEvent arg1) {
								if(arg1.getAction() == MotionEvent.ACTION_UP){
										createA.setBackgroundColor(Color.rgb(0, 0, 0));
										
										List<NameValuePair> AcreateAlerts = new ArrayList<NameValuePair>();
								        AcreateAlerts.add(new BasicNameValuePair("description", description.getText().toString()));
								        AcreateAlerts.add(new BasicNameValuePair("user_id", MyappGV.user_id_now));
								        AcreateAlerts.add(new BasicNameValuePair("neighborhood_id", MyappGV.neighborhood_id_now));
								        
								        Rest.POST("http://secure-plains-4968.herokuapp.com/alerts.json", AcreateAlerts);
								        
										Toast.makeText(getApplicationContext(), "Alerta Creada", Toast.LENGTH_SHORT).show();
										alertDialog.dismiss();
										return true;
								}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
										createA.setBackgroundColor(Color.rgb(192,192 , 192));
										return false;
								}else{
										return false;
										}
									}
							});
						
							cancelA.setOnTouchListener(new OnTouchListener() {
														
									@Override
									public boolean onTouch(View arg0, MotionEvent arg1) {
										if(arg1.getAction() == MotionEvent.ACTION_UP){
												cancelA.setBackgroundColor(Color.rgb(0, 0, 0));
												alertDialog.cancel();
												return true;
										}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
												cancelA.setBackgroundColor(Color.rgb(192,192 , 192));
												return false;
										}else{
												return false;
												}
											}
							});
						
						alertDialog.show();
						return true;
					}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
						Create.setBackgroundColor(Color.rgb(0, 204, 51));
						return false;
					}else{
						return false;
					}
				}
			});
	  
	}
	
	public void arrayAlerts(String resultAlerts, String nombre, String nombre_mio){
	      
	      try {
	    	  listalerts = new ArrayList<listAlerts>();
			  JSONArray listAlerts = new JSONArray(resultAlerts);
			   for(int i=0; i<listAlerts.length();i++){
				   JSONObject list = listAlerts.getJSONObject(i);
				   if(list.getString(nombre).equals(nombre_mio)){
					  listAlerts lista = new listAlerts(list.getString("id"),list.getString("description"),list.getString("user_id"));
					  listalerts.add(lista);
				   }  
			   }
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
	  	  
	  }

}
