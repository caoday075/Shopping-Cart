<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="lo" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>  
        <link rel="stylesheet" type="text/css" href="css/login.css">
        <script type="text/javascript" src="js/login.js"></script>
        <script type="text/javascript" src="js/password.js"></script>
    </head>
    <body>
        <div class="login-reg-panel">
            <div class="login-info-box">    
                <h2>Have an account?</h2>
                <div id="label-register-wrapper"><label id="label-register" for="log-reg-show">Login</label></div>
                <input type="radio" name="active-log-panel" id="log-reg-show"  checked="checked">
            </div>

            <div class="register-info-box">
                <h2>Don't have an account?</h2>

                <lo:if test = "${(sessionScope.DUPLICATE != null) || (sessionScope.DUPLICATE == 'true')}">
                    <h4>Username already existed!</h4>
                </lo:if>
                <lo:remove var="DUPLICATE" scope="session" />

                <div id="label-login-wrapper"><label id="label-login" for="log-login-show">Register</label></div>
                <input type="radio" name="active-log-panel" id="log-login-show">
            </div>

            <div class="white-panel">
                <form action="MainServlet" method="post">
                    <div class="login-show">
                        <!-- <h2>LOGIN</h2>                                                
                        <input type="text" placeholder="Username" name="txtusername" onblur='validUsername(this)'
                               value=""> 
                        <div class="line-left"></div>       
                        <input type="password" placeholder="Password" name="txtpassword"
                               value="${Request.Cookies("PASSWORD").getValue()}">
                        <div class="line-left"></div>
                        <p><input type="checkbox" name="txtremember" value="1" checked> Remember me</p>

                        <lo:if test = "${(sessionScope.LOGIN_STATUS != null) || (sessionScope.LOGIN_STATUS == 'true')}">
                            <h4 class="loginFailure"> Authentication Failure </h4>  
                        </lo:if>

                        <input class="btnLogin" type="submit" value="Login" name="action">
                        <a href="">Forgot password?</a> -->

                        <h2>LOGIN</h2>
                        <div class="group">
                            <input id="username" type="text" name="txtusername" onblur='validUsername(this)' required/>
                            <label for="username">Username</label>
                            <span class="bar"></span>
                        </div>
                        <div class="group">
                            <input id="password" type="password" name="txtpassword" required>
                            <label for="password">Password</label>
                            <span class="bar"></span>
                        </div>
                        <p><input type="checkbox" name="txtremember" value="1" checked> Remember me</p>
                        <lo:if test = "${(sessionScope.LOGIN_STATUS != null) || (sessionScope.LOGIN_STATUS == 'true')}">
                            <h4 class="loginFailure"> Authentication Failure </h4>  
                        </lo:if>
                        <lo:remove var="LOGIN_STATUS" scope="session" />
                        <input class="btnLogin" type="submit" value="Login" name="action">
                    </div>
                </form>

                <form action="MainServlet" method="post">
                    <div class="register-show">
                        <h2>REGISTER</h2>  
                        <div class="group">                      
                            <input id="usernameRe" type="text" name="txtusername" pattern="^[a-z\d\.]{3,}$" spellcheck="false" title="Invalid Username"  minlength="3" maxlength="24" required>
                            <label for="username">Username</label>       
                            <span class="bar"></span>
                        </div>
                        <div class="group">   
                            <input id="passwordRe" type="password" name="txtpassword" placeholder="password" minlength=6" maxlength="32" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" 
                               spellcheck="false" title="Must 6 characters, lowercase and uppercase, number" required>                                
                            <span class="bar"></span>
                        </div>
                        <div class="group"> 
                            <input id="confirm_password" type="password" minlength="6" maxlength="32" placeholder="Confirm password" required>
                            <span class="bar"></span>
                        </div>
                        <h4 class="errorMsg"></h4>
                        <div id="checkconfirm" style="color: red"> </div>
                        <input class="btnLogin" type="submit" value="Register" name="action">
                    </div>
                </form>
            </div>
        </div> 


        <script type="text/javascript">
            $(document).ready(function () {
                $('.login-info-box').fadeOut();
                $('.login-show').addClass('show-log-panel');
            });


            $('.login-reg-panel input[type="radio"]').on('change', function () {
                if ($('#log-login-show').is(':checked')) {
                    $('.register-info-box').fadeOut();
                    $('.login-info-box').fadeIn();
                    $('.white-panel').addClass('right-log');
                    $('.register-show').addClass('show-log-panel');
                    $('.login-show').removeClass('show-log-panel');
                } else if ($('#log-reg-show').is(':checked')) {
                    $('.register-info-box').fadeIn();
                    $('.login-info-box').fadeOut();
                    $('.white-panel').removeClass('right-log');
                    $('.login-show').addClass('show-log-panel');
                    $('.register-show').removeClass('show-log-panel');
                }
            });


            var password = document.getElementById("passwordRe")
                    , confirm_password = document.getElementById("confirm_password");

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