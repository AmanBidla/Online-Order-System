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

public class OrderProduct {
    
    private int ID;
    private int OrderID;
    private int ProductCode;
    private int Quantity;
    private double PriceEach;
    
    public OrderProduct(int ID, int OrderID, int ProductCode, int Quantity, double PriceEach){
        this.ID=ID;
        this.OrderID=OrderID;
        this.ProductCode=ProductCode;
        this.Quantity=Quantity;
        this.PriceEach=PriceEach;
    }
    
    
    public OrderProduct(int OrderID, int ProductCode, int Quantity, double PriceEach) {
        this.Quantity=Quantity;
        this.PriceEach=PriceEach;
    }
    
    public OrderProduct(int Quantity, double PriceEach) {
        this.Quantity=Quantity;
        this.PriceEach=PriceEach;
    }
    

    public OrderProduct() {
    
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
     * @return the OrderID
     */
    public int getOrderID() {
        return OrderID;
    }

    /**
     * @param OrderID the OrderID to set
     */
    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    /**
     * @return the ProductCode
     */
    public int getProductCode() {
        return ProductCode;
    }

    /**
     * @param ProductCode the ProductCode to set
     */
    public void setProductCode(int ProductCode) {
        this.ProductCode = ProductCode;
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
     * @return the PriceEach
     */
    public double getPriceEach() {
        return PriceEach;
    }

    /**
     * @param PriceEach the PriceEach to set
     */
    public void setPriceEach(double PriceEach) {
        this.PriceEach = PriceEach;
    }
    
    
}
