/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.CustomerDAO;
import DAO.OrderDAO;
import DAO.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
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
public class BuyServlet extends HttpServlet {

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
            CustomerDAO cusDAO = new CustomerDAO();
            OrderDAO oDAO = new OrderDAO();
            OrderDetailDAO ordetailDAO = new OrderDetailDAO();
            int countOrderDetail;
            String tmp = "";
            
            String[] proIDArr = request.getParameterValues("txtProID");
            String[] proQuanTMP = request.getParameterValues("txtProQuantity");
            String cusName = request.getParameter("txtCusName");
            String cusAddress = request.getParameter("txtCusAddress");
            String cusSDT = request.getParameter("txtCusSDT");
            String email = request.getParameter("txtEmail");

            //-------------------KEY
            int[] proQuanArr = new int[proQuanTMP.length];
            for (int i = 0; i < proQuanTMP.length; i++) {
                proQuanArr[i] = Integer.parseInt(proQuanTMP[i]);
            }

            if (proIDArr != null || proQuanArr != null) {
                HashMap<String, Integer> cart = new HashMap<>();

                for (int i = 0; i < proIDArr.length; i++) {
                    cart.put(proIDArr[i], proQuanArr[i]);
                }

                try {
                    if (cusDAO.checkDuplicate(cusSDT) == false) {
                        if (cusDAO.addCustomer(cusName, cusSDT, cusAddress, email)) {
                            if (oDAO.checkDuplicate() == false) {
                                if (oDAO.addOrder(cusSDT)) {
                                    session.setAttribute("ORDERID", oDAO.getOrderID());
                                    tmp = oDAO.getOrderID();
                                    session.setAttribute("TXTEMAIL", email);
                                    Set<String> proids = cart.keySet();
                                    Iterator<String> it = proids.iterator();
                                    while (it.hasNext()) {
                                        String proid = it.next();
                                        countOrderDetail = (ordetailDAO.getCountOrderDetail() + 1);
                                        if (ordetailDAO.addOderDetail(countOrderDetail, oDAO.getOrderID(), proid, cart.get(proid))) {
                                        }
                                    }
                                }
                            }
                        }
                    } else {

                        if (oDAO.addOrder(cusSDT)) {
                            session.setAttribute("ORDERID", oDAO.getOrderID());
                            tmp = oDAO.getOrderID();
                            session.setAttribute("TXTEMAIL", email);
                            Set<String> proids = cart.keySet();
                            Iterator<String> it = proids.iterator();
                            while (it.hasNext()) {
                                String proid = it.next();

                                countOrderDetail = (ordetailDAO.getCountOrderDetail() + 1);
                                if (ordetailDAO.addOderDetail(countOrderDetail, oDAO.getOrderID(), proid, cart.get(proid))) {
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(BuyServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NamingException ex) {
                    Logger.getLogger(BuyServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

                session.setAttribute("CART", cart);
                session.setAttribute("ODERCUSNAME", cusName);
                session.setAttribute("ODERCUSSDT", cusSDT);
                session.setAttribute("ODERCUSADD", cusAddress);
                
                main(new String[]{}, tmp);
                response.sendRedirect("index.jsp");
            }

//            if(proid!=null) {
//                HttpSession session = request.getSession(true);
//                HashMap<String, Integer> cart = (HashMap) session.getAttribute("CART");
//                if(cart == null) {
//                    cart = new HashMap<>();
//                }
//                int quantity = 1;
//                if(cart.containsKey(proid)) {
//                    quantity = cart.get(proid);
//                    quantity++; 
//                }
//                cart.put(proid, quantity);
//                                
//                //update vao session
//                session.setAttribute("CART", cart);
//                response.sendRedirect("product.jsp");
//                
//            }
       
        }      
    }
    
    public static void main(String[] args, String tmp) {
        sendMail.SendMail.main(args, tmp);
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
