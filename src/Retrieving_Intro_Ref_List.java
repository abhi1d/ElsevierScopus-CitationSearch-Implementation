import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Retrieving_Intro_Ref_List {

    public ArrayList<String> Get_Ref_From_Intro(String pathname) throws ParserConfigurationException, IOException, org.xml.sax.SAXException {
        File inputFile = new File(pathname);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc_div = dBuilder.parse(inputFile);
        doc_div.getDocumentElement().normalize();
        System.out.println("Root element :" + doc_div.getDocumentElement().getNodeName());

        int flag = 0;
        System.out.println("\t\tFeature 3 extracting...........\n");
        NodeList nList_div = doc_div.getElementsByTagName("div");

        ArrayList<String> ref_list = new ArrayList<>();

        for(int i = 0 ; i < nList_div.getLength(); i++ )
        {
            Node node_div = nList_div.item(i);
            Element el_div = (Element)node_div;
            NodeList nList_head  = el_div.getElementsByTagName("head");
            for(int j = 0 ; j < nList_div.getLength() ; j++)
            {
                Node node_head_type = nList_head.item(j);
                Element el_head = (Element) node_head_type;

                String type_of_head = "";
                // here we are extracting the type(n) of the headings
                // Because headings will start from 1.,2.,3. etc and sub headings will be like 1.1,2.3 etc
                // when we get exact number then that will be main heading.
                // So we will extract introduction part and when the 2nd heading comes then we will not count.
                try{
                    type_of_head = el_head.getAttribute("n");
                }catch (Exception e){

                }
                String head_counter = "";
                if(type_of_head.contains("."))
                {
                    head_counter = type_of_head.replace(".","").trim();

                }
                int head_type;
                try{
                    head_type = Integer.parseInt(head_counter);
                }catch (Exception e)
                {
                   head_type = 0;
                }

                if( head_type != 2)
                {
                    NodeList nList_ref = el_div.getElementsByTagName("ref");

                    for(int k = 0 ; k < nList_ref.getLength() ; k++)
                    {
                        Node node_ref = nList_ref.item(k);
                        Element el_ref = (Element) node_ref;
                        String ref_name = el_ref.getTextContent();
                        ref_list.add(ref_name);
                    }
                }
                else{
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)
                break;
        }


        ArrayList<String> intro_ref_string_list = new ArrayList<String>();
        for (String s : ref_list) {
            s = s.replace("[", "");
            s = s.replace("]", "");

            // sometimes we will get range of references
            if(s.contains("-"))
            {
                String [] range =  s.split("-");



                //System.out.println("end of range");
                try {
                    int num1 = Integer.parseInt(range[0].trim());

                    int num2 = Integer.parseInt(range[1].trim());

                    while (num1 <= num2) {

                        intro_ref_string_list.add(Integer.toString(num1).trim());
                        num1++;
                    }
                }catch (Exception e){System.out.print("");}
            }
            else {
                intro_ref_string_list.add(s.trim());
            }



        }


        return  intro_ref_string_list;
    }
}

