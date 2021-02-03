package com.lms.spd.servlets;

import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.services.LectureServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/dell_literature"})
public class RemoveLiteratureByChosenLectureServlet extends HttpServlet {

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

        String lectureID = request.getParameter("lecture_id");
        String literatureID = request.getParameter("lit_id");

        Lecture byID = service.getByID(Integer.parseInt(lectureID));
        List<Literature> literatures = byID.getLiteratures();
        literatures.removeIf(literature -> literature.getId() == Integer.parseInt(literatureID));
        byID.setLiteratures(literatures);
        service.updateLecture(byID);
        request.setAttribute("lecture", byID);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/choiseupdate.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}

