<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" 
          type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Welcome to Spitter</h1>

    <a href="<c:url value="/spitter/register" />">Register (String concatination)(look up in database again)</a>
    <br />
    <a href="<c:url value="/spitter/register_url" />">Register (URL template)(look up in database again)</a>
    <br />
    <a href="<c:url value="/spitter/register_flash" />">Register (falsh attributes)(no look up, awesome!)</a>
  </body>
</html>