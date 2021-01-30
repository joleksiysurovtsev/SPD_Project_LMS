package com.lms.spd.servlets;


import com.lms.spd.models.LectureIModel;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.process(request, response);
    }

    /*
       generate the page showing all the request parameters
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException {

        LectureServiceImpl service = new LectureServiceImpl();
        Lecture lecture = new LectureIModel();

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/choiseupdate.jsp");

        AddLectureServlet.buildLectureModel(request, lecture);
        service.updateLecture(lecture);
        request.setAttribute("message", buildMassage(lecture));

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
