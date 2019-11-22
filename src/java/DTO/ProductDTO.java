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
public class ProductDTO implements Serializable {
    private String id;
    private String name;
    private double price;
    private String imagePath;
    private boolean status;

    public ProductDTO() {
    }

    public ProductDTO(String id, String name, double price, String imagePath, boolean status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imagePath = imagePath;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

        
    
}
