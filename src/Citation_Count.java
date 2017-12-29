

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Citation_Count {
	public int citation_count = 0;
   /*
    * Function GET_Citation_Count gives us the citation count
    * We are Scraping the Data from the web then extracting the data from the table data right
    */

	public int GET_Citation_Count(String topic1) throws IOException {
		 
		 	Document doc = Jsoup.connect("https://www.scopus.com/results/results.uri?numberOfFields=0&src=s&clickedLink=&edit=&editSaveSearch=&origin=searchbasic&authorTab=&affiliationTab=&advancedTab=&scint=1&menu=search&tablin=&searchterm1="+ topic1 +"&field1=TITLE_ABS_KEY&dateType=Publication_Date_Type&yearFrom=Before+1960&yearTo=Present&loadDate=7&documenttype=All&resetFormLink=&st1=" + topic1 + "&st2=&sot=b&sdt=b&sl=95&s=TITLE-ABS-KEY%28"+ topic1 +"%29").get();
		    String title = doc.title();
		    System.out.println("title : " + title);
		    
		  org.jsoup.select.Elements movies =  doc.select("td.textRight");
		  System.out.println("waiting ....");
		  
		  int i = 0;
		  for(org.jsoup.nodes.Element e : movies)
		  {
			  i++;
			  if(i == 2)
			  {
				  
				  citation_count = Integer.parseInt((String)e.text());
				  System.out.println(e.text());
				  break;
			  }
			  
			 
		  }
		  return citation_count;
		
	}
}

