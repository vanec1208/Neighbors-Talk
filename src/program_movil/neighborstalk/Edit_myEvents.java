package program_movil.neighborstalk;

import java.util.Calendar;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Edit_myEvents extends Activity {

	protected Button editE;
	protected Button cancelE;
	protected static EditText EditDate;
	protected DatePicker datePicker;
	protected ImageButton back;
	protected LinearLayout linearLayout;
	protected ScrollView Scroll;
	final Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_my_events);
		StrictMode.enableDefaults();
		
		linearLayout = (LinearLayout)findViewById(R.id.linear);
		linearLayout.removeAllViews();
		for(int i = 0; i < Events.listevents.size(); i++){
			if(Events.listevents.get(i).getUser_id().equals(MyappGV.user_id_now)){
					
					final LinearLayout myEvents = new LinearLayout(this);
					myEvents.setOrientation(LinearLayout.HORIZONTAL);
		
					final String id_evento = new String(Events.listevents.get(i).getId());
					
					TextView descriptionEvent = new TextView(this);
					descriptionEvent.setTextSize(18);
					descriptionEvent.setWidth(350);
					
					final String tit = new String(Events.listevents.get(i).getTitle());
					final String des = new String(Events.listevents.get(i).getDescription());
					final String dat = new String(Events.listevents.get(i).getDate());
					final String tim = new String(Events.listevents.get(i).getTime());
					final String pla = new String(Events.listevents.get(i).getPlace());
					
					descriptionEvent.append("Title: " + tit + "\n" + "Description: " + des + "\n" + "Date: " + dat + "\n" + "Time: " + tim + "\n" + "Place: " + pla + "\n" + "\n");
					
					final ImageButton Edit = new ImageButton(this);
					Edit.setBackgroundColor(Color.rgb(255, 255, 255));
					Edit.setImageResource(R.drawable.edit_select_icon);
					
					Edit.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							if(arg1.getAction() == MotionEvent.ACTION_UP){
									Edit.setBackgroundColor(Color.rgb(255, 255, 255));
									LayoutInflater lay = LayoutInflater.from(context);
									View promptsView = lay.inflate(R.layout.create_event, null);
									AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
									alertDialogBuilder.setView(promptsView);
									alertDialogBuilder.setCancelable(false);
									
									EditText title1 = (EditText)promptsView.findViewById(R.id.title);
									EditText description1 = (EditText)promptsView.findViewById(R.id.description);
									EditText date1 = (EditText)promptsView.findViewById(R.id.dateEvent);
									EditText time1 = (EditText)promptsView.findViewById(R.id.time);
									EditText place1 = (EditText)promptsView.findViewById(R.id.place);
									
									title1.setText(tit);
									description1.setText(des);
									date1.setText(dat);
									time1.setText(tim);
									place1.setText(pla);
									
									editE = (Button)promptsView.findViewById(R.id.safeEvent);
									editE.setText("SAVE CHANGES");
									cancelE = (Button)promptsView.findViewById(R.id.cancelE);
		
									final AlertDialog alertDialog = alertDialogBuilder.create();
									
									EditDate = (EditText)promptsView.findViewById(R.id.dateEvent);
									
									EditDate.setOnClickListener(new OnClickListener() {
									
										@Override
										public void onClick(View v) {
											DialogFragment newFragment = new DatePickerFragment2();
										    newFragment.show(getFragmentManager(), "datePicker");
										   
										}
									});
									
									editE.setOnTouchListener(new OnTouchListener() {
										
										@Override
										public boolean onTouch(View arg0, MotionEvent arg1) {
											if(arg1.getAction() == MotionEvent.ACTION_UP){
													editE.setBackgroundColor(Color.rgb(0, 0, 0));
													Toast.makeText(getApplicationContext(), "Evento Modificado", Toast.LENGTH_SHORT).show();
													alertDialog.dismiss();
													return true;
											}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
													editE.setBackgroundColor(Color.rgb(192,192 , 192));
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
									Edit.setBackgroundColor(Color.rgb(0, 204, 51));
									return false;
							}else{
									return false;
									}
								}
						});
					
					
					final ImageButton Delete = new ImageButton(this);
					Delete.setBackgroundColor(Color.rgb(255, 255, 255));
					Delete.setImageResource(R.drawable.cross_icon);
					
					Delete.setOnTouchListener(new OnTouchListener() {
						@Override
						public boolean onTouch(View arg0, MotionEvent arg1) {
							if(arg1.getAction() == MotionEvent.ACTION_UP){
									Delete.setBackgroundColor(Color.rgb(255, 255, 255));
									myEvents.removeAllViews();
									Toast.makeText(getApplicationContext(), "Evento eliminado", Toast.LENGTH_SHORT).show();
									return true;
							}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
									Delete.setBackgroundColor(Color.rgb(0, 204, 51));
									return false;
							}else{
									return false;
									}
								}
						});
					
					myEvents.addView(descriptionEvent);
					myEvents.addView(Edit);
					myEvents.addView(Delete);
					linearLayout.addView(myEvents);
			}
		}
		
		
		back = (ImageButton)findViewById(R.id.backMyEvents);
		
		back.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if(arg1.getAction() == MotionEvent.ACTION_UP){
						back.setBackgroundColor(Color.rgb(255, 255, 255));
						Intent intent = new Intent(Edit_myEvents.this, Events.class);
						startActivity(intent);
						return true;
				}else if(arg1.getAction() == MotionEvent.ACTION_DOWN){
						back.setBackgroundColor(Color.rgb(0, 204, 51));
						return false;
				}else{
						return false;
						}
					}
			});
	}
	
	
	public static class DatePickerFragment2 extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
			  EditDate.setText(month+"-"+day+"-"+year);
		    }


}
