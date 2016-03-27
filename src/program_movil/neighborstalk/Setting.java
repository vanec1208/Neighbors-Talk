package program_movil.neighborstalk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Setting extends Activity {
	
	Button back_home;
	Button modify;
	
	EditText user_setting;
	EditText name_setting;
	EditText birth_setting;
	EditText email_setting;
	EditText gender_setting;
	EditText city_setting;
	EditText neighbor_setting;
	
	
	
	
	 protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_settings);
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//No rotar la pantalla
	       
	        back_home = (Button)findViewById(R.id.button_home); //Regresar al home de la app
	        modify = (Button)findViewById(R.id.button_modify); // Modificar usuario
	        
	        
	        user_setting = (EditText)findViewById(R.id.user_profile); //edit-text con el usuario de la persona
	        
	        name_setting = (EditText) findViewById(R.id.name_profile); //edit-text con el nombre de la persona
	        
	        email_setting = (EditText) findViewById(R.id.email_profile); //edit-text con el email de la persona
	        city_setting=(EditText) findViewById(R.id.city_profile); //edit-text con la ciudad de la persona
	        neighbor_setting=(EditText) findViewById(R.id.neighbor_profile);//edit-text con el barrio de la persona
	        birth_setting = (EditText) findViewById(R.id.birth_profile);//edit-text con el barrio de la persona
	        gender_setting=(EditText) findViewById(R.id.gender_profile);//edit-text con el sexo de la persona
	        
	        /*
	         * Aqui se pone en los edit text los datos referente al usuario que esta usando ahora mismo 
	         * la aplicacion
	         * */
	        
	        user_setting.setText(MyappGV.username_now); //Poner edittext referente al usuario de la persona
	        name_setting.setText(MyappGV.name_now); //Poner edittext referente al nombre de la persona
	        email_setting.setText(MyappGV.email_now);//Poner edittext referente al correo de la persona
	        gender_setting.setText(MyappGV.gender_now);//Poner edittext referente al sexo de la persona
	        city_setting.setText(MyappGV.city_now); //Poner edittext referente la ciudad de la persona
	        neighbor_setting.setText(MyappGV.neighbor_now); //Poner edittext referente al barrio de la persona
	        birth_setting.setText(MyappGV.birthday_now); //Poner edittext referente fecha de nacimiento de la persona
	        
	        
	        // Se configura el boton para ir  Home
	        back_home.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(Setting.this,App.class);
					startActivity(intent);	
					finish();
				}
			});
	        
	     // Se configura el boton para modificar los campos
	        modify.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent=new Intent(Setting.this,Modify.class);
					startActivity(intent);	
					finish();
					
				}
			});
	        
	        
	 }
	 protected void onDestroy() {
	        super.onDestroy();
	        
	    }  

}
