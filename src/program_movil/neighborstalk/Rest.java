package program_movil.neighborstalk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import android.util.Log;

public class Rest {

	 public static void POST(String url, List<NameValuePair> nameValuePairs ) {
		 
		   	HttpClient httpClient = new DefaultHttpClient();
		    HttpPost httpPost = new HttpPost(url);

		    try {
		    
		        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		        // Execute HTTP Post Request
		        @SuppressWarnings("unused")
				HttpResponse response = httpClient.execute(httpPost);

		    } catch (ClientProtocolException e) {
		        // TODO Auto-generated catch block
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		    }

	}
	 
	  public static String GET(String url){
	    	String result = "";
	    	InputStream isr = null;
	    	try{
	            HttpClient httpclient = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(url); //YOUR SCRIPT ADDRESS 
	            HttpResponse response = httpclient.execute(httpGet);
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
}
