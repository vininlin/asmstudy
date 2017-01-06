/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import net.sf.cglib.beans.BeanCopier;
import pojo.LocalPerson;
import pojo.RemotePerson;


/**
 * 1、利用apache common bean
 * 反映机制实现，性能不高
 * 2、CGLib，动态代理实现，性能好
 * 
 * 
 * @author linwn@ucweb.com
 * @createDate 2014-6-29
 * 
 */
public class BeanUtil {

    private static final ConcurrentHashMap<String,BeanCopier> 
                                        cglibBeanCopierMap = new ConcurrentHashMap<String,BeanCopier>();
    
    /**
     * 采用CGLib动态代理实现，性能较好，推荐使用
     * @param dest目标对象
     * @param orig原始对象
     */
    public static void copy(Object dest,Object orig){
        if(dest == null || orig == null){
            return;
        }
        //这个动态代理创建比较耗性能,缓存在map中。
        String key = String.format("%s_%s", dest.getClass().getName(),orig.getClass().getName());
        BeanCopier copier = null;
        if(!cglibBeanCopierMap.containsKey(key)){
            copier = BeanCopier.create(orig.getClass(), dest.getClass(), false);
            cglibBeanCopierMap.put(key, copier);
        }else{
            copier = cglibBeanCopierMap.get(key);
        }
        //没有使用转换器,避免使用属性类型不同,名字相同，类型不同不会复制
        copier.copy(orig, dest, null);
    }
    
    public static <T> T convert(Object source,Class<T> targetClass){
        if(source == null ){
            return null;
        }
        //这个动态代理创建比较耗性能,缓存在map中。
        String key = String.format("%s_%s", targetClass.getName(),source.getClass().getName());
        BeanCopier copier = null;
        if(!cglibBeanCopierMap.containsKey(key)){
            copier = BeanCopier.create(source.getClass(), targetClass, false);
            cglibBeanCopierMap.put(key, copier);
        }else{
            copier = cglibBeanCopierMap.get(key);
        }
        //没有使用转换器,避免使用属性类型不同,名字相同，类型不同不会复制
        T target;
        try {
            target = targetClass.newInstance();
            copier.copy(source, target, null);
            return target;
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
        
    }
    
    public static <T> List<T> convertList(List source,Class<T> targetClass){
        if(source == null){
            return null;
        }
        try{
            if(source.isEmpty()){
                return source.getClass().newInstance();
            }
            List result = source.getClass().newInstance();
            for(Object src : source){
                result.add(convert(src,targetClass));
            }
            return result;
        }catch(Exception e){
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
    
    
    public static void main(String[] args){
        List<RemotePerson> remoteList = new ArrayList<>();
        RemotePerson rp = new RemotePerson("a",1);
        remoteList.add(rp);
       /* LocalPerson lp = new LocalPerson();
        copy(lp,rp);
        System.out.println(lp.getName());*/
        
        List<LocalPerson> localList = convertList(remoteList,LocalPerson.class);
        for(LocalPerson p : localList){
            System.out.println(p.getName());
        }
        
    }
    
    
    
    
    
}
