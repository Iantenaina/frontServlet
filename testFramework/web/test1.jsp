<%-- 
    Document   : test1
    Created on : May 9, 2023, 12:10:43 AM
    Author     : Iante
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%int valeurMap=(int)request.getAttribute("huhu");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%out.print(valeurMap);%></h1>
    </body>
</html>
