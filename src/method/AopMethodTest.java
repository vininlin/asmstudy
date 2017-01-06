/**
 * 
 */
package method;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;


public class AopMethodTest extends ClassLoader{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("method.TargetClass");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new AopClassAdapter(Opcodes.ASM5,cw);
        cr.accept(cv, ClassReader.SKIP_DEBUG);
        byte[] bytecodes = cw.toByteArray();
        AopMethodTest loader = new AopMethodTest();
        Class<?> transferClass = loader.defineClass(null, bytecodes, 0, bytecodes.length);
        transferClass.getMethods()[0].invoke(transferClass.newInstance(), new Object[]{});
    }

}
