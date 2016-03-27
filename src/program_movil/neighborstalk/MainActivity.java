package program_movil.neighborstalk;

//Libraries
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import program_movil.neighborstalk.R;

public class MainActivity extends Activity {

	Button boton1;
	Button boton2;
	private ImageView imagen;
    @Override
    
    
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Application was closed", Toast.LENGTH_LONG).show();
    }  
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//No rotar la pantalla
        boton1 = (Button)findViewById(R.id.login); //Log in (inicio de seccion)
        boton2 = (Button)findViewById(R.id.signup); // sign up (registro de nuevo usuario)
        imagen=(ImageView)(findViewById(R.id.Imag)); //Logo de la aplicacion
        imagen.setImageResource(R.drawable.icon_256);
        
       /*
        * Inicializacion de las variables globales
        * */
    	MyappGV.username_now="";
		MyappGV.password_now="";
		MyappGV.birthday_now="";
		MyappGV.city_now="";
		MyappGV.neighbor_now="";
		MyappGV.gender_now="";
		MyappGV.email_now="";
		MyappGV.name_now="";
		
        
        // Se configura el boton para iniciar seccion 
        boton1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,LoginUser.class);
				startActivity(intent);	
			}
		});
     // Se configura el boton para registro de un nuevo usuario 
        boton2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(MainActivity.this,SignupUser.class);
				startActivity(intent);	
			}
		});
        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
  
}

    
    