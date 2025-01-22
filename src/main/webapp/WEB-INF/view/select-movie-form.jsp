<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Select a Movie</title>
</head>
<body>
<h1>Available Movies</h1>
<form method="post" action="/submit-selected-movie">
<%--    <ul>--%>
<%--        <c:forEach var="movie" items="${movies}">--%>
<%--            <li>--%>
<%--                <input type="radio" name="selectedMovie" value="${movie.id}" required />--%>
<%--                    ${movie.title} (${movie.genre}) - ${movie.duration} minutes--%>
<%--            </li>--%>
<%--        </c:forEach>--%>
<%--    </ul>--%>
    <button type="submit">Submit</button>
</form>
</body>
</html>
