<%@page import="DTO.CartConfirmDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.CartConfirmDAO"%>
<%@page import="DTO.CustomerDTO"%>
<%@page import="DTO.OrderDTO"%>
<%@page import="DAO.CustomerDAO"%>
<%@page import="DAO.OrderDAO"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <style type="text/css">

        h2, p {
        }




        .table tr:hover {
            background-color: #f5f5f5;
        }

    </style>
    <body style="font-family: arial;">
        <%
            OrderDAO orDAO = new OrderDAO();
            CustomerDAO cusDAO = new CustomerDAO();
            String orderID = "20190620003109";
            OrderDTO orDTO = orDAO.getOrderByID(orderID);
            CustomerDTO cusDTO = cusDAO.getCusByID(orDTO.getCusID());
            String timeStamp = orDTO.getOrderDate() + "   -   " + orDTO.getTime();

            CartConfirmDAO cartDAO = new CartConfirmDAO();
            List<CartConfirmDTO> listCart = cartDAO.getCart(orderID);
            double total = 0;
            for (CartConfirmDTO cart : listCart) {
                total += cart.getTotal();
            }

        %>


        <h2 style="margin: 0; font-weight: 200">Thank you customer <span style="font-weight: 200"><%= cusDTO.getCusName()%></span></h2>

        <h2 style="margin: 0; font-weight: 200">Information of order <span style="font-weight: 600"> #<%= orderID%> </span> <span><%= timeStamp%></span></h2>    

        <hr>
        <ul style="list-style-type: none; padding: 0">
            <li style="float: left; width: 50%;">
                <div>
                    <b style="margin: 0">Customer Information</b>
                    <p style="margin: 0"><%= cusDTO.getCusName()%></p>
                    <p style="margin: 0"><%= cusDTO.getPhone()%></p>
                    <p style="margin: 0"><%= cusDTO.getEmail()%></p>
                </div>
            </li>
            <li style="float: left; width: 50%;">
                <div>
                    <b style="margin: 0">Delivery address</b>
                    <p style="margin: 0"><%= cusDTO.getCusName()%></p>
                    <p style="margin: 0"><%= cusDTO.getEmail()%></p>
                    <p style="margin: 0"><%= cusDTO.getAddress()%></p>
                </div>
            </li>
        </ul>
            
        <p style="margin: 0;"><b>Purchase method: </b> Ship Code</p>
        <p style="margin: 0"><b>Fee: </b> 0</p>

        <h2 style="margin:0 20px 0 0">Details Order</h2>
        <hr>
        <table class="table" style="border-collapse: collapse; width: 100%; font-family: 'Open Sans','arial'; font-weight: 400; font-size: 16px;">
            <thead style="color: white; background-color: dodgerblue;">
            <th style="text-align: center !important; padding: 8px;">Product</th>
            <th style="text-align: center !important; padding: 8px;">Price</th>
            <th style="text-align: center !important; padding: 8px;">Quantity</th>   				
            <th style="text-align: center !important; padding: 8px;">Temp Total</th>
        </thead> 		
        <tbody>
            <%
                for (CartConfirmDTO pro : listCart) {


            %>

            <tr style="height: 40px; background-color: white;">
                <td style="text-align: center !important; padding: 8px;"><%= pro.getProName() %></td>
                <td style="text-align: center !important; padding: 8px;"><%= pro.getPrice()%></td>
                <td style="text-align: center !important; padding: 8px;"><%= pro.getQuantity()%></td>
                <td style="text-align: center !important; padding: 8px;"><%= pro.getTotal() %></td>
            </tr>      

            <%                }
            %>

            <tr>
                <td style="text-align: center !important; padding: 8px;"></td>
                <td style="text-align: center !important; padding: 8px;"></td>
                <td style="text-align: center !important; padding: 8px;"><b>Temp Total:</b></td>
                <td style="text-align: center !important; padding: 8px;">0</td>
            </tr>
            <tr>
                <td style="text-align: center !important; padding: 8px;"></td>
                <td style="text-align: center !important; padding: 8px;"></td>
                <td style="text-align: center !important; padding: 8px;"><b>Ship:</b></td>
                <td style="text-align: center !important; padding: 8px;">0</td>
            </tr>

        </tbody>
    </table>

    <div style="text-align: right; background-color: grey">
        <div>
            <h2 style="margin: 0 155px 0 0"><b>Total: </b> $<%= total %></h2>
        </div>
    </div>

</body>
</html>
