package com.lms.spd.servlets;


import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.utils.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet(urlPatterns = {"/addLecture"})
public class AddLectureServlet extends HttpServlet {

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
        LectureServiceImpl service = new LectureServiceImpl();
        Lecture lecture = new LectureIModel();

        checkParameter(request,response);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/add.jsp");
        lecture.setNameOfLecture(request.getParameter("title"));
        lecture.setLectorName(request.getParameter("lector_name"));
        lecture.setType(LectureType.valueOf(request.getParameter("type")));
        lecture.setLectureDate(Util.enterTheDate(request.getParameter("calendar") + " " + request.getParameter("cron")));
        lecture.setDurationOfTheLesson(Integer.parseInt(request.getParameter("duration")));

        Lecture lecture1 = service.addItem(lecture);

        request.setAttribute("message", buildMassage(lecture1));

        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void checkParameter(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String[]> parameterMap = request.getParameterMap();

        parameterMap.forEach((key, value) -> {
            if (value[0].isEmpty()) {
                request.setAttribute("message", "Unsuccessful field: " + key + "is empty");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/addingErr.jsp");
                try {
                    requestDispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private String buildMassage(Lecture lecture) {
        return "ID: " + lecture.getId() + "Lecture:" + lecture.getNameOfLecture() + "\n"
                + "Lector: " + lecture.getLectorName() + "\n"
                + "Type: " + lecture.getType() + "\n"
                + "Date: " + lecture.getLectureDate().getTime() + "\n"
                + "Duration: " + lecture.getDurationOfTheLesson() + "\n";
    }
}
