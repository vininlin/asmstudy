/**
 * 
 */
package util;

import java.util.ArrayList;
import java.util.List;

public class IdResolver {

    public static List<Long> getIds(List<Long> ids,boolean local){
        List<Long> idArr = new ArrayList<Long>();
        if(local){
            for(Long id : ids){
                if(id % 2 == 0){
                    idArr.add(id);
                }
            }
        }else{
            for(Long id : ids){
                if(id % 2 != 0){
                    idArr.add(id);
                }
            }
        }
        return idArr;
        
    }
}
