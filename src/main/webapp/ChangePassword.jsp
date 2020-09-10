<%--
  Created by IntelliJ IDEA.
  User: vishwa_p
  Date: 9/10/2020
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
    <!-- MATERIAL DESIGN ICONIC FONT -->
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="css/style.css">
    <style>
        .tooltiptext {
            visibility: hidden;
            width: 350px;
            background-color: #6c7ae0;
            color: #fff;
            text-align: center;
            border-radius: 6px;
            padding: 5px 0;

            /* Position the tooltip */
            position: absolute;
            z-index: 1;
        }

        .tooltip:hover .tooltiptext {
            visibility: visible;
        }
    </style>
</head>
<body>
<div class="wrapper" style="background-image: url('images/bg-registration-form-4.png');">
    <div class="inner">
        <div class="image-holder">
            <img src="images/registration-form-3.jfif" alt="">
        </div>
        <form action="ChangePasswordServlet" method="post">
            <h3>Change Password</h3>

                <div class="form-wrapper">
                    <div class="tooltip">
                        <input type="password" placeholder="New Password" name="password" class="form-control"
                               pattern="(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}" required>
                        <i class="zmdi zmdi-lock"></i>
                        <span class="tooltiptext">Need to have atleast 1 UpperCase, 1 Lower case, 1 number and a character with 8.</span>
                    </div>
                </div>
                <div class="form-wrapper">
                    <input type="password" placeholder="Confirm Password" name="confirmPassword" class="form-control"
                           pattern="(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}" required>
                    <i class="zmdi zmdi-lock"></i>
                </div>
                <button>Change
                    <i class="zmdi zmdi-arrow-right"></i>
                </button>
        </form>
    </div>
</div>


</body>
</html>
