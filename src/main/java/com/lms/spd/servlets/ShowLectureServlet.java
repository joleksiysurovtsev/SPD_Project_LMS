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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/all"})
public class ShowLectureServlet extends HttpServlet {

    private static void initCashes() {
        IRepository<Lecture> dbPostgresLectureRepository = new DBLectureRepository(JDBCConnector.getConnection());
        IRepository<Literature> dbPostgresLiteratureRepository = new DBLiteratureRepository(JDBCConnector.getConnection());

        LecturesCache.getInstance().setLectureRepository(dbPostgresLectureRepository);
        LiteratureCache.getInstance().setLiteratureRepository(dbPostgresLiteratureRepository);

        LecturesCache.getInstance().updateCashedLectures();
        LiteratureCache.getInstance().updateCashedLiteratures();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        initCashes();
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