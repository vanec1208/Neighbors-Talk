package program_movil.neighborstalk;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Events extends Activity {

	final Context context = this;
	protected Button Create;
	protected Button Edit;
	protected Button Home;
	protected TextView allEvents;
	protected EditText title;
	protected EditText description;
	protected EditText date;
	protected EditText time;
	protected EditText place;
	protected Button createE;
	protected Button cancelE;
	protected static EditText EditDate;
	protected DatePicker datePicker;
	protected static String Date;
	protected static Button addComment;
	protected ImageButton updateEvent;
	protected String list_Events = "";
	protected String list_Comments = "";
	protected static ArrayList <listEvents> listevents;
	protected ArrayList <listComments> commentss;
	protected LinearLayout linearLayout;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		StrictMode.enableDefaults();
		
		Create = (Button)findViewById(R.id.createEvent);
		Edit = (Button)findViewById(R.id.editEvent);
		Home = (Button)findViewById(R.id.homeEvent);
		updateEvent = (ImageButton)findViewById(R.id.updateEvent);
		
		list_Events = Rest.GET("http://secure-plains-4968.herokuapp.com/events.json");
		arrayEvents(list_Events, "neighborhood_id", MyappGV.neighborhood_id_now);
		
		linearLayout = (LinearLayout)findViewById(R.id.linearMyEvents);
		linearLayout.removeAllViews();
		
		for(int i = 0; i < listevents.size(); i++){
			LinearLayout eventMyNeigh = new LinearLayout(this);
			eventMyNeigh.setOrientation(LinearLayout.VERTICAL);
			
			String name = "";
			String username = "";
			final String id_evento = new String(listevents.get(i).getId());
			
			for(int j = 0; j < LoginUser.listusers.size(); j ++){
				if(listevents.get(i).getUser_id().equals(LoginUser.listusers.get(j).getId())){
					name = LoginUser.listusers.get(j).getName();
					username = LoginUser.listusers.get(j).getUserName();
				}
			}
			
			Spannable wordtoSpan = new SpannableString("$" + username);        
			wordtoSpan.setSpan(new ForegroundColorSpan(Color.rgb(0, 153, 51)), 0, username.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			TextView descriptionEvent = new TextView(this);
			final ImageButton comment = new ImageButton(this);
			
			descriptionEvent.setTextSize(18);
			comment.setBackgroundColor(Color.rgb(0, 153, 51));
			
			descriptionEvent.setWidth(350);
			
			descriptionEvent.append(name + " ");
			descriptionEvent.append(wordtoSpan);
			descriptionEvent.append("\n" + "Title: " + listevents.get(i).getTitle() + "\n" + "Description: " + listevents.get(i).getDescription() + "\n" + "Date: " + listevents.get(i).getDate() + "\n" + "Time: " + listevents.get(i).getTime() + "\n" + "Place: " + listevents.get(i).getPlace());
			comment.setImageResource(R.drawable.comment_icon);
		
			comment.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					if(event.getAction() == MotionEvent.ACTION_UP){
						comment.setBackgroundColor(Color.rgb(0, 153, 51));
						LayoutInflater lay = LayoutInflater.from(context);
						View promptsView = lay.inflate(R.layout.comments, null);
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
						alertDialogBuilder.setView(promptsView);
						final AlertDialog alertDialog = alertDialogBuilder.create();
						
						list_Comments = Rest.GET("http://secure-plains-4968.herokuapp.com/comments.json");
						arrayComments(list_Comments, "event_id", id_evento);
						
						final TextView comments = (TextView)promptsView.findViewById(R.id.allComments);
						comments.setTextSize(18);
						
						for(int k = 0; k< commentss.size(); k++){
							
							String name1 = "";
							String username1 = "";
							
							for(int h = 0; h < LoginUser.listusers.size(); h ++){
								if(commentss.get(k).getUser_id().equals(LoginUser.listusers.get(h).getId())){
									name1 = LoginUser.listusers.get(h).getName();
									username1 = LoginUser.listusers.get(h).getUserName();
								}
							}
							
							Spannable wordtoSpan = new SpannableString("$" + username1);        
							wordtoSpan.setSpan(new ForegroundColorSpan(Color.rgb(0, 153, 51)), 0, username1.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
							comments.append(name1 + " ");
							comments.append(wordtoSpan);
							comments.append("\n");
							comments.append(commentss.get(k).getContent());
							comments.append("\n" + "\n");
						}
						
						final EditText insert = (EditText)promptsView.findViewById(R.id.insertComment);
						addComment = (Button)promptsView.findViewById(R.id.addComment);
						addComment.setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View arg0, MotionEvent arg1) {
								if(arg1.getAction() == MotionEvent.ACTION_UP){
										addComment.setBackgroundColor(Color.rgb(0, 153, 51));
										List<NameValuePair> AcreateComments = new ArrayList<NameValuePair>();
								        AcreateComments.add(new BasicNameValuePair("content", insert.getText().toString()));
								        AcreateComments.add(new BasicNameValuePair("user_id", MyappGV.user_id_now));
								        AcreateComments.add(new BasicNameValuePair("event_id", id_evento));
										Toast.makeText(getApplicationContext(), "Comentario realizado", Toast.LENGTH_LONG).show();
										return true;
								}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
										addComment.setBackgroundColor(Color.rgb(0, 204, 51));
										return false;
								}else{
										return false;
										}
									}
							});
						
						alertDialog.show();
						return true;
				}else if(event.getAction() == MotionEvent.ACTION_DOWN){
					comment
					.setBackgroundColor(Color.rgb(0, 204, 51));
					return false;
			}else{
					return false;
					}
				}
			});
			
			eventMyNeigh.addView(descriptionEvent);
			eventMyNeigh.addView(comment);
			linearLayout.addView(eventMyNeigh);
		}	
	
		/*allEvents = (TextView)findViewById(R.id.allEvents1);
		Spannable comment = new SpannableString("Comments"); 
		allEvents.append("Luis Herrera " + "@Negrito del Sabor" + "\n" + "'Alcohol sin control'" + "\n" + "Rumbeteo en mi casa.. Alcohol ilimitado" + "\n" + "Date: 19 de mayo" + "\n" + "Time: 9:00pm" + "\n" + "Place: cra 34 # 107 - 35");
		allEvents.append("\n" + "\n");
		comment.setSpan(new ForegroundColorSpan(Color.rgb(0, 153, 51)), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		allEvents.append(comment);
		allEvents.append("\n");*/
		
		//presscomment(allEvents);
		
		createEvent();
		 

		Edit.setOnTouchListener(new OnTouchListener() {
							
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_UP){
						Edit.setBackgroundColor(Color.rgb(0, 153, 51));
						Intent intent = new Intent(Events.this, Edit_myEvents.class);
						startActivity(intent);	
						return true;
				}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
						Edit.setBackgroundColor(Color.rgb(0, 204, 51));
						return false;
				}else{
						return false;
						}
					}
			});

		Home.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_UP){
						Home.setBackgroundColor(Color.rgb(0, 153, 51));
						Intent intent = new Intent(Events.this, App.class);
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
		
		
			updateEvent.setOnTouchListener(new OnTouchListener() {
						
						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							if(arg1.getAction() == MotionEvent.ACTION_UP){
									updateEvent.setBackgroundColor(Color.rgb(0, 153, 51));
									Intent intent = getIntent();
									finish();
									startActivity(intent);
									return true;
							}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
									updateEvent.setBackgroundColor(Color.rgb(0, 204, 51));
									return false;
							}else{
									return false;
									}
								}
						});
	}
	
	@SuppressLint("NewApi")
	public static class DatePickerFragment1 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		// Create a new instance of DatePickerDialog and return it
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
		
		public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
			Event_Date(year,  month+1, day);
		}
	}
		
		  public static void Event_Date(int year, int month, int day) {
			  Date = String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(year);
			  EditDate.setText(month+"-"+day+"-"+year);
		  }

		  
		  public void presscomment(TextView Event){
				Event.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						LayoutInflater lay = LayoutInflater.from(context);
						View promptsView = lay.inflate(R.layout.comments, null);
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
						alertDialogBuilder.setView(promptsView);
						final AlertDialog alertDialog = alertDialogBuilder.create();
						
						
						addComment = (Button)promptsView.findViewById(R.id.addComment);
						
						addComment.setOnTouchListener(new OnTouchListener() {
							
							@Override
							public boolean onTouch(View arg0, MotionEvent arg1) {
								if(arg1.getAction() == MotionEvent.ACTION_UP){
										addComment.setBackgroundColor(Color.rgb(0, 153, 51));
										Toast.makeText(getApplicationContext(), "Comentario realizado", Toast.LENGTH_LONG).show();
										return true;
								}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
										addComment.setBackgroundColor(Color.rgb(0, 204, 51));
										return false;
								}else{
										return false;
										}
									}
							});
						
						alertDialog.show();
					}
				});
		  }
		  
		  public void createEvent(){
				Create.setOnTouchListener(new OnTouchListener() {
					
					@Override
					public boolean onTouch(View arg0, MotionEvent arg1) {
						if(arg1.getAction() == MotionEvent.ACTION_UP){
							Create.setBackgroundColor(Color.rgb(0, 153, 51));
							LayoutInflater lay = LayoutInflater.from(context);
							View promptsView = lay.inflate(R.layout.create_event, null);
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
							alertDialogBuilder.setView(promptsView);
							alertDialogBuilder.setCancelable(false);
							
							title = (EditText)promptsView.findViewById(R.id.title);
							description = (EditText)promptsView.findViewById(R.id.description);
							date = (EditText)promptsView.findViewById(R.id.dateEvent);
							time = (EditText)promptsView.findViewById(R.id.time);
							place = (EditText)promptsView.findViewById(R.id.place);
							
							createE = (Button)promptsView.findViewById(R.id.safeEvent);
							createE.setText("CREATE");
							
							cancelE = (Button)promptsView.findViewById(R.id.cancelE);

							final AlertDialog alertDialog = alertDialogBuilder.create();
							
							EditDate = (EditText)promptsView.findViewById(R.id.dateEvent);
							
							EditDate.setOnClickListener(new OnClickListener() {
								
								@SuppressLint("NewApi")
								@Override
								public void onClick(View v) {
									DialogFragment newFragment = new DatePickerFragment1();
								    newFragment.show(getFragmentManager(), "datePicker");
									
								}
							});
							
							createE.setOnTouchListener(new OnTouchListener() {
								
								@Override
								public boolean onTouch(View arg0, MotionEvent arg1) {
									if(arg1.getAction() == MotionEvent.ACTION_UP){
											createE.setBackgroundColor(Color.rgb(0, 0, 0));
											
											List<NameValuePair> AcreateEvents = new ArrayList<NameValuePair>();
									        AcreateEvents.add(new BasicNameValuePair("title", title.getText().toString()));
									        AcreateEvents.add(new BasicNameValuePair("description", description.getText().toString()));
									        AcreateEvents.add(new BasicNameValuePair("date_event", date.getText().toString()));
									        AcreateEvents.add(new BasicNameValuePair("time_event", time.getText().toString()));
									        AcreateEvents.add(new BasicNameValuePair("place", place.getText().toString()));
									        AcreateEvents.add(new BasicNameValuePair("user_id", MyappGV.user_id_now));
									        AcreateEvents.add(new BasicNameValuePair("neighborhood_id",MyappGV.neighborhood_id_now));
											
											Rest.POST("http://secure-plains-4968.herokuapp.com/events.json", AcreateEvents );
									        
									        Toast.makeText(getApplicationContext(), "Evento Creado", Toast.LENGTH_SHORT).show();
											alertDialog.dismiss();
											return true;
									}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
											createE.setBackgroundColor(Color.rgb(192,192 , 192));
											return false;
									}else{
											return false;
											}
										}
								});
							
							cancelE.setOnTouchListener(new OnTouchListener() {
								
								@Override
								public boolean onTouch(View arg0, MotionEvent arg1) {
									if(arg1.getAction() == MotionEvent.ACTION_UP){
											cancelE.setBackgroundColor(Color.rgb(0, 0, 0));
											alertDialog.cancel();
											return true;
									}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
											cancelE.setBackgroundColor(Color.rgb(192,192 , 192));
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
		  
		  
		@SuppressLint("NewApi")
		public void arrayEvents(String resultEvents, String nombre, String nombre_mio){
		      
		      try {
		    	  listevents = new ArrayList<listEvents>();
				  JSONArray listEvents = new JSONArray(resultEvents);
				   for(int i=0; i<listEvents.length();i++){
					   JSONObject list = listEvents.getJSONObject(i);
					   if(list.getString(nombre).equals(nombre_mio)){
						  listEvents lista = new listEvents(list.getString("id"), list.getString("title"), list.getString("description"), list.getString("date_event"), list.getString("time_event"), list.getString("place"), list.getString("user_id"));
						  listevents.add(lista);
					   }  
				   }
			   } catch (Exception e) {
				// TODO: handle exception
				   Log.e("log_tag", "Error Parsing Data "+e.toString());
			   }
		  	  
		  }
		
		public void arrayComments(String resultEvents, String nombre, String nombre_mio){
		      
		      try {
		    	  commentss = new ArrayList <listComments>();
				  JSONArray listComments = new JSONArray(resultEvents);
				   for(int i=0; i<listComments.length();i++){
					   JSONObject list = listComments.getJSONObject(i);
					   if(list.getString(nombre).equals(nombre_mio)){
						  listComments lista = new listComments(list.getString("id"), list.getString("content"), list.getString("user_id"), list.getString("event_id") ); 
						  commentss.add(lista);
					   }  
				   }
			   } catch (Exception e) {
				// TODO: handle exception
				   Log.e("log_tag", "Error Parsing Data "+e.toString());
			   }
		  	  
		  }
}
