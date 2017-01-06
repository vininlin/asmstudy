/**
 * 
 */
package pojo;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RemotePerson {
    private String name;
    private Integer age;
    public RemotePerson(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }
    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
    
}
