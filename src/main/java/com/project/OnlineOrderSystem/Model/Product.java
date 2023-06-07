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

public class Product {
    
    private int Code;
    private int ProductLineID;
    private String Name;
    private String vendor;
    private String Description;
    private int Quantity;
    private double BuyPrice;
    private double MSRP;
    
    public Product(int Code, int ProductLineID, String Name, String vendor, String Description, int Quantity, double BuyPrice, double MSRP){
        this.Code=Code;
        this.ProductLineID=ProductLineID;
        this.Name=Name;
        this.vendor=vendor;
        this.Description=Description;
        this.Quantity=Quantity;
        this.BuyPrice=BuyPrice;
        this.MSRP=MSRP;  
    }
    
    public Product(int ProductLineID, String Name, String vendor, String Description, int Quantity, double BuyPrice, double MSRP){
        this.ProductLineID=ProductLineID;
        this.Name=Name;
        this.vendor=vendor;
        this.Description=Description;
        this.Quantity=Quantity;
        this.BuyPrice=BuyPrice;
        this.MSRP=MSRP;  
    }    

    public Product(String Name, String vendor){
        this.Name=Name;
        this.vendor=vendor;  
    }

    public Product() {

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
     * @return the ProductLineID
     */
    public int getProductLineID() {
        return ProductLineID;
    }

    /**
     * @param ProductLineID the ProductLineID to set
     */
    public void setProductLineID(int ProductLineID) {
        this.ProductLineID = ProductLineID;
    }

    /**
     * @return the Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name the Name to set
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * @return the vendor
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * @param vendor the vendor to set
     */
    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /**
     * @return the Quantity
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * @param Quantity the Quantity to set
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * @return the BuyPrice
     */
    public double getBuyPrice() {
        return BuyPrice;
    }

    /**
     * @param BuyPrice the BuyPrice to set
     */
    public void setBuyPrice(double BuyPrice) {
        this.BuyPrice = BuyPrice;
    }

    /**
     * @return the MSRP
     */
    public double getMSRP() {
        return MSRP;
    }

    /**
     * @param MSRP the MSRP to set
     */
    public void setMSRP(double MSRP) {
        this.MSRP = MSRP;
    }
    
}
