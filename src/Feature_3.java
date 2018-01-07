/*
 * Actually final list after matching the elements into the Intro part and Result section part are stored into the final_list and this list may have duplicate values
 * By using the Set we remove the duplicate values from the final_list
 * Now For feature 3 we are counting the final_list.size() and introduction_section.size().
 * And the ratio between them will give us the 3rd feature.
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Feature_3
{
  public static void main(String args[])
  {
      Retrieving_Intro_Ref_List obj_intro_ref = new Retrieving_Intro_Ref_List();
      ArrayList<String> ref_list_from_intro = new ArrayList<>();
      try {
          ref_list_from_intro =  obj_intro_ref.Get_Ref_From_Intro();
      } catch (Exception e) {
          e.printStackTrace();
      }
      System.out.println("Ref List from the Introduction and Related work Section :   ");
      System.out.println(ref_list_from_intro);

      ArrayList<String> ref_list_from_result = new ArrayList<>();

      Retrieving_Result_Ref_List obj_result_ref = new Retrieving_Result_Ref_List();
      try{
          ref_list_from_result =  obj_result_ref.Get_Ref_From_Result();
      }catch (Exception e)
      {
          e.printStackTrace();
      }
     System.out.println("Ref List from the Result Section :   ");
     System.out.println(ref_list_from_result);


     System.out.println("Now we are matching the elements from Introduction to result section  :  ");
     ArrayList<String> final_list = new ArrayList<String>();
     int counter1 = 0;
     for(String s1 : ref_list_from_intro )
     {
         for(String s2 : ref_list_from_result)
         {
             if(s1.equals(s2))
             {
                 System.out.println(s1);
                 final_list.add(s1);
             }
         }
     }

      Set<String> s = new HashSet<String>(final_list);
     for(String str : s)
     {
         System.out.println(str);
     }
     counter1 = s.size();
     int counter2 = ref_list_from_intro.size();

     System.out.println("feature  3  :  " + ((counter1*1.0)/counter2) );
  }
}