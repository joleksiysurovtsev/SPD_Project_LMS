<%@ page import="com.lms.spd.models.interfaces.Lecture" %>
<%@ page import="com.lms.spd.models.LectureIModel" %>
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
    <style>
        /* стилизация содержимого страницы */
        body {
            font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            font-size: 16px;
            font-weight: 400;
            line-height: 1.5;
            color: #292b2c;
            background-color: #fff;
        }

        /* свойства модального окна по умолчанию */
        .modal {
            position: fixed; /* фиксированное положение */
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            background: rgba(0, 0, 0, 0.5); /* цвет фона */
            z-index: 1050;
            opacity: 0; /* по умолчанию модальное окно прозрачно */
            -webkit-transition: opacity 200ms ease-in;
            -moz-transition: opacity 200ms ease-in;
            transition: opacity 200ms ease-in; /* анимация перехода */
            pointer-events: none; /* элемент невидим для событий мыши */
            margin: 0;
            padding: 0;
        }

        /* при отображении модального окно */
        .modal:target {
            opacity: 1; /* делаем окно видимым */
            pointer-events: auto; /* элемент видим для событий мыши */
            overflow-y: auto; /* добавляем прокрутку по y, когда элемент не помещается на страницу */
        }

        /* ширина модального окна и его отступы от экрана */
        .modal-dialog {
            position: relative;
            width: auto;
            margin: 10px;
        }

        @media (min-width: 576px) {
            .modal-dialog {
                max-width: 500px;
                margin: 30px auto; /* для отображения модального окна по центру */
            }
        }

        /* свойства для блока, содержащего контент модального окна */
        .modal-content {
            position: relative;
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-orient: vertical;
            -webkit-box-direction: normal;
            -webkit-flex-direction: column;
            -ms-flex-direction: column;
            flex-direction: column;
            background-color: #fff;
            -webkit-background-clip: padding-box;
            background-clip: padding-box;
            border: 1px solid rgba(0, 0, 0, .2);
            border-radius: .3rem;
            outline: 0;
        }

        @media (min-width: 768px) {
            .modal-content {
                -webkit-box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
                box-shadow: 0 5px 15px rgba(0, 0, 0, .5);
            }
        }

        /* свойства для заголовка модального окна */
        .modal-header {
            display: -webkit-box;
            display: -webkit-flex;
            display: -ms-flexbox;
            display: flex;
            -webkit-box-align: center;
            -webkit-align-items: center;
            -ms-flex-align: center;
            align-items: center;
            -webkit-box-pack: justify;
            -webkit-justify-content: space-between;
            -ms-flex-pack: justify;
            justify-content: space-between;
            padding: 15px;
            border-bottom: 1px solid #eceeef;
        }

        .modal-title {
            margin-top: 0;
            margin-bottom: 0;
            line-height: 1.5;
            font-size: 1.25rem;
            font-weight: 500;
        }

        /* свойства для кнопки "Закрыть" */
        .close {
            float: right;
            font-family: sans-serif;
            font-size: 24px;
            font-weight: 700;
            line-height: 1;
            color: #000;
            text-shadow: 0 1px 0 #fff;
            opacity: .5;
            text-decoration: none;
        }

        /* свойства для кнопки "Закрыть" при нахождении её в фокусе или наведении */
        .close:focus, .close:hover {
            color: #000;
            text-decoration: none;
            cursor: pointer;
            opacity: .75;
        }

        /* свойства для блока, содержащего основное содержимое окна */
        .modal-body {
            position: relative;
            -webkit-box-flex: 1;
            -webkit-flex: 1 1 auto;
            -ms-flex: 1 1 auto;
            flex: 1 1 auto;
            padding: 15px;
            overflow: auto;
        }
    </style>
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
            <%! int ID; %>
            <%
                if (request.getAttribute("lecture") != null) {
                    Lecture addedLecture = (LectureIModel) request.getAttribute("lecture");
                    ID = addedLecture.getId();
                }
            %>
            <h2>"Lecture added assigned ID:" <label>
                <input type="number" name="id" value=<%=ID%> readonly="readonly"/>
            </label></h2>
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
                    <input type="hidden" name="id" value=<%=ID%> readonly="readonly"/>
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
                    <input type="hidden" name="id" value=<%=ID%> readonly="readonly"/>
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
                    <input type="hidden" name="id" value=<%=ID%> readonly="readonly"/>
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
            <div class="footerblok">
                <h4 class="footer_title">Contact details</h4>
                <address class="faddress">
                    <p>Ukraine, Cherkasy</p>
                    <p>тел. +38(063)744-70-55</p>
                </address>
            </div>

            <div class="footerblok">
                <img class="footericon" src="img/fbico.png" alt="">
                <img class="footericon" src="img/email.png" alt="">
                <img class="footericon" src="img/teleg.png" alt="">
            </div>

        </div>
    </div>
</div>

</body>

</html>