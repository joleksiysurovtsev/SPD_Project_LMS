package com.lms.spd.servlets;

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

@WebServlet(urlPatterns = {"/date"})
public class ShowByDateLectureServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        LectureServiceImpl service = new LectureServiceImpl();

        Calendar calendar =  Util.enterTheDateWithoutTime(req.getParameter("calendar"));
        System.out.println(calendar.getTime());
        List<Lecture> lectures = service.getLectureListByDate(calendar);
        RequestDispatcher requestDispatcher;
        if (lectures.isEmpty()) {
            requestDispatcher = req.getRequestDispatcher("views/viewemptyerr.jsp");
        } else {
            requestDispatcher = req.getRequestDispatcher("views/viewall.jsp");
        }
        req.setAttribute("lectures", lectures);
        try {
            requestDispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
