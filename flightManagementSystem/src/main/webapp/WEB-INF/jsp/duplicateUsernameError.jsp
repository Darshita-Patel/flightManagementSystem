<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Username Error</title>
</head>
<body>
<div >
<script type="text/javascript">
alert("Entered Username already exists. Please choose a different one.");
window.location.href = "/signup";
</script>
</div>
</body>
</html>