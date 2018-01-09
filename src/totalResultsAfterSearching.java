import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class totalResultsAfterSearching {

	public String apiKey = "288b2ca0b06c75c02c0ec188e6226d3e";
	String total_results = "";
	int resultBySearching = 0;
	public int totalResult(String title) 
	{

	   try {
		   String url = "http://api.elsevier.com/content/search/scopus?query=" + title + "&apiKey=" + apiKey + "&httpAccept=application/json";
		   URL obj  = new URL(url);
	  
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
	    
		
	    BufferedReader in  = new BufferedReader(new InputStreamReader(con.getInputStream()));
		
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	  
		while ((inputLine = in.readLine()) != null)
		 {
		     response.append(inputLine);
		 }
	    in.close();
	    
	    JSONObject obj_JSONObject = new JSONObject(response.toString());
        JSONObject obj_JSONObject_searchResults = obj_JSONObject.getJSONObject("search-results");
	    
	    String result = obj_JSONObject_searchResults.getString("opensearch:totalResults");
	    
	    
	  
	    System.out.println("Searching total results ...............");
	    System.out.println("SEARCH      :::::::::::::::::::::" + result +":::::::::::::::::::::::::::::RESULT\n");
		
		resultBySearching = Integer.parseInt(result);
		return resultBySearching;
	   }
	   catch(Exception e)
	   {
		   System.out.println("Problem occured during total searching result extraction-1");
		   return 0;

	   }

	}
}
