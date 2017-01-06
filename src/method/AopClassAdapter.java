/**
 * 
 */
package method;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;


public class AopClassAdapter extends ClassVisitor {

    /**
     * @param paramInt
     */
    public AopClassAdapter(int api,ClassVisitor cv) {
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
         if( name.equals("method1")){
             mv = new AopMethodAdapter(mv);
         }
      return mv;
   }

       @Override
    public void visitEnd() {
        super.visitEnd();
    }
    
    

}
