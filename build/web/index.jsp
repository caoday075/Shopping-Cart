<%@page import="DTO.CustomerDTO"%>
<%@page import="DTO.OrderDTO"%>
<%@page import="DAO.CustomerDAO"%>
<%@page import="DAO.OrderDAO"%>
<%@page import="DAO.CartConfirmDAO"%>
<%@page import="DTO.CartConfirmDTO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="DAO.ProductDAO"%>
<%@page import="DTO.ProductDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="javax.mail.*"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="java.util.Properties"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="in" %>
<!DOCTYPE html>

<html>    
    <head>
        <meta charset="UTF-8">
        <title>Shopping Cart</title>        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link href="https://fonts.googleapis.com/css?family=Pacifico&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">       

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->

        <!-- ------------------------------------Cart---------------------------------- -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn" crossorigin="anonymous"></script>



        <link rel="stylesheet" type="text/css" href="css/header.css">        
        <link rel="stylesheet" type="text/css" href="css/home.css">        
        <link rel="stylesheet" type="text/css" href="css/profile.css">        


    </head>
    <body>
        <div class="header container-fluid">
            <div class="row">
                <div class="col-md-2 titleWrapper">
                    <h3 class="align-content-center title">
                        Shopping
                        </h1>
                </div>
                <div class="col-md-4"></div>
                <div class="link col-md-2">
                    <ul class="d-flex ulHeader">
                        <li><a href="#">Home</a></li>
                        <li><a href="#">News</a></li>
                    </ul>
                </div>
                <div class="search col-md-3">
                    <input type="text" name="" placeholder="Search">
                </div>
                <div class="btnLogin col-md-1">
                    <a href="login.jsp">
                        <i class="fa fa-share-square"></i>
                        Login
                    </a>
                </div>

            </div>
        </div>


       	<div class="row mainBody">
            <div class="col-sm-2 slide-bar" style="text-align: center">               
                <ul class="nav nav-pills nav-stacked" id="myTab" role="tablist">                    
                    <li class="nav-item active">
                        <a class="nav-link" id="profile-tab" data-toggle="pill" href="#cakes" role="tab" aria-controls="profile" aria-selected="false"><i class=""></i> Cakes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="privacy-tab" data-toggle="pill" href="#privacy" role="tab" aria-controls="privacy" aria-selected="false"><i class=""></i> Fruits </a>
                    </li>
                </ul>
            </div>
            <div class="col-sm-10 content">               
                <div class="tab-content">                 
                    <div class="tab-pane active container" id="cakes" role="tabpanel" aria-labelledby="profile-tab">
                        <div class="row">
                       
                          
                            <jsp:useBean id="productList" class="DAO.ProductDAO"></jsp:useBean>        
                            <in:forEach var="list" items="${productList.activeProducts}">                
                                
                                


                            <div class="col-md-3">
                                <div class="card" style="width: 15rem;">
                                    <img class="card-img-top" src="images/${list.imagePath}" alt="Card image cap"> 
                                    <div class="card-block">
                                        <p><input type="hidden" name="txtProID" value="${list.id}"></p>
                                        <h4 class="card-title">${list.name} </h4>
                                        <p class="card-text">Price: ${list.price}</p>                              

                                        <a href="#" data-id="${list.id}" data-name="${list.name}" data-price="${list.price}" class="add-to-cart btn btn-primary">
                                           <p>Add to cart</p>                                            
                                        </a>

                                    </div>
                                </div>
                            </div>
                        </in:forEach>
                           

                        </div>
                    </div>                    
                </div>
            </div>          
        </div>

        <nav class="cart cartList navbar-inverse bg-inverse fixed-top bg-faded">
            <div class="row">
                <div class="col">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#cart">Cart (<span class="total-count"></span>)</button><button class="clear-cart btn btn-danger">Clear Cart</button></div>
            </div>
        </nav>

        <div class="modal fade" id="cart" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <form action="MainServlet" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Cart</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden= "true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <table class="show-cart table">
                                <!-- JAVASCRIPT -->

                            </table>
                            <div style="font-weight: bold">Total price: $<span id="total-cart" class="total-cart"></span></div>                            

                        </div>

                        <div class="checkoutForm">
                            <div class="inforFormWrapper">
                                <div class="row">
                                    <p class="col-md-1 inforTitle" style="margin-right: 40px;">Name: </p>
                                    <input class="col-md-3" type="text" name="txtCusName" minlength="4" maxlength="32" pattern="^[0-9a-zA-Z \-'_]+$" 
                                    spellcheck="false" title="Invalid Full Name" required>
                                    <p class="col-md-1 inforTitle" style="margin-right: 40px;">Address: </p>
                                    <input class="col-md-4" type="text" name="txtCusAddress" required minlength="10" minlength="6">                 

                                    <div class="col-md-12" style="height: 15px;"> </div>

                                    <p class="col-md-1 inforTitle" style="margin-right: 40px;">Phone: </p>                                    
                                    <input class="col-md-3" type="text" name="txtCusSDT" pattern="(0[3|5|7|8|9])+([0-9]{8})" spellcheck="false" titile="Invalid Phone" required>
                                    <p class="col-md-1 inforTitle" style="margin-right: 40px;">Email: </p>
                                    <input class="col-md-4" type="email" name="txtEmail" required>
                                    <div class="col-md-2"></div>
                                    <h4 class="errorMsg" style="margin-top: 10px"></h4>
                                </div>
                            </div>
                            <!-- --------CORE------- -->

                            <div class="modal-footer" style="margin-top: 60px;">
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>

                                <button id="btnBuy" type="submit" name="action" value="Buy" class="btn btn-primary" > 
                                    Buy Now
                  


                                </button>
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>


    <script  src="js/home.js"></script>
    <script src="js/validate.js"></script>

    <script>
        function checkBuy() {
            if (document.getElementById("total-cart").value === "0") {
                document.getElementById("btnBuy").disabled = true;
            } else
                document.getElementById("btnBuy").disabled = false;
        }

    </script>


</html>
