/**
 * 
 */
package proxy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;

public class MethodInfo {

    public String className;
    public List<String> names = new ArrayList<>();
    public List<String> descs = new ArrayList<>();
    public List<String> annoNames = new ArrayList<>();
    public Map<String,String> item = new HashMap<>();
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
    
    
}
