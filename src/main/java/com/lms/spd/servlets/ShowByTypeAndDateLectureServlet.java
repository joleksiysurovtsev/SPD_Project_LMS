package com.lms.spd.servlets;

import com.lms.spd.enums.LectureType;
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
import java.util.Calendar;
import java.util.List;

@WebServlet(urlPatterns = {"/type_and_date"})
public class ShowByTypeAndDateLectureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LectureServiceImpl service = new LectureServiceImpl();
        LectureType type = LectureType.valueOf(req.getParameter("type"));
        Calendar calendar = Util.enterTheDateWithoutTime(req.getParameter("calendar"));
        List<Lecture> lectures = service.getLectureListByTypeAndDate(type, calendar);
        setRequestDispatcher(req, resp, lectures);
    }

    static void setRequestDispatcher(HttpServletRequest req, HttpServletResponse resp, List<Lecture> lectures) {
        RequestDispatcher requestDispatcher;
        requestDispatcher = req.getRequestDispatcher("views/viewall.jsp");
        if (lectures.isEmpty()) {
            requestDispatcher = req.getRequestDispatcher("views/viewemptyerr.jsp");
        }
        req.setAttribute("lectures", lectures);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
