<%-- 
    Document   : viewCart
    Created on : 31-May-2019, 08:26:36
    Author     : SE130531
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            HashMap<String, Integer> cart = (HashMap) session.getAttribute("CART");
            if (cart != null) {

                Set<String> proids = cart.keySet();
                Iterator<String> it = proids.iterator();
                while (it.hasNext()) {
                    String  id = it.next();
        %>

    <tr>
        <td> <%= id%> </td>
        <td> <%= cart.get(id)%> </td>

    </tr>

    <%                }
        }
    %>
</body>
</html>
