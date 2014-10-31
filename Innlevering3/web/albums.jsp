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
                <!--Using genre field from database because of security issues (not a user input attribute)-->
                <h1><c:out value="Genre:  ${albums.get(0).getGenre()}"></c:out></h1>
            </header>

            <div class="albums">

                <c:forEach items="${albums}" var="album">
                
                    <div class="album clearfix">
                        <div class="title">
                            <h2>${album.getTitle()} <span class="year">(${album.getReleasedYear()})</span></h2>
                            <h3>${album.getArtist()}</h3>
                        </div>
                        <div class="tracks">${album.getTracks()} tracks</div>
                    </div>                    

                </c:forEach>
                
            </div>

            <h3><a href="/">Select another genre</a></h3>

        </div>

        <footer>
            Laget av Ekaterina Orlova
        </footer>

    </div>

</body>

</html>
