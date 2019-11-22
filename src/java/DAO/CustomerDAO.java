/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CustomerDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author SE130531
 */
public class CustomerDAO implements Serializable {

    public int countCustomer() throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "Use JavaWebDB2 \n";
            sql += "SELECT COUNT (cusID) FROM [Customers] AS Count";
             if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                while(rs.next()) {
                    count = rs.getInt("Count");
                }
            }            
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return count;
    }
    
     public boolean checkDuplicate(String cusSDT) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        String cusID = "CUS";
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [cusid]\n";
            sql += "FROM [Customers]\n";
            sql += "WHERE [cusid]=?";
            cusID += cusSDT;
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, cusID);
                rs = ps.executeQuery();
                if (rs != null & rs.next()) {
                    check = true;
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return check;
    }
    

    public boolean addCustomer(String name, String sdt, String address, String email) throws SQLException, NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "Use JavaWebDB2 \n";
            sql += "INSERT INTO [Customers] VALUES (?, ?, ?, ?, ?)";
           
            String idFormat = "CUS" + sdt;
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, idFormat);
                pst.setString(2, name);
                pst.setString(3, sdt);
                pst.setString(4, address);
                pst.setString(5, email);
                flag = pst.executeUpdate() > 0;
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }
    
    public String getID(String sdt) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String cusID = null;
        
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "Use JavaWebDB2 \n";
            sql += "SELECT cusid FROM [Customers] WHERE cusSDT = ?";
 
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, sdt);
                rs = pst.executeQuery();
                while(rs.next()) {
                     cusID = rs.getString("cusSDT");
                }
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return cusID;
    }
    
    public CustomerDTO getCusByID(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        CustomerDTO dto = null;
        
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "Use JavaWebDB2 \n";
            sql += "SELECT cusid, cusname, cusPhone, address, email FROM [Customers] Where [Customers].cusid = ?";
 
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                while(rs.next()) {
                    String cusid = rs.getString("cusid");
                    String name = rs.getString("cusname");
                    String phone = rs.getString("cusPhone");
                    String address = rs.getString("address");
                    String email = rs.getString("email");
                    
                    dto = new CustomerDTO(cusid, name, address, phone, email);
                }
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return dto;
    } 

}
