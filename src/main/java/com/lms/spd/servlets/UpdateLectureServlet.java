package com.lms.spd.servlets;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/update"})
public class UpdateLectureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    /*
       generate the page showing all the request parameters
     */
    private void process(HttpServletRequest request, HttpServletResponse response) {
        LectureServiceImpl service = new LectureServiceImpl();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/choiseupdate.jsp");
        int id = Integer.parseInt(request.getParameter("id"));
        //получили лекцию по айди
        Lecture lectureByID = service.getByID(id);
        //пересетили все поля из реквеста нам вернулась уже изменённая лекция
        Lecture lecture1 = AddLectureServlet.buildLectureModel(request, lectureByID);

        service.updateLecture(lecture1);

        Lecture byID = service.getByID(id);

        //перетираем в реквесте нашу лекцию уже изменённую
        request.setAttribute("lecture", byID);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private String buildMassage(Lecture lecture) {
        return "ID: " + lecture.getId() + "Lecture:" + lecture.getNameOfLecture() + "\n"
                + "Lector: " + lecture.getLectorName() + "\n"
                + "Type: " + lecture.getType() + "\n"
                + "Date: " + lecture.getLectureDate().getTime() + "\n"
                + "Duration: " + lecture.getDurationOfTheLesson() + "\n";
    }
}
