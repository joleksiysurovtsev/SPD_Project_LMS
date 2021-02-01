<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>

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
                <a class="navlink" href="../add.html">Add a new lecture</a>
                <a class="navlink" href="../remove_lecture.html">Delete a lecture by its ID</a>
                <a class="navlink" href="../choose.html">Choose a lecture by its ID</a>
            </nav>
        </div>
    </div>
</header>

<div class="intro">
    <div class="container">
        <div class="intro_inner">
            <div>
                <h2>
                    <%
                        if (request.getAttribute("message") != null) {
                            out.println(request.getAttribute("message").toString());
                        }
                    %>
                </h2>
            </div>
            <br>
            <div>
                <a class="btnflip" href="${pageContext.request.contextPath}/add.html">
                    <span class="btnflip-item btnflip__front">Come</span>
                    <span class="btnflip-item btnflip__center"></span>
                    <span class="btnflip-item btnflip__back">Back</span>
                </a>
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