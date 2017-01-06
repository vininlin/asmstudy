/**
 * 
 */
package proxy;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class ClusterMethodProxy extends ClassVisitor{

    private String cname;
    private String mname;
    private String desc;
    private String LocalEntity;
    
    /**
     * @param arg0
     * @param arg1
     */
    public ClusterMethodProxy(int api, ClassVisitor cv,String mname,String desc,String LocalEntity) {
        super(api, cv);
        this.mname = mname;
        this.desc = desc;
        this.LocalEntity = LocalEntity;
    }
    
    
    @Override
    public void visit(int version, int access, String name, String signature, 
            String superName, String[] interfaces) {
        this.cname = name;
        System.out.println(name + " extends " + superName + " {");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
        System.out.println("Annotation="+desc);
        return super.visitAnnotation(desc, visible);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
           String[] exceptions) {
       System.out.println(" " + name + desc);
       String newName = name;
       if( cv != null && name.equals(mname)){
          newName = name + "$Local";
          Type[] types = Type.getArgumentTypes(desc);
          for(Type type : types){
              System.out.println("arg type="+type.getDescriptor());
          }
          System.out.println("return type="+Type.getReturnType(desc).getDescriptor());
          genrateMergeMethod(access,name,newName,desc,signature,exceptions);
       }
       return super.visitMethod(access, newName, desc, signature, exceptions);
    }
    
    private void genrateMergeMethod(int access, String name,String newName, String desc, String signature,
            String[] exceptions){
        //生成与旧方法同名的方法
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        //方法体
        mv.visitCode();
        //调用旧方法
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, cname, newName, desc, false);
        mv.visitVarInsn(Opcodes.ASTORE, 1);
        //保存到list
        mv.visitTypeInsn(Opcodes.NEW, "java/util/ArrayList");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        mv.visitVarInsn(Opcodes.ASTORE, 2);
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "addAll", "(Ljava/util/Collection;)Z", false);
        mv.visitInsn(Opcodes.POP);
        //调用远程服务方法
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "service/RemotePersonService", "getList", "(Ljava/util/List;)Ljava/util/List;", false);
        mv.visitVarInsn(Opcodes.ASTORE, 3);
        //类型转换
        mv.visitVarInsn(Opcodes.ALOAD, 3);
        mv.visitLdcInsn(Type.getType(this.LocalEntity));
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "util/BeanUtil", "convertList","(Ljava/util/List;Ljava/lang/Class;)Ljava/util/List;", false);
        //mv.visitMethodInsn(Opcodes.INVOKESTATIC, "util/Transfer", "transfer","(Ljava/util/List;)Ljava/util/List;", false);
        mv.visitVarInsn(Opcodes.ASTORE, 4);
        //mv.visitInsn(Opcodes.POP);
        //添加到list
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitVarInsn(Opcodes.ALOAD, 4);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/ArrayList", "addAll", "(Ljava/util/Collection;)Z", false);
        //返回list
        mv.visitInsn(Opcodes.POP);
        mv.visitVarInsn(Opcodes.ALOAD, 2);
        mv.visitInsn(Opcodes.ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    
}
