/*
 * issue :1
 *  Sometimes the range of references can be like 2-6 or 2~6 so here tilde sign. What should I do ?
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Retrieving_Intro_Ref_List {
    public static int total_no_ref = 0;
    public static int numberOfAuthers = 1;
    public static String tag_name_head_content = null;
    public static File inputFile_div;
    private static final String REGEX = "ref";
    public ArrayList<String> Get_Ref_From_Intro(String pathname) throws ParserConfigurationException, SAXException, IOException {
        File inputFile = new File(pathname);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc_div = dBuilder.parse(inputFile);
        doc_div.getDocumentElement().normalize();
        System.out.println("Root element :" + doc_div.getDocumentElement().getNodeName());

        System.out.println("\t\tFeature 3 extracting...........\n");
        NodeList nList_div = doc_div.getElementsByTagName("div");
        String start_head = "1";
        int counting_head = 1;
        int counter = 0;
        int array[] = new int[10000];
        int number_of_ref = 0;

        int flag1 = 1;
        int flag2 = 1;
        int flag3 = 1; //this flag variable for getting the exact number of references in the introduction section.
        int last_co_ref = 0;
        ArrayList<String> al = new ArrayList<>();
        for (int temp = 0; temp < nList_div.getLength(); temp++) {

            Node nNode = nList_div.item(temp);
            //name of the tag eg: div,head ..etc.
            String node_name = nNode.getNodeName();
            Element line = null;

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                try {
                    tag_name_head_content = eElement.getElementsByTagName("head").item(0).getTextContent();
                } catch (Exception e) {
                }
                //System.out.println("\t heading : " + tag_name_head_content+"\n");
                NodeList nList_head = eElement.getElementsByTagName("head");
                NodeList nList_ref = eElement.getElementsByTagName("ref");


                number_of_ref = number_of_ref + nList_ref.getLength();
                last_co_ref = nList_ref.getLength();
                //System.out.println("co ref : "+last_co_ref);
                total_no_ref = total_no_ref + nList_ref.getLength();

                for (int i = 0; i < total_no_ref; i++) {
                    Node node_ref = nList_ref.item(i);
                    Element el_ref = (Element) node_ref;
                    if (tag_name_head_content.contains("Introduction")) {
                        String ref_name = el_ref.getTextContent();

                        String[] ref_string_array = ref_name.split(",");
                        for (int j = 0; j < ref_string_array.length; j++) {
                            al.add(ref_string_array[j]);
                        }

                        System.out.println(" ref   :   " + ref_name);


                    }

                }

                    /* if(flag1 == 1)
                     {
                       number_of_ref = number_of_ref + nList_ref.getLength();
                       flag1 = 0;
                     }*/
                //System.out.println("testing ref : "+number_of_ref + "\n");
                //for checking the type of the heading
                //It is helping us to recognize the difference between the heading and sub-headings
                //starting of a loop for counting the elements in the one head
                for (int i = 0; i < nList_head.getLength(); i++) {
                    Node node_head = nList_head.item(i);

                    if (node_head.getNodeType() == Node.ELEMENT_NODE) {
                        Element el_head = (Element) node_head;
                        //variable for differentiating the type of the head
                        String type_of_head = el_head.getAttribute("n");
                        //System.out.println("type  :  "+ type_of_head );
                        start_head = counting_head + ".";
                        String start_head_without_dot = counting_head + "";
                        //System.out.println("now head : "+start_head);


                        if (start_head.equals(type_of_head)) {

                            FileWriter fw = new FileWriter("/home/abhi/eclipse-oxygen/INS_CleanUpdate/results/file4.txt", true);

                            //there is a case when introduction has only one division then
                            // it won't able to give correct output.

                            if (flag2 == 1) {
                                flag2 = 0;
                                System.out.println("heading : " + tag_name_head_content);
                                //fw.write("heading   :    " + tag_name_head_content + "\n");
                                fw.close();
                            } else {
                                //we are removing the last_co_ref from the number_of_ref
                                //becoz last_co_ref is the ref of last head
                                //so we are removing from the current references
                                //and adding into the next immediate reference


                                //fw.write("ref   :   " + (number_of_ref - last_co_ref) + "\n");
                                System.out.println("ref : " + (number_of_ref - last_co_ref) + "\n");
                                //System.out.println("total_no_ref " +total_no_ref);
                                //Double feature = ((number_of_ref - last_co_ref) * (1.0) / numberOfAuthers);
                                //fw.write("feature   :   " + feature);
                                //System.out.println("feature :  " + feature  + "\n");
                                fw.write("-------------------------------------------------" + "\n");
                                flag1 = 0;


                                fw.close();

                                number_of_ref = last_co_ref;

                            }
                            counting_head = counting_head + 1;


                        }


                    }

                }//end of the loop of couting the elements in the one head
                if (flag1 != 1) {

                    break;
                }


            }


        }//end of the loop which iterate the div





        ArrayList<String> intro_ref_string_list = new ArrayList<String>();
        for (String s : al) {
            s = s.replace("[", "");
            s = s.replace("]", "");

            // sometimes we will get range of references
            if(s.contains("-"))
            {
                String [] range =  s.split("-");


                System.out.println("range like references  : ");
                for(String x : range)
                {
                    System.out.println(x);
                }
                System.out.println("end of range");
                try {
                    int num1 = Integer.parseInt(range[0].trim());

                    int num2 = Integer.parseInt(range[1].trim());

                    while (num1 <= num2) {

                        intro_ref_string_list.add(Integer.toString(num1).trim());
                        num1++;
                    }
                }catch (Exception e){System.out.println("no range found");}
            }
            else {
                intro_ref_string_list.add(s.trim());
            }



        }


     return intro_ref_string_list;
    }
}
