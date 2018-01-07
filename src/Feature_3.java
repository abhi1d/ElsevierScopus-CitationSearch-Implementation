/*
  * Now For feature 3 we are counting the ref_list_from_result.size() and ref_list_from_intro.size().
 * And the ratio between them will give us the 3rd feature.
 *
 */
/*
 * Issue : 1
 * References are repeating into the list.
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


     int counter1 = ref_list_from_result.size();
     int counter2 = ref_list_from_intro.size();
     //Set<String> set = new HashSet<>();


     System.out.println("feature  3  :  " + ((counter1*1.0)/counter2) );
  }
}