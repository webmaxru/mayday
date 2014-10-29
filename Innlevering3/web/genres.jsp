<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Music recommendations</title>
  </head>
  <body>
  <form action="/albums" method="get">

      <h3>Choose a music genre you like:</h3>
      <select name="genre">
          <c:forEach items="${genres}" var="genre">
              <option value="${genre}">${genre}</option>
          </c:forEach>

      </select>
      <input type="submit" value="Send" />
  </form>
  </body>
</html>
