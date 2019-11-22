/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author SE130531
 */
public class ChangePassServlet extends HttpServlet {

    private final String PROFILE = "profile.jsp?usrUpdate=";

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
            String url = PROFILE;
            UserDAO uDAO = new UserDAO();
            HttpSession session = request.getSession(true);
            session.removeAttribute("PASSWORDSTATUS");
            session.removeAttribute("PASSWORDSUCCESS");

            String userID = request.getParameter("txtId");
            String oldPass = request.getParameter("txtOldPass");
            String newPass = request.getParameter("txtNewPass");
            String conPass = request.getParameter("txtConPass");

            try {
                if (!oldPass.equalsIgnoreCase(uDAO.getPasswordbyID(userID))) {
                    session.setAttribute("PASSWORDSTATUS", false);
                    url += userID;
                    response.sendRedirect(url);
                    return;
                } else {
                    session.setAttribute("PASSWORDSTATUS", true);
                    if (uDAO.setNewPass(newPass, userID)) {
                        session.setAttribute("PASSWORDSUCCESS", true);
                    } else {
                        session.setAttribute("PASSWORDSUCCESS", false);
                    }
                }
            } catch (NamingException ex) {
                Logger.getLogger(ChangePassServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ChangePassServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            url += userID;
            response.sendRedirect(url);

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
