package hellojson;
import java.io.FileReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class test
{
    public static boolean DSC= false;
    
	public static void main(String[] args) throws Exception 
    {
        // parsing file "document.json"
		
    	Object obj = new JSONParser().parse(new FileReader("/home/codecook/document.json"));
    	
    	  // typecasting obj to JSONObject
    	
        JSONObject jo = (JSONObject) obj;
     
        Map source = ((Map)jo.get("search-results"));
        
       //  source.get("entry");
        // iterating address Map
        
         JSONArray ja = (JSONArray) source.get("entry");  //getting Entry section
         
         Iterator itr2 = ja.iterator();   //iterating JsonArray element
         
         HashMap<String,Integer> result=new HashMap<String,Integer>();  //to store publishing date
         																//and title of the paper
         
         while(itr2.hasNext())
         {
        	Iterator itr1 = ((Map) itr2.next()).entrySet().iterator();
        	
        	String s2,s3 = null,s4 = null;
        	
        	while (itr1.hasNext())
        	{
                Map.Entry pair = (Entry) itr1.next();
                
                s2=(String) pair.getKey();
                
                if(s2.equals("prism:coverDate"))
                {
                	s3=(String) pair.getValue();
                }
                
                if(s2.equals("dc:title"))
                {
                	s4=(String) pair.getValue();
                }
            }
        	
        	result.put(s4,Integer.parseInt(s3.substring(0, 4)));  
         }
        
        Map<String, Integer> result1= sortByComparator(result, DSC);
        //Printing the title and date of publication
        
        for(Map.Entry m:result1.entrySet())
        {  
       	   System.out.println(m.getKey());
       	   System.out.println(m.getValue());
        } 
    }
	private static Map<String, Integer> sortByComparator(HashMap<String, Integer> result, boolean aSC2) 
	{
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(result.entrySet());

        // Sorting the list based on values
        Collections.sort(list, new Comparator<Entry<String, Integer>>()
        {
            public int compare(Entry<String, Integer> o1,
                    Entry<String, Integer> o2)
            {
                if (aSC2)
                {
                    return o1.getValue().compareTo(o2.getValue());
                }
                else
                {
                    return o2.getValue().compareTo(o1.getValue());

                }
            }
        });
        // Maintaining insertion order with the help of LinkedList
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Entry<String, Integer> entry : list)
        {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
	}
}