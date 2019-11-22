/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.StaffDTO;
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
public class StaffDAO implements Serializable {

    public boolean checkLogin(String username, String password) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB \n";
            sql += "select [username],[password] \n";
            sql += "from [Staffs]";
            sql += "where [username]=? and password =?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
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

    public List<StaffDTO> getAllStaff() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<StaffDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB \n";
            sql += "SELECT * from [Staffs]";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    String id, us, pw, fname;
                    int status;
                    id = rs.getString("staffid");
                    us = rs.getString("username");
                    pw = rs.getString("password");
                    fname = rs.getString("fullname");
                    status = rs.getInt("status");
                    StaffDTO dto = new StaffDTO(id, us, pw, fname, status);                
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

    public List<StaffDTO> findStaff(String name) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<StaffDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB \n";
            sql += "select * \n";
            sql += "from [Staffs] \n";
            sql += "where fullname like '%" + name + "%'";

            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    String id, us, pw, fname;
                    int status;
                    id = rs.getString("staffid");
                    us = rs.getString("username");
                    pw = rs.getString("password");
                    fname = rs.getString("fullname");
                    status = rs.getInt("status");
                    StaffDTO dto = new StaffDTO(id, us, pw, fname, status);
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

    public boolean checkDuplicate(String userName) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB\n";
            sql += "SELECT [username]\n";
            sql += "FROM [Staffs]\n";
            sql += "WHERE [username]=?";
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, userName);
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
    
    
    public boolean registerUser(String usr, String passwd, String fullname) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = true;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB \n";
            sql += "INSERT INTO [Staffs] VALUES (?,?,?)";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, usr);
                pst.setString(2, passwd);
                pst.setString(3, fullname);
                flag = pst.executeUpdate() > 0;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

    public boolean deleteUser(String username) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB \n";
            sql += "delete [Staff] \n";
            sql += "where [username] =? ";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);

                if (pst.executeUpdate() >= 1) {
                    flag = true;
                }
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

//    public StaffDTO getUserByUsName(String usr) throws SQLException, NamingException {
//        Connection cn = null;
//        PreparedStatement pst = null;
//        ResultSet rs = null;
//        StaffDTO dto = null;
//
//        try {
//            cn = DBUtils.DBUtils.makeConnection();
//            String sql = "use JavaWebDB \n";
//            sql += "SELECT * FROM [Staffs] \n";
//            sql += "WHERE username=?";
//
//            if (cn != null) {
//                pst = cn.prepareStatement(sql);
//                pst.setString(1, usr);
//                rs = pst.executeQuery();
//                while (rs.next()) {
//                    String username = rs.getString("username");
//                    String password = rs.getString("password");
//                    String fullname = rs.getString("fullname");
//                    dto = new StaffDTO(username, password, fullname);
//                }
//            }
//
//        } finally {
//            if (cn != null) {
//                cn.close();
//            }
//        }
//        return dto;
//    }

    public boolean updateProfileUser(StaffDTO dto) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB \n";
            sql += "UPDATE [Staffs] SET [fullname] =?, [password]=? WHERE [username]=?";
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, dto.getFullname());
                pst.setString(2, dto.getPassword());
                pst.setString(3, dto.getUsername());
                if (pst.executeUpdate() > 0) {
                    flag = true;
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

}
