/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ProductDTO;
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
public class ProductDAO implements Serializable {

    public List<ProductDTO> getActiveProducts() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT ProID, ProName, Price, image \n";
            sql += "FROM Products\n";
            sql += "WHERE [status] = 1";

            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("ProID");
                    String name = rs.getString("ProName");
                    double price = rs.getDouble("Price");
                    String path = rs.getString("image");
                    ProductDTO dto = new ProductDTO(id, name, price, path, true);
                    list.add(dto);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<ProductDTO> getAllProducts() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT ProID, ProName, Price, image, [status] \n";
            sql += "FROM Products";

            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();

            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    String id = rs.getString("ProID");
                    String name = rs.getString("ProName");
                    double price = rs.getDouble("Price");
                    String path = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    ProductDTO dto = new ProductDTO(id, name, price, path, status);
                    list.add(dto);
                }
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<ProductDTO> getProductsByName(String name) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<ProductDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT ProID, ProName, Price, image, [status] \n";
            sql += "FROM Products \n";
            sql += "WHERE ProName like ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                rs = pst.executeQuery();
                if (rs != null) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        String id = rs.getString("ProID");
                        String proName = rs.getString("ProName");
                        double price = rs.getDouble("Price");
                        String path = rs.getString("image");
                        boolean status = rs.getBoolean("status");
                        ProductDTO dto = new ProductDTO(id, proName, price, path, status);
                        list.add(dto);
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

    public ProductDTO getProductByID(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ProductDTO dto = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT ProID, ProName, Price, image, [status] \n";
            sql += "FROM Products \n";
            sql += "WHERE ProID=?";

            pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            rs = pst.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    String proID = rs.getString("ProID");
                    String proName = rs.getString("ProName");
                    double price = rs.getDouble("Price");
                    String path = rs.getString("image");
                    boolean status = rs.getBoolean("status");
                    dto = new ProductDTO(proID, proName, price, path, status);
                }
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return dto;
    }

    public boolean updateProduct(String id, String name, double price, String image, int status) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "UPDATE [Products] SET [ProName]=?, [Price]=?, [image]=?, [status]=? WHERE [ProID]=?";
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, name);
                pst.setDouble(2, price);
                pst.setString(3, image);
                pst.setInt(4, status);
                pst.setString(5, id);

                flag = pst.executeUpdate() > 0;
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }

        return flag;
    }
    
     public int countProduct() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT COUNT (*) AS total FROM [Products]";

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
    
    public boolean addProduct(ProductDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;
        
        try {
            cn = DBUtils.DBUtils.makeConnection();      
            String sql = "use JavaWebDB2 \n";
            sql += "INSERT INTO [Products] VALUES(?,?,?,?,?)";
            
            if(cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getId());
                pst.setString(2, dto.getName());
                pst.setDouble(3, dto.getPrice());
                pst.setString(4, dto.getImagePath());
                pst.setBoolean(5, dto.isStatus());
                flag = pst.executeUpdate() > 0;
            }
        } finally {
            if(cn != null)
                cn.close();
        }
        return flag;
    }
}
