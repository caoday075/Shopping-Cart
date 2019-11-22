package sendMail;

import DAO.CartConfirmDAO;
import DAO.CustomerDAO;
import DAO.OrderDAO;
import DTO.CartConfirmDTO;
import DTO.CustomerDTO;
import DTO.OrderDTO;
import DTO.ProductDTO;
import java.sql.SQLException;
import java.util.List;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;

public class SendMail {

    public static String getTemplate(OrderDTO oDTO, CustomerDTO cusDTO) {

        String template = null;

        try {
            OrderDAO orDAO = new OrderDAO();
            CustomerDAO cusDAO = new CustomerDAO();
            String timeStamp = oDTO.getOrderDate() + "   -   " + oDTO.getTime();
            CartConfirmDAO cartDAO = new CartConfirmDAO();
            List<CartConfirmDTO> listCart = cartDAO.getCart(oDTO.getOrderID());
            double total = 0;
            for (CartConfirmDTO cart : listCart) {
                total += cart.getTotal();
            }
            template = "<body style=\"font-family: arial;\">"
                    + "<h2 style=\"margin: 0; font-weight: 200\">Thank you customer <span style=\"font-weight: 200\">" + cusDTO.getCusName() + "</span></h2>\n"
                    + "\n"
                    + "        <h2 style=\"margin: 0; font-weight: 200\">Information of order <span style=\"font-weight: 600\"> #" + oDTO.getOrderID() + " </span> <span>" + timeStamp + "</span></h2>    \n"
                    + "\n"
                    + "        <hr>\n"
                    + "        <ul style=\"list-style-type: none; padding: 0\">\n"
                    + "            <li style=\"float: left; width: 50%;\">\n"
                    + "                <div>\n"
                    + "                    <b style=\"margin: 0\">Customer Information</b>\n"
                    + "                    <p style=\"margin: 0\">" + oDTO.getOrderID() + "</p>\n"
                    + "                    <p style=\"margin: 0\">" + cusDTO.getPhone() + "</p>\n"
                    + "                    <p style=\"margin: 0\">" + cusDTO.getAddress() + "</p>\n"
                    + "                </div>\n"
                    + "            </li>\n"
                    + "            <li style=\"float: left; width: 50%;\">\n"
                    + "                <div>\n"
                    + "                    <b style=\"margin: 0\">Delivery address</b>\n"
                    + "                    <p style=\"margin: 0\">" + cusDTO.getCusName() + "</p>\n"
                    + "                    <p style=\"margin: 0\">" + cusDTO.getPhone() + "</p>\n"
                    + "                    <p style=\"margin: 0\">" + cusDTO.getEmail() + "</p>\n"
                    + "                </div>\n"
                    + "            </li>\n"
                    + "        </ul>\n"
                    + "            \n"
                    + "        <p style=\"margin: 0;\"><b>Purchase method: </b> Ship Code</p>\n"
                    + "        <p style=\"margin: 0\"><b>Fee: </b> 0</p>\n"
                    + "\n"
                    + "        <h2 style=\"margin:0 20px 0 0\">Details Order</h2>\n"
                    + "        <hr>\n"
                    + "        <table class=\"table\" style=\"border-collapse: collapse; width: 100%; font-family: 'Open Sans','arial'; font-weight: 400; font-size: 16px;\">\n"
                    + "            <thead style=\"color: white; background-color: dodgerblue;\">\n"
                    + "            <th style=\"text-align: center !important; padding: 8px;\">Product</th>\n"
                    + "            <th style=\"text-align: center !important; padding: 8px;\">Price</th>\n"
                    + "            <th style=\"text-align: center !important; padding: 8px;\">Quantity</th>   				\n"
                    + "            <th style=\"text-align: center !important; padding: 8px;\">Temp Total</th>\n"
                    + "        </thead> 		\n"
                    + "        <tbody>\n";
                    
                    for (CartConfirmDTO pro : listCart) {
            
                 
                    template += "            <tr style=\"height: 40px; background-color: white;\">\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\">" + pro.getProName() + "</td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\">" + pro.getPrice() + "</td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\">" + pro.getQuantity() + "</td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\">" + pro.getTotal()+ "</td>\n"
                    + "            </tr>      \n";
                            }                
                    template += "            <tr>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\"><b>Temp Total:</b></td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\">0</td>\n"
                    + "            </tr>\n"
                    + "            <tr>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\"><b>Ship:</b></td>\n"
                    + "                <td style=\"text-align: center !important; padding: 8px;\">0</td>\n"
                    + "            </tr>\n"
                    + "\n"
                    + "        </tbody>\n"
                    + "    </table>\n"
                    + "\n"
                    + "    <div style=\"text-align: right; background-color: grey\">\n"
                    + "        <div>\n"
                    + "            <h2 style=\"margin: 0 155px 0 0\"><b>Total: </b> $" + total + "</h2>\n"
                    + "        </div>\n"
                    + "    </div>"
                    + "</body>";
                    
//                    + "            <%\n"
//                    + "                for (CartConfirmDTO pro : listCart) {\n"
//                    + "            %>\n"
//                    + "\n"
//                    + "            <tr style=\"height: 40px; background-color: white;\">\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"><%= pro.getProName() %></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"><%= pro.getPrice()%></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"><%= pro.getQuantity()%></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"><%= pro.getTotal() %></td>\n"
//                    + "            </tr>      \n"
//                    + "\n"
//                    + "            <%                }\n"
//                    + "            %>\n"
//                    + "\n"
//                    + "            <tr>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"><b>Temp Total:</b></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\">0</td>\n"
//                    + "            </tr>\n"
//                    + "            <tr>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\"><b>Ship:</b></td>\n"
//                    + "                <td style=\"text-align: center !important; padding: 8px;\">0</td>\n"
//                    + "            </tr>\n"
//                    + "\n"
//                    + "        </tbody>\n"
//                    + "    </table>\n"
//                    + "\n"
//                    + "    <div style=\"text-align: right; background-color: grey\">\n"
//                    + "        <div>\n"
//                    + "            <h2 style=\"margin: 0 155px 0 0\"><b>Total: </b> $<%= total %></h2>\n"
//                    + "        </div>\n"
//                    + "    </div>"
//                    + "</body>";
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }

        return template;
    }

    public static void main(String[] args, String orderID) {

        final String username = "daycdse130531@fpt.edu.vn";
        final String password = "01242515139lvna";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            OrderDAO oDAO = new OrderDAO();
            CustomerDAO cusDAO = new CustomerDAO();
            OrderDTO oDTO = null;
            CustomerDTO cusDTO = null;
            try {
                oDTO = oDAO.getOrderByID(orderID);
                cusDTO = cusDAO.getCusByID(oDTO.getCusID());
            } catch (NamingException ex) {
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
            }

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("???@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(cusDTO.getEmail())
            );
            message.setSubject("Confirm Your Order");

            message.setContent(getTemplate(oDTO, cusDTO), "text/html");
            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
