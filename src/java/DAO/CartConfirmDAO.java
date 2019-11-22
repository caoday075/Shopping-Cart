/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.CartConfirmDTO;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author SE130531
 */
public class CartConfirmDAO implements Serializable {

    public List<CartConfirmDTO> getCart(String orderid) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<CartConfirmDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [OrderDetails].proid, [Products].ProName, [Products].Price, [Products].[image], [quantity]  \n"
                    + "FROM OrderDetails INNER JOIN [Products] \n"
                    + "ON [Products].proid = [OrderDetails].proid\n"
                    + "WHERE orderid = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, orderid);
                rs = pst.executeQuery();
                if (rs != null) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        String proid = rs.getString("proid");
                        String name = rs.getString("proName");
                        double price = rs.getDouble("price");
                        String image = rs.getString("image");
                        int quantity = rs.getInt("quantity");
                        list.add(new CartConfirmDTO(proid, name, price, image, quantity));
                    }
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }
    
     
    
}
