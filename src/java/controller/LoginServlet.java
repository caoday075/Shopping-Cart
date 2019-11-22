/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE130531
 */
public class LoginServlet extends HttpServlet {

    private final String FAIL = "login.jsp";
    private final String ADMIN = "admin.jsp";
    private final String STAFF = "staff.jsp";
    private final String MEMBER = "member.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String username = request.getParameter("txtusername");
            String password = request.getParameter("txtpassword");
            String re = request.getParameter("txtremember");

            String url = FAIL;

            UserDAO uDAO = new UserDAO();
            HttpSession session = request.getSession(true);
            Cookie[] arr = request.getCookies();

            if (username == null || password == null) {//cookie đã tạo
                url = FAIL;
            } else { // chưa có cookie
                try {                    
                    if (!username.equalsIgnoreCase("admin") && !uDAO.checkStatus(username)) {    //fail
                        session.setAttribute("LOGIN_STATUS", true);
                    } else {
                        int role = uDAO.checkLogin(username, password);
                        if (role != -1) {
                            session.setAttribute("USERNAME", username);
                            session.setAttribute("ROLE", role);
                            int tmp = arr.length;
                            if (arr.length == 1) { // chua co cookie
                                if (re != null && re.equals("1")) {//Tạo remember
                                    Cookie us = new Cookie("USERNAME", username);
                                    us.setMaxAge(10 * 30);
                                    Cookie pw = new Cookie("PASSWORD", password);
                                    pw.setMaxAge(10 * 30);
                                    response.addCookie(us);
                                    response.addCookie(pw);
                                }
                                switch (role) {
                                    case 0:
                                        url = ADMIN;
                                        break;
                                    case 1:
                                        url = STAFF;
                                        break;
                                    case 2:
                                        url = MEMBER;
                                        break;
                                }
                            } else { // co nhiu cookie
                                switch (role) {
                                    case 0:
                                        url = ADMIN;
                                        break;
                                    case 1:
                                        url = STAFF;
                                        break;
                                    case 2:
                                        url = MEMBER;
                                        break;
                                }
                            }
                        } else {
                            session.setAttribute("LOGIN_STATUS", true);
                        }
                    }
                    response.sendRedirect(url);
                } catch (NamingException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
//                        }

        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
