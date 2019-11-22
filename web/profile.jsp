<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="pro" %>
<!DOCTYPE html>
<html lang="en">
    <head>	
        <jsp:useBean id="memDAO" class="DAO.MemberDAO" />                
        <pro:set var="username" value="${memDAO.getUsernameByID(param.usrUpdate)}" />
        <pro:set var="memDTO" value="${memDAO.getMemberByUsrID(param.usrUpdate)}" />
        <title>${username}</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="css/admin.css">	
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

    </head>

    <body>	
        <div class="wrapper_da container-fuild">
            <div class="">
                <div class="wrap_menu_da col-md-2">		
                    <div class="menu_logo_da">
                        <!-- <img id="logo_da" src="img/logo.png" alt="logo">  -->
                        <h3 id="title_da">Shopping</h3>			
                    </div>

                    <div class="line_da"></div>
                    <div class="sidebar" style="text-align: center">                
                        <ul class="nav nav-pills nav-stacked" id="myTab" role="tablist">                    
                            <li class="nav-item active">
                                <a class="nav-link active" id="account-tab" data-toggle="pill" href="#account" role="tab" aria-controls="account" aria-selected="true"><i class=""></i> Account</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="profile-tab" data-toggle="pill" href="#profile" role="tab" aria-controls="profile" aria-selected="false"><i class="fas fa-user-lock"></i> Profile</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="privacy-tab" data-toggle="pill" href="#privacy" role="tab" aria-controls="privacy" aria-selected="false"><i class="fas fa-user-lock"></i> Privacy</a>
                            </li>

                        </ul>
                    </div>
                </div>

                <div class="main_da col-md-10" style="width: 83% !important; height: 100vh;">                
                    <div class="header_da">
                        <h3 id="top_da">DASHBOARD</h3>
                        <div class="inner_right_da">
                            <ul>						
                                <li class="dropdown_da">
                                    <a href="javascript:void(0)">
                                        <i id="showDropdown_da" class="fa fa-cog"></i>					
                                    </a>	
                                    <ul class="wrap_dropdown_da" id="wrap_drop_da">							
                                        <li>
                                            <a href="index.html" target="_blank">
                                                <i class="fa fa-home"></i>
                                                <span>Home Page</span>
                                            </a>
                                        </li>	
                                        <li>
                                            <a href="login.jsp">
                                                <i class="fa fa-sign-out"></i>
                                                <span>Sign out</span>
                                            </a>
                                        </li>									
                                    </ul>						
                                </li>	
                            </ul>	

                        </div>
                    </div>

                    <div>             
                        <div class="tab-content">
                            <div class="tab-pane active" id="account" role="tabpanel" aria-labelledby="account-tab">
                                <div class="col-sm-4"></div>
                                <div class="col-sm-4" style="text-align: center">
                                    <img class="mem_pic" src="images/member_pic1.jpg">
                                    <form>
                                        <div class="form-group">
                                            <label for="nickname-duc">Alias:</label>
                                            <input type="text" class="form-control" id="nickname-duc" disabled>
                                        </div>
                                    </form>
                                </div>
                                <div class="col-sm-4"></div>
                            </div>

                            <div class="tab-pane" id="profile" role="tabpanel" aria-labelledby="profile-tab">

                                <form action="MainServlet" method="post">
                                    <div class="form-row">
                                        <div class="form-group col-sm-6">
                                            <label for="firstname-duc">Id: </label>
                                            <input type="text" class="form-control" name="txtId" id="firstname-duc" value="${param.usrUpdate}" readonly>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="lastname-duc">Username: </label>
                                            <input type="text" class="form-control" name="txtUsername" id="lastname-duc" value="${username}" readonly>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-sm-6">
                                            <label for="dob-duc">Full Name: </label>
                                            <input type="text" class="form-control" name="txtFullName" id="dob-duc" value="${memDTO.getFullname()}" minlength="4" maxlength="32" pattern="^[0-9a-zA-Z \-'_]+$" 
                                            spellcheck="false" title="Invalid Full Name" required>
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="gender-duc">Phone: </label>
                                            <input type="text" class="form-control" name="txtPhone" id="gender-duc" value="${memDTO.getPhone()}" pattern="(0[3|5|7|8|9])+([0-9]{8})" spellcheck="false" titile="Invalid Phone" required>
                                        </div>
                                    </div>


                                    <div class="form-row">
                                        <div class="form-group col-sm-6">
                                            <label for="status">Status: </label>
                                            <select class="form-control" name="txtStatus">      
                                                <pro:choose>
                                                    <pro:when test="${memDTO.getStatus()}">
                                                        <option value="1">ACTIVE</option>                                                            
                                                        <option value="0">INACTIVE</option>      
                                                    </pro:when>
                                                    <pro:when test="${!memDTO.getStatus()}">
                                                        <option value="0">INACTIVE</option>                                                                                                                                 
                                                        <option value="1">ACTIVE</option> 
                                                    </pro:when>
                                                </pro:choose>                                                       
                                            </select>
                                    <!--<input type="text" class="form-control" name="txtStatus" value="${memDTO.getStatus()}">-->
                                        </div>
                                        <div class="form-group col-sm-6">
                                            <label for="email-duc">Address: </label>
                                            <input type="text" class="form-control" name="txtAddress" id="email-duc" value="${memDTO.getAddress()}" minlength="6" required>
                                        </div>
                                    </div>

                                    <div class="form-row">
                                        <div class="form-group col-sm-6">
                                            <label for="status"> </label>
                                            <input type="hidden" class="form-control" name="txtUsrID" value="${param.usrUpdate}">
                                        </div>                                      
                                    </div>
                                    <button type="submit" name="action" value="UpdateProfile" class="btn btn-success" style="margin: 0 15px">Confirm Changes</button>
                                </form>
                            </div>      

                            <div class="tab-pane" id="privacy" role="tabpanel" aria-labelledby="privacy-tab">
                                <div class="container" style="padding-top: 120px">
                                    <div class="row">
                                        <form action="ChangePassServlet" method="post">
                                            <div class="col-md-4"></div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <input type="hidden" class="form-control" name="txtId" id="firstname-duc" value="${param.usrUpdate}" readonly>
                                                    <label>Old Password:</label>
                                                    <input id="passwd" type="password" class="form-control" name="txtOldPass" required>
                                                    
                                                    <pro:if test="${sessionScope.PASSWORDSTATUS == 'false'}">
                                                        <div class="errorMsg"><b style="color: red">*Password is not correct</b></div>
                                                    </pro:if>
                                                    <pro:if test="${sessionScope.PASSWORDSTATUS != 'false'}">
                                                        <div class="errorMsg"></div>
                                                    </pro:if>    
                                                        
                                                    <label>New Password: </label>
                                                    <input id="passwordNew" type="password" class="form-control" name="txtNewPass" minlength="6" maxlength="32" required>   
                                                    <label>Confirm Password:</label> 
                                                    
                                                    <input id="passwordNewCon" type="password" class="form-control" name="txtConPass" minlength="6" maxlength="32" required> 
                                                    <pro:if test="${sessionScope.PASSWORDSUCCESS == 'true'}">
                                                        <div class="successMsg"><b style="color: green"><i class="fa fa-check-circle"></i>Change password successfully</b></div>
                                                    </pro:if>
                                                    <pro:if test="${sessionScope.PASSWORDSTATUS == 'false'}">
                                                        <div class="successMsg"></div>
                                                    </pro:if> 
                                                        
                                                </div>
                                                <button type="submit" name="action" value="UpdateProfile" class="btn btn-success" style="margin: 0 15px">Confirm Changes</button>
                                            </div>
                                            <div class="col-md-4"></div>      
                                        </form>                                 
                                    </div>                                    
                                </div>                               
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>	
        <script src="js/admin.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>  
        <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>   
        <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.js"></script>
    
        <script>
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

            var password = document.getElementById("passwordNew")
                    , confirm_password = document.getElementById("passwordNewCon");                   

            function validatePassword() {
                if (password.value != confirm_password.value) {
                    confirm_password.setCustomValidity("Passwords Don't Match");
                } else {
                    confirm_password.setCustomValidity('');
                }
            }
     
            password.onchange = validatePassword;
            confirm_password.onkeyup = validatePassword;

        </script>
    </body>
</html>