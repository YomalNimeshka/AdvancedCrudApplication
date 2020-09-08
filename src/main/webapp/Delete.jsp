<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 9/4/2020
  Time: 8:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Delete</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- MATERIAL DESIGN ICONIC FONT -->
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<div class="wrapper"  style="background-image: url('images/bg-registration-form-4.png')">
    <div class="inner">
        <div class="image-holder">
            <img src="images/registration-form-3.jfif" alt="">
        </div>
        <form action="Deleted" method="post">
            <h3>Delete</h3>
            <%--<div class="form-group">
                <input type="text" placeholder="First Name" class="form-control">
                <input type="text" placeholder="Last Name" class="form-control">
            </div>--%>
            <div class="form-wrapper">ID
                <input type="text" placeholder="" name="id" class="form-control" value="<c:out value="${account.id}"/>" readonly/>
                <i class="zmdi zmdi-face"></i>
            </div>
            <div class="form-wrapper">USER NAME
                <input type="text" placeholder="Username" maxlength="255" name="userName" class="form-control" value="<c:out value="${account.userName}"/>" readonly>
                <i class="zmdi zmdi-account"></i>
            </div>
            <div class="form-wrapper">NIC
                <input type="text" placeholder=""  name="nic" class="form-control" value="<c:out value="${account.nic}"/>" readonly>
                 <i class="zmdi zmdi-card"></i>
            </div>
            <div class="form-wrapper">MOBILE NUMBER
                <input type="text" placeholder=""  name="mobileNumber" class="form-control" value="<c:out value="${account.mobileNumber}"/>" readonly>
                <i class="zmdi zmdi-phone"></i>
            </div>
            <div class="form-wrapper">GENDER
                <input type="text" placeholder=""  name="gender" class="form-control" value="<c:out value="${account.gender}"/>" readonly>
                <i class="zmdi zmdi-male-female"></i>
            </div>

            <button>Delete
                <i class="zmdi zmdi-arrow-right"></i>
            </button><br/><br/>

        </form>
    </div>
</div>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
