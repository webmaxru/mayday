<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<body>
    <!--Using genre field from database because of security issues (not a user input attribute)-->
<h1 align="center"><c:out value=" Genre:  ${albums.get(0).getGenre()}"></c:out>

</h1>
<table>

    <c:forEach items="${albums}" var="album">
        <tr>
            <td>${album.getArtist()} </td>
            <td>${album.getTitle()} </td>
            <td>${album.getTracks()} </td>
            <td>${album.getReleasedYear()} </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
