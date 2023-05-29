/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 *
 * @author Aman Bidla
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    
    private int ID;
    private int SalesRepEmpNo;
    private String FirstName;
    private String LastName;
    private String Phone;
    private String Address1;
    private String Address2;
    private String City;
    private String Province;
    private String PostalCode;
    private double CreditLimit;
    
    public Customer(int ID, int SalesRepEmpNo, String FirstName, String LastName, String Phone, String Address1, String Address2, String City, String Province, String PostalCode, double CreditLimit){
        this.ID=ID;
        this.SalesRepEmpNo=SalesRepEmpNo;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Phone=Phone;
        this.Address1=Address1;
        this.Address2=Address2;
        this.City=City;
        this.Province=Province;
        this.PostalCode=PostalCode;
        this.CreditLimit=CreditLimit;
    }
    
    public Customer(String FirstName, String LastName, String Phone, String Address1, String Address2, String City, String Province, String PostalCode, double CreditLimit){
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Phone=Phone;
        this.Address1=Address1;
        this.Address2=Address2;
        this.City=City;
        this.Province=Province;
        this.PostalCode=PostalCode;
        this.CreditLimit=CreditLimit;
    }
    
    public Customer(){
    }
    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the SalesRepEmpNo
     */
    public int getSalesRepEmpNo() {
        return SalesRepEmpNo;
    }

    /**
     * @param SalesRepEmpNo the SalesRepEmpNo to set
     */
    public void setSalesRepEmpNo(int SalesRepEmpNo) {
        this.SalesRepEmpNo = SalesRepEmpNo;
    }

    /**
     * @return the FirstName
     */
    public String getFirstName() {
        return FirstName;
    }

    /**
     * @param FirstName the FirstName to set
     */
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    /**
     * @return the LastName
     */
    public String getLastName() {
        return LastName;
    }

    /**
     * @param LastName the LastName to set
     */
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the PhoneNumber to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
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
     * @return the Province
     */
    public String getProvince() {
        return Province;
    }

    /**
     * @param Province the Province to set
     */
    public void setProvince(String Province) {
        this.Province = Province;
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
     * @return the CreditLimit
     */
    public double getCreditLimit() {
        return CreditLimit;
    }

    /**
     * @param CreditLimit the CreditLimit to set
     */
    public void setCreditLimit(double CreditLimit) {
        this.CreditLimit = CreditLimit;
    }
    
}
