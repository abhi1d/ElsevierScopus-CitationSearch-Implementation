/*
 * This class is used in FeatureImplementation at 284-286 line.
 * This Class retrieve the Reference list entries like year of publishing and title of the papers.
 * Here I am using the Map key,value pair where key is the title of the paper and the value is the published date
 * Generally, I am extracting the only year of publish of paper.
 * Here Key => title & Value => year because year for one or more paper can be same.
 */
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Extracting_Paper_ReferenceData {
    Map<String, Integer> mapping = new HashMap<>();
    public Map<String, Integer> Get_Reference_Mapping_List(String str)
    {
        try {
            // here we can change the pathname according our choice
            File inputFile = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_ACC/INS-D-13-1234[1]_cleaned.pdf.xml" + str.trim());
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            int flag_of_bib_str = 0;

            //hash mapping

            String key = null;
            int value;

            NodeList nodeList_bib_str = doc.getElementsByTagName("biblStruct");
            for (int i = 1; i < nodeList_bib_str.getLength(); i++) {
                Node node_bib_str = nodeList_bib_str.item(i);

                if (node_bib_str.getNodeType() == Node.ELEMENT_NODE) {
                    Element el_bib_str = (Element) node_bib_str;

                    //node list for getting the title.
                    NodeList nodeList_title = el_bib_str.getElementsByTagName("title");

                    for (int j = 0; j < nodeList_title.getLength(); j++) {
                        Node node_title = nodeList_title.item(j);
                        if (node_title.getNodeType() == Node.ELEMENT_NODE) {
                            Element el_title = (Element) node_title;
                            String title_type = el_title.getAttribute("type");
                            String title_name = null;
                            if (title_type.contains("main")) {
                                title_name = el_bib_str.getElementsByTagName("title").item(0).getTextContent();
                                //System.out.println(title_name);
                                key = title_name;
                                flag_of_bib_str = 1;
                                //break;
                            }
                        }
                    }
                    if (flag_of_bib_str == 1) {
                        //extracting the date tag
                        NodeList nodeList_date = el_bib_str.getElementsByTagName("date");
                        //iterate the nodelist of date tags
                        for (int k = 0; k < nodeList_date.getLength(); k++) {
                            Node node_date = nodeList_date.item(k);

                            if (node_date.getNodeType() == Node.ELEMENT_NODE) {
                                Element el_date = (Element) node_date;
                                // here date string maybe contains the date and year or maybe year only
                                // so we will split the string by '-'
                                String date = el_date.getAttribute("when");
                                // now spliting the array
                                // if it contains the month
                                // otherwise we won't split the array
                                int flag_for_mainTitle = 1;
                                if (date.contains("-")) // date has year as well as month of publishing
                                {
                                    String[] date_array = date.split("-");
                                    String date1 = date_array[0];
                                    String date2 = date_array[1];
                                    //System.out.println(date1 + " : " + date2);
                                    String year = null;
                                    String month = null;

                                    if (date1.length() == 4) {
                                        year = date1;
                                        month = date2;
                                    } else {
                                        year = date2;
                                        month = date1;
                                    }
                                    value = Integer.parseInt(year);
                                } else // date string has only the year of publishing
                                {
                                    value = Integer.parseInt(date);
                                }


                                mapping.put(key, value);
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
             System.out.println("Reference Data in Target Paper Section :\n\n");
            for (Map.Entry<String, Integer> entry : mapping.entrySet())
            {

                System.out.println( entry.getValue()+" : "+entry.getKey());

            }

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error During the Extraction of reference list");
        }

      return mapping;
    }

}
