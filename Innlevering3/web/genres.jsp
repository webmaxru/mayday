<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>Music Chooser</title>
    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="main.css" />
</head>

<body>

    <div class="wrapper">

        <div class="main">

            <header>
                <h1>Music Chooser</h1>
                <h2>Select your favorite genre</h2>
            </header>

            <form action="/albums" method="get">
                <select name="genre">
                
                <c:forEach items="${genres}" var="genre">
                    <option value="${genre}">${genre}</option>
                </c:forEach>
          
                </select>
                <input type="submit" value="Go!" />
                
            </form>
            
        </div>

        <footer>
            Laget av Ekaterina Orlova
        </footer>

    </div>

</body>

</html>
