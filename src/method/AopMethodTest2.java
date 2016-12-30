/**
 * 
 */
package method;

import java.io.IOException;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ��/�ӿ�ע��
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016��12��30��
 * 
 */
public class AopMethodTest2 extends ClassLoader{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("method.TargetClass2");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        addNewMethod(cw);
        ClassVisitor cv = new AopClassAdapter2(Opcodes.ASM5,cw);
        cr.accept(cv, ClassReader.SKIP_DEBUG);
        byte[] bytecodes = cw.toByteArray();
        AopMethodTest2 loader = new AopMethodTest2();
        Class<?> transferClass = loader.defineClass(null, bytecodes, 0, bytecodes.length);
        //transferClass.getMethods()[0].invoke(transferClass.newInstance(), new Object[]{});
        InvokeClass.getList();
    }
    
    public static void addNewMethod(ClassWriter cw){
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, 
                "getList", "()Ljava.util.List;", null, null);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("this is getList method print");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava.lang.String;)V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

}
