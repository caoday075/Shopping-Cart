/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductDAO;
import DTO.ProductDTO;
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
public class AddProductServlet extends HttpServlet {

    public final String ADMIN = "admin.jsp";
    public final String ERROR = "error.html";

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
            ProductDAO dao = new ProductDAO();
            ProductDTO dto = null;
            HttpSession session = request.getSession(true);
            String url = ERROR;

            String name = request.getParameter("txtAddProName");
            double price = Double.parseDouble(request.getParameter("txtAddProPrice"));
            String image = request.getParameter("txtAddProImage");
            String tmp = request.getParameter("txtAddProStatus");
            boolean status = false;   
            if(tmp == null)
                status = false;
            else {
                status = true;
            } 
            
            try {
                int idNum = (dao.countProduct() + 1);
                String id = "prod" + idNum;
                dto = new ProductDTO(id, name, price, image, status);
                if (dao.addProduct(dto)) {
                    url = ADMIN;
                    session.setAttribute("ADDPRODUCTSTATUS", true);
                } else {
                    url = ERROR;
                }
            } catch (NamingException ex) {
                Logger.getLogger(AddProductServlet2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AddProductServlet2.class.getName()).log(Level.SEVERE, null, ex);
            }
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
