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

public class Employee {
    
    private int ID;
    private int OfficeCode;
    private int ReportsTo;
    private String FirstName;
    private String LastName;
    private String Email;
    private String Title;
    private String DeskPhoneNo;
    private String CellPhoneNo;
    
    public Employee(int ID, int OfficeCode, int ReportsTo, String FirstName, String LastName, String Email, String Title, String DeskPhoneNo, String CellPhoneNo){
        this.ID=ID;
        this.OfficeCode=OfficeCode;
        this.ReportsTo=ReportsTo;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Email=Email;
        this.Title=Title;
        this.DeskPhoneNo=DeskPhoneNo;
        this.CellPhoneNo=CellPhoneNo;    
    }

    
    public Employee(int OfficeCode, int ReportsTo, String FirstName, String LastName, String Email, String Title, String DeskPhoneNo, String CellPhoneNo){
        this.OfficeCode=OfficeCode;
        this.ReportsTo=ReportsTo;
        this.FirstName=FirstName;
        this.LastName=LastName;
        this.Email=Email;
        this.Title=Title;
        this.DeskPhoneNo=DeskPhoneNo;
        this.CellPhoneNo=CellPhoneNo;    
    }
    
    public Employee() {
    
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
     * @return the OfficeCode
     */
    public int getOfficeCode() {
        return OfficeCode;
    }

    /**
     * @param OfficeCode the OfficeCode to set
     */
    public void setOfficeCode(int OfficeCode) {
        this.OfficeCode = OfficeCode;
    }

    /**
     * @return the ReportsTo
     */
    public int getReportsTo() {
        return ReportsTo;
    }

    /**
     * @param ReportsTo the ReportsTo to set
     */
    public void setReportsTo(int ReportsTo) {
        this.ReportsTo = ReportsTo;
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
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * @param Title the Title to set
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * @return the DeskPhoneNo
     */
    public String getDeskPhoneNo() {
        return DeskPhoneNo;
    }

    /**
     * @param DeskPhoneNo the DeskPhoneNo to set
     */
    public void setDeskPhoneNo(String DeskPhoneNo) {
        this.DeskPhoneNo = DeskPhoneNo;
    }

    /**
     * @return the CellPhoneNo
     */
    public String getCellPhoneNo() {
        return CellPhoneNo;
    }

    /**
     * @param CellPhoneNo the CellPhoneNo to set
     */
    public void setCellPhoneNo(String CellPhoneNo) {
        this.CellPhoneNo = CellPhoneNo;
    }
    
}
