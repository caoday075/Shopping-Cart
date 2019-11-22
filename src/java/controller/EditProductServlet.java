/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.ProductDAO;
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
public class EditProductServlet extends HttpServlet {

    private final String ADMIN = "admin.jsp";
    private final String ERROR = "error.html";

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
            HttpSession session = request.getSession(true);
            String url = "";
            ProductDAO proDAO = new ProductDAO();
            String proID = request.getParameter("txtEditID");
            String proName = request.getParameter("txtEditName");
            double price = Double.parseDouble(request.getParameter("txtEditPrice"));
            String image = request.getParameter("txtImage");

            if (image == "") {
                try {
                    image = proDAO.getProductByID(proID).getImagePath();
                } catch (NamingException ex) {
                    Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            //XU LI ANH-------------------------------
//            String image=null;
//            if(request.getParameter("txtImage") != null)
//                image = request.getParameter("txtImage");
//            else
//                try {
//                    image = proDAO.getProductByID(proID).getImagePath();
//            } catch (NamingException | SQLException ex) {
//                Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
            //------------------------------------------
            int status = Integer.parseInt(request.getParameter("txtIsStatus"));

            try {
                if (proDAO.updateProduct(proID, proName, price, image, status)) {
                    url = ADMIN + "?txtProductID=" + proID;
                } else {
                    url = ERROR;
                }
                response.sendRedirect(url);
            } catch (SQLException ex) {
                Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NamingException ex) {
                Logger.getLogger(EditProductServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

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
