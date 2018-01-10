/*
 * created by
 * ABHISHEK SHARMA
 * NIT Mizoram
 * Happy Coding
 */

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Auto_Script_Generation_for_feaures {
    public static void main(String args[])
    {

        //String pathname = "/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_RAR/INS-D-15-246[1]_cleaned.pdf.xml";
        /*File folder = new File("/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_RAR");
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
                countForFile++;*/
                // String pathname = "/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_RAR" + fileName;
                String pathname = "/home/abhi/eclipse-oxygen/INS_CleanUpdate/XML/INS_RAR/INS-D-14-2101[1]_cleaned.pdf.xml";
                File inputFile = new File(pathname);
                System.out.println("file name  :  " + pathname);
                // extracting the main title
                Extracting_Main_TitleOfPaper obj_new_paper = new Extracting_Main_TitleOfPaper();
                String title = null;
                try {
                    title = obj_new_paper.Get_Main_Title(pathname);
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }

                // extracting the total search number
                totalResultsAfterSearching obj_searching_result = new totalResultsAfterSearching();
                int total_related_search = obj_searching_result.totalResult(title);


                /**************************Feature-1***************************/
                if(title != null)
                {
                    try{
                        Feature_1 obj_Fe1 = new Feature_1();
                        obj_Fe1.Get_Feature_1(title,total_related_search,pathname);
                    }catch (Exception e){System.out.println("Somthing wrong");}

                }
                else{
                    System.out.println("File not Found");
                }



                /**************************Feature-2**************************/
                if(title != null)
                {
                    try{
                        Feature_2 obj_Fe2 = new Feature_2();
                        obj_Fe2.Get_Feature_2(title,total_related_search,pathname);
                    }catch (Exception e){System.out.println("Something wrong");}

                }
                else{
                    System.out.println("File not Found");
                }



                /**************************Feature-3**************************/
                if(title != null)
                {
                    try{
                        Feature_3 obj_Fe3 = new Feature_3();
                        obj_Fe3.Get_Feature_3(pathname);
                    }catch (Exception e){System.out.println("Something wrong");}

                }
                else{
                    System.out.println("File not Found");
                }



            //}
        //}


    }
}
