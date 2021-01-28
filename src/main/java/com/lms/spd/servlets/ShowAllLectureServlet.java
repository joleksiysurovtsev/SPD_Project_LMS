package com.lms.spd.servlets;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.services.LectureServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/all"})
public class ShowAllLectureServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LectureServiceImpl service = new LectureServiceImpl();
        List<Lecture> lectures = service.getItems();

        PrintWriter writer = resp.getWriter();
        lectures.forEach(lecture -> writer.println(
                "Lecture:" + lecture.getNameOfLecture() + "\n" +
                        "Lector" + lecture.getLectorName()

        ));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
