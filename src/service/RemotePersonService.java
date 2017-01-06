/**
 * 
 */
package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.RemotePerson;
import util.IdResolver;

public class RemotePersonService {
    
    private static Map<Long,RemotePerson> dataMap = new HashMap<>();
    
    static {
        dataMap.put(1L, new RemotePerson("a",12));
        dataMap.put(2L, new RemotePerson("b",13));
        dataMap.put(3L, new RemotePerson("c",14));
        dataMap.put(4L, new RemotePerson("d",15));
    }

    public static List<RemotePerson> getList(List<Long> ids){
        List<Long> localIds = IdResolver.getIds(ids, false);
        List<RemotePerson> personList = new ArrayList<RemotePerson>();
        for(Long id : localIds){
            personList.add(dataMap.get(id));
        }
        return personList;
    }
}
