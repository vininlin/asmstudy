/**
 * 
 */
package method;

/**
 * 类/接口注释
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016年12月30日
 * 
 */
public class ProxyClass {

    public static void before(){
        System.out.println("do something before");
    }
    
    public static void after(){
        System.out.println("do something after");
    }
}
