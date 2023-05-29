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

public class Payment {
    
    private String ChequeNo;
    private int CustomerID;
    private Date PaymentDate;
    private double Amount;

    /**
     * @return the ChequeNo
     */
    public String getChequeNo() {
        return ChequeNo;
    }

    /**
     * @param ChequeNo the ChequeNo to set
     */
    public void setChequeNo(String ChequeNo) {
        this.ChequeNo = ChequeNo;
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
     * @return the PaymentDate
     */
    public Date getPaymentDate() {
        return PaymentDate;
    }

    /**
     * @param PaymentDate the PaymentDate to set
     */
    public void setPaymentDate(Date PaymentDate) {
        this.PaymentDate = PaymentDate;
    }

    /**
     * @return the Amount
     */
    public double getAmount() {
        return Amount;
    }

    /**
     * @param Amount the Amount to set
     */
    public void setAmount(double Amount) {
        this.Amount = Amount;
    }
    
}
