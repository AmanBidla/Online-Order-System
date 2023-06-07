/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.OnlineOrderSystem.Model;

import java.util.Date;

/**
 *
 * @author Aman Bidla
 */

public class Order {
    
    private int ID;
    private int CustomerID;
    private Date OrderDate;
    private Date RequiredDate;
    private Date Shipped;
    private String Status;
    private String Comments;
    
    public Order(int ID, int CustomerID, Date OrderDate, Date RequiredDate,Date Shipped, String Status, String Comments){
        this.ID=ID;
        this.CustomerID=CustomerID;
        this.OrderDate=OrderDate;
        this.RequiredDate=RequiredDate;
        this.Shipped=Shipped;
        this.Status=Status;
        this.Comments=Comments;    
    }

    public Order(int CustomerID, Date OrderDate, Date RequiredDate,Date Shipped, String Status, String Comments){
        this.CustomerID=CustomerID;
        this.OrderDate=OrderDate;
        this.RequiredDate=RequiredDate;
        this.Shipped=Shipped;
        this.Status=Status;
        this.Comments=Comments;    
    }    

    public Order(int CustomerID, Date OrderDate){
        this.CustomerID=CustomerID;
        this.OrderDate=OrderDate;
    }
    
    public Order() {
    
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
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /**
     * @return the OrderDate
     */
    public Date getOrderDate() {
        return OrderDate;
    }

    /**
     * @param OrderDate the OrderDate to set
     */
    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    /**
     * @return the RequireDate
     */
    public Date getRequiredDate() {
        return RequiredDate;
    }

    /**
     * @param RequireDate the RequireDate to set
     */
    public void setRequiredDate(Date RequiredDate) {
        this.RequiredDate = RequiredDate;
    }

    /**
     * @return the Shipped
     */
    public Date getShipped() {
        return Shipped;
    }

    /**
     * @param Shipped the Shipped to set
     */
    public void setShipped(Date Shipped) {
        this.Shipped = Shipped;
    }

    /**
     * @return the Status
     */
    public String getStatus() {
        return Status;
    }

    /**
     * @param Status the Status to set
     */
    public void setStatus(String Status) {
        this.Status = Status;
    }

    /**
     * @return the Comments
     */
    public String getComments() {
        return Comments;
    }

    /**
     * @param Comments the Comments to set
     */
    public void setComments(String Comments) {
        this.Comments = Comments;
    }
    
    
}
