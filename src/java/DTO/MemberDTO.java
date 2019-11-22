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
public class MemberDTO implements Serializable {
    private int id;
    private String fullname;
    private String phone;
    private String address;
    private boolean status;
    private int userid;

    public MemberDTO() {
    }

    public MemberDTO(int id, String fullname, String phone, String address, boolean status, int userid) {
        this.id = id;
        this.fullname = fullname;
        this.phone = phone;
        this.address = address;
        this.status = status;
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }    
}
