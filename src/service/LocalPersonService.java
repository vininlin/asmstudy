/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.ws.ServiceMode;

import pojo.LocalPerson;
import util.IdResolver;

@ClassAnno(value = "22")
public class LocalPersonService {
    
    private static Map<Long,LocalPerson> dataMap = new HashMap<>();
    
    static {
        dataMap.put(1L, new LocalPerson("a",11));
        dataMap.put(2L, new LocalPerson("b",11));
        dataMap.put(3L, new LocalPerson("c",11));
        dataMap.put(4L, new LocalPerson("d",11));
    }

    @MethodAnno(value = "11",name="SB")
    public static List<LocalPerson> getList(List<Long> ids){
        List<Long> localIds = IdResolver.getIds(ids, true);
        List<LocalPerson> personList = new ArrayList<LocalPerson>();
        for(Long id : localIds){
            personList.add(dataMap.get(id));
        }
        return personList;
    }
    
    public static void m(){
        
    }
}
