<%-- 
    Document   : test
    Created on : 24 mars 2023, 12:00:29
    Author     : itu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <%= request.getAttribute("nom") %>
          <%= request.getAttribute("prenom") %>
          <%= request.getAttribute("age") %>
    </body>
</html>
