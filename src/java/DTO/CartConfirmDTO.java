/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;

/**
 *
 * @author SE130531
 */
public class CartConfirmDTO implements Serializable {
    private String proid;
    private String proName;
    private double price;
    private String image;
    private int quantity;

    public CartConfirmDTO() {
    }

    public CartConfirmDTO(String proid, String proName, double price, String image, int quantity) {
        this.proid = proid;
        this.proName = proName;
        this.price = price;
        this.image = image;
        this.quantity = quantity;
    }

    public String getProid() {
        return proid;
    }

    public void setProid(String proid) {
        this.proid = proid;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public double getTotal() {
        return price * quantity;
    }
}
