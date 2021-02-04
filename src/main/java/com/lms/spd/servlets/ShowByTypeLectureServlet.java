package com.lms.spd.servlets;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(urlPatterns = {"/type"})
public class ShowByTypeLectureServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        LectureServiceImpl service = new LectureServiceImpl();
        LectureType type = LectureType.valueOf(req.getParameter("type"));
        List<Lecture> lectures = service.getLectureListByType(type);

        ShowByTypeAndDateLectureServlet.setRequestDispatcher(req, resp, lectures);
    }
}
