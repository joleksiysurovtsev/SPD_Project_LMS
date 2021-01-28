<%@ page import="java.util.List" %>
<%@ page import="com.lms.spd.models.interfaces.Lecture" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c' %>

<html>
<head>
    <title>View Lecture</title>
    <style>
        /* Стили таблицы (IKSWEB) */
        table.iksweb{text-decoration: none;border-collapse:collapse;width:auto;text-align:left;}
        table.iksweb th{font-weight:normal;font-size:22px; color:#ffffff;background-color:#4540a1;}
        table.iksweb td{font-size:15px;color:#000000;}
        table.iksweb td,table.iksweb th{white-space:pre-wrap;padding:13px 13px;line-height:15px;vertical-align: middle;border: 2px solid #1e9c20;}	table.iksweb tr:hover{background-color:#f9fafb}
        table.iksweb tr:hover td{color:#354251;cursor:default;}
    </style>
</head>

<body>
<div>
    <h1>View Lecture</h1>
</div>

<div>
    <table class="iksweb">
        <thead>
        <tr>
            <td>ID</td>
            <td>Title</td>
            <td>Lector name</td>
            <td>Lector type</td>
            <td>Lecture Date</td>
            <td>Duration Of The Lesson</td>
        </tr>
        </thead>
        <jsp:useBean id="lectures" scope="request" type="java.util.List"/>
        <tbody>
        <c:forEach items="${lectures}" var="lecture">
            <tr>
                <td>${lecture.getId()}</td>
                <td>${lecture.getNameOfLecture()}</td>
                <td>${lecture.getLectorName()}</td>
                <td>${lecture.getType()}</td>
                <td>${lecture.getLectureDate().getTime()}</td>
                <td>${lecture.getDurationOfTheLesson()}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<div>
    <button onclick="location.href='/add.html'">Back</button>
</div>
</body>
</html>