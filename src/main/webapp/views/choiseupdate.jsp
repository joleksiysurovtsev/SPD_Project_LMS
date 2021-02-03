<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.lms.spd.models.interfaces.Lecture" %>
<%@ page import="com.lms.spd.models.LectureIModel" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.lms.spd.models.interfaces.Literature" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="../style.css">
    <link href="https://fonts.googleapis.com/css?family=Cardo:400i|Open+Sans|Raleway:400,600,700&display=swap&subset=cyrillic-ext"
          rel="stylesheet">
    <title>LMS</title>
</head>

<body>
<header class="header">
    <div class="container">
        <div class="header__inner">
            <div class="header__logo"><img src="../img/logo.png" alt=""></div>
            <nav class="nav">
                <a class="navlink" href="../view_lectures.html">Display lectures</a>
                <a class="navlink" href="../add_lecture.html">Add a new lecture</a>
                <a class="navlink" href="../remove_lecture.html">Delete a lecture by its ID</a>
                <a class="navlink" href="../choose.html">Choose a lecture by its ID</a>
            </nav>
        </div>
    </div>
</header>
<div class="intro">
    <div class="container">
        <div class="intro_inner">
            <div class="block-left">
                <form method="post" action="${pageContext.request.contextPath}update">
                    <%! int ID;
                        String title;
                        String lectorName;
                        String type;
                        int durationOfTheLesson;
                        String date;
                        String time;
                        List<Literature> literature; %>
                    <%
                        if (request.getAttribute("lecture") != null) {
                            Lecture lecture = (LectureIModel) request.getAttribute("lecture");
                            ID = lecture.getId();
                            title = lecture.getNameOfLecture();
                            lectorName = lecture.getLectorName();
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
                    <p><label> <input type="number" name="id" value=<%=ID%> readonly> </label></p>

                    <p><strong>Enter the title of the lecture</strong></p>
                    <p><label><input name="title" value=<%=title%>></label></p>

                    <p><strong>Enter the lecturer name</strong></p>
                    <p><label><input name="lector_name" value=<%=lectorName%>></label></p>

                    <p><strong>Please, choose lecture type:</strong></p>
                    <p>
                        <label><select name="type">
                            <option value<%=type%>><%=type%>
                            </option>
                            <option value="JAVA_CORE">JAVA_CORE</option>
                            <option value="JAVA_CONCURRENCY">JAVA_CONCURRENCY</option>
                            <option value="DB">DB</option>
                            <option value="EE">EE</option>
                            <option value="COMMON">COMMON</option>
                            <option value="SOFT_SKILLS">SOFT_SKILLS</option>
                            <option value="TECH_SKILLS">TECH_SKILLS</option>
                            <option value="CAREER">CAREER</option>
                        </select>
                        </label>
                    </p>

                    <p><strong> Please, choose lecture date and time: </strong></p>
                    <p><label> <input type="date" name="calendar" value=<%=date%>> </label></p>
                    <label><input type="time" name="cron" value=<%=time%>></label>

                    <p><strong>Please enter the lecture duration</strong></p>
                    <p><label><input name="duration" value=<%=durationOfTheLesson%>></label></p>
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
                        <td class="col4">Remove</td>
                    </tr>
                    </thead>
                </table>
                <table>
                    <tbody>
                        <%
                    for (Literature lit : literature) {
                        %>
                    <tr>

                        <td class="col1"><%= lit.getId() %>
                        </td>
                        <td class="col2"><%= lit.getType() %>
                        </td>
                        <td class="col3"><%= lit.getTitle() %>
                        </td>
                        <td class="col4">
                            <form action="${pageContext.request.contextPath}/dell_literature">
                                <label><input hidden name="lit_id" type="text" value="<%= lit.getId() %>"/></label>
                                <label><input hidden name="lecture_id" type="text" value="<%= ID %>"/></label>
                                <input type="submit" value="Dell Literature"/>
                            </form>
                        </td>
                    </tr>
                        <%
                    }
                    %>
                </table>


                <a href="#openModal" class="btn btn-primary" id="show-modal">Add Book</a>
                <br>
                <br>
                <a href="#openModal2" class="btn btn-primary" id="show-modal2">Add Journal</a>
                <br>
                <br>
                <a href="#openModal3" class="btn btn-primary" id="show-modal3">Add InternetArticle</a>

                <div id="openModal" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Add book</h3>
                                <a href="#close" title="Close" class="close">×</a>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="${pageContext.request.contextPath}addLiterature">
                                    <input type="hidden" name="id" value=<%=ID%> readonly="readonly"/>
                                    <label>
                                        <input type="text" name="lit_type" value=BOOK readonly="readonly"/>
                                    </label>
                                    <br>
                                    <p><strong>Enter the title of the book</strong></p>
                                    <label> <input name="lit_title" type="text"/> </label>
                                    <br>
                                    <p><strong>Enter the author of the book</strong></p>
                                    <label> <input name="lit_author" type="text"/> </label>
                                    <br>
                                    <br>
                                    <p><strong>Enter the genre of the book</strong></p>
                                    <label> <input name="genre" type="text"/> </label>
                                    <br>
                                    <br>
                                    <p><strong>Enter the publishedInYear of the book</strong></p>
                                    <label> <input name="publishedInYear" type="text"/> </label>
                                    <br>
                                    <input type="submit" value="Add Book"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>


                <div id="openModal2" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Add Journal</h3>
                                <a href="#close" title="Close" class="close">×</a>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="${pageContext.request.contextPath}addLiterature">
                                    <input type="hidden" name="id" value=<%=ID%> readonly="readonly"/>
                                    <label>
                                        <input type="text" name="lit_type" value=JOURNAL_ARTICLE readonly="readonly"/>
                                    </label>
                                    <br>
                                    <p><strong>Enter the title of the Journal</strong></p>
                                    <label> <input name="lit_title" type="text"/> </label>
                                    <br>
                                    <p><strong>Enter the author of the Journal</strong></p>
                                    <label> <input name="lit_author" type="text"/> </label>
                                    <br>
                                    <br>
                                    <p><strong>Enter the titleOfArticle of the Journal</strong></p>
                                    <label> <input name="titleOfArticle" type="text"/> </label>
                                    <br>
                                    <br>
                                    <p><strong>Enter the issueOfTheJournal of the Journal</strong></p>
                                    <label> <input name="issueOfTheJournal" type="text"/> </label>
                                    <br>
                                    <input type="submit" value="Add Journal"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div id="openModal3" class="modal">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h3 class="modal-title">Add InternetArticle</h3>
                                <a href="#close" title="Close" class="close">×</a>
                            </div>
                            <div class="modal-body">
                                <form method="post" action="${pageContext.request.contextPath}addLiterature">
                                    <input type="hidden" name="id" value=<%=ID%> readonly="readonly"/>
                                    <label>
                                        <input type="text" name="lit_type" value=INTERNET_ARTICLE readonly="readonly"/>
                                    </label>
                                    <br>
                                    <p><strong>Enter the title of the InternetArticle</strong></p>
                                    <label> <input name="lit_title" type="text"/> </label>
                                    <br>
                                    <p><strong>Enter the author of the InternetArticle</strong></p>
                                    <label> <input name="lit_author" type="text"/> </label>
                                    <br>
                                    <br>
                                    <p><strong>Enter the urlAddress of the InternetArticle</strong></p>
                                    <label> <input name="urlAddress" type="text"/> </label>
                                    <br>
                                    <br>
                                    <input type="submit" value="Add Journal"/>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="footer">
    <div class="container">
        <div class="footer_inner">
            <div class="footer_block">
                <h4 class="footer_title">Contact details</h4>
                <address class="faddress">
                    <p>Ukraine, Cherkasy</p>
                    <p>тел. +38(063)744-70-55</p>
                </address>
            </div>
            <div class="footer_block">
                <a href="https://www.facebook.com/settings?tab=account&section=username&view">
                    <img class="footer_icon" src="../img/fb_icon.png" alt=""> </a>
                <a href="mailto:joleksiysurovtsev@gmail.com">
                    <img class="footer_icon" src="../img/email.png" alt=""></a>
                <a href="https://t.me/SurovcevAlexsey">
                    <img class="footer_icon" src="../img/telegram.png" alt=""></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>


