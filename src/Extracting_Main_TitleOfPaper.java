import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Extracting_Main_TitleOfPaper {
    public String Get_Main_Title(String pathname) throws ParserConfigurationException, IOException, SAXException {

        // extracting main title of the paper
        String main_title_of_paper  = null;

        File inputFile_title = new File(pathname);
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



        return main_title_of_paper;
    }
}
