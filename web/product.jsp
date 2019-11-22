<%-- 
    Document   : product
    Created on : 31-May-2019, 07:39:10
    Author     : SE130531
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="DTO.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/product.css">

    </head>
    <body>
        <%
            List<ProductDTO> list = (ArrayList) session.getAttribute("LISTPRODUCT");
            if (list != null) {
                for (ProductDTO pro : list) {


        %>

        <div>
            <form action="buyServlet" method="post">
                <p><%= pro.getId()%>
                    <input type="hidden" value="<%= pro.getId()%>" name="txtProID">
                </p>
                <p><%= pro.getName()%></p>
                <p><%= pro.getPrice()%></p>
                <p><input type="submit" value="buy" name="action"></p>
            </form>
        </div>


        <%                }
            }
        %>
        <a href="viewCart.jsp"> view Cart </a>

    </body>
</html>
