<%-- 
    Document   : search
    Created on : 18-May-2019, 23:46:52
    Author     : SE130531
--%>

<%@page import="DAO.UserDAO"%>
<%@page import="DTO.UserDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2> Welcome <%= session.getAttribute("USERNAME")%></h2>

        <%
            String value = "";
            String keyword = request.getParameter("txtSearch");
            if (keyword != null) {
                value = keyword;
            }
        %>


        <form action="MainServlet" method="get">
            <p> <input type="text" name="txtSearch" value="<%= value%>"> 
                <input type="submit" value="Search" name="action"> 
        </form>

        <table border="1" cellspacing="0">
            <thead>
                <tr>
                    <th>oder</th>
                    <th>username</th>
                    <th>password</th>
                    <th>full name</th>
                </tr>  
            </thead>

            <tbody>
                <%
                    List<UserDTO> list = (ArrayList) session.getAttribute("LIST");
//List<UserDTO> list = (ArrayList) new UserDAO().getAllUser();

                    if (list != null) {
                        int count = 1;
                        for (UserDTO item : list) {
                            String deleteURL = "MainServlet?usrDel=" + item.getUsername() + "&action=Delete&txtSearch=" + value;
                            String editURL = "profile.jsp?usrUpdate=" + item.getUsername();
                %>        
                <tr>
                    <td><%= count%></td>
                    <td><%= item.getUsername()%></td>
                    <td><%= item.getPassword()%></td>
                    <td><%= item.getFullname()%></td>
                    <td><a href=<%= deleteURL%> > delete </a></td>
                    <td><a href=<%= editURL%>>update</a></td>
                </tr>

                <%
                            count++;
                        }
                    }
                %>
            </tbody>

        </table>

    </body>
</html>
