/**
 * 
 */
package method;

import java.util.ArrayList;
import java.util.List;

public class TargetClass {

    public static void method1(){
        System.out.println("I am a static method");
    }
    
    public void method2(){
        System.out.println("I am a instance method");
    }
    
    public static List<String> getList(List<Integer> idx){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        return list;
    }
}
