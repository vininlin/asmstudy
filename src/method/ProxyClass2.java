/**
 * 
 */
package method;

import java.util.ArrayList;
import java.util.List;


public class ProxyClass2 {

    public static List<String> getList(List<Integer> params){
        List<String> list = TargetClass.getList(params);
        List<String> allList = new ArrayList<String>();
        allList.addAll(list);
        List<String> list2 = TargetClass2.getList(params);
        allList.addAll(list2);
        return allList;
    }
}
