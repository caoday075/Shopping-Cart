<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
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
    <body>
        <%
            String cusName = (String) session.getAttribute("ODERCUSNAME");
            String cusAdd = (String) session.getAttribute("ODERCUSADD");
            if (cusName != null || cusAdd != null) {

                HashMap<String, Integer> cart = (HashMap) session.getAttribute("CART");

                if (cart != null) {

                    Set<String> proids = cart.keySet();
                    Iterator<String> it = proids.iterator();
                    while (it.hasNext()) {
                        String id = it.next();

        %>  
        <p> <%= id%></p> 
        <p> <%= cart.get(id)%></p>  

        <%
                }
            }
        %>
        <p><%= cusName%></p>  
        <p><%= cusAdd%></p> 
        <%
            }
        %>




    </body>
</html>
