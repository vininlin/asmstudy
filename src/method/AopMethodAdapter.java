/**
 * 
 */
package method;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * ��/�ӿ�ע��
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016��12��30��
 * 
 */
public class AopMethodAdapter extends MethodVisitor{

    

    /**
     * @param paramInt
     * @param paramMethodVisitor
     */
    public AopMethodAdapter(MethodVisitor mv) {
        super(Opcodes.ASM5, mv);
    }

       @Override
    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(Opcodes.INVOKESTATIC, "method/ProxyClass", "before", "()V", false);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("before");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

      @Override
    public void visitInsn(int opcode) {
        if(opcode == Opcodes.RETURN){
            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            mv.visitLdcInsn("after");
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
            this.visitMethodInsn(Opcodes.INVOKESTATIC, "method/ProxyClass", "after", "()V", false);
        }
        super.visitInsn(opcode);
    }
}
