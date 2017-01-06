/**
 * 
 */
package proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import pojo.LocalPerson;
import service.LocalPersonService;

public class MethodAnnoTest extends ClassLoader{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("service.LocalPersonService");
        MethodInfo methodInfo = new MethodInfo();
        ClassVisitor cv = new MethodAnnoScanner(methodInfo);
        cr.accept(cv, ClassReader.SKIP_DEBUG);
        System.out.println(methodInfo);
    }

}
