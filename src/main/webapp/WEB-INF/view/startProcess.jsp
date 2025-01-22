<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Start Process</title>
</head>
<body>
<h1>Start BPMN Process</h1>

<!-- Formularz POST do uruchomienia procesu -->
<form action="${pageContext.request.contextPath}/start" method="post">
    <!-- Pole do wprowadzenia zmiennej -->
    <label for="variable">Variable:</label>
    <input type="text" id="variable" name="variable" required>
    <br><br>
    <!-- Przycisk do wysłania formularza -->
    <button type="submit">Start Process</button>
</form>
</body>
</html>
