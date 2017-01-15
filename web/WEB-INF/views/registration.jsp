<%-- 
    Document   : registration
    Created on : Oct 11, 2016, 10:43:11 AM
    Author     : Sergey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <link href="css/bootstrap-datetimepicker.min.css" rel="stylesheet"/>
        <link href="css/font-awesome.min.css" rel="stylesheet" />
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript" src="js/moment-with-locales.min.js"></script>
        <script type="text/javascript" src="js/bootstrap-datetimepicker.min.js"></script>
        <script src="js/calendar_ru.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        </br>
        </br>
        </br>
        </br>
        </br>
        </br>
        <form method="post" action="registration">
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">Registration users</h3>
                            </div>
                            <div class="panel-body">
                                <span style="color: red;">${requestScope.regisrtation}</span><br/>
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Username" name="username" type="text">
                                    </div>
                                    <div class="form-group">
                                        <span style="color: red;">${requestScope.regisrtationEmail}</span>
                                        <input class="form-control" placeholder="E-mail" name="email" type="text">
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" name="password" type="password" value="">
                                    </div>

                                    <div class="col-md-6">
                                        <input class="btn btn-lg btn-success btn-block" type="submit" value="Registration">
                                    </div>
                                    <div class="col-md-6">
                                        <a class="btn btn-lg btn-danger btn-block" href=index >Cancel</a>
                                    </div>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </form>
</body>
</html>
