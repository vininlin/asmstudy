/**
 * 
 */
package method;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * ��/�ӿ�ע��
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2016��12��30��
 * 
 */
public class AopClassAdapter2 extends ClassVisitor {

    /**
     * @param paramInt
     */
    public AopClassAdapter2(int api,ClassVisitor cv) {
        super(api,cv);
    }

   @Override
   public void visit(int version, int access, String name, String signature, 
           String superName, String[] interfaces) {
           System.out.println(name + " extends " + superName + " {");
           super.visit(version, access, name, signature, superName, interfaces);
   }

      @Override
   public MethodVisitor visitMethod(int access, String name, String desc, String signature,
          String[] exceptions) {
         System.out.println(" " + name + desc);
         MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
         if( name.equals("getList")){
             mv = cv.visitMethod(access, name + "$Local", desc, signature, exceptions);
         }
      return mv;
   }

       @Override
    public void visitEnd() {
        super.visitEnd();
    }
    
    

}
