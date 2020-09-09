<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 9/3/2020
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- MATERIAL DESIGN ICONIC FONT -->
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
<style>
    /* COMPACT CAPTCHA */

    .capbox {
        background-color: #fff;
        background-image: linear-gradient(#fff, #E3E3E3);
        border: #6c7ae0 0px solid;
        border-width: 2px 2px 2px 20px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
        display: inline-block;
        padding: 5px 8px 5px 8px;
        border-radius: 4px 4px 4px 4px;
    }

    .capbox-inner {
        font: bold 12px arial, sans-serif;
        color: #000000;
        background-color: #E3E3E3;
        margin: 0px auto 0px auto;
        padding: 3px 10px 5px 10px;
        border-radius: 4px;
        display: inline-block;
        vertical-align: middle;
    }

    #CaptchaDiv {
        color: #000000;
        font: normal 25px Impact, Charcoal, arial, sans-serif;
        font-style: italic;
        text-align: center;
        vertical-align: middle;
        background-color: #FFFFFF;
        user-select: none;
        display: inline-block;
        padding: 3px 14px 3px 8px;
        margin-right: 4px;
        border-radius: 4px;
    }

    #CaptchaInput {
        border: #6c7ae0 2px solid;
        margin: 3px 0px 1px 0px;
        width: 105px;
    }

    input[type=button] {
        width: 25px;
        height: 25px;
        background-image: url('images/ref.png');
        border: white;
    }

</style>

<body onload="generate()">

<div class="wrapper" style="background-image: url('images/bg-registration-form-4.png');">
    <div class="inner">
        <div class="image-holder">
            <img src="images/registration-form-3.jfif" alt="">
        </div>
        <form action="Login" method="post" onsubmit="return checkform(this);">
            <h3>Login</h3>
            <%--<div class="form-group">
                <input type="text" placeholder="First Name" class="form-control">
                <input type="text" placeholder="Last Name" class="form-control">
            </div>--%>
            <div class="form-wrapper">
                <input type="text" placeholder="Username" maxlength="255" name="userName" class="form-control" required>
                <i class="zmdi zmdi-account"></i>
            </div>
            <div class="form-wrapper">
                <input type="password" placeholder="Password" name="password" class="form-control" required>
                <i class="zmdi zmdi-lock"></i>
            </div>

            <!-- START CAPTCHA -->
            Enter Code to Continue
            <br>
            <div class="capbox">

                <div id="CaptchaDiv"></div>

                <div class="capbox-inner">
                    Type the number:<br>

                    <input type="hidden" id="txtCaptcha">
                    <input type="text" name="CaptchaInput" id="CaptchaInput" size="15"><br>


                </div>
                <div>
                    <input type="button" onclick="generate()">
                </div>

            </div>
            <br><br>
            <!-- END CAPTCHA -->

            <button>Login
                <i class="zmdi zmdi-arrow-right"></i>
            </button>
            <br/><br/>
            <a href="Register.jsp">Register Now!</a>
        </form>
    </div>
</div>

<%--authentication--%>
<script type="text/javascript">

    // Captcha Script

    function checkform(theform) {
        var why = "";

        if (theform.CaptchaInput.value == "") {
            why += "Please Enter CAPTCHA Code.\n";
        }
        if (theform.CaptchaInput.value != "") {
            if (ValidCaptcha(theform.CaptchaInput.value) == false) {
                why += "The CAPTCHA Code Does Not Match.\n";
            }
        }
        if (why != "") {
            alert(why);
            return false;
        }
    }

    function generate() {
        var a = Math.ceil(Math.random() * 9) + '';
        var b = Math.ceil(Math.random() * 9) + '';
        var c = Math.ceil(Math.random() * 9) + '';
        var d = Math.ceil(Math.random() * 9) + '';
        var e = Math.ceil(Math.random() * 9) + '';

        var code = a + b + c + d + e;
        document.getElementById("txtCaptcha").value = code;
        document.getElementById("CaptchaDiv").innerHTML = code;
    }

    // Validate input against the generated number
    function ValidCaptcha() {
        var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
        var str2 = removeSpaces(document.getElementById('CaptchaInput').value);
        if (str1 == str2) {
            return true;
        } else {
            return false;
        }
    }

    // Remove the spaces from the entered and generated code
    function removeSpaces(string) {
        return string.split(' ').join('');
    }
</script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
