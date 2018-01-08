import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class GET_DOI {
 public String doi_String;
 public String DOI_String(String title, String author) 
 {
	 String url = "https://api.crossref.org/works?query.title=" + title
			       + "&query.author=" + author;
     URL obj = null;
	try {
		obj = new URL(url);
	} catch (MalformedURLException e) {
		//e.printStackTrace();
	}
     HttpURLConnection con = null;
	try {
		con = (HttpURLConnection) obj.openConnection();
	} catch (IOException e) {
		//e.printStackTrace();
	}
     // optional default is GET
     try {
		con.setRequestMethod("GET");
	} catch (ProtocolException e) {
		///e.printStackTrace();
	}
     //add request header
     con.setRequestProperty("User-Agent", "Mozilla/5.0");
     try {
		int responseCode = con.getResponseCode();
	} catch (IOException e) {
		//e.printStackTrace();
	}
    // System.out.println("\nSending 'GET' request to URL : " + url);
     //System.out.println("Response Code : " + responseCode);
     BufferedReader in = null;
	try {
		in = new BufferedReader(
		         new InputStreamReader(con.getInputStream()));
	} catch (IOException e) {
		//e.printStackTrace();
	}
     String inputLine;
     StringBuffer response = new StringBuffer();
     try {
		while ((inputLine = in.readLine()) != null) {
		 	response.append(inputLine);
		 }
	} catch (IOException e) {
		//e.printStackTrace();
	}
     try {
		in.close();
	} catch (IOException e) {
		///e.printStackTrace();
	}
     //print in String
     //System.out.println(response.toString());
     JSONObject obj_JSONObject = null;
	try {
		obj_JSONObject = new JSONObject(response.toString());
	} catch (JSONException e) {
		//e.printStackTrace();
	}
     JSONObject obj_JSONObject_Message = null;
	try {
		obj_JSONObject_Message = obj_JSONObject.getJSONObject("message");
	} catch (JSONException e) {
		//e.printStackTrace();
	}
     JSONArray items_array = null;
	try {
		items_array = obj_JSONObject_Message.getJSONArray("items");
	} catch (JSONException e1) {
		//e1.printStackTrace();
	}
     JSONObject obj_JSONObject_sixth = null;
	try {
		obj_JSONObject_sixth = items_array.getJSONObject(0);
	} catch (JSONException e) {
	//	e.printStackTrace();
	}
     try {
		JSONObject obj_JSONObject_created_date = obj_JSONObject_sixth.getJSONObject("published-print");
	} catch (JSONException e) {
	//	e.printStackTrace();
	}
    
     //System.out.println(obj_JSONObject_created_date);
     
     //JSONArray date_part_array = obj_JSONObject_created_date.getJSONArray("date-parts");
     //JSONArray zero_index_date_part_array = date_part_array.getJSONArray(0);
     //JSONObject obj_JSONObject_date= date_part_array.getJSONObject(0);
    
    // System.out.println(zero_index_date_part_array.get(0));
     try {
		doi_String = obj_JSONObject_sixth.getString("DOI");
	} catch (JSONException e) {
		//e.printStackTrace();
	}
	 
    //System.out.println(doi_String);
	 return doi_String;
 }

}
