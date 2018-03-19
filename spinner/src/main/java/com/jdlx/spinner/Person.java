package com.jdlx.spinner;
/**
 * 用户对象
 * @author hbj403
 */
public class Person {
    private String personName;
    private String personAddress;

    public Person(String personName,String personAddress) {
        super();
        this.personName=personName;
        this.personAddress=personAddress;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAddress() {
        return personAddress;
    }

    public void setPersonAddress(String personAddress) {
        this.personAddress = personAddress;
    }

}