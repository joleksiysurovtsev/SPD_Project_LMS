package com.lms.spd.servlets;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.utils.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.List;

@WebServlet(urlPatterns = {"/date"})
public class ShowByDateLectureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LectureServiceImpl service = new LectureServiceImpl();
        Calendar calendar = Util.enterTheDateWithoutTime(req.getParameter("calendar"));
        List<Lecture> lectures = service.getLectureListByDate(calendar);
        ShowByTypeAndDateLectureServlet.setRequestDispatcher(req, resp, lectures);
    }
}
