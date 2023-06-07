/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Model;

/**
 *
 * @author Aman Bidla
 */

public class Office {
    
    private int Code;
    private String Address1;
    private String Address2;
    private String City;
    private String PostalCode;
    private String Phone;
    
    public Office(int Code, String Address1, String Address2, String City, String PostalCode, String Phone){
        this.Code=Code;
        this.Address1=Address1;
        this.Address2=Address2;
        this.City=City;
        this.PostalCode=PostalCode;
        this.Phone=Phone;
    }
    
    public Office(String Address1, String Address2, String City, String PostalCode, String Phone){
        this.Address1=Address1;
        this.Address2=Address2;
        this.City=City;
        this.PostalCode=PostalCode;
        this.Phone=Phone;
    }    
    
    public Office(){
    
    }

    /**
     * @return the Code
     */
    public int getCode() {
        return Code;
    }

    /**
     * @param Code the Code to set
     */
    public void setCode(int Code) {
        this.Code = Code;
    }

    /**
     * @return the Address1
     */
    public String getAddress1() {
        return Address1;
    }

    /**
     * @param Address1 the Address1 to set
     */
    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    /**
     * @return the Address2
     */
    public String getAddress2() {
        return Address2;
    }

    /**
     * @param Address2 the Address2 to set
     */
    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return the PostalCode
     */
    public String getPostalCode() {
        return PostalCode;
    }

    /**
     * @param PostalCode the PostalCode to set
     */
    public void setPostalCode(String PostalCode) {
        this.PostalCode = PostalCode;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

}
