/**
 * 
 */
package method;

import java.util.List;

/**
 * 类/接口注释
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016年12月30日
 * 
 */
public class InvokeClass {

    public static List<String> getList(){
        return TargetClass2.getList();
    }
}
