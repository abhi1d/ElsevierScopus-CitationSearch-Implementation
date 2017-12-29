/*
* created by
* ABHISHEK SHARMA
* NIT Mizoram
* Happy Coding
*/

/*
 * Due Part :
 * 1. last Extraction of header with the value of the count of reference
*/
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.*;
import java.lang.*;
import java.text.ParseException; 
import java.util.ArrayList; 
import java.util.Collections; 
import java.util.Comparator; 
import java.util.HashMap; 
import java.util.LinkedHashMap; 
import java.util.List; 
import java.util.Map.Entry; 
import java.util.Set; 
import java.util.TreeMap;



public class FeatureImplementation
{
   public static int total_no_ref = 0;
   public static int numberOfAuthers = 1;
   public static String tag_name_head_content = null;
   public static File inputFile_div;
   private static final String REGEX = "ref";

   //function for calculating the last heading references
   public static void last_heading(String heading,File inputFile, String fileName)
   {


      try{
      
       // System.out.println("heading : "+heading);
          DocumentBuilderFactory dbFactory_last = DocumentBuilderFactory.newInstance();
          DocumentBuilder dBuilder_last = dbFactory_last.newDocumentBuilder();
          Document doc_last = dBuilder_last.parse(inputFile);
          doc_last.getDocumentElement().normalize();
          //System.out.println("Root element :" + doc_div.getDocumentElement().getNodeName());
          NodeList nList_div = doc_last.getElementsByTagName("div");
          int number_of_ref = 0;
          //start of the loop for iterate the div
          for (int temp = 0; temp < nList_div.getLength(); temp++)
                {
                                
                    Node node_div = nList_div.item(temp);
                                                    
                    if (node_div.getNodeType() == Node.ELEMENT_NODE)
                        {  
                          Element el_div = (Element) node_div;
                          NodeList ref = el_div.getElementsByTagName("ref");
                          int co_ref = ref.getLength();
                          number_of_ref = number_of_ref + co_ref;
                          //variable for differentiating the type of the head 

                          String type_of_div = el_div.getAttribute("type");  
                          String INPUT = type_of_div.toLowerCase();
                          //System.out.println("type : "+INPUT);
                          Pattern p = Pattern.compile(REGEX);
                          Matcher m = p.matcher(INPUT);   // get a matcher object
                          
                          if(m.lookingAt()) 
                              {
                                  System.out.println("matched");
                                  System.out.println("ref   :  " + (number_of_ref) );
                                  FileWriter fw = new FileWriter("/home/abhi/eclipse-oxygen/INS_CleanUpdate/results/" + fileName + ".txt", true);
                                  fw.write("ref    :   " + (number_of_ref - total_no_ref) + "\n");
                                  Double feature = ((number_of_ref - total_no_ref) * (1.0) / numberOfAuthers);
                                  fw.write("feature  :   " + feature + "\n");
                                  fw.close();
                                  break;
                              }
                         }

                }//end of the loop which iterate the div
              } catch (Exception e) {
                System.out.println("completed");
              }            
   }
   
   public static void main(String args[])
   {
      
    File folder = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/");
		File[] listOfFiles = folder.listFiles();
    int countForFile = 0;
    //start of the file extraction loop from the folder
    for(File file : listOfFiles)
     {
           // start of if for cheking the file  
            if(file.isFile())
             {
            	String fileName = file.getName();
            	System.out.println(fileName);
            	countForFile++;
              try {
                   File inputFile = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/" + fileName);
                   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                   DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                   Document doc = dBuilder.parse(inputFile);
                   doc.getDocumentElement().normalize();
                  //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
                   NodeList nList = doc.getElementsByTagName("author");
                   System.out.println("----------------------------");
                   System.out.println(nList.getLength());
              
                  //writing into the file 
                   FileWriter fw = new FileWriter("/home/abhi/eclipse-oxygen/INS_CleanUpdate/results/"+fileName+".txt",true);
                    String heading_in_file = "Total Authors  : \t";
                   for(int i = 0 ; i < heading_in_file.length(); i++)
                      {
                         fw.write(heading_in_file.charAt(i));
                      }
                      
                    numberOfAuthers =   nList.getLength() ;   
                     fw.write( nList.getLength() + "\n\n");
                     fw.close();
    
                   } catch (Exception e) {
                     System.out.println("Error occured during parsing");
                   }
                
              try {
         			      inputFile_div = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/" + fileName);
        		          DocumentBuilderFactory dbFactory_div = DocumentBuilderFactory.newInstance();
         			      DocumentBuilder dBuilder_div = dbFactory_div.newDocumentBuilder();
         			      Document doc_div = dBuilder_div.parse(inputFile_div);
         			      doc_div.getDocumentElement().normalize();
         			      System.out.println("Root element :" + doc_div.getDocumentElement().getNodeName());
         		        NodeList nList_div = doc_div.getElementsByTagName("div");
       			         
                    System.out.println("----------------------------");
         			      //System.out.println(nList_div.getLength());
              
                    String start_head = "1";
                    int counting_head = 1;
                    int counter = 0 ;
                    int array[] = new int[10000];
                    int number_of_ref = 0;
                     
                    int flag1 = 1 ;
                    int flag2 = 1 ;
                    int last_co_ref = 0;
          		      
                    //start of the loop for iterate the div
                    for (int temp = 0; temp < nList_div.getLength(); temp++)
                           {
          				              
           					            Node nNode = nList_div.item(temp);
            		             		//name of the tag eg: div,head ..etc.
           					            String node_name = nNode.getNodeName();
           					            Element line = null;
           					
                                if (nNode.getNodeType() == Node.ELEMENT_NODE)
                                   {
             								             Element eElement = (Element) nNode;
              								          tag_name_head_content = eElement.getElementsByTagName("head").item(0).getTextContent();
                            
                                         //System.out.println("\theading : " + tag_name_head_content+"\n");
                                         NodeList nList_head  = eElement.getElementsByTagName("head");
                                         
                                         
                                         NodeList nList_ref = eElement.getElementsByTagName("ref");
                                              
                                             
 
                                        number_of_ref = number_of_ref + nList_ref.getLength();
                                        last_co_ref = nList_ref.getLength();
                                        //System.out.println("co ref : "+last_co_ref);
                                        total_no_ref = total_no_ref + nList_ref.getLength();
                                                 
                                        /* if(flag1 == 1)
                                         {
                                           number_of_ref = number_of_ref + nList_ref.getLength();
                                           flag1 = 0;
                                         }*/
                                         //System.out.println("testing ref : "+number_of_ref + "\n");
                                         //for checking the type of the heading
                                         //It is helping us to recognize the difference between the heading and sub-headings
                                         //starting of a loop for counting the elements in the one head 
                                         for(int i = 0 ; i < nList_head.getLength() ; i++)
                                               {
                                                    Node node_head = nList_head.item(i);
                                                    
                                                    if (node_head.getNodeType() == Node.ELEMENT_NODE)
                                                       {  
                                                            Element el_head = (Element) node_head;
                                                            //variable for differentiating the type of the head  
                                                            String type_of_head = el_head.getAttribute("n");
                                                            //System.out.println("type  :  "+ type_of_head );
                                                            start_head = counting_head + ".";
                                                            String start_head_without_dot = counting_head +"";
                                                            //System.out.println("now head : "+start_head);
                                                             
                                                                  
                                                            if(start_head.equals(type_of_head))
                                                              {
                                                                  
                                                                 FileWriter fw = new FileWriter("/home/abhi/eclipse-oxygen/INS_CleanUpdate/results/" + fileName + ".txt", true);
                                                                
                                                                 //there is a case when introduction has only one division then
                                                                 // it won't able to give correct output.
                                                                 
                                                                 if(flag2 == 1)
                                                                 {
                                                                    flag2 = 0;       
                                                                    System.out.println("heading : " + tag_name_head_content);
                                                                    fw.write("heading   :    " + tag_name_head_content + "\n");
                                                                    fw.close();                                                            
                                                                 }
                                                                 else 
                                                                 {
                                                                  //we are removing the last_co_ref from the number_of_ref
                                                                  //becoz last_co_ref is the ref of last head
                                                                  //so we are removing from the current references 
                                                                  //and adding into the next immediate reference
                                                                  fw.write("ref   :   " +  (number_of_ref - last_co_ref) + "\n" );
                                                                  System.out.println("ref : " + (number_of_ref - last_co_ref) + "\n"); 
                                                                  //System.out.println("total_no_ref " +total_no_ref);
                                                                  Double feature = ((number_of_ref - last_co_ref) * (1.0) / numberOfAuthers);
                                                                  fw.write("feature   :   " + feature);
                                                                  System.out.println("feature :  " + feature  + "\n");
                                                                  fw.write("-------------------------------------------------" + "\n");
                                                                  System.out.println("heading : " + tag_name_head_content);
                                                                  fw.write("heading   :    " + tag_name_head_content + "\n");
                                                                  //writing into the file 
                                                                   

                      
                                                                  fw.close();
                                                                  
                                                                  number_of_ref = last_co_ref;
                                                                 }
                                                                 counting_head = counting_head + 1;
                                                                 
                                                                 
                                                              }
                                                              

                                                        }
                                              }//end of the loop of couting the elements in the one head


                                          

                                      }
                                    

             	                  }//end of the loop which iterate the div

                         } catch(Exception e) {
             		
                        last_heading(tag_name_head_content,inputFile_div,fileName);
                        // System.out.println("ref  : ")
                        System.out.println("-----------------completed------------------------------");
               
                      }
 
                    //now we are calculating the published paper and their published dates

                  try{
            
                        File inputFile = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/" + fileName);
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document doc = dBuilder.parse(inputFile);
                        doc.getDocumentElement().normalize();
                        int flag_of_bib_str = 0;

                        //hash mapping
                        Map<String,Integer> mapping = new HashMap<>();
                        String key = null;
                        int value;
                      
                        NodeList nodeList_bib_str = doc.getElementsByTagName("biblStruct");
                        for(int i = 1 ; i < nodeList_bib_str.getLength() ; i++)
                            {
                                 Node node_bib_str = nodeList_bib_str.item(i);

                                  if (node_bib_str.getNodeType() == Node.ELEMENT_NODE)
                                    {  
                                         Element el_bib_str = (Element) node_bib_str;

                                        //node list for getting the title.
                                        NodeList nodeList_title = el_bib_str.getElementsByTagName("title");

                                        for(int j = 0 ; j < nodeList_title.getLength() ; j++)
                                              {
                                                   Node node_title = nodeList_title.item(j);
                                                   if(node_title.getNodeType() == Node.ELEMENT_NODE)
                                                    {
                                                        Element el_title = (Element) node_title;
                                                        String title_type = el_title.getAttribute("type");
                                                        String title_name = null;
                                                        if(title_type.contains("main"))
                                                          {
                                                              title_name = el_bib_str.getElementsByTagName("title").item(0).getTextContent();
                                                               //System.out.println(title_name);
                                                              key = title_name;
                                                              flag_of_bib_str = 1;
                                                               //break;
                                                           } 
                                                    }
                                          }
                                          if(flag_of_bib_str == 1)
                                                 {
                                                    //extracting the date tag
                                                      NodeList nodeList_date = el_bib_str.getElementsByTagName("date");
                                                      //iterate the nodelist of date tags
                                                      for(int k = 0 ; k < nodeList_date.getLength(); k++)
                                                        { 
                                                           Node node_date = nodeList_date.item(k);
 
                                                          if(node_date.getNodeType() == Node.ELEMENT_NODE)
                                                                  {
                                                                      Element el_date = (Element) node_date;
                                                                      // here date string maybe contains the date and year or maybe year only
                                                                      // so we will split the string by '-'
                                                                      String date = el_date.getAttribute("when"); 
                                                                      // now spliting the array
                                                                      // if it contains the month
                                                                      // otherwise we won't split the array
                                                                      int flag_for_mainTitle = 1;if(date.contains("-")) // date has year as well as month of publishing
                                                                       {
                                                                            String [] date_array = date.split("-");
                                                                            String date1 = date_array[0];
                                                                            String date2 = date_array[1];
                                                                            //System.out.println(date1 + " : " + date2);
                                                                            String year = null;
                                                                            String month = null;
                                          
                                                                            if(date1.length() == 4 )
                                                                              {
                                                                                  year = date1;
                                                                                  month = date2;
                                                                                }     
                                                                            else
                                                                                 {
                                                                                   year = date2;
                                                                                   month = date1;
                                                                                  }
                                                                           value = Integer.parseInt(year);
                                                                          }
                                                                      else // date string has only the year of publishing
                                                                           {
                                                                           value = Integer.parseInt(date);
                                                                           }    
                                       
                                       
                                                                      mapping.put(key,value);
                                                                       //System.out.println(key);
                                                                        //System.out.println("date : "+mapping.get(key));
                                                                        flag_of_bib_str = 0;
                                                                         key = null;
                                                                        value = 0;
                                                                   }
                                                         }//end of the loop for date tags

                                                   } 
                            
                             
                                    }

                              }
                       // extracting the main title and author for 
                        //the purpose of extracting the year
                        
                        String main_title_of_paper  = null;
                        String author = "";
                        /* 
                         * ***************************Extracting the Main Title of the Paper********************************************************************************
                         */
                       
                       
                       File inputFile_title = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/" + fileName);
      		           DocumentBuilderFactory dbFactory_title = DocumentBuilderFactory.newInstance();
       			       DocumentBuilder dBuilder_title = dbFactory_title.newDocumentBuilder();
       			       Document doc_title = dBuilder_title.parse(inputFile_title);
       			       doc_title.getDocumentElement().normalize();
       		           NodeList nList_title = doc_title.getElementsByTagName("titleStmt");
                        
       		           for(int i = 0 ; i < nList_title.getLength(); i++)
       		           {
       		        	Node node_title = nList_title.item(i);

                        if (node_title.getNodeType() == Node.ELEMENT_NODE)
                          {  
                               Element el_title = (Element) node_title; 
                               String main_title = (String)el_title.getElementsByTagName("title").item(0).getTextContent();
                               System.out.println("-------------main title---------------------\n"+main_title +"\n------end---------------");
                               //spliting the array with single-space and storing into the array
                               String [] MAIN_TITLE_ARRAY = main_title.split(" ");
                               //now concat with +
                               main_title_of_paper = String.join("+",MAIN_TITLE_ARRAY);
                               //System.out.println("\n-------------main title---------------------\n"+main_title_of_paper +"\n------end---------------");

                               break;
                          }
       		           }
                       /***************************************** E N D ***************************************************************************************************************/ 
                        
       		          /*
       		           * ***********************************Extracting the Authors of the Paper ********************************************************************************** 
       		           * only one author can be sufficient for the extracting the Year of the publication
       		           */
       		           
       		           File inputFile_author = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/" + fileName);
   		               DocumentBuilderFactory dbFactory_author = DocumentBuilderFactory.newInstance();
    			       DocumentBuilder dBuilder_author = dbFactory_author.newDocumentBuilder();
    			       Document doc_author = dBuilder_author.parse(inputFile_author);
    			       doc_author.getDocumentElement().normalize();
    		           NodeList nList_author = doc_title.getElementsByTagName("persName");
                     
    		           for(int i = 0 ; i < nList_author.getLength(); i++)
    		           {
    		        	 Node node_author = nList_author.item(i);

    		        	 if (node_author.getNodeType() == Node.ELEMENT_NODE)
                          {  
                            Element el_author = (Element) node_author; 
                            String first_name = (String)el_author.getElementsByTagName("forename").item(0).getTextContent();
                            String last_name = (String)el_author.getElementsByTagName("surname").item(0).getTextContent();
                            author = first_name.trim()+"+"+last_name.trim();
                            // it will give us like : abhishek+sharma
                            
                            break;
                          }
    		           }
                       /***************************************** E N D ***************************************************************************************************************/ 
                        
                         // Making the Object of the GET_Publish_date
                         // B'coz we are extracting the published year of the paper
                          GET_Published_Date published_date_obj = new GET_Published_Date();
                          int published_year = published_date_obj.Published_Year(main_title_of_paper,author);
                          System.out.println("\n-------------published year---------------------\n"+published_year +"\n------end---------------");
                     
                          
                       /* Getting the DOI 
                        * What is DOI ?
                        * Digital Object Identifier 
                        * It is a alphaNumerical String.
                        * DOI is similar to URL.
                        * It provides us a unique link on the Internet to the Object.
                        * URL may be modified, deleted and changing its owner But DOI Link never Changes.
                        * It is the persistent link and can be accessed at all times over the Internet.
                        * 
                        */
                          
                       /*
                        * So I am extracting the DOI From the Crossref.org
                        * which provides us the API So we can get the DOI 
                        * of the research paper on the Internet.
                        *     
                        */
                      /*
                       * After Getting the DOI of the Paper we will use this DOI for count the citation
                       * on the scopus which provides us the API So we can get the number of the citation count 
                       * on the internet But thing is that they use the DOI for searching the paper
                       * that is why we are extracting the DOI from the crossref.org website using the API.    
                       */
                          
                       // Making the Object of the GET_DOI
                       // B'coz we are extracting DOI of the paper 
                         GET_DOI DOI_Object = new GET_DOI();
                         String temp = "";
                         temp = temp + DOI_Object.DOI_String(main_title_of_paper,author);
                         System.out.println("\n-----------------Successfully extracted the DOI : " + temp +"--------------\n-----------------------------------");
                         
                      // Extraction of the Total results when we search the title in Scopus
                      // And we count the total number of the results
                       totalResultsAfterSearching obj_searchResult = new totalResultsAfterSearching(); 
                       int result_by_searching  = 0;
                       result_by_searching = result_by_searching + obj_searchResult.totalResult(main_title_of_paper);
                       System.out.println("searching for searching results --------------------------------->");
                       System.out.println(result_by_searching);   
                       
                       
                       // Extracting the citation count from the web 
                       Citation_Count obj_citationCount = new Citation_Count();
                       int number_of_citation = 0;
                       number_of_citation = obj_citationCount.GET_Citation_Count(main_title_of_paper);
                          
                           //map entry method for iterating each key value hash map
                           // here entry is a interface inside map interface 
                           // so  nested interface.
                           // it's amazing.
                           // entry map will give the set of the entries [ (key,value )box ]
                          Set<Map.Entry<String,Integer>> values = mapping.entrySet();
                 
                          /*for( Map.Entry<String,Integer>  e: values)
                            {
                              System.out.println( e.getValue());
      
                            }
                        * /
                
                          /*************************************************************************************************** */
                
                          // sorting the hash map by values 
                          // here values are integer type
                          // no direct way available to sort HashMap by values but
                          // we can do this by writing our own comparator
                          // which takes Map.Entry object and arrange them in order ( ASC or DESC)
                          // here we are sorting in the descending order
                          // for taking the recent year
                         Comparator<Entry<String,Integer>> valueComparator = new Comparator<Entry<String,Integer>>()
                            {
                               @Override
                               public int compare (Entry<String,Integer> e1, Entry<String, Integer> e2) {
                               int v1 = e1.getValue();
                               int v2 = e2.getValue();

                              // return (v2 - v1) for desc
                              // and (v1 - v2) for asc
                              // this (v2 - v1) is similar to compareTo(object) String function 
                              // for giving the difference
                              return v2-v1;
                     
                            }

                           };
                          // Sort method needs a List, so let's first convert Set to list in java 
                          // "values" which is the Set object has the entries in the type of sets
                           // and we are making the List for each entries 
                        List<Entry<String,Integer>> listOfEntries = new ArrayList<Entry<String, Integer>>(values); 
                
                       // sorting HashMap by values using comparator
                           Collections.sort(listOfEntries,valueComparator);

                          LinkedHashMap<String, Integer> sortedByValue = new LinkedHashMap<String,Integer>(listOfEntries.size()); // copying entries from List to Map 
                          //now we are copying entries from List to Map
                         for(Entry<String, Integer> entry : listOfEntries)
                          { 
                             sortedByValue.put(entry.getKey(), entry.getValue());
                          }

                        System.out.println("HashMap after sorting entries by values ");

                        Set<Entry<String, Integer>> entrySetSortedByValue = sortedByValue.entrySet();
                        int counter_recent_year = 0;
                        int total_references = 0;
                       for(Entry<String,Integer>mappings :entrySetSortedByValue)
                        {
                           System.out.println(mappings.getValue() + " :  " + mappings.getKey());
                           //writing into the file 
                          FileWriter fw = new FileWriter("/home/abhi/eclipse-oxygen/INS_CleanUpdate/results/" + fileName + ".txt", true);
                          String heading_in_file = " Year and reference   : \t";
                          for (int j = 0; j < heading_in_file.length(); j++)
                          {
                        	  fw.write(heading_in_file.charAt(j));
                          }

                          fw.write(mappings.getValue() + "   :    " + mappings.getKey() +"\n");
                          // I am using two if condition because of good readability. but they are same we can merge them
                          if(mappings.getValue()  == published_year || mappings.getValue()  == (published_year - 1) || mappings.getValue()  == (published_year - 2) || mappings.getValue()  == (published_year - 3))
                          	{
                        	  counter_recent_year++;
                          	}
                          if( mappings.getValue()  == (published_year - 4) || mappings.getValue()  == (published_year - 5))
                          	{
                        	  counter_recent_year++;
                          	}
                          total_references++;
                          fw.close();
                          
                           
                         }
                     
                       double feature1 = (counter_recent_year * 1.0)/result_by_searching;
                       System.out.println("\n----------------------\n******************feature one is  :  " + feature1 + "***********************\n-------------------------------\n------------------------------------------" );
                      
                       double feature2 = (number_of_citation *1.0 )/result_by_searching;
                       
                       System.out.println("\n----------------------\n******************feature two is  :  " + feature2 + "***********************\n-------------------------------\n------------------------------------------" );

                } catch(Exception e) {
                 System.out.println("in the mapping section");

            }    						  

    } // end of if for Checking the file  
  }// end of the file extraction loop from the folder

}

}

