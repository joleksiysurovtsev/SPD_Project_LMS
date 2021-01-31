<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lms.spd.models.interfaces.Lecture" %>
<%@ page import="com.lms.spd.models.LectureIModel" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.lms.spd.models.interfaces.Literature" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html>

<head>
    <!-- заголовок страници, подключают стили, шрифты и так далее -->
    <!-- в первую очередь в хеде задаём кодировку -->
    <meta charset="utf-8">
    <!--Подключаем фаил с нашими стилями (style.css)-->
    <link rel="stylesheet" href="style.css">
    <!--Подключаем наши шрифты заходим на fonts.google.com в поиске выбираем шрифты которые нам нужны выбираем если нужно латиницу и кирилицу и потом копируем ссылки на шрифты -->
    <link href="https://fonts.googleapis.com/css?family=Cardo:400i|Open+Sans|Raleway:400,600,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <!--Далее заголовок-->
    <title>LMS</title>
</head>

<body>
<!--в бади пишем основной код который мы видем на странице-->
<!--берём расписываем шапку-->
<!--Создали хедер (класс хедер) в нём создаём контейнер,
    в котором определяем саму шапочку header__inner,
    в иннере расписываем лого и кнопки навигации -->
<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="header__logo"><img src="img/logo.png" alt=""></div>
            <nav class="nav">
                <a class="navlink" href="lectnav.html">Display lectures</a>
                <a class="navlink" href="add.html">Add a new lecture</a>
                <a class="navlink" href="remove_lecture.html">Delete a lecture by its ID</a>
                <a class="navlink" href="choose.html">Choose a lecture by its ID</a>
            </nav>
        </div>
    </div>
</header>

<!--Интро-->


<div class="intro">
    <div class="container">
        <div class="intro_inner">
            <%--    Left Block        --%>
            <div class="block-left">
                <form action="/update">
                    <%! int ID; %>
                    <%! String title = "Empty"; %>
                    <%! String lectorName = "Empty"; %>
                    <%! String type; %>
                    <%! int durationOfTheLesson; %>
                    <%! String date; %>
                    <%! String time; %>
                    <%! List<Literature> literature; %>
                    <%
                        if (request.getAttribute("lecture") != null) {
                            Lecture lecture = (LectureIModel) request.getAttribute("lecture");
                            ID = lecture.getId();
                            title = lecture.getNameOfLecture();
                            lectorName = lecture.getNameOfLecture();
                            type = lecture.getType().toString();
                            durationOfTheLesson = lecture.getDurationOfTheLesson();
                            Calendar lectureDate = lecture.getLectureDate();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm");
                            date = dateFormat.format(lectureDate.getTime());
                            time = dateFormatTime.format(lectureDate.getTime());
                            literature = lecture.getLiteratures();
                        }
                    %>

                    <p><strong>Lectures ID</strong></p>
                    <label><input type="text" name="comment" size="20" value=<%=ID%> readonly></label>
                    <br>
                    <br>
                    <p><strong>Enter the title of the lecture</strong></p>
                    <label> <input name="title" maxlength="50" size="50" value=<%=title%>/> </label>
                    <br>
                    <br>
                    <p><strong>Enter the lecturer name</strong></p>
                    <p><label><input name="lector_name" maxlength="50" size="50" value=<%=lectorName%>/></label></p>
                    <p><strong>Please, choose lecture type:</strong></p>
                    <label>
                        <select name="type">
                            <option value<%=type%>><%=type%>
                            </option>
                            <option value="JAVA_CORE">Java Core</option>
                            <option value="JAVA_CONCURRENCY">Java Concurrency</option>
                            <option value="DB">Database</option>
                            <option value="EE">Java Enterprise Edition</option>
                            <option value="COMMON">Java Common</option>
                            <option value="SOFT_SKILLS">Soft skills</option>
                            <option value="TECH_SKILLS">Tech skills</option>
                            <option value="CAREER">Career</option>
                        </select>
                    </label>
                    <br>
                    <br>
                    <p><strong> Please, choose lecture date and time: </strong></p>
                    <label>
                        <input type="date" name="calendar" value=<%=date%>>
                    </label>

                    <label>
                        <input type="time" name="cron" value=<%=time%>>
                    </label>

                    <br>
                    <br>
                    <p><strong>Please enter the lecture duration</strong></p>
                    <p><label><input name="duration" maxlength="50" size="50" value=<%=durationOfTheLesson%>></label>
                    </p>
                    <input type="submit" value="Update lecture Lecture"/>
                </form>
            </div>
            <div class="block-right">
                <p><strong>Lectures literature</strong></p>
                <table>
                    <thead>
                    <tr>
                        <td class="col1">ID</td>
                        <td class="col2">Type</td>
                        <td class="col3">Title</td>
                    </tr>
                    </thead>
                </table>
                <table>
                    <tbody>
                    <jsp:useBean id="literature" scope="request" type="java.util.List"/>
                    <c:forEach items="${literature}" var="literature">
                    <tr>
                        <td class="col1">${literature.getID()}</td>
                        <td class="col2">${literature.getType()()}</td>
                        <td class="col3">${literature.getTitle()}</td>
                    </tr>
                    </c:forEach>
                </table>

            </div>

        </div>
    </div>
</div>

<div class="footer">
    <div class="conteiner">
        <div class="footer_inner">
            <div class="footer_blok">
                <h4 class="footer_title">Contact details</h4>
                <address class="faddress">
                    <p>Ukraine, Cherkasy</p>
                    <p>тел. +38(063)744-70-55</p>
                </address>
            </div>

            <div class="footer_blok">
                <img class="footericon" src="img/fbico.png" alt="">
                <img class="footericon" src="img/email.png" alt="">
                <img class="footericon" src="img/teleg.png" alt="">
            </div>

        </div>
    </div>
</div>

</body>

</html>