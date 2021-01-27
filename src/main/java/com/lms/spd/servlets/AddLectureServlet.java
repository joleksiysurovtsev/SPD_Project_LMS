package com.lms.spd.servlets;

import com.lms.spd.ConsoleInputValidator;
import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.enums.ConsoleMassage;
import com.lms.spd.enums.LectureType;
import com.lms.spd.error.DateFormatException;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.pgsql.JDBCConnector;
import com.lms.spd.repository.DBLectureRepository;
import com.lms.spd.repository.DBLiteratureRepository;
import com.lms.spd.repository.interfaces.IRepository;
import com.lms.spd.services.LectureServiceImpl;
import com.lms.spd.utils.Util;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@WebServlet(urlPatterns = {"/addLecture"})
public class AddLectureServlet extends HttpServlet {

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
        Lecture lecture = new LectureIModel();
        response.setStatus(200);
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        // Получить значения всех параметров запроса
        lecture.setNameOfLecture(request.getParameter("title"));
        lecture.setLectorName(request.getParameter("lector_name"));
        lecture.setType(LectureType.valueOf(request.getParameter("type")));
        String dateAndTime = request.getParameter("calendar")+" "+request.getParameter("cron");
        lecture.setLectureDate(enterTheDate(dateAndTime));
        lecture.setDurationOfTheLesson(Integer.parseInt(request.getParameter("duration")));
        Lecture lecture1 = service.addItem(lecture);
        out.println(lecture1.toString());
        out.close();
    }


    public static Calendar enterTheDate(String dateAndTime) {
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-mm-dd HH:mm");
        Calendar lectureDate = new GregorianCalendar();
        lectureDate.setTimeZone(TimeZone.getDefault());
        try {
            lectureDate.setTime(DATE_FORMAT.parse(dateAndTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return lectureDate;
    }


}
