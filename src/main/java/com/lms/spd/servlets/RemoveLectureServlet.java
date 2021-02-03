package com.lms.spd.servlets;

import com.lms.spd.services.LectureServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/dell"})
public class RemoveLectureServlet extends HttpServlet {

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
        boolean resultByDellete = service.removeItem(Integer.parseInt(request.getParameter("number")));
        response.setContentType("text");
        if (!resultByDellete){
            try {
                response.sendError(404);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            response.setStatus(200);
            response.setContentType("text");
            try {
                PrintWriter writer = response.getWriter();
                writer.println(resultByDellete);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

