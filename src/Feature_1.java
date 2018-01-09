import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class Feature_1 {

    public static void main(String args[])
    {
        String title = "Fault-tolerant+edge-bipancyclicity+of+faulty+hypercubes+under+the+conditional-fault+model";
        String apiKey = "288b2ca0b06c75c02c0ec188e6226d3e";
        totalResultsAfterSearching obj_searching_result = new totalResultsAfterSearching();
        int total_related_search = obj_searching_result.totalResult(title);

        String url = "http://api.elsevier.com/content/search/scopus?start=0&count=" + total_related_search +"&query="+title+"&apiKey="+apiKey+"&httpAccept=application/json";
        try {
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
                // System.out.println(inputLine);
            }
            in.close();

            JSONObject obj_JSONObject = new JSONObject(response.toString());
            JSONObject obj_JSONObject_searchResults = obj_JSONObject.getJSONObject("search-results");

            String result = obj_JSONObject_searchResults.getString("opensearch:totalResults");

            JSONArray obj_array = obj_JSONObject_searchResults.getJSONArray("entry");

            int i = 0;
            int n = 0;
            ArrayList<String> related_papers = new ArrayList<>();

            while(i<total_related_search)
            {
                JSONObject obj_counter = obj_array.getJSONObject(i);
                String year_paper = obj_counter.getString("prism:coverDate");
                int year_of_paper = Integer.parseInt(year_paper.substring(0,4));
                int year = Calendar.getInstance().get(Calendar.YEAR);
               // if((year_of_paper+5) >= year)
                //{
                    String paper_search_counter  = obj_counter.getString("dc:title");
                    related_papers.add(paper_search_counter);
                    n++;
              //  }

                i++;
            }

            Extracting_Paper_ReferenceData obj_referenceList = new Extracting_Paper_ReferenceData();
            // here we are passing the null string because for the testing purpose it already associated in Extracting_paper_reference Class
            Map<String, Integer> mapping = obj_referenceList.Get_Reference_Mapping_List("");

            int m = 0;
            for(String s : related_papers)
            {
                for (Map.Entry<String, Integer> entry : mapping.entrySet())
                {

                    if(s.equals(entry.getKey()))
                    {
                        m++;
                        System.out.println(s+"\n"+m);


                    }
                }
            }


            System.out.println("Searching total results ............... ");
            System.out.println("SEARCH      :::::::::::::::::::::" + result +":::::::::::::::::::::::::::::RESULT\n");

            //resultBySearching = Integer.parseInt(result);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Problem occured during total searching result extraction");
        }

    }
}
