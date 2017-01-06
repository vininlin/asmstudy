/**
 * 
 */
package method;

import java.util.ArrayList;
import java.util.List;


public class TargetClass2 {

    public static List<String> getList(List<Integer> idx){
        List<String> list = new ArrayList<>();
        list.add("c");
        list.add("d");
        System.out.println("this is getList");
        return list;
    }
}
