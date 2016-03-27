package program_movil.neighborstalk;
//libraries
import java.util.Calendar;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Modify extends Activity {
	//Variables 
	EditText mEditNombre;
	static EditText mEditBirthday;
	EditText mEditEmail;
	EditText mEditUserName;
	EditText mEditPassword;
	Button mboton_save;
	Button cancel;
	Spinner mspinner_city;
	Spinner mspinner_neighbor;
	Spinner mspinner_gender;
	DatePicker mdatePicker;
	private  String mName="";
	private String mUsername="";
	private String mPassword="";
	private String mCity="";
	private String mGender="";
	private String mNeighbor="";
	private String mEmail="";
	private static String mBirthday="";
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_profile);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//No rotar la pantalla
        //Recuperar los valores de las variables globales
        mName=MyappGV.name_now;
        mGender=MyappGV.gender_now;
        mEmail=MyappGV.email_now;
    	mUsername=MyappGV.username_now;
    	mPassword=MyappGV.password_now;
    	mCity=MyappGV.city_now;
    	mNeighbor=MyappGV.neighbor_now;
    	mBirthday=MyappGV.birthday_now;
    	
    	//Constructor items  layout
    	 mEditBirthday = (EditText)findViewById(R.id.meditText1);//edit-text fecha de cumpleaños
		 mEditNombre = (EditText) findViewById(R.id.mname); //edit-text con el nombre de la persona
		 mEditEmail = (EditText) findViewById(R.id.memail); //edit-text con el email de la persona
		 mEditUserName=(EditText) findViewById(R.id.medit_username); //edit-text con el usuario de la persona
		 mEditPassword=(EditText) findViewById(R.id.medit_password);//edit-text con la contraseña de la persona
		 mboton_save = (Button)findViewById(R.id.msave);//Enviar datos a la base de datos
		 cancel=(Button)findViewById(R.id.button_cancel); //Boton para cancelar la edicion
		 mspinner_gender = (Spinner) findViewById(R.id.mspinner_gender);
		 mspinner_city = (Spinner) findViewById(R.id.mspinner_city);
		 mspinner_neighbor = (Spinner) findViewById(R.id.mspinner_neighbor);
		 
		 //Poner valores en los edit text de las variables globales
		 mEditBirthday.setText(mBirthday); //Poner edittext referente fecha de nacimiento de la persona
		 mEditNombre.setText(mName); //Poner edittext referente al nombre de la persona
		 mEditEmail.setText(mEmail); //Poner edittext referente al correo de la persona
		 mEditUserName.setText(mUsername);//Poner edittext referente al usuario de la persona
		 mEditPassword.setText(mPassword);//Poner edittext referente al usuario de la persona
		 
	       
		 
		 //Colocar el valor de spinner sexo
		 @SuppressWarnings("rawtypes")
		ArrayAdapter myAdap = (ArrayAdapter)  mspinner_gender.getAdapter(); //cast to an ArrayAdapter
		 @SuppressWarnings("unchecked")
		 int gender_pos= myAdap.getPosition( mGender);
		 //Poner en valor por defecto
		 mspinner_gender.setSelection(gender_pos);
		 
		 
		 //Colocar el valor de spinner ciudad
		 @SuppressWarnings("rawtypes")
		ArrayAdapter myAdap1 = (ArrayAdapter)  mspinner_city.getAdapter(); //cast to an ArrayAdapter
		 @SuppressWarnings("unchecked")
		 int city_pos= myAdap1.getPosition( mCity);
		 //Poner en valor por defecto
		 mspinner_city.setSelection(city_pos);
		 
		 //Colocar el valor de spinner barrios
		 @SuppressWarnings("rawtypes")
		ArrayAdapter myAdap2 = (ArrayAdapter)  mspinner_neighbor.getAdapter(); //cast to an ArrayAdapter
		 @SuppressWarnings("unchecked")
		 int neigh_pos= myAdap2.getPosition( mNeighbor);
		 //Poner en valor por defecto
		 mspinner_neighbor.setSelection(neigh_pos);
		 
		 
		 
		 
		 mboton_save.setOnClickListener(new OnClickListener() {
				
				@SuppressLint("NewApi")
				public void onClick(View v) {
					
					
					//Nombre de la persona
			        mName=mEditNombre.getText().toString();
			        
			      //Usuario de la persona
			        mUsername=mEditUserName.getText().toString();
			        
			      //Contraseña de la persona
			       mPassword=mEditPassword.getText().toString();
			       
			       //Email  la persona
			       mEmail=mEditEmail.getText().toString();
			       
			     //Guardar variables globales
					MyappGV.username_now=mUsername;
					MyappGV.name_now=mName;
					MyappGV.password_now=mPassword;
					MyappGV.birthday_now=mBirthday;
					MyappGV.city_now=mCity;
					MyappGV.neighbor_now=mNeighbor;
					MyappGV.gender_now=mGender;
					MyappGV.email_now=mEmail;
					
					
			       if (mName.isEmpty()|| mUsername.isEmpty()|| mPassword.isEmpty()|| mNeighbor.isEmpty()||
			    		 mGender.isEmpty()|| mBirthday.isEmpty() || mCity.isEmpty()||mEmail.isEmpty()){
					Toast.makeText(getApplicationContext(), "Any item  should not be null" , Toast.LENGTH_SHORT).show();
			       }
			       else{
			    	  
			    	   //aqui debe hacer lo demas (actualizar db)
			    	   
			    	   Toast.makeText(getApplicationContext(), "Saved data", Toast.LENGTH_SHORT).show();
			    	   Intent intent = new Intent (Modify.this, Setting.class);
			    	   startActivity(intent);	
			    	   finish();
			    	   
			       }
			       
				}
			});
		 
		 
		 cancel.setOnClickListener(new OnClickListener() {
				
				
				public void onClick(View v) {
					
			    	   Intent intent = new Intent (Modify.this, Setting.class);
			    	   startActivity(intent);	
			    	   finish();
			    	   
			       }
			       
				
			});
		 
		 
		 
		//Conseguir el sexo
		
		 mspinner_gender.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
			                mGender =(String)parent.getSelectedItem();             
			         }
			 public void onNothingSelected(AdapterView<?> parent) {
				 			mGender="";
			 }
			});
		
        //Conseguir la ciudad
		
		 mspinner_city.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
			                mCity =(String)parent.getSelectedItem();             
			         }
			 public void onNothingSelected(AdapterView<?> parent) {
				 			mCity="";
			 }
			});
		
		
		//Conseguir barrio
		 
		 mspinner_neighbor.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
				                mNeighbor =(String)parent.getSelectedItem();             
				         }
				 public void onNothingSelected(AdapterView<?> parent) {
					 			mNeighbor="";
				 }
				});
		 
	}
	
		@SuppressLint("NewApi")
		public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
	
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
				Conseguir_Fecha(year,  month+1, day);
			}
			
			
			
			
			
			
	  
	  
	  }
		  @TargetApi(Build.VERSION_CODES.HONEYCOMB) //Fragment se necesita de la version android 3.0 en adelante.
		  	public void showDatePickerDialog(View v) {
			    DialogFragment newFragment = new DatePickerFragment();
			    newFragment.show(getFragmentManager(), "datePicker");
			}
  
		  //Metodo para conseguir la fecha del datepicker y ponerlo en el text-edit
		  public static void Conseguir_Fecha(int year, int month, int day) {
			  mBirthday=String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(year);
			  mEditBirthday.setText(month+"-"+day+"-"+year);
		    }
}
