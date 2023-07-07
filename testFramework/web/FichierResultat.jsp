<%-- 
    Document   : FichierResultat
    Created on : Jul 7, 2023, 7:43:42 PM
    Author     : aris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <p>Bytes: <%= request.getAttribute("bytes") %></p>
        <p>File name: <%= request.getAttribute("fileName") %></p>
    </body>
</html>
