/**
 * 
 */
package clazz;

import java.io.IOException;

import org.objectweb.asm.ClassReader;


/**
 * ��/�ӿ�ע��
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016��12��30��
 * 
 */
public class ClassPrinterTest {

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("java.lang.Runnable");
        cr.accept(new ClassPrinter(), 0);
    }

}
