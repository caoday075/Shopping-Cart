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
public class CustomerDTO implements Serializable {
    private String cusID;
    private String cusName;
    private String address;
    private String phone;
    private String email;

    public CustomerDTO() {
    }

    public CustomerDTO(String cusID, String cusName, String address, String phone, String email) {
        this.cusID = cusID;
        this.cusName = cusName;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getCusID() {
        return cusID;
    }

    public void setCusID(String cusID) {
        this.cusID = cusID;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
