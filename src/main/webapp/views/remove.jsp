<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<!-- обьявляем тип документа -->
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
                <a class="btnflip" href="${pageContext.request.contextPath}/remove_lecture.html">
                    <span class="btnflip-item btnflip__front">Come</span>
                    <span class="btnflip-item btnflip__center"></span>
                    <span class="btnflip-item btnflip__back">Back</span>
                </a>
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

