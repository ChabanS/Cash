<%-- 
    Document   : cash
    Created on : Oct 3, 2016, 5:19:15 PM
    Author     : Sergey
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/moment-with-locales.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
        <script src="js/calendar_ru.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        </br>
        <div align="center" class="container" >
            <div class="col-lg-9"> 

                <div class="col-lg-4">
                    <%-- All Account --%> 
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Account</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:choose>
                                    <c:when test="${param.select == 0}">
                                        <td>
                                            <a class="btn btn-success btn-block" href="?select=0">
                                                <span class="glyphicon glyphicon-ok"> </span> All Accounts
                                            </a>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a class="btn btn-default btn-block" href="?select=0">
                                                All Accounts
                                            </a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                            <c:forEach var="account" items="${acc}">
                                <tr>

                                    <c:choose>
                                        <c:when test="${param.select == account.idaccount}">
                                            <td>
                                                <a class="btn btn-success btn-block" href="?select=${account.idaccount}">
                                                    <span class="glyphicon glyphicon-ok"> </span> ${account.accountName} ${sumByIdAccount}
                                                </a>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <a class="btn btn-default btn-block" href="?select=${account.idaccount}">
                                                    ${account.accountName}
                                                </a>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr> 
                            </c:forEach>
                        </tbody>
                    </table>

                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <td class="success" align="center">
                                    Всего    <i class="fa fa-money " ></i>
                                </td>
                                <td class="success" align="center" >${sumAllAccount}</td>
                            </tr>
                        </thead>
                    </table>
                    <%-- Sort date --%> 
                    <form name="formSort" method="post">
                        <table class="table table-condensed">
                            <thead>
                                <tr>
                                    <th>Filter Date</th>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="input-group date" id="datetimepicker1">
                                            <jsp:useBean id="date1" class="java.util.Date" />
                                            <fmt:formatDate pattern="dd.MM.yyyy" value="${date1}" var="currDate"/>
                                            <input style="text-align: center;" type="text" value="${currDate}" name="date1" class="form-control" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon-calendar glyphicon"></span>
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="input-group date" id="datetimepicker2">
                                            <jsp:useBean id="date2" class="java.util.Date" />
                                            <fmt:formatDate pattern="dd.MM.yyyy" value="${date2}" var="currDate"/>
                                            <input style="text-align: center;" type="text" value="${currDate}" name="date2" class="form-control" />
                                            <span class="input-group-addon">
                                                <span class="glyphicon-calendar glyphicon"></span>
                                            </span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <input class="btn btn-success btn-block" type="submit" name="formSort"/> 
                                    </td>
                                </tr>
                            </thead>
                        </table>
                    </form>
                </div>
                <div class="col-lg-8">
                    <%-- Table result --%>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Account</th>
                                <th>Operation</th>
                                <th>Date</th>
                                <th>Finrez</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="finance" items="${cash}">
                                <tr>
                                    <td>${finance.account.accountName}</td>
                                    <td>${finance.operation.operationName}</td>
                                    <td><fmt:formatDate value="${finance.data}" pattern="dd-MMMM-yyyy"/></td>
                                    <td>${finance.finrez}</td>
                                    <td><a href="cash?remove=${finance.id}" class="btn btn-danger btn-sm">Delete</a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        
                    </table>

                    <%-- Peging --%>        
                    <ul class="pagination">
                        <li><a href="#">&laquo;</a></li>
                        <li><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">&raquo;</a></li>
                    </ul>
                </div>
            </div>
            <div class="col-lg-3">
                <%-- Add --%>
                <form method="post">
                    <table class="table table-condensed">
                        <thead>
                            <tr>
                                <th>Add</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Account</td>
                                <td><select class="form-control" name="nameAccount">
                                        <c:forEach var="account" items="${acc}">
                                            <option name="accountID" value="${account.idaccount}">${account.accountName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Operation</td>
                                <td>
                                    <select class="form-control" name="nameOperation">
                                        <c:forEach var="operation" items="${operat}">
                                            <option  value="${operation.idoperation}">${operation.operationName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Date</td>
                                <td>
                                    <div class="input-group date" id="datetimepicker3">
                                        <jsp:useBean id="date" class="java.util.Date" />
                                        <fmt:formatDate pattern="dd.MM.yyyy" value="${date}" var="currDate"/>
                                        <input style="text-align: center;" type="text" value="${currDate}" name="data" class="form-control" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon-calendar glyphicon"></span>
                                        </span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>Finrez</td>
                                <td><input class="form-control" type="text" name="finrez" value="0" /></td>
                            </tr>
                            <tr>
                                <td colspan="2" style="text-align: center;">
                                    <input class="btn btn-success btn-block" type="submit" name="addFinance" />
                                    
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <%-- Add Name Operation --%>
                <form name="formOperation" method="post">
                    <table class="table table-condensed" >
                        <thead>
                            <tr>
                                <th>Add Name Operation</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <div  style="text-align: center;" class="form-inline required">
                                        <div class="form-group has-feedback">
                                            <label class="input-group">
                                                <span class="input-group-addon">
                                                    <input type="radio" name="profits" value="1" checked="checked" />
                                                </span>
                                                <div class="form-control form-control-static">
                                                    <span class="glyphicon-plus glyphicon"></span>
                                                </div>
                                                <span class="glyphicon form-control-feedback "></span>
                                            </label>
                                        </div>
                                        <div class="form-group has-feedback ">
                                            <label class="input-group">
                                                <span class="input-group-addon">
                                                    <input type="radio" name="profits" value="-1" />
                                                </span>
                                                <div class=" form-control form-control-static ">
                                                    <span class="glyphicon-minus glyphicon " ></span>

                                                </div>
                                                <span class="glyphicon form-control-feedback "></span>
                                            </label>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="text" class="form-control" name="operationName" value="" />

                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div style="text-align: center;">
                                        <input class="btn btn-success btn-block" type="submit" name="formOperation" />
                                        
                                    </div>

                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
        </br>

        <script type="text/javascript">
            $(function () {
                var dateNow = new Date();
                $('#datetimepicker1').datetimepicker({language: 'ru', pickTime: false, defaultDate:dateNow});
                $('#datetimepicker2').datetimepicker({language: 'ru', pickTime: false, defaultDate:dateNow });
                $('#datetimepicker3').datetimepicker({language: 'ru', pickTime: false, defaultDate:dateNow});
            });
        </script>
    </body>
</html>
