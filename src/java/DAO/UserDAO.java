/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.UserDTO;
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
public class UserDAO implements Serializable {

    public int checkLogin(String username, String password) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int role = -1;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
//            sql += "select [username],[password] \n";
//            sql += "from [Ussers]";
//            sql += "where [username]=? and password =?";
            sql += "select role\n"
                    + "from [Users]\n"
                    + "where [username]=? and password=?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                pst.setString(2, password);
                rs = pst.executeQuery();
                if (rs != null && rs.next()) {
                    role = rs.getInt("role");
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return role;
    }

    public boolean checkStatus(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [Members].status FROM [Members] INNER JOIN [Users] \n"
                    + "ON [Members].userid = [Users].id \n"
                    + "WHERE [Users].username = ?";
            if(cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                rs.next();
                flag = rs.getBoolean("status");
                rs.close();
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

    public boolean checkDuplicate(String userName) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [username] \n";
            sql += "FROM [Users]\n";
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

    public int countUser() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT COUNT (*) AS total FROM [Users]";

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

    public int[] getAllID() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int[] arrID = null;
        int i = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [id] FROM [Users]";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                arrID = new int[countUser()];
                while (rs.next()) {
                    arrID[i] = rs.getInt("id");
                    i++;
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return arrID;
    }

    public int getIndex() throws NamingException, SQLException {
        int[] arrID = getAllID();
        int size = arrID.length;
        int i = arrID[size - 1] + 1;
        int tmp = 0, tmp2;

        for (int j = 0; j < arrID.length; j++) {
            tmp = arrID[j];
            if (j == (arrID.length - 1)) {
                i = arrID[size - 1] + 1;
                return i;
            } else {
                tmp2 = arrID[j + 1];
            }
            tmp2--;
            if (tmp != tmp2) {
                tmp++;
                i = tmp;
                return i;
            }
        }

        return i;
    }

    public boolean registerUser(String usr, String passwd) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = true;
        int index = getIndex();

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "INSERT INTO [Users] VALUES (?,?,?,?)";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, index);
                pst.setString(2, usr);
                pst.setString(3, passwd);
                pst.setInt(4, 2);
                flag = pst.executeUpdate() > 0;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

    public boolean deleteUser(int id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "DELETE [Users]\n"
                    + "FROM [Users] WHERE [id] = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
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

    public String getPasswordbyID(String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String passwd = null;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [Users].password FROM [Users] WHERE [Users].id = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, id);
                rs = pst.executeQuery();
                rs.next();
                passwd = rs.getString("password");
                rs.close();
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return passwd;
    }

    public String getPasswordbyUserName(String usrName) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String passwd = null;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [Users].password FROM [Users] WHERE [Users].username = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, usrName);
                rs = pst.executeQuery();
                rs.next();
                passwd = rs.getString("password");
                rs.close();
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return passwd;
    }

    public boolean setNewPass(String passwd, String id) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "UPDATE [Users] SET [password] = ? WHERE [Users].id = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, passwd);
                pst.setString(2, id);
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
