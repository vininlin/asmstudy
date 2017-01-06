/**
 * 
 */
package clazz;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.TypePath;


public class ClassPrinter extends ClassVisitor {

    public ClassPrinter() {
        super(Opcodes.ASM5);
    }

        @Override
    public void visit(int version, int access, String name, String signature, 
            String superName, String[] interfaces) {
            System.out.println(name + " extends " + superName + " {");
    }

       @Override
    public void visitSource(String arg0, String arg1) {
        
    }

    
    @Override
    public void visitOuterClass(String arg0, String arg1, String arg2) {
        
    }

       @Override
    public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
        return null;
    }

        @Override
    public AnnotationVisitor visitTypeAnnotation(int arg0, TypePath arg1, String arg2,
            boolean arg3) {
        return null;
    }

       @Override
    public void visitAttribute(Attribute arg0) {
        super.visitAttribute(arg0);
    }

       @Override
    public void visitInnerClass(String arg0, String arg1, String arg2, int arg3) {
        super.visitInnerClass(arg0, arg1, arg2, arg3);
    }

        @Override
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
            System.out.println(" " + desc + " " + name);
        return null;
    }

       @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature,
            String[] exceptions) {
           System.out.println(" " + name + desc);
        return null;
    }

       @Override
    public void visitEnd() {
           System.out.println("}");
    }
    
    

}
