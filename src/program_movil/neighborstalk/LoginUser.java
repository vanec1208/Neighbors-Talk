package program_movil.neighborstalk;


//Libraries
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import program_movil.neighborstalk.MyappGV;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class LoginUser  extends Activity {
	Button Done;
	private String Id_neigh="0";
	private String Log_Username="";
	private String Log_Password="";
	private EditText username;
	private EditText password;
	private ImageView imagen_contact;
	//http://secure-plains-4968.herokuapp.com/users
	//private static String url_user="http://192.168.1.6:3000/users";
	private static String url_user="http://secure-plains-4968.herokuapp.com/users";
	private static String url_neigh="http://secure-plains-4968.herokuapp.com/neighborhoods.json";
	//private static String url_neigh="http://192.168.1.6:3000/neighborhoods.json";
	private String user_list="";
	private String list_neigh="";
	protected static ArrayList <listUsers> listusers;
	
	
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.enableDefaults(); 
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//No rotar la pantalla
       
        Done = (Button)findViewById(R.id.done); // al Hundir este boton empieza la autenticacion
        username=(EditText)(findViewById(R.id.username)); //Aqui se digita el username (edit text)
        password=(EditText)(findViewById(R.id.password)); //Aqui se digita la clave (edit text)
        imagen_contact=(ImageView)(findViewById(R.id.Imagcontact));
        imagen_contact.setImageResource(R.drawable.contacts); 
        
        //Obtener el array de los barrios
		 list_neigh=getData(url_neigh);
		 
        //Obtener el json con la informacion de los usuarios registrados
		user_list=getData(url_user+".json");
		
		
        Done.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			public void onClick(View v) {
				
					
					//Usuario de la persona  de la persona
					Log_Username=username.getText().toString();
			        
			      //Usuario de la persona
					Log_Password=password.getText().toString();	
				// 
					if (Log_Username.isEmpty()|| Log_Password.isEmpty()){
						Toast.makeText(getApplicationContext(),"Username and / or Password empty", Toast.LENGTH_SHORT).show();
					}
					else{
						//Realizar la validacion en la DB aqui
						 if (Valid_array_user_pass(user_list,Log_Username,Log_Password)){	 
							 MyappGV.city_now="BARRANQUILLA";//Por default hasta ahora
							 //Conseguir el barrio
							 String neighbour_now=id_neigh( list_neigh, Id_neigh);
							 MyappGV.neighbor_now=neighbour_now;
							 //Cuando entra, significa que los usuarios y las contraseña son validos
							 arrayUsers(user_list, "neighborhood_id", MyappGV.neighborhood_id_now);
							 Intent intent=new Intent(LoginUser.this,App.class);
							 startActivity(intent);
							 finish();
						 }
						 else{
							 Toast.makeText(getApplicationContext(),"Username or Password not found", Toast.LENGTH_SHORT).show();		 
						 }
						 
						
					}
				
				
			}
		});
       
        
    }
	
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
	 
	 public void arrayUsers(String resultUsers, String nombre, String nombre_mio){
	      
	      try {
	    	  listusers = new ArrayList<listUsers>();
			  JSONArray listUsers = new JSONArray(resultUsers);
			   for(int i=0; i<listUsers.length();i++){
				   JSONObject list = listUsers.getJSONObject(i);
				   if(list.getString(nombre).equals(nombre_mio)){
					  listUsers lista = new listUsers(list.getString("id"), list.getString("name"), list.getString("username"));
					  listusers.add(lista);
				   }  
			   }
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
	  	  
	  }
	 
	 //Funcion para validar el usuario y contraseña existente en la base de datos
	 public boolean Valid_array_user_pass(String array_list,String txt,String txtpass ){
		 boolean sw = false;
		 
		 try {			 
			 
			  JSONArray jArray = new JSONArray(array_list);
			  for(int i=0; i<jArray.length();i++){
				   JSONObject json = jArray.getJSONObject(i);
				   //realizar comparacion 
				   if (json.getString("username").equals(txt)&& json.getString("password").equals(txtpass)){ 
					   sw = true;
					   //Guardar variables globales)(falta barrio y ciudad)
					   	MyappGV.user_id_now = json.getString("id");
						MyappGV.username_now=json.getString("username");
						MyappGV.password_now=json.getString("password");
						MyappGV.birthday_now=json.getString("birthday");
						MyappGV.gender_now=json.getString("gender");
						MyappGV.email_now=json.getString("email");
						MyappGV.name_now=json.getString("name");
						MyappGV.neighborhood_id_now = json.getString("neighborhood_id");
						Id_neigh=json.getString("neighborhood_id");
						
					   
					  
				   }
				  
			   }
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
		 
		 return sw;
	 }
	 
	 public String id_neigh (String list, String id_nei){
		 String neigh ="";
		 try {
			  JSONArray jArray = new JSONArray(list);
			   for(int i=0; i<jArray.length();i++){
				   JSONObject json = jArray.getJSONObject(i);
				   if(json.getString("id").equals(id_nei)){
					   neigh=json.getString("name");
				   }
				   
				   
			   }
		   } catch (Exception e) {
			// TODO: handle exception
			   Log.e("log_tag", "Error Parsing Data "+e.toString());
		   }
		 return neigh;
		 
	 }

	
}
