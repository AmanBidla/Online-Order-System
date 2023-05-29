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

public class ProductLine {
    
    private int ID;
    private String TextDescription;
    private String HTMLDescription;
    private String Image;
    
    public ProductLine(int ID, String TextDescription, String HTMLDescription, String Image){
        this.ID=ID;
        this.TextDescription=TextDescription;
        this.HTMLDescription=HTMLDescription;
        this.Image=Image;    
    }

    public ProductLine(String TextDescription){
        this.TextDescription=TextDescription;
    }

    public ProductLine() {
    
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
     * @return the TextDescription
     */
    public String getTextDescription() {
        return TextDescription;
    }

    /**
     * @param TextDescription the TextDescription to set
     */
    public void setTextDescription(String TextDescription) {
        this.TextDescription = TextDescription;
    }

    /**
     * @return the HTMLDescription
     */
    public String getHTMLDescription() {
        return HTMLDescription;
    }

    /**
     * @param HTMLDescription the HTMLDescription to set
     */
    public void setHTMLDescription(String HTMLDescription) {
        this.HTMLDescription = HTMLDescription;
    }

    /**
     * @return the Image
     */
    public String getImage() {
        return Image;
    }

    /**
     * @param Image the Image to set
     */
    public void setImage(String Image) {
        this.Image = Image;
    }
        
    
}
