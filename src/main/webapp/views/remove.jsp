<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove Lecture</title>
</head>

<body>
<div>
    <h1>Remove Lecture</h1>
</div>

<div>
    <h2>
        <%
            if (request.getAttribute("message") != null) {
                out.println(request.getAttribute("message").toString());
            }
        %>
    </h2>
</div>


<div>
    <button onclick="location.href='/remove_lecture.html'">Back</button>
</div>
</body>
</html>