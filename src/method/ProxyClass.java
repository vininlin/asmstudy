/**
 * 
 */
package method;

/**
 * ��/�ӿ�ע��
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016��12��30��
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
