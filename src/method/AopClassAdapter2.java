/**
 * 
 */
package method;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;


public class AopClassAdapter2 extends ClassVisitor {

    private String mname;
    private String cname;
    /**
     * @param paramInt
     */
    public AopClassAdapter2(int api,ClassVisitor cv,String mname,String desc) {
        super(api,cv);
        this.mname = mname;
    }

   @Override
   public void visit(int version, int access, String name, String signature, 
           String superName, String[] interfaces) {
       this.cname = name;
       System.out.println(name + " extends " + superName + " {");
       super.visit(version, access, name, signature, superName, interfaces);
   }

   @Override
   public MethodVisitor visitMethod(int access, String name, String desc, String signature,
          String[] exceptions) {
      System.out.println(" " + name + desc);
      String newName = name;
      if( cv != null && name.equals(mname)){
         newName = name + "$Local";
         addNewMethod2(access,name,newName,desc,signature,exceptions);
      }
      return super.visitMethod(access, newName, desc, signature, exceptions);
   }
   
      
      private void addNewMethod2(int access, String name,String newName, String desc, String signature,
              String[] exceptions){
          System.out.println("name="+name+";desc="+desc+";signature="+signature);
          MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
          mv.visitCode();
          mv.visitVarInsn(Opcodes.ALOAD, 0);
          mv.visitMethodInsn(Opcodes.INVOKESTATIC, cname, newName, desc, false);
          mv.visitVarInsn(Opcodes.ASTORE, 0);
          mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList");
          mv.visitInsn(Opcodes.DUP);
          mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
          mv.visitVarInsn(Opcodes.ASTORE, 1);
          mv.visitVarInsn(Opcodes.ALOAD, 1);
          mv.visitVarInsn(Opcodes.ALOAD, 0);
          mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "addAll", "(Ljava/util/Collection;)Z", false);
          mv.visitInsn(Opcodes.POP);
          mv.visitVarInsn(Opcodes.ALOAD, 0);
          mv.visitMethodInsn(Opcodes.INVOKESTATIC, "method/TargetClass", "getList", "(Ljava/util/List;)Ljava/util/List;", false);
          mv.visitVarInsn(Opcodes.ASTORE, 2);
          mv.visitVarInsn(Opcodes.ALOAD, 1);
          mv.visitVarInsn(Opcodes.ALOAD, 2);
          mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "addAll", "(Ljava/util/Collection;)Z", false);
          mv.visitInsn(Opcodes.POP);
          mv.visitVarInsn(Opcodes.ALOAD, 1);
          mv.visitInsn(Opcodes.ARETURN);
          mv.visitMaxs(0, 0);
          mv.visitEnd();
      }

      private void addNewMethod(int access, String name,String newName, String desc, String signature,
              String[] exceptions){
          System.out.println("name:" + name + ";newName:" + newName);
          MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
          mv.visitCode();
          //mv.visitVarInsn(Opcodes.AALOAD, 0);
          mv.visitMethodInsn(Opcodes.INVOKESTATIC, cname, newName, desc, false);
          mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
          mv.visitLdcInsn("GOTit!");
          mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V",false);
          //mv.visitInsn(Opcodes.ICONST_0);
          mv.visitInsn(Opcodes.RETURN);
          mv.visitMaxs(0, 0);
          mv.visitEnd();
      }
    

}
