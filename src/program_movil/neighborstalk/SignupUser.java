package program_movil.neighborstalk;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.pm.ActivityInfo;
import android.database.SQLException;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
public class SignupUser  extends Activity {
	EditText EditNombre;
	static EditText EditBirthday;
	EditText EditEmail;
	EditText EditUserName;
	EditText EditPassword;
	Button boton_send;
	Spinner spinner_city;
	Spinner spinner_neighbor;
	Spinner spinner_gender;
	DatePicker datePicker;
	String Prueba;
	
	private  String Nombre="";
	private String Username="";
	private String Email="";
	private String Password="";
	private String City="";
	private String Gender="";
	private String Neighbor;
	private static String Birthday="";
	private static String url_neigh="secure-plains-4968.herokuapp.com/neighborhoods.json";
	private static String url_user="secure-plains-4968.herokuapp.com/users";
	private String user_list="";
	private String list_neigh="";
	
	  @SuppressLint("NewApi")
	protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_signup);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//No rotar la pantalla
	        StrictMode.enableDefaults(); //STRICT MODE ENABLED
	         EditBirthday = (EditText)findViewById(R.id.editText1);
			 EditNombre = (EditText) findViewById(R.id.name); //edit-text con el nombre de la persona
			 EditEmail = (EditText) findViewById(R.id.email); //edit-text con el email de la persona
			 EditUserName=(EditText) findViewById(R.id.edit_username); //edit-text con el usuario de la persona
			 EditPassword=(EditText) findViewById(R.id.edit_password);//edit-text con la contrase�a de la persona
			 boton_send = (Button)findViewById(R.id.signup_send);//Enviar datos a la base de datos
				//Nombre de la persona
		        Nombre=EditNombre.getText().toString();
		        
		      //Usuario de la persona
		        Username=EditUserName.getText().toString();
		        
		      //Contrase�a de la persona
		       Password=EditPassword.getText().toString();
		       
		       //Conseguir email
		       Email= EditEmail.getText().toString();
		       
			  //Conseguir el sexo
		       spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
		       spinner_gender.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
				                Gender =(String)parent.getSelectedItem();             
				         }
				 public void onNothingSelected(AdapterView<?> parent) {
					 			Gender="";
				 }
				});
			
	        //Conseguir la ciudad
			 spinner_city = (Spinner) findViewById(R.id.spinner_city);
			 spinner_city.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
				                City =(String)parent.getSelectedItem();             
				         }
				 public void onNothingSelected(AdapterView<?> parent) {
					 			City="";
				 }
				});
			
			
			//Conseguir barrio
			 spinner_neighbor = (Spinner) findViewById(R.id.spinner_neighbor);
			//Obtener el array de los barrios
			 list_neigh=getData(url_neigh);
			 //Agregar los barrios al spinner
			 addItemsOnSpinner_neigh(list_neigh);
			 
			 //Obtener el array de los usarios y correos ya registrados
			 user_list=getData(url_user);
			 
			 
			 
			 spinner_neighbor.setOnItemSelectedListener(new OnItemSelectedListener() {
					public void onItemSelected(AdapterView<?> parent, View view, int position,long id) {
					                Neighbor =(String)parent.getSelectedItem();             
					         }
					 public void onNothingSelected(AdapterView<?> parent) {
						 			Neighbor="";
					 }
					});
			 
		
		
	       
	      //Conseguir Fecha de Cumplea�os
	       
	       
	      
	        boton_send.setOnClickListener(new OnClickListener() {
				
				public void onClick(View v) {
					//Thread para la base de datos
					
					
					//Nombre de la persona
			        Nombre=EditNombre.getText().toString();
			        
			      //Usuario de la persona
			        Username=EditUserName.getText().toString();
			        
			      //Contrase�a de la persona
			       Password=EditPassword.getText().toString();
			       
			       //Email de la persona
			       Email=EditEmail.getText().toString();
			       
			       if (Nombre.isEmpty()|| Username.isEmpty()|| Password.isEmpty()|| Neighbor.isEmpty()||
			    		 Gender.isEmpty()|| Birthday.isEmpty() || City.isEmpty()||Email.isEmpty()){
					Toast.makeText(getApplicationContext(), "Any item  should not be null" , Toast.LENGTH_SHORT).show();
			       }
			       else{
			    	  
			    	   //aqui debe hacer lo demas
			    	   //Validacion de username
			    	  
			    	 
			    	   if (!Valid_array_user(user_list,Username)&& !Valid_array_email(user_list, Email)){
			    		   //Guardar en DB
			    		   
			    		   //Encontrar el id
			    		   int id_neighbor=id_neigh (list_neigh, Neighbor);
			    		   //Crear el array para guardar
			    		   
			    		   	List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			    		   	nameValuePairs.add(new BasicNameValuePair("name", "Colinas"));
					        nameValuePairs.add(new BasicNameValuePair("city_id", "1"));
					        /*nameValuePairs.add(new BasicNameValuePair("name", Nombre));
					        nameValuePairs.add(new BasicNameValuePair("username", Username));
					        nameValuePairs.add(new BasicNameValuePair("email", Email));
					        nameValuePairs.add(new BasicNameValuePair("gender", Gender));
					        nameValuePairs.add(new BasicNameValuePair("password", Password));
					        nameValuePairs.add(new BasicNameValuePair("password_confirmation", Password));
					        nameValuePairs.add(new BasicNameValuePair("neighborhood_id",String.valueOf(id_neighbor)));*/
					        //nameValuePairs.add(new BasicNameValuePair("birthday", Birthday));
					        
					        //Guardar base de datos con post
					        Post(url_user,nameValuePairs);
					       
			    		   
			    		   Toast.makeText(getApplicationContext(), "Saved data", Toast.LENGTH_SHORT).show();
			    	   }   
			    	   else{
			    		   Toast.makeText(getApplicationContext(), "Username and Email are repeated", Toast.LENGTH_SHORT).show();
			    	   }
			    		   
			    	 
			    	   
			       }
			       
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
		  Birthday=String.valueOf(month)+"-"+String.valueOf(day)+"-"+String.valueOf(year);
		  EditBirthday.setText(month+"-"+day+"-"+year);
	    }

	  
	/*  
	// Agregar items de forma dinamica
	 * */	  
	    @SuppressWarnings("unchecked")
		public void addItemsOnSpinner_neigh(String array_list) {
	      
	      @SuppressWarnings("rawtypes")
		List list = new ArrayList();
	      
	      try {
			  JSONArray jArray = new JSONArray(array_list);
			   for(int i=0; i<jArray.length();i++){
				   JSONObject json = jArray.getJSONObject(i);
				   list.add(json.getString("name"));
				  
				   
			   }
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
	      
	      //Toast.makeText(getApplicationContext(), list.get(0).toString(), Toast.LENGTH_LONG).show();
	      @SuppressWarnings({ "rawtypes" })	      
	      ArrayAdapter dataAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, list);
	      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	  	  spinner_neighbor.setAdapter(dataAdapter);
	  
	    }

	  

		  //
		  
		  public String getData(String url){
		    	String result = "";
		    	InputStream isr = null;
		    	try{
		            HttpClient httpclient = new DefaultHttpClient();
		            HttpGet httppost = new HttpGet(url); //YOUR SCRIPT ADDRESS 
		            HttpResponse response = httpclient.execute(httppost);
		            HttpEntity entity = response.getEntity();
		            isr = entity.getContent();
		    }
		    catch(Exception e){
		            Log.e("log_tag", "Error in http connection "+e.toString());
		          
		    }
		    //convert response to string
		    try{
		            BufferedReader reader = new BufferedReader(new InputStreamReader(isr,"iso-8859-1"),8);
		            StringBuilder sb = new StringBuilder();
		            String line = null;
		            while ((line = reader.readLine()) != null) {
		                    sb.append(line + "\n");
		            }
		            isr.close();
		     
		            result=sb.toString();
		            
		            
		    }
		    catch(Exception e){
		            Log.e("log_tag", "Error  converting result "+e.toString());
		    }
		     
		   
		    	return result;
		    }
		  
		 public boolean Valid_array_user(String array_list,String txt ){
			 boolean sw = false;
			 //int i = 0;
			 try {			 
				 
				  JSONArray jArray = new JSONArray(array_list);
				  for(int i=0; i<jArray.length();i++){
					   JSONObject json = jArray.getJSONObject(i);
					   
					   if (json.getString("username").equals(txt)){ 
						   sw = true;
						   Toast.makeText(getApplicationContext(), "son iguales" , Toast.LENGTH_SHORT).show();
					   }	
					   
					  
					  
				  /* while(i<jArray.length() && !sw){
					   JSONObject json = jArray.getJSONObject(i);
					   if(json.getString("username")==txt) {
						   sw = true;
						   Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_SHORT).show();
					   }else{
						   i = i+1;
					   }	*/			   
					   
				   }
			   } catch (Exception e) {
				// TODO: handle exception
				   Log.e("log_tag", "Error Parsing Data "+e.toString());
			   }
			 
			 return sw;
		 }
		 
		
		 public boolean Valid_array_email(String array_list,String txt ){
			 boolean sw = false;
			 //int i = 0;
			 try {			 
				 
				  JSONArray jArray = new JSONArray(array_list);
				  for(int i=0; i<jArray.length();i++){
					   JSONObject json = jArray.getJSONObject(i);
					   
					   if (json.getString("email").equals(txt)){ 
						   sw = true;
						   Toast.makeText(getApplicationContext(), "son iguales" , Toast.LENGTH_SHORT).show();
					   }	
					   
				   }
			   } catch (Exception e) {
				// TODO: handle exception
				   Log.e("log_tag", "Error Parsing Data "+e.toString());
			   }
			 
			 return sw;
		 }
		 
		  
		 public  void Post(String url, List<NameValuePair> nameValuePairs ) {
			   HttpClient httpclient = new DefaultHttpClient();
			    HttpPost httppost = new HttpPost(url);

			    try {
			    	 System.out.println("entreeeeeeeeeeeee");
			    	
			    	System.out.println(nameValuePairs.get(0).getValue());
			       
			        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

			        // Execute HTTP Post Request
			        @SuppressWarnings("unused")
					HttpResponse response = httpclient.execute(httppost);

			    } catch (ClientProtocolException e) {
			        // TODO Auto-generated catch block
			    } catch (IOException e) {
			        // TODO Auto-generated catch block
			    }

		}
		 
		 public int id_neigh (String list, String neigh){
			 int id_neigh =0;
			 try {
				  JSONArray jArray = new JSONArray(list);
				   for(int i=0; i<jArray.length();i++){
					   JSONObject json = jArray.getJSONObject(i);
					   if(json.getString("name").equals(neigh)){
						   id_neigh=Integer.parseInt(json.getString("id"));
					   }
					   
					   
				   }
			   } catch (Exception e) {
				// TODO: handle exception
				   Log.e("log_tag", "Error Parsing Data "+e.toString());
			   }
			 return id_neigh;
			 
		 }

		 
		  
		  
		  
		  

}

