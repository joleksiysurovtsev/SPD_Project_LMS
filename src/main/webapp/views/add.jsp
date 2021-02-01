<%@ page import="com.lms.spd.models.interfaces.Lecture" %>
<%@ page import="com.lms.spd.models.LectureIModel" %>
<%@ page import="com.lms.spd.models.interfaces.Literature" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!doctype html>
<!-- обьявляем тип документа -->
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
            <div class="header__logo"><img src="img/logo.png" alt="" width=50%;></div>
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
                <%! Lecture addedLecture; %>
                <%
                    if (request.getAttribute("lecture") != null) {
                        addedLecture = (LectureIModel) request.getAttribute("lecture");
                    }
                %>
                <table>
                    <thead>
                    <tr>
                        <td class="column1">ID</td>
                        <td class="column2">Type</td>
                        <td class="column3">Title</td>
                    </tr>
                    </thead>
                </table>
                <table>
                    <tbody>
                    <tr>
                        <td class="column1"><%= addedLecture.getId() %>
                        </td>
                        <td class="column2"><%= addedLecture.getType() %>
                        </td>
                        <td class="column3"><%= addedLecture.getNameOfLecture() %>
                        </td>
                    </tr>
                </table>
                <!-- openModal - id модального окна (элемента div) -->

                <a href="#openModal" class="btn btn-primary" id="show-modal">Add Book</a>
                <br>
                <br>
                <a href="#openModal2" class="btn btn-primary" id="show-modal2">Add Journal</a>
                <br>
                <br>
                <a href="#openModal3" class="btn btn-primary" id="show-modal3">Add InternetArticle</a>
            </div>
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
                <%try{ %>

                    <%

                    for (Literature lit : addedLecture.getLiteratures()) {
                        %>
                <tr>
                    <td class="col1"><%= lit.getId() %>
                    </td>
                    <td class="col2"><%= lit.getType() %>
                    </td>
                    <td class="col3"><%= lit.getTitle() %>
                    </td>
                </tr>
                    <%
                    }
                    %>
                    <%  }catch (NullPointerException e){

                } %>
            </table>
        </div>

    </div>
</div>

<div id="openModal" class="modal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">Add book</h3>
                <a href="#close" title="Close" class="close">×</a>
            </div>
            <div class="modal-body">
                <form action="/addLiterature">
                    <input type="hidden" name="id" value=<%=addedLecture.getId()%> readonly="readonly"/>
                    <label>
                        <input type="text" name="type" value=BOOK readonly="readonly"/>
                    </label>
                    <br>
                    <p><strong>Enter the title of the book</strong></p>
                    <label> <input name="title" type="text"/> </label>
                    <br>
                    <p><strong>Enter the author of the book</strong></p>
                    <label> <input name="author" type="text"/> </label>
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
                <form action="/addLiterature">
                    <input type="hidden" name="id" value=<%=addedLecture.getId()%> readonly="readonly"/>
                    <label>
                        <input type="text" name="type" value=JOURNAL_ARTICLE readonly="readonly"/>
                    </label>
                    <br>
                    <p><strong>Enter the title of the Journal</strong></p>
                    <label> <input name="title" type="text"/> </label>
                    <br>
                    <p><strong>Enter the author of the Journal</strong></p>
                    <label> <input name="author" type="text"/> </label>
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
                <form action="/addLiterature">
                    <input type="hidden" name="id" value=<%=addedLecture.getId()%> readonly="readonly"/>
                    <label>
                        <input type="text" name="type" value=INTERNET_ARTICLE readonly="readonly"/>
                    </label>
                    <br>
                    <p><strong>Enter the title of the InternetArticle</strong></p>
                    <label> <input name="title" type="text"/> </label>
                    <br>
                    <p><strong>Enter the author of the InternetArticle</strong></p>
                    <label> <input name="author" type="text"/> </label>
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