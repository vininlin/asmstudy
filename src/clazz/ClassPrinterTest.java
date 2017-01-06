/**
 * 
 */
package clazz;

import java.io.IOException;

import org.objectweb.asm.ClassReader;


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
