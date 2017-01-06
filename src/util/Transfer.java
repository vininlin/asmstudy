/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;

import pojo.LocalPerson;
import pojo.RemotePerson;

/**
 * 类/接口注释
 * 
 * @author weining.lwn@alibaba-inc.com
 * @createDate 2017年1月3日
 * 
 */
public class Transfer {

    public static List<LocalPerson> transfer(List<RemotePerson> remoteList){
        List<LocalPerson> localList = new ArrayList<>();
        for(RemotePerson p : remoteList){
            LocalPerson lp = new LocalPerson(p.getName(),p.getAge());
            localList.add(lp);
        }
        return localList;
    }
}
