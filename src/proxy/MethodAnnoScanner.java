/**
 * 
 */
package proxy;


import org.objectweb.asm.AnnotationVisitor;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;


public class MethodAnnoScanner extends ClassVisitor{
    
    private MethodInfo methodInfo;
    
    /**
     * @param arg0
     * @param arg1
     */
    public MethodAnnoScanner(MethodInfo methodInfo) {
        super(Opcodes.ASM5);
        this.methodInfo = methodInfo;
    }
    
    
    @Override
    public void visit(int version, int access, String name, String signature, 
            String superName, String[] interfaces) {
        methodInfo.className = name;
        System.out.println(name + " extends " + superName + " {");
        super.visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public AnnotationVisitor visitAnnotation(String annoName, boolean visible) {
        System.out.println(Type.getType(annoName).getClassName() + "=" + visible);
        return super.visitAnnotation(annoName, visible);
    }


    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
           String[] exceptions) {
       System.out.println(" " + name + desc);
       if(!name.equals("<clinit>") 
               && !name.equals("<init>") ){
           return new MethodAnnoVisitor(name,desc);
       }
       return super.visitMethod(access, name, desc, signature, exceptions);
       
       
    }
    
    class MethodAnnoVisitor extends MethodVisitor {
        
        private String name;
        private String desc;
        /**
         * @param paramInt
         * @param paramMethodVisitor
         */
        public MethodAnnoVisitor(String name,String desc) {
            super(Opcodes.ASM5);
            this.name = name;
            this.desc = desc;
            
        }
        @Override
        public AnnotationVisitor visitAnnotation(String annoName, boolean visible) {
            methodInfo.annoNames.add(Type.getType(annoName).getClassName());
            methodInfo.names.add(this.name);
            methodInfo.descs.add(this.desc);
            System.out.println(Type.getType(annoName).getClassName() + "=" + visible);
            return new MethodAnno();
        }
        
        class MethodAnno extends AnnotationVisitor{
            
            public MethodAnno() {
                super(Opcodes.ASM5);
            }
            
            @Override
            public void visit(String itemName, Object itemValue) {
                methodInfo.item.put(itemName, itemValue.toString());
                System.out.println(itemName + "=" + itemValue.toString() );
            }
            
        }
        

    }
    
   
    
}
