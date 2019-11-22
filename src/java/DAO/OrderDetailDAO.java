/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

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
public class OrderDetailDAO implements Serializable {

    
    public int getCountOrderDetail() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT COUNT (*) AS total FROM [OrderDetails]";

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
    
    
    
    public boolean addOderDetail(int index, String orderid, String proid, int quantity ) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql = "INSERT INTO [OrderDetails] VALUES (?, "
                    + "(SELECT orderid FROM [Orders] WHERE orderid = ?), "
                    + "(SELECT proid FROM [Products] WHERE proid = ?), ?)";
            
            if(cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, index);
                pst.setString(2, orderid);
                pst.setString(3, proid);
                pst.setInt(4, quantity);
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
