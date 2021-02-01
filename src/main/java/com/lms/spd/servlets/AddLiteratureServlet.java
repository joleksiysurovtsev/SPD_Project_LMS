package com.lms.spd.servlets;


import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.services.LiteratureServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/addLiterature"})
public class AddLiteratureServlet extends HttpServlet {
    private LiteratureServiceImpl service = new LiteratureServiceImpl();
    private LectureServiceImpl serviceLecture = new LectureServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    /*
       generate the page showing all the request parameters
     */
    private void process(HttpServletRequest request, HttpServletResponse response) {
        LiteratureType type = LiteratureType.valueOf(request.getParameter("type"));
        Literature literature = null;
        switch (type) {
            case BOOK:
                literature = buildBook(request);
                break;
            case JOURNAL_ARTICLE:
                literature = buildJournal(request);
                break;
            case INTERNET_ARTICLE:
                literature = buildInternetArticle(request);
                break;
            default:
                break;
        }
        Lecture lecture = updateDB(request, literature);
        request.setAttribute("lecture", lecture);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/add.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private Lecture updateDB(HttpServletRequest request, Literature literature) {
        Literature addingLit = service.addItem(literature);
        Lecture lecture = serviceLecture.getByID(Integer.parseInt(request.getParameter("id")));
        List<Literature> literatures = lecture.getLiteratures();
        if (literatures == null){
            literatures = new ArrayList<>();
        }
        literatures.add(addingLit);
        lecture.setLiteratures(literatures);
        serviceLecture.updateLecture(lecture);
        serviceLecture.addLinkLiteratureLectures(lecture.getId(), addingLit.getId());
        return lecture;
    }

    private Literature buildInternetArticle(HttpServletRequest request) {
        Literature literature = new InternetArticleModel();
        literature.setAuthor(request.getParameter("author"));
        literature.setTitle(request.getParameter("title"));
        literature.setUrlAddress(request.getParameter("urlAddress"));
        return literature;
    }

    private Literature buildJournal(HttpServletRequest request) {
        Literature literature = new JournalArticleModel();
        literature.setAuthor(request.getParameter("author"));
        literature.setTitle(request.getParameter("title"));
        literature.setTitleOfArticle(request.getParameter("titleOfArticle"));
        literature.setIssueOfTheJournal(Integer.parseInt(request.getParameter("issueOfTheJournal")));
        return literature;
    }

    public Literature buildBook(HttpServletRequest request) {
        Literature literature = new BookModel();
        literature.setAuthor(request.getParameter("author"));
        literature.setTitle(request.getParameter("title"));
        literature.setGenre(request.getParameter("genre"));
        literature.setPublishedInYear(Integer.parseInt(request.getParameter("publishedInYear")));
        return literature;
    }
}