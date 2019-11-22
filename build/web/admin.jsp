
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="ad" %>
<!DOCTYPE html>
<html lang="en">
    <head>	
        <title>Admin</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/admin.css">	
        <link rel="stylesheet" type="text/css" href="css/logo.css">
        <!-- -----------ICON---------- -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- -------------FONTS------------ -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Pacifico">	
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Nova+Mono">
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">	
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>

        <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
        <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">

        <!-- ----------RESPONSIVE---------- -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <link rel='stylesheet' href='https://koalyptus.github.io/TableFilter/tablefilter/style/tablefilter.css'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>        
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>  
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>     
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>   
        <script src='https://unpkg.com/tablefilter@latest/dist/tablefilter/tablefilter.js'></script>
    </head>

    <body>	
        <div class="wrapper_da container-fuild">
            <div class="">
                <div class="wrap_menu_da col-md-2">		
                    <div class="menu_logo_da">
                        <img id="logo_da" src="images/logo.png" alt="logo"> 
                        <h2 id="title_da">Bakery</h2>			
                    </div>

                    <div class="line_da"></div>
                    <div class="sidebar" style="text-align: center">                
                        <ul class="nav nav-tabss nav-stacked" id="myTab" role="tablist">                    
                            <li class="nav-item active">
                                <a class="nav-link active" id="profile-tab" data-toggle="tab" href="#dashboard" role="tab" aria-controls="profile" aria-selected="true"><i class=""></i> Dashboard</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="product-tab" data-toggle="tab" href="#product" role="tab" aria-controls="product" aria-selected="false"><i class="fas fa-user-lock"></i> Product</a>
                            </li>
                            <li class="nav-item" style="display: none">
                                <a class="nav-link" id="editProduct-tab" data-toggle="tab" href="#editProduct" role="tab" aria-controls="editProduct" aria-selected="false" class="view_action_daProduct">Edit</a>                                
                            </li>

                            <li class="nav-item">
                                <a class="nav-link" id="order-tab" data-toggle="tab" href="#order" role="tab" aria-controls="order" aria-selected="false"><i class="fas fa-user-lock"></i> Order</a>
                            </li>                        
                            <li class="nav-item">
                                <a class="nav-link" id="member-tab" data-toggle="tab" href="#member" role="tab" aria-controls="member" aria-selected="false"><i class="fas fa-user-lock"></i> Members</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="addProduct-tab" data-toggle="tab" href="#addProduct" role="tab" aria-controls="addProduct" aria-selected="false"><i class="fas fa-user-lock"></i> Add Product</a>
                            </li>
                        </ul>
                    </div>
                </div>

                <div class="main_da col-md-10" style="width: 83% !important; height: 100vh">                
                    <div class="header_da">
                        <ad:out value="${sessionScope.USERNAME}" />
                        <div class="inner_right_da">
                            <form action="MainServlet" method="post">
                                <ul>						
                                    <li class="dropdown_da">
                                        <a href="javascript:void(0)">
                                            <i id="showDropdown_da" class="fa fa-cog"></i>				
                                        </a>	
                                        <ul class="wrap_dropdown_da" id="wrap_drop_da">							
                                            <li>
                                                <a href="index.jsp" target="_blank">
                                                    <i class="fa fa-home"></i>
                                                    <span>Home Page</span>
                                                </a>
                                            </li>	
                                            <li>
                                                <a href="">
                                                    <i class="fa fa-sign-out"></i>
                                                    <input id="btnLogOut" type="submit" name="action" value="Sign Out">
                                                </a>
                                            </li>									
                                        </ul>						
                                    </li>	
                                </ul>
                            </form>
                        </div>
                    </div>

                    <div>             
                        <div class="tab-content">
                            <div class="tab-pane active" id="dashboard" role="tabpanel" aria-labelledby="profile-tab"> 
                                <div class="content_da">
                                    <div id="dashboard_da" class="dashboard_da">
                                        <!-- <div class="counter">
                                            <div><h1 class="tmptmp">fawaz</h1></div>
                                        </div> -->
                                        <!--  <div class="under_header_da">
                                             <h3 style="padding: 0">DASHBOARD</h3>
                                         </div> -->                                   
                                        <!-- <div class="container">
                                            <div class="overview_da row">
                                                <h4>Overview: </h4>
                                                <ul style="padding: 0">
                                                    <li class="first_overview_da col-md-3">									
                                                        <a href="javascript:void(0)">
                                                            <div>
                                                                <span class="glyphicon glyphicon-refresh"></span>
                                                            </div>						
                                                        </a>
                                                        <div style="display: inline-block; margin-top: 10px">
                                                            <span class="glyphicon glyphicon-globe"></span>					
                                                            <p>9999</p>
                                                        </div>
                                                        <h5>TOTAL VISITORS</h5>											
                                                    </li>	

                                                    <li class="second_overview_da col-md-3">
                                                        <a href="javascript:void(0)">
                                                            <div>
                                                                <span class="glyphicon glyphicon-refresh"></span>
                                                            </div>						
                                                        </a>
                                                        <div style="display: inline-block;">
                                                            <i class="fa fa-newspaper-o"></i>								
                                                            <p>Big AO QUAN sale 99% aaaaaaaaaaaaaaa</p>
                                                        </div>
                                                        <h5>RECENT SALES</h5>	
                                                    </li>		

                                                    <li class="third_overview_da col-md-3">
                                                        <a href="javascript:void(0)">
                                                            <div>
                                                                <span class="glyphicon glyphicon-refresh"></span>
                                                            </div>						
                                                        </a>
                                                        <div style="display: inline-block;">
                                                            <i class="fa fa-calendar"></i>								
                                                            <p>There are 6969 orders now</p>
                                                        </div>
                                                        <h5>ORDER NOW</h5>
                                                    </li>

                                                    <li class="fourth_overview_da col-md-3">
                                                        <a href="javascript:void(0)">
                                                            <div>
                                                                <span class="glyphicon glyphicon-refresh"></span>
                                                            </div>						
                                                        </a>
                                                        <div style="display: inline-block; margin-top: 10px">
                                                            <i class="fa fa-users"></i>								
                                                            <p>9999</p>
                                                        </div>
                                                        <h5>TOTAL MEMBERS</h5>
                                                    </li>
                                                </ul>
                                            </div>
                                        </div> -->	

                                        <div class="dashbeard-container">
                                            <div class="dashbeard-card card-2">
                                                <div class="card-content">
                                                    <div class="card-icon" style="background-color:#24c6da;"><i class="fa fa-cart-arrow-down"></i></div>
                                                    <h3 class="card-title">Orders<span class="card-count start-counter">5000</span></h3>
                                                </div>
                                            </div>
                                            <div class="dashbeard-card card-2">
                                                <div class="card-content">
                                                    <div class="card-icon" style="background-color:#66b965;"><i class="fa fa-line-chart"></i></div>
                                                    <h3 class="card-title">Promotion<span class="card-count start-counter">100000</span></h3>
                                                </div>
                                            </div>
                                            <div class="dashbeard-card card-2">
                                                <div class="card-content">
                                                    <div class="card-icon" style="background-color:#ec3c7a;"><i class="fa fa-envelope"></i></div>
                                                    <h3 class="card-title">Mails<span class="card-count start-counter">500</span></h3>
                                                </div>
                                            </div>
                                            <div class="dashbeard-card card-2">
                                                <div class="card-content">
                                                    <div class="card-icon" style="background-color:#ffa724;"><i class="fa fa-users"></i></div>
                                                    <h3 class="card-title">Visistors<span class="card-count start-counter">500</span></h3>
                                                </div>
                                            </div>
                                        </div>	


                                        <div id="chartContainer" style="height: 240px; max-width: 920px; margin: 0px auto;"></div>
                                    </div>
                                </div>                                     
                            </div>


                            <div class="tab-pane" id="product" role="tabpanel" aria-labelledby="product-tab">
                                <div class="col-sm-12">                                  
                                    <table id="filter_product" style="font-size: 18px;" class="table_product_da table_da">      
                                        <thead style="display: block; width: 78.65vw">
                                            <tr style="background: #792ffd !important">
                                                <th class="th_pro" style="width: 65px;">#</th>
                                                <th class="th_pro" style="width: 160px;">ID</th>
                                                <th class="th_pro" style="width: 200px;">Name</th>     
                                                <th class="th_pro" style="width: 120px">Price</th>
                                                <th class="th_pro" style="width: 313px">Image</th>
                                                <th class="th_pro" style="width: 95px;">Status</th>
                                                <th class="th_pro" style="width: 120px;">Action</th>
                                            </tr>  
                                        </thead>                                        
                                        <tbody style="height: 500px; overflow: auto; display: block; width: 80vw">
                                            <jsp:useBean id="productList" class="DAO.ProductDAO"></jsp:useBean>
                                            <ad:forEach var="listPro" items="${productList.getAllProducts()}" varStatus="count">
                                                <tr>
                                                    <td class="td_pro" style="width: 65px;">${count.count}</td>
                                                    <td class="td_pro" style="width: 160px;">${listPro.getId()}</td>                    
                                                    <td class="td_pro" style="width: 200px;">${listPro.getName()}</td>
                                                    <td class="td_pro" style="width: 120px">${listPro.getPrice()}</td>
                                                    <td class="td_pro" style="width: 313px"><img style="width: 15%" src="images/${listPro.getImagePath()}"></td> 

                                                    <td class="cell_active_table_da td_pro" style="font-size: 14px !important; width: 95px">
                                                        <ad:if test="${listPro.isStatus()}">                                              
                                                            <p class="active_table_da">ACTIVE</p>
                                                        </ad:if>                                                  
                                                        <ad:if test="${!listPro.isStatus()}">
                                                            <p class="deactive_member_da">INACTIVE</p>
                                                        </ad:if> 
                                                    </td>    
                                                    <td class="td_pro" style="width: 120px;">
                                                        <ad:url value="/admin.jsp" var="productURL">
                                                            <ad:param name="txtProductID" value="${listPro.getId()}"></ad:param>                                                            
                                                        </ad:url>                                                    
                                                        <a href="${productURL}" id="editProduct-tab1" > 
                                                            Fix
                                                            <a class="nav-link" id="editProduct-tab" data-toggle="tab" href="#editProduct" 
                                                               role="tab" aria-controls="editProduct" aria-selected="false" 
                                                               class="view_action_daProduct" style="font-size: 18px">
                                                                Edit
                                                            </a>
                                                        </a> 
                                                    </td>                                               
                                                </tr>       
                                            </ad:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                            <div class="tab-pane" id="order" role="tabpanel" aria-labelledby="order-tab">
                                <div class="col-md-6">
                                    <jsp:useBean id="orderYetList" class="DAO.OrderDAO"/>
                                    <h1>Queue Order</h1>
                                    <table id="filter-order" style="font-size: 14px; float: left" class="table_yetorder_da table_da">      
                                        <thead style="display: block; width: 38vw">
                                            <tr>
                                                <th class="th_yet" rowspan="1" style="width: 34px;">#</th>
                                                <th class="th_yet" rowspan="1" style="width: 191px;">ID</th>
                                                <th class="th_yet" rowspan="1" style="width: 163px;">Date</th>          
                                                <th class="th_yet" rowspan="1" style="width: 168px;">Time</th>
                                                <th class="th_yet" rowspan="1" style="width: 50px;">Action</th>
                                            </tr>  
                                        </thead>
                                        <tbody style="height: 380px; overflow: auto; display: block; width: 38vw">
                                            <ad:forEach var="yetList" items="${orderYetList.getYetOrder()}" varStatus="count"> 
                                                <tr>
                                                    <td style="width: 32px;">${count.count}</td>
                                                    <td style="width: 168px;">${yetList.getOrderID()}</td>                      
                                                    <td style="width: 168px;">${yetList.getOrderDate()}</td>
                                                    <td style="width: 168px;">${yetList.getTime()}</td> 
                                                    <td style="width: 57px;">
                                                        <ad:url var="confirmURL" value="orderConfirmmation.jsp">
                                                            <ad:param name="txtOrderID" value="${yetList.getOrderID()}"/>
                                                            <ad:param name="action" value="ConfirmOrder" />
                                                        </ad:url>                                                
                                                        <a style="border: 1px solid red !important;" href="${confirmURL}" class="view_action_da view_action_daTmp">
                                                            <i style = "color: red"class="fa fa-eye"></i>
                                                        </a>                    
                                                    </td>
                                                </tr>
                                            </ad:forEach> 
                                        </tbody>
                                    </table>
                                </div>

                                <div class="col-md-6">
                                    <h1>Done Order</h1>
                                    <jsp:useBean id="orderDoneList" class="DAO.OrderDAO"/>                             
                                    <table id="filter-order2" style="font-size: 14px" class="table_doneorder_da table_da">      
                                        <thead style="display: block; width: 38vw">
                                            <tr>
                                                <th class="th_done" rowspan="1" style="width: 34px;">#</th>
                                                <th class="th_done" rowspan="1" style="width: 191px;">ID</th>
                                                <th class="th_done" rowspan="1" style="width: 163px;">Date</th>          
                                                <th class="th_done" rowspan="1" style="width: 168px;">Time</th>
                                                <th class="th_done" rowspan="1" style="width: 50px;">Action</th>
                                            </tr>  
                                        </thead>
                                        <tbody style="height: 380px; overflow: auto; display: block; width: 38vw">
                                            <ad:forEach var="doneList" items="${orderDoneList.getDoneOrder()}" varStatus="count">
                                                <tr>
                                                    <td style="width: 32px;">${count.count}</td>
                                                    <td style="width: 168px;">${doneList.getOrderID()}</td>
                                                    <td style="width: 168px;">${doneList.getOrderDate()}</td>
                                                    <td style="width: 168px;">${doneList.getTime()}</td>
                                                    <td style="width: 57px;">
                                                        <ad:url var="watchConfirmURL" value="orderConfirmmation2.jsp">
                                                            <ad:param name="txtOrderID2" value="${doneList.getOrderID()}"/>                           
                                                        </ad:url> 
                                                        <a href="${watchConfirmURL}" class="view_action_da" target="_blank">
                                                            <i class="fa fa-eye"></i>
                                                        </a>
                                                    </td>
                                                </tr>
                                            </ad:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>


                            <div class="tab-pane" id="member" role="tabpanel" aria-labelledby="member-tab">
                                <div class="col-sm-4">
                                    <jsp:useBean id="memDAO" class="DAO.MemberDAO"></jsp:useBean>                                   
                                        <table id="filer_member" style="font-size: 16px; width: 79vw" class="table_da">
                                            <thead style="display: block; width: 78.7vw">
                                                <tr>
                                                    <th class="th_mem" style="width: 40px">#</th>
                                                    <th class="th_mem" style="width: 40px">ID</th>
                                                    <th class="th_mem" style="width: 90px">Username</th>          
                                                    <th class="th_mem" style="width: 180px">Full Name</th>
                                                    <th class="th_mem" style="width: 180px">Phone</th>
                                                    <th class="th_mem" style="width: 250px">Address</th>                                                                            
                                                    <th class="th_mem" style="width: 100px">Status</th>
                                                    <th class="th_mem" style="width: 120px">Action</th>
                                                </tr> 
                                            </thead>
                                            <tbody style="height: 500px; overflow: auto; display: block; width: 80vw">
                                            <ad:forEach var="listMem" items="${memDAO.getAllMembers()}" varStatus="count">
                                                <ad:set var="userid" value="${listMem.getUserid()}" />
                                                <ad:set var="username" value="${memDAO.getUsernameByID(userid)}" />
                                                <tr>
                                                    <td style="width: 42px">${count.count}</td>
                                                    <td style="width: 42px">${listMem.getUserid()}</td>
                                                    <td style="width: 112px">${username}</td>
                                                    <td style="width: 191px">${listMem.getFullname()}</td>
                                                    <td style="width: 191px">${listMem.getPhone()}</td>    
                                                    <td style="width: 263px">${listMem.getAddress()}</td>                                          
                                                    <td class="cell_active_table_da" style="width: 106px">
                                                        <ad:if test="${listMem.getStatus()}">
                                                            <p class="active_table_da">ACTIVE</p>
                                                        </ad:if>
                                                        <ad:if test="${!listMem.getStatus()}">
                                                            <p class="deactive_member_da">INACTIVE</p>
                                                        </ad:if>
                                                    </td>
                                                    <td style="width: 127px">
                                                        <a href="javascript:void(0)" class="view_action_da">
                                                            <i class="fa fa-eye"></i>
                                                        </a>   
                                                        <ad:url var="editMemberURL" value="profile.jsp">
                                                            <ad:param name="usrUpdate" value="${listMem.getUserid()}"/>                                                            
                                                        </ad:url>                                                       
                                                        <a href="${editMemberURL}" class="edit_action_da" target="_blank">
                                                            <i class="fa fa-pencil"></i>
                                                        </a>
                                                        <ad:url var="deleteUserURL" value="MainServlet">
                                                            <ad:param name="usrDel" value="${listMem.getUserid()}" />
                                                            <ad:param name="action" value="Delete" />
                                                        </ad:url>
                                                        <a href="#" class="delete_action_da">
                                                            <i class="fa fa-trash"></i>
                                                        </a>    
                                                    </td>
                                                </tr>
                                            </ad:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>


                            <div class="tab-pane" id="addProduct" role="tabpanel" aria-labelledby="addProduct-tab">
                                <div class="container">
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="row">
                                                <form action="MainServlet" method="post">
                                                    <div class="col-md-4">
                                                        <img src="#" width="220px" height="220px" id="productImg" style="display: block;">
                                                    </div> 
                                                    <div class="col-md-8">
                                                        <div class="inputSecction">
                                                            <label style="margin: 0; width: 50px; border-right: #ddd thin solid; text-align: center; padding: 15px 20px">
                                                                <i style="font-size: 20px" class="fa fa-bookmark" aria-hidden="true"></i>
                                                            </label>
                                                            <input style="padding: 10px 20px; width: 80%; font-size: 16px;" type="name" name="txtAddProName" placeholder="Name" minlength="4"
                                                            maxlength="32" required>
                                                        </div>
                                                    </div>
                                                    <div class="col-md-8">
                                                        <div class="inputSecction">
                                                            <label style="margin: 0; width: 50px; border-right: #ddd thin solid; text-align: center; padding: 15px 20px">
                                                                <i style="font-size: 20px" class="fa fa-bookmark" aria-hidden="true"></i>
                                                            </label>
                                                            <input style="padding: 10px 20px; width: 80%; font-size: 16px;" type="name" name="txtAddProPrice" placeholder="Price" pattern="\d+(\.\d{2})?" title="Invalid Price" spellcheck = "false" required>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-6">
                                                        <input style="width: 20px; height: 20px" type="checkbox" name="txtAddProStatus" value="1"> <span style="font-size: 16px; margin-left: 10px">Available</span> 
                                                        <input type="file" id="image" name="txtAddProImage" title="" id="productImgTag"  style="margin-top: 10px" required> 
                                                    </div>
                                                    <ad:choose>
                                                        <ad:when test="${sessionScope.ADDPRODUCTSTATUS == 'true'}">
                                                        <div class="successMsg col-md-4">
                                                            <b style="color: green"><i class="fa fa-check-circle"></i>Add Product Successfully</b>
                                                        </div>
                                                        </ad:when>                                                        
                                                    </ad:choose>
                                                    <ad:remove var="ADDPRODUCTSTATUS" scope="session" />
                                                    <div class="col-md-2">    
                                                        <input class="btnAđdProduct" type="submit" name="action" value="Add Product" required>                              
                                                    </div>   
                                                    <div class="col-md-12">
                                                        <p><b>Description: </b></p>
                                                        <div id="event_text_da"></div>  
                                                    </div>
                                                </form>                                             
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="tab-pane" id="editProduct" role="tabpanel" aria-labelledby="editProduct-tab">
                                <div class="container" style="padding-top: 20px">
                                    <div class="row">
                                        <jsp:useBean id="productIDEditTab" class="DAO.ProductDAO">
                                        <ad:set var="productIDEdit" value='${productIDEditTab.getProductByID(param.txtProductID)}' scope="session"/>  

                                            <form action="MainServlet" method="post"> 
                                                <div class="col-md-3">
                                                    <img src="images/${productIDEdit.getImagePath()}" id="editProductImg" width="200px" height="200px">
                                                </div>
                                                <div class="col-md-9">    
                                                    <div class="row">
                                                        <div class="form-row">
                                                            <div class="form-group col-sm-6">
                                                                <label for="firstname-duc">Product ID:</label>
                                                                <input type="text" class="form-control" name="txtEditID" value="${param.txtProductID}" readonly>
                                                            </div>
                                                            <div class="form-group col-sm-6">
                                                                <label for="lastname-duc">Product Name:</label>
                                                                <input type="text" class="form-control" name="txtEditName" value="${productIDEdit.getName()}" 
                                                                minlength="4" maxlength="32" required>
                                                            </div>
                                                        </div>
                                                        <div class="form-row">
                                                            <div class="form-group col-sm-6">
                                                                <label for="lastname-duc">Price:</label>
                                                                <input type="text" class="form-control" name="txtEditPrice" value="${productIDEdit.getPrice()}" pattern="\d+(\.\d{2})?" title="Invalid Price" spellcheck = "false" required>
                                                            </div>
                                                            <div class="form-group col-sm-6">
                                                                <label for="lastname-duc">Status:</label>
                                                                <select class="form-control" name="txtIsStatus">      
                                                                    <ad:choose>
                                                                        <ad:when test="${productIDEdit.isStatus()}">
                                                                            <option value="1">ACTIVE</option>                                                            
                                                                            <option value="0">INACTIVE</option>      
                                                                        </ad:when>
                                                                        <ad:when test="${!productIDEdit.isStatus()}">
                                                                            <option value="0">INACTIVE</option>                                                                                                                                 
                                                                            <option value="1">ACTIVE</option> 
                                                                        </ad:when>
                                                                    </ad:choose>
                                                                </select>
                                                            </div>
                                                        </div

                                                        <div class="form-row">
                                                            <div class="form-group col-md-4">
                                                                <input type="file" id="image" name="txtImage" title=" " id="editProductImgTab" onchange="previewFileEdit()">
                                                            </div>        
                                                            <div class="form-group col-md-8"></div>
                                                        </div> 

                                                        <div class="form-row">
                                                            <div class="form-group col-md-6"></div>
                                                            <div class="form-group col-md-6">
                                                                <a class="nav-link product-tab-edit" id="product-tab-edit" data-toggle="tab" href="#product" role="tab" aria-controls="product" aria-selected="false">Close</a>                                                  
                                                                <input class="btnEditProduct" id="btnEditProduct" type="submit" name="action" value="Edit Product">  
                                                            </div>
                                                        </div>                                           
                                                    </div>                                                      
                                            </form>
                                            <div class="col-md-12">
                                                <p><b>Description: </b></p>
                                                <div id="event_text_da"></div>  
                                            </div>
                                        </jsp:useBean>
                                    </div>
                                </div>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function () {
                $('.menu1_da').click();
            });

            $(document).ready(function () {
                $('a[data-toggle="tab"]').on('show.bs.tab', function (e) {
                    localStorage.setItem('activeTab', $(e.target).attr('href'));
                });
                var activeTab = localStorage.getItem('activeTab');
                if (activeTab) {
                    $('#myTab a[href="' + activeTab + '"]').tab('show');
                }
            });
        </script>

        <script type="text/javascript">
            var tfConfig = {
                base_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/',
                col_0: 'none',
                col_1: 'none',
                col_4: 'none',
                filters_row_index: 1,
                alternate_rows: true,
                rows_counter: true,
                btn_reset: true,
                loader: true,
                status_bar: true,
                col_types: [
                    'text',
                    'text',
                    'date',
                    'text',
                    'text'
                ],

                sort_filter_options_desc: [4, 5, 7],

                extensions: [{
                        name: 'sort',
                        images_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/style/themes/'
                    }]
            };

            var tfConfig2 = {
                base_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/',
                filters_row_index: 1,
                alternate_rows: true,
                rows_counter: true,
                btn_reset: true,
                loader: true,
                status_bar: true,
                col_types: [
                    'text',
                    'text',
                    'date',
                    'text',
                    'text'
                ],

                sort_filter_options_desc: [4, 5, 7],
                col_widths: [
                    // '150px', '75px', '90px',
                    // '110px', '110px',
                    '*'
                ],
                extensions: [{
                        name: 'sort',
                        images_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/style/themes/'
                    }]
            };



            var tfConfigMember = {
                base_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/',
                col_0: 'none',
                col_1: 'none',
                col_6: 'select',
                col_7: 'none',
                filters_row_index: 1,
                alternate_rows: true,
                rows_counter: true,
                btn_reset: true,
                loader: true,
                status_bar: true,
                col_types: [

                ],

                sort_filter_options_desc: [4, 5, 7],
                col_widths: [
                    '40px', '40px', '90px',
                    '110px', '110px',
                    '*'
                ],
                extensions: [{
                        name: 'sort',
                        images_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/style/themes/'
                    }]
            };



            var tfConfigProduct = {
                base_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/',
                col_0: 'none',
                col_4: 'none',
                col_5: 'select',
                col_6: 'none',
                filters_row_index: 1,
                alternate_rows: true,
                rows_counter: true,
                btn_reset: true,
                loader: true,
                status_bar: true,
                col_types: [
                    'number',
                    'string',
                    'string',
                    'number',
                    'text',
                    'text',
                    'text',
                    'text'
                ],

                sort_filter_options_desc: [4, 5, 7],
                col_widths: [
//                     '150px', '75px', '90px',
//                     '110px', '110px',
                    '*'
                ],
                extensions: [{
                        name: 'sort',
                        images_path: 'https://unpkg.com/tablefilter@latest/dist/tablefilter/style/themes/'
                    }]
            };


            var tf = new TableFilter('filter-order', tfConfig);
            var tf2 = new TableFilter('filter-order2', tfConfig2);
            var tfMember = new TableFilter('filer_member', tfConfigMember);
            var tfProduct = new TableFilter('filter_product', tfConfigProduct);

            tf.init();
            tf2.init();
            tfMember.init();
            tfProduct.init();

            $("#flt2_filter-order").click(function () {
                $(this).prop('type', 'date');
            });

            $("#flt2_filter-order2").click(function () {
                $(this).prop('type', 'date');
            });

        </script>



    </body>
    <scrip src="js/filterOrder.js"></script>
    <script src="js/admin.js"></script>           
    <script type="text/javascript" src="js/logo.js"></script>   
    <script type="text/javascript" src="js/image.js"></script>   
    <script type="text/javascript" src="js/canvas.js"></script>
    <script>
            window.onload = function () {

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    theme: "light2",
                    title: {
                        text: "Profits"
                    },
                    axisY: {
                        includeZero: false
                    },
                    data: [{
                            type: "line",
                            dataPoints: [
                                {y: 450},
                                {y: 414},
                                {y: 520, indexLabel: "highest", markerColor: "red", markerType: "triangle"},
                                {y: 460},
                                {y: 450},
                                {y: 500},
                                {y: 480},
                                {y: 480},
                                {y: 410, indexLabel: "lowest", markerColor: "DarkSlateGrey", markerType: "cross"},
                                {y: 500},
                                {y: 480},
                                {y: 510}
                            ]
                        }]
                });
                chart.render();
            }
    </script>  

    <script>
        $(function () {
            $("input[type=file]").change(function () {
                readURL(this);
            });
        });
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {                   
                    $('#productImg').attr('src', e.target.result);
                    $('#editProductImg').attr('src', e.target.result);
                }

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>

</html>