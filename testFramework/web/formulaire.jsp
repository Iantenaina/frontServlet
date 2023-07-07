<%-- 
    Document   : formulaire
    Created on : May 9, 2023, 1:02:55 AM
    Author     : Iante
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="GET" action="InsertIdentite">
            <h6>NOM</h6>
            <input type="text" name="nom">
             <h6>PRENOM</h6>
            <input type="text" name="prenom">
             <h6>AGE</h6>
            <input type="text" name="age">
            <input type="submit" value="Valider">
        </form>
        
    </body>
</html>
