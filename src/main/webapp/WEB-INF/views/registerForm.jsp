<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>
    <title>Spitter</title>
    <link rel="stylesheet" type="text/css" 
          href="<c:url value="/resources/style.css" />" >
  </head>
  <body>
    <h1>Register</h1>

    <form method="POST">
      First Name: <input type="text" name="firstName" value="myFirstName"/><br/>
      Last Name: <input type="text" name="lastName" value="myLastName"/><br/>
      Email: <input type="email" name="email" value="myEmail@mail.com"/><br/>
      Username: <input type="text" name="username" value="myUsername"/><br/>
      Password: <input type="password" name="password" value="myPassword"/><br/>
      <input type="submit" value="Register" />
    </form>
  </body>
</html>
