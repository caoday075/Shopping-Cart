<%@page import="java.util.List"%>
<%@page import="DTO.CartConfirmDTO"%>
<%@page import="DAO.CartConfirmDAO"%>
<%@page import="DTO.CustomerDTO"%>
<%@page import="DAO.CustomerDAO"%>
<%@page import="DTO.OrderDTO"%>
<%@page import="DAO.OrderDAO"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="co" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comfirmation</title>
        <link rel="stylesheet" type="text/css" href="css/orderConfirm.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

    </head>

    <body>

        <jsp:useBean id="orDAO" class="DAO.OrderDAO"></jsp:useBean>
        <jsp:useBean id="cusDAO" class="DAO.CustomerDAO"></jsp:useBean>
        <jsp:useBean id="cartDAO" class="DAO.CartConfirmDAO"></jsp:useBean>
        <co:set var="orDTO" value="${orDAO.getOrderByID(param.txtOrderID)}"/>
        <co:set var="cusID" value="${orDTO.getCusID()}"/>
        <co:set var="cusDTO" value="${cusDAO.getCusByID(cusID)}"/>
        <co:set var="timeStamp" value="${orDTO.getOrderDate()}   -   ${orDTO.getTime()}"/>
        <co:set var="listCart" value="${cartDAO.getCart(param.txtOrderID)}"/>

        <co:forEach var="list" items="${listCart}">
            <co:set var="total" value="${list.getTotal()}" />
        </co:forEach>

        <div class="container-fluid">
            <div class="orderDetails">
                <div class="navTop">
                    <h4>Order Details</h4>                  
                </div>

                <div class="row">
                    <div class="col-md-7">
                        <div class="row orderDetailsContent">
                            <div class="col-sm-6">
                                <h6><b>Order#</b></h6>
                                <h6><b>${param.txtOrderID}</b></h6>
                            </div>
                            <div class="col-sm-6">
                                <h6><b>TimeStamp: </b></h6>
                                <h6><b>${timeStamp}</b></h6>
                            </div>
                        </div>

                        <div class="space1"></div>

                        <div class="row orderDetailsContent">
                            <div class="col-sm-6">
                                <h6><b>Delivery to:</b></h6>
                                <p>${cusDTO.getAddress()}</p>
                            </div>
                            <div class="col-sm-6">
                                <h6><b>Contact: </b></h6>
                                <p>Phone: ${cusDTO.getPhone()}</p>
                                <p>Email: ${cusDTO.getEmail()}</p>
                            </div>
                        </div>      
                    </div>                    

                    <div class="col-md-5 wrapper">
                        <div class="row cartWrapper">
                            <div class="col-sm-1"></div>
                            <table class="cartTotal col-sm-7">
                                <tr>
                                    <td><h5>Price: </h5></td>
                                    <td>${total}</td>
                                </tr>
                                <tr>
                                    <td><h5>Sale: </h5></td>
                                    <td>0</td>
                                </tr>
                                <tr>
                                    <td><h5>Tax: </h5></td>
                                    <td>0</td>
                                </tr>   
                                <tr>
                                    <td><h5>Ship: </h5></td>
                                    <td>0</td>
                                </tr>                           
                            </table> 
                            <div class="col-sm-4"></div>

                            <div class="col-sm-3"></div>
                            <div class="col-sm-6 line" style="margin: 8px 0"></div>
                            <div class="col-sm-3"></div>

                            <div class="col-sm-1"></div>
                            <table class="cartTotal col-sm-7">
                                <tr>
                                    <td><h5>Total Due: </h5></td>
                                    <td>${total}</td>
                                </tr>                                                 
                            </table> 
                            <div class="col-sm-4"></div>                        
                        </div> 
                    </div>
                </div> 
            </div>




            <div class="confirmation">
                <div class="navTop">
                    <h4>Order Lists</h4>
                </div>

                <div class="row" style="padding: 10px">
                    <div class="col-md-2">
                        <div>
                            <h6>Order#</h6>
                            <p>${param.txtOrderID}</p>
                        </div>
                        <div class="space2" style="height: 15px;" ></div>
                        <div>
                            <h6>Address:</h6>
                            <p>${cusDTO.getAddress()}</p>
                        </div>
                        <div class="space2" style="height: 15px;" ></div>
                        <div>
                            <h6>Shipping Option: </h6>
                            <p>Cast</p>
                        </div>
                    </div> 

                    <div class="col-md-8" style="padding-left: 60px">
                        <table class="tableConfirm">
                            <thead>
                                <tr>
                                    <th> </th>                                    
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>

                            <tbody>
                                <co:forEach var="list" items="${listCart}">
                                    <co:set var="total" value="${list.getTotal()}" />
                                
                                <tr style="height: 80px">
                                    <td style="padding: 0 !important"><img width="25%" src="images/${list.getImage()}"></td>
                                    <td>${list.getProName()}</td>     
                                    <td>${list.getPrice()}</td>   
                                    <td>${list.getQuantity()}</td>                            
                                </tr>                                
                                </co:forEach>
                            </tbody>
                        </table>
                    </div>

                    <div class="col-md-2">
                        <button style="width: 150px; padding: 10px; margin-bottom: 20px">Print Bills</button>
                        <form action="MainServlet" method="post">
                            <input type="hidden" value="${param.txtOrderID}" name="txtOrderIDConfirm">
                            <input style="padding: 10px; width: 150px" type="submit" name="action" value="Confirm">
                        </form>

                    </div>
                </div>
            </div> 
        </div>
    </body>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</html>
