/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.OrderDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author SE130531
 */
public class OrderDAO implements Serializable {

    public String tmp = null;

    public int getCountOrder() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT COUNT (*) AS total FROM [Orders]";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                rs.next();
                count = rs.getInt("total");
                rs.close();
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return count;
    }

    public boolean checkDuplicate() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        tmp = timeStamp;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [orderid]\n";
            sql += "FROM [Orders]\n";
            sql += "WHERE [orderid]=?";

            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, timeStamp);
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

    public boolean addOrder(String cusPhone) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        if (tmp == null) {
            tmp = timeStamp;
        }
        Time time = new Time(new Date().getTime());

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "INSERT INTO [Orders] VALUES(?, GETDATE(), ?, ?, "
                    + "(SELECT cusid FROM Customers WHERE cusPhone = ?))";

            String orderID = tmp;
            String cusID = cusPhone;

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                pst.setTime(2, time);
                pst.setBoolean(3, false);
                pst.setString(4, cusID);
                flag = pst.executeUpdate() > 0;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

    public String getOrderID() {
        return tmp;
    }

    public List<OrderDTO> getYetOrder() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDTO> listYet = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT orderid, orderDate, [timestamp] FROM [Orders] WHERE [Orders].status = 0";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null) {
                    listYet = new ArrayList<>();
                    while (rs.next()) {
                        String id = rs.getString("orderid");
                        String date = rs.getString("orderDate");
                        Time time = rs.getTime("timestamp");
                        listYet.add(new OrderDTO(id, date, time.toString()));
                    }
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return listYet;
    }
    
    public List<OrderDTO> getDoneOrder() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<OrderDTO> listDone = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT orderid, orderDate, [timestamp] FROM [Orders] WHERE [Orders].status = 1";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                if (rs != null) {
                    listDone = new ArrayList<>();
                    while (rs.next()) {
                        String id = rs.getString("orderid");
                        String date = rs.getString("orderDate");
                        Time time = rs.getTime("timestamp");
                        listDone.add(new OrderDTO(id, date, time.toString()));
                    }
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return listDone;
    }
    
    public OrderDTO getOrderByID(String orderid) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        OrderDTO dto = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT orderid, orderDate, [timestamp], [status], [cusid] FROM [Orders] WHERE [Orders].orderid = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderid);
                rs = pst.executeQuery();
                if (rs != null) {                    
                    while (rs.next()) {
                        String id = rs.getString("orderid");
                        String date = rs.getString("orderDate");
                        Time time = rs.getTime("timestamp");
                        String timeStr = time.toString();
                        boolean status = rs.getBoolean("status");
                        String cusid = rs.getString("cusid");
                        dto = new OrderDTO(id, date, cusid, timeStr, status);
                    }
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return dto;        
    }
          
    public boolean confirmCart(String orderID) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;
        
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "UPDATE [Orders] SET [status] = 'true' WHERE [orderid] = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderID);
                flag = pst.executeUpdate() > 0;               
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag; 
    }
    
}
