/**
 * 
 */
package proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.sun.xml.internal.ws.org.objectweb.asm.Type;

import pojo.LocalPerson;
import service.LocalPersonService;

public class ClusterMethodProxyTest extends ClassLoader{

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws Exception {
        ClassReader cr = new ClassReader("service.LocalPersonService");
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        ClassVisitor cv = new ClusterMethodProxy(Opcodes.ASM5,cw,"getList","",
                Type.getDescriptor(LocalPerson.class));
        cr.accept(cv, ClassReader.SKIP_FRAMES);
        byte[] bytecodes = cw.toByteArray();
        ClusterMethodProxyTest loader = new ClusterMethodProxyTest();
        System.out.println(Type.getDescriptor(Class.forName("pojo.LocalPerson")));
        Class<?> transferClass = loader.defineClass(LocalPersonService.class.getName(), bytecodes, 0, bytecodes.length);
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        ids.add(2L);
        ids.add(3L);
        ids.add(4L);
        List<LocalPerson> list = (List<LocalPerson>)transferClass.getMethods()[0].invoke(transferClass.newInstance(), ids);
        for(LocalPerson str : list){
            System.out.println(str);
        }
        
        //InvokeClass.getList();
    }

}
