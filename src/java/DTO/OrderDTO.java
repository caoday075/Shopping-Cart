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
public class OrderDTO implements Serializable {
    private String orderID;
    private String orderDate;
    private String cusID;
    private String time;
    private boolean status;    

    public OrderDTO() {
    }

    public OrderDTO(String orderID, String orderDate, String cusID, String time, boolean status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.cusID = cusID;
        this.time = time;
        this.status = status;
    }

    public OrderDTO(String orderID, String orderDate, String time) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.time = time;
    }

    
    
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

        
}
