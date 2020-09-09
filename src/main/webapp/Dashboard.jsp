<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%--
  Created by IntelliJ IDEA.
  User: yomal_m
  Date: 9/3/2020
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Dashboard</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="css/util.css">
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="fonts/material-design-iconic-font/css/material-design-iconic-font.min.css">

    <!--===============================================================================================-->
    <style>


        .page {
            display: inline-block;
        }

        .page a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
        }

        .page a.active {
            background-color: #4CAF50;
            color: white;
        }

        .page a:hover:not(.active) {background-color: #ddd;}

        .container-table101 {
            width: 100%;
            min-height: 200px;
            /*background: #fff;*/


            display: -webkit-box;
            display: -webkit-flex;
            display: -moz-box;
            display: -ms-flexbox;
            display: flex;
            align-items: center;
            justify-content: center;
            flex-wrap: wrap;
            padding: 33px 30px;
        }
    </style>
</head>
<body>
<%--this is to make sure that once logged out there is no backtracking from the browser--%>
<%
    try {
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Cache-Control","no-store");
        response.setHeader("Pragma","no-cache");
        response.setDateHeader ("Expires", 0);
        if (session.getAttribute("id")==null) {
            response.sendRedirect("Login.jsp");
        }
        else {}
    }
    catch(Exception ex) {
        out.println(ex);
    }
%>
<div  class="container-table101" style="background-image: url('images/bg-registration-form-5.jpg');">
    <div class="wrap-table100">
        <%--Search--%>
        <div class="table100-head js-pscroll">
            <table>
                <th class="cell100 column1" colspan="5">
                    <table cellpadding="5" cellspacing="5">
                        <tr>
                            <td colspan="5">
                                <form action="Search" method="get" >
                                    <label style="color: white" >Search:</label>
                                    <input type="text" name="search-bar" id="searchBar" value="<c:out value="${searchValue}"/>" class="form-control">
                                    <button type="submit" value="Search" class="btn btn-primary btn-block btn-large">Search<i class="zmdi zmdi-search"></i></button>
                                </form>
                            </td>
                        </tr>
                    </table>
                </th>
            </table>
        </div>
    </div>
</div>
<div class="limiter">

    <div class="container-table100" style="background-image: url('images/bg-registration-form-4.png');">
        <div class="wrap-table100">
            <div class="table100 ver1 m-b-110">

                <%--table headers--%>
                <div class="table100-head">
                    <table >
                        <thead>

                        <tr class="row100 head" >
                            <th class="cell100 column1" onclick="sortTable(0)" style="cursor: pointer">User Name  <i class="zmdi zmdi-unfold-more"></i></th>
                            <th class="cell100 column2" onclick="sortTable(1)" style="cursor: pointer">ID  <i class="zmdi zmdi-unfold-more"></i></th>
                            <th class="cell100 column3" onclick="sortTable(2)" style="cursor: pointer">NIC  <i class="zmdi zmdi-unfold-more"></i></th>
                            <th class="cell100 column4" onclick="sortTable(4)" style="cursor: pointer">Mobile Number  <i class="zmdi zmdi-unfold-more"></i></th>
                            <th class="cell100 column5" onclick="sortTable(5)" style="cursor: pointer">Gender  <i class="zmdi zmdi-unfold-more"></i></th>
                            <th class="cell100 column6" style="cursor: default">Edit/Delete</th>
                        </tr>
                        </thead>
                    </table>


                </div>



                <%--dashboard data--%>
                <div class="table100-body js-pscroll">
                    <table id="myTable">
                        <tbody>
                        <c:forEach var="listOfAcc" items="${listOfAcc}">

                            <tr class="row100 body">
                                <td class="cell100 column1"><c:out value="${listOfAcc.userName}"/></td>
                                <td class="cell100 column2"><c:out value="${listOfAcc.id}"/></td>
                                <td class="cell100 column3"><c:out value="${listOfAcc.nic}"/></td>
                                <td class="cell100 column4"><c:out value="${listOfAcc.mobileNumber}"/></td>
                                <td class="cell100 column5"><c:out value="${listOfAcc.gender}"/></td>
                                <td class="cell100 column6">
                                    <a class="btn btn-sm btn-success" href="Edit?id=<c:out value="${listOfAcc.id}"/>"><i class="zmdi zmdi-edit"></i>  Edit</a><br/><br/>
                                    <a class="btn btn-sm btn-danger" href="Delete?id=<c:out value="${listOfAcc.id}"/>"><i class="zmdi zmdi-delete"></i>  Delete</a>
                                </td>

                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>

                </div>

                <%--pagination--%>
                <div class="center">
                    <div class="page">
                        <table>
                            <th class="" colspan="5">
                                <table cellpadding="5" cellspacing="5">
                                    <tr class="">
                                        <c:forEach begin="1" end="${noOfPages}" var="i">
                                            <c:choose>
                                                <c:when test="${currentPage eq i}">
                                                    <td class="" style="color: white"><a >${i}</a>  </td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td class="" ><a href="Dashboard?page=${i}" style="color: white">   ${i}</a></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </tr>
                                </table>
                            </th>
                        </table>
                    </div>

                </div>

                <%--logout buttons--%>
                <div class="table100-body js-pscroll">
                        <table>
                            <th class="cell100 column1" colspan="6">
                                <table cellpadding="5" cellspacing="5">
                                    <tr>
                                       <%-- <a href="/DownloadPDF">Download to PDF</a>
                                        <a href="/DownloadExcel">Download to Excel</a>--%>
                                        <td>
                                            <form action="DownloadPDF" method="get">
                                                <button>Download PDF
                                                    <i class="zmdi zmdi-download"></i>
                                                </button>
                                            </form>
                                        </td>
                                           <td>
                                               <form action="DownloadExcel" method="get">
                                                   <button>Download Excel
                                                       <i class="zmdi zmdi-download"></i>
                                                   </button>
                                               </form>
                                           </td>
                                        <td>
                                            <form action="Logout" method="get">
                                                <button>LogOut
                                                    <i class="zmdi zmdi-power-off"></i>
                                                </button>
                                            </form>
                                        </td>
                                           <td>
                                               <form action="Upload" method="post" enctype="multipart/form-data">
                                                   <input id="fileAttachment" type="file" name="fileUpload" multiple="multiple" />
                                                    <button>Upload<i class="zmdi zmdi-upload"></i>
                                                    </button>
                                               </form>
                                           </td>
                                    </tr>
                                </table>
                            </th>
                        </table>
                    </div>

            </div>

            <%--for sorting the headers--%>
            <script>
                function sortTable(n) {
                    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
                    table = document.getElementById("myTable");
                    switching = true;
                    //Set the sorting direction to ascending:
                    dir = "asc";
                    /*Make a loop that will continue until
                    no switching has been done:*/
                    while (switching) {
                        //start by saying: no switching is done:
                        switching = false;
                        rows = table.rows;
                        /*Loop through all table rows (except the
                        first, which contains table headers):*/
                        for (i = 0; i < (rows.length - 1); i++) {
                            //start by saying there should be no switching:
                            shouldSwitch = false;
                            /*Get the two elements you want to compare,
                            one from current row and one from the next:*/
                            x = rows[i].getElementsByTagName("TD")[n];
                            y = rows[i + 1].getElementsByTagName("TD")[n];
                            /*check if the two rows should switch place,
                            based on the direction, asc or desc:*/
                            if (dir == "asc") {
                                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                                    //if so, mark as a switch and break the loop:
                                    shouldSwitch= true;
                                    break;
                                }
                            } else if (dir == "desc") {
                                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                                    //if so, mark as a switch and break the loop:
                                    shouldSwitch = true;
                                    break;
                                }
                            }
                        }
                        if (shouldSwitch) {
                            /*If a switch has been marked, make the switch
                            and mark that a switch has been done:*/
                            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                            switching = true;
                            //Each time a switch is done, increase this count by 1:
                            switchcount ++;
                        } else {
                            /*If no switching has been done AND the direction is "asc",
                            set the direction to "desc" and run the while loop again.*/
                            if (switchcount == 0 && dir == "asc") {
                                dir = "desc";
                                switching = true;
                            }
                        }
                    }
                }
            </script>



        </div>
        <%--<form action="Logout" method="get">
            <button>LogOut
                <i class="zmdi zmdi-arrow-back"></i>
            </button>
        </form>--%>
    </div>

</div>




<%--<!--===============================================================================================-->
<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/bootstrap/js/popper.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->--%>
<%--<script src="vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        var ps = new PerfectScrollbar(this);

        $(window).on('resize', function(){
            ps.update();
        })
    });


</script>--%>
<!--===============================================================================================-->
<%--<script src="js/main.js"></script>--%>

</body>
</html>
