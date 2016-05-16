<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head><title>Users</title></head>
<body>
<table>
<tr><th>ID</th><th>First Name</th><th>Last Name</th><th>E Mail</th></tr>
<c:forEach items="${users}" var="user">
<tr>
  <td>${user.id}</td>
  <td>${user.firstName}</td>
  <td>${user.lastName}</td>
  <td>${user.eMail}</td>
</tr>
</c:forEach>
</table>
</body>
</html>
