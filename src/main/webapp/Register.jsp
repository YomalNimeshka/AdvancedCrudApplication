<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 9/3/2020
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>User Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- MATERIAL DESIGN ICONIC FONT -->
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>
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

<body>

<div class="wrapper" style="background-image: url('images/bg-registration-form-4.png')">
    <div class="inner">
        <div class="image-holder">
            <img src="images/registration-form-3.jfif" alt="">
        </div>
        <form action="Register" method="post">
            <h3>Registration Form</h3>
            <%--<div class="form-group">
                <input type="text" placeholder="First Name" class="form-control">
                <input type="text" placeholder="Last Name" class="form-control">
            </div>--%>
            <div class="form-wrapper">
                <input type="text" maxlength="255" placeholder="Username" name="userName" class="form-control" required>
                <i class="zmdi zmdi-account"></i>
            </div>
            <div class="form-wrapper">

                <div class="tooltip">
                    <input type="text" placeholder="NIC" name="nic" class="form-control" pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$" required>
                    <i class="zmdi zmdi-card"></i>
                    <span class="tooltiptext">Can contain the old NIC patter and the new Pattern</span>
                </div>
            </div>
            <div class="form-wrapper">
                <div class="tooltip">
                    <input type="text" placeholder="Mobile Number" name="mobileNumber" maxlength="15"  class="form-control" pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$" required>
                    <i class="zmdi zmdi-phone"></i>
                    <span class="tooltiptext">Can Start with +94, 0094, 07, 01</span>
                </div>
            </div>
            <div class="form-wrapper">
                <select name="gender" id="" class="form-control" required>
                    <option value="" disabled selected>Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
                <i class="zmdi zmdi-caret-down" style="font-size: 17px"></i>
            </div>
            <div class="form-wrapper">
                <div class="tooltip">
                    <input type="password" placeholder="Password" name="password" class="form-control" pattern="(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}" required>
                    <i class="zmdi zmdi-lock"></i>
                    <span class="tooltiptext">Need to have atleast 1 UpperCase, 1 Lower case, 1 number and a character with 8.</span>
                </div>
            </div>
            <div class="form-wrapper">
                <input type="password" placeholder="Confirm Password" name="confirmPassword" class="form-control" pattern="(?=.*\d)(?=.*[a-z])(?=.*[!@#$%^&*])(?=.*[A-Z]).{8,}" required>
                <i class="zmdi zmdi-lock"></i>
            </div>
            <button>Register
                <i class="zmdi zmdi-arrow-right"></i>
            </button>
        </form>
    </div>
</div>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
