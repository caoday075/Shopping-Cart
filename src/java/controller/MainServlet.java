/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author SE130531
 */
public class MainServlet extends HttpServlet {

    private final String LOGIN = "LoginServlet";
    private final String SEARCH = "SearchServlet";
    private final String LOGINPAGE = "login.jsp";
    private final String DELETE = "DeleteServlet";
    private final String REGISTER = "RegisterServlet";
    private final String UPDATEPROFILE = "UpdateServlet";
    private final String UPDATEPROFILE2 = "UpdateServlet2";
    private final String BUY = "BuyServlet";
    private final String INDEX = "ProductServlet";
    private final String SIGNOUT = "SignOutServlet";
    private final String CONFIRMCART = "ConfirmOrder";
    private final String EDITPRODUCT = "EditProductServlet";
    private final String ADDPRODUCT = "AddProductServlet";

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

            String action = request.getParameter("action");
            String url = INDEX;
            if (action == null || action.equals("")) {
                url = LOGINPAGE;
            } else if (action.equals("Login")) {
                url = LOGIN;
            } else if (action.equals("Search")) {
                url = SEARCH;
            } else if (action.equals("Delete")) {
                url = DELETE;
            } else if (action.equals("Register")) {
                url = REGISTER;
            } else if (action.equals("UpdateProfile")) {
                url = UPDATEPROFILE;
            } else if (action.equals("Buy")) {
                url = BUY;
            } else if (action.equals("Sign Out")) {
                url = SIGNOUT;
            } else if (action.equals("UpdateProfile2")) {
                url = UPDATEPROFILE2;
            } else if (action.equals("Confirm")) {
                url = CONFIRMCART;
            } else if (action.equals("Edit Product")) {
                url = EDITPRODUCT;
            } else if (action.equals("Add Product")) {
                url = ADDPRODUCT;
            }

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
