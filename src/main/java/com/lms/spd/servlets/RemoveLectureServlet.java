package com.lms.spd.servlets;

import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.pgsql.JDBCConnector;
import com.lms.spd.repository.DBLectureRepository;
import com.lms.spd.repository.DBLiteratureRepository;
import com.lms.spd.repository.interfaces.IRepository;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.utils.Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@WebServlet(urlPatterns = {"/dell"})
public class RemoveLectureServlet extends HttpServlet {

    private static void initCashes() {
        IRepository<Lecture> dbPostgresLectureRepository = new DBLectureRepository(JDBCConnector.getConnection());
        IRepository<Literature> dbPostgresLiteratureRepository = new DBLiteratureRepository(JDBCConnector.getConnection());

        LecturesCache.getInstance().setLectureRepository(dbPostgresLectureRepository);
        LiteratureCache.getInstance().setLiteratureRepository(dbPostgresLiteratureRepository);

        LecturesCache.getInstance().updateCashedLectures();
        LiteratureCache.getInstance().updateCashedLiteratures();
    }

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
        initCashes();
        LectureServiceImpl service = new LectureServiceImpl();
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        // Получить значения всех параметров запроса
        Enumeration en = request.getParameterNames();
        while (en.hasMoreElements()) {
            // Получить имя параметра запроса
            String name = (String) en.nextElement();
            if (name.equals("number")) {
                // Get the value of the request parameter
                String value = request.getParameter(name);
                int[] stringsNumberLecture = Util.getStringsNumberLecture(value);
                if (stringsNumberLecture.length > 0) {
                    boolean resultByDellete = service.removeItems(stringsNumberLecture);
                    if (resultByDellete) {
                        out.println("Lecture under ID No. " + stringsNumberLecture.toString() + " removed removed from the database");
                    } else {
                        out.println("Lecture under ID No. " + stringsNumberLecture.toString() + " missing in the database");
                    }
                }else {
                    out.println("Incorrect input");
                }
            }
        }
        out.close();
    }
}
