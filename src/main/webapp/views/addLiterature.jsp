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
            <div class="header__logo"><img src="../img/logo.png" alt="" width=50%;></div>
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
            <%! int ID; %>
            <%
                if (request.getAttribute("id") != null) {
                    ID = Integer.parseInt(request.getParameter("id"));
                }
            %>
            <form action="addLiterature.jsp">
                <h2>"Lecture added assigned ID:"
                    <label> <input type="number" name="id" value=<%=ID%> readonly="readonly"/> </label>
                </h2>
                <input type="submit" value="Add Literature"/>
                <input type="button" value="Come Back" onClick='location.href="/index.html"'>
            </form>
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