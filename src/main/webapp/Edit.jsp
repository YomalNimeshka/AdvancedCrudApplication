<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 9/3/2020
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Edit</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- MATERIAL DESIGN ICONIC FONT -->
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!-- STYLE CSS -->
    <link rel="stylesheet" href="css/style.css">
</head>

<body>

<div class="wrapper" style="background-image: url('images/bg-registration-form-4.png')">
    <div class="inner">
        <div class="image-holder">
            <img src="images/registration-form-3.jfif" alt="">
        </div>
        <form action="Edited" method="post">
            <h3>Edit</h3>
            <%--<div class="form-group">
                <input type="text" placeholder="First Name" class="form-control">
                <input type="text" placeholder="Last Name" class="form-control">
            </div>--%>
            <div class="form-wrapper">ID
                <input type="text" placeholder="" name="id" class="form-control" value="<c:out value="${account.id}"/>"
                       readonly/>
                <i class="zmdi zmdi-face"></i>
            </div>
            <div class="form-wrapper">USER NAME
                <input type="text" placeholder="Username" maxlength="255" name="userName" class="form-control"
                       value="<c:out value="${account.userName}"/>">
                <i class="zmdi zmdi-account"></i>
            </div>
            <div class="form-wrapper">NIC
                <input type="text" placeholder="" name="nic" class="form-control"
                       value="<c:out value="${account.nic}"/>" pattern="^([0-9]{9}[x|X|v|V]|[0-9]{12})$">
                <i class="zmdi zmdi-card"></i>
            </div>
            <div class="form-wrapper">MOBILE NUMBER
                <input type="text" placeholder="" name="mobileNumber" maxlength="15" class="form-control"
                       value="<c:out value="${account.mobileNumber}"/>"
                       pattern="^(?:0|94|\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|5|6|7|8)\d)\d{6}$">
                <i class="zmdi zmdi-phone"></i>
            </div>
            <div class="form-wrapper">GENDER
                <select name="gender" id="" class="form-control" required>
                    <option value="" disabled selected>Gender</option>
                    <option value="male">Male</option>
                    <option value="female">Female</option>
                </select>
                <i class="zmdi zmdi-caret-down" style="font-size: 17px"></i>
            </div>
            <button>Edit
                <i class="zmdi zmdi-arrow-right"></i>
            </button>
            <br/><br/>

        </form>
    </div>
</div>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
