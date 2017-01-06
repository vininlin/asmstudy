/**
 * 
 */
package method;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class AopMethodTest2 extends ClassLoader{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("method.TargetClass2");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        //addNewMethod(cw);
        ClassVisitor cv = new AopClassAdapter2(Opcodes.ASM5,cw,"getList","");
        cr.accept(cv, ClassReader.SKIP_FRAMES);
        byte[] bytecodes = cw.toByteArray();
        AopMethodTest2 loader = new AopMethodTest2();
        Class<?> transferClass = loader.defineClass(TargetClass2.class.getName(), bytecodes, 0, bytecodes.length);
        List<Integer> params = new ArrayList<>();
        List<String> list = (List<String>)transferClass.getMethods()[0].invoke(transferClass.newInstance(),params);
        for(String str : list){
            System.out.println(str);
        }
        //InvokeClass.getList();
    }

}
