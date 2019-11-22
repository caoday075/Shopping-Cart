/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.MemberDTO;
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
public class MemberDAO implements Serializable {

    public List<MemberDTO> getAllMembers() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<MemberDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT * from [Members]";
            pst = cn.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs != null) {
                list = new ArrayList<>();
                while (rs.next()) {
                    String fname, phone, address;
                    int id, userid;
                    id = rs.getInt("memberid");
                    fname = rs.getString("fullname");
                    phone = rs.getString("phone");
                    address = rs.getString("address");
                    boolean status = rs.getBoolean("status");
                    userid = rs.getInt("userid");

                    MemberDTO u = new MemberDTO(id, fname, phone, address, status, userid);
                    list.add(u);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return list;
    }

    public List<MemberDTO> getMembers(String name) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<MemberDTO> list = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "select * \n";
            sql += "from [Members] \n";
            sql += "where [fullname] like ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, "%" + name + "%");
                rs = pst.executeQuery();

                if (rs != null) {
                    list = new ArrayList<>();
                    while (rs.next()) {
                        String fname, phone, address;
                        int id, userid;
                        id = rs.getInt("memberid");
                        fname = rs.getString("fullname");
                        phone = rs.getString("phone");
                        address = rs.getString("address");
                        boolean status = rs.getBoolean("status");
                        userid = rs.getInt("userid");
                        MemberDTO u = new MemberDTO(id, fname, phone, address, status, userid);
                        list.add(u);
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

    public boolean checkDuplicate(String userName) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [username]\n";
            sql += "FROM [Members]\n";
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

    public int countMember() throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT COUNT (*) AS total FROM [Members]";

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
            sql += "SELECT [memberid] FROM [Members]";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                rs = pst.executeQuery();
                arrID = new int[countMember()];
                while (rs.next()) {
                    arrID[i] = rs.getInt("memberid");
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

    public boolean registerMember(int usrid) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = true;
        int index = getIndex();

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "INSERT INTO [Members] VALUES (?,?,?,?,?,"
                    + "(SELECT [Users].id FROM [Users] WHERE [Users].id =?))";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, index);
                pst.setString(2, "");
                pst.setString(3, "");
                pst.setString(4, "");
                pst.setInt(5, 1);
                pst.setInt(6, usrid);
                flag = pst.executeUpdate() > 0;
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return flag;
    }

    public boolean deleteMember(int userid) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebBD2 \n";
            sql += "DELETE [Members]\n"
                    + "FROM [Members] INNER JOIN [Users] \n"
                    + "ON [Users].id = [Members].userid\n"
                    + "WHERE [Members].userid = ?;";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, userid);
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
    
    public MemberDTO getMemberByUsrID(int usrID) throws NamingException, SQLException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        MemberDTO dto = null;
        
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [memberid], [fullname], [phone], [address], [status], [userid] FROM [Members]\n";
            sql += "WHERE [userid] = ?";
            
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, usrID);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String fname, phone, address;
                    int memid, userid;
                    memid = rs.getInt("memberid");
                    fname = rs.getString("fullname");
                    phone = rs.getString("phone");
                    address = rs.getString("address");
                    boolean status = rs.getBoolean("status");
                    userid = rs.getInt("userid");
                    dto = new MemberDTO(memid, fname, phone, address, status, userid);
                }
            }
        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return dto;
    }

    public MemberDTO getMemberByUsername(String username) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        MemberDTO dto = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [memberid], [fullname], [phone], [address], [status], [userid] FROM [Members]\n"
                    + "INNER JOIN [Users]\n"
                    + "ON [Users].username = ? AND\n"
                    + "	[Users].id = [Members].userid";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                rs = pst.executeQuery();
                while (rs.next()) {
                    String fname, phone, address;
                    int memid, userid;
                    memid = rs.getInt("memberid");
                    fname = rs.getString("fullname");
                    phone = rs.getString("phone");
                    address = rs.getString("address");
                    boolean status = rs.getBoolean("status");
                    userid = rs.getInt("userid");
                    dto = new MemberDTO(memid, fname, phone, address, status, userid);
                }
            }

        } finally {
            
        }
        return dto;
    }

    public boolean updateProfileMember(String fname, String phone, String addr, int status, int usrid) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        boolean flag = false;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "UPDATE [Members] SET [fullname] =?, [phone]=?, [address]=?, [status]=? WHERE [userid]=?";
            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, fname);
                pst.setString(2, phone);
                pst.setString(3, addr);
                pst.setInt(4, status);
                pst.setInt(5, usrid);
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

    public String getUsernameByID(int id) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        String username = null;

        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT DISTINCT [Users].username AS [tmp] FROM [Users]\n"
                    + "INNER JOIN [Members]\n"
                    + "ON [Users].id = ?";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setInt(1, id);
                rs = pst.executeQuery();
                rs.next();
                username = rs.getString("tmp");
                rs.close();
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return username;
    }

    public int getUsrIDByUserName(String username) throws SQLException, NamingException {
        Connection cn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int usrid = 0;
        try {
            cn = DBUtils.DBUtils.makeConnection();
            String sql = "use JavaWebDB2 \n";
            sql += "SELECT [fullname], [phone], [address] FROM [Members]\n"
                    + "INNER JOIN [Users]\n"
                    + "ON [Users].username = ? AND\n"
                    + "	[Users].id = [Members].userid";

            if (cn != null) {
                pst = cn.prepareStatement(sql);
                pst.setString(1, username);
                rs = pst.executeQuery();
                rs.next();
                usrid = rs.getInt("id");
                rs.close();
            }

        } finally {
            if (cn != null) {
                cn.close();
            }
        }
        return usrid;
    }

}
