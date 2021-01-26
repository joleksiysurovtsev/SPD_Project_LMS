package com.lms.spd;

import com.lms.spd.cashes.LecturesCache;
import com.lms.spd.cashes.LiteratureCache;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.pgsql.JDBCConnector;
import com.lms.spd.repository.DBLectureRepository;
import com.lms.spd.repository.DBLiteratureRepository;
import com.lms.spd.repository.interfaces.IRepository;
import com.lms.spd.terminal.LMSTerminal;

public class Main {


    public static void main(String[] args) {
        initCashes();
        LMSTerminal.startLMS();

    }



    private static void initCashes() {
        IRepository<Lecture> dbPostgresLectureRepository = new DBLectureRepository(JDBCConnector.getConnection());
        IRepository<Literature> dbPostgresLiteratureRepository = new DBLiteratureRepository(JDBCConnector.getConnection());

        LecturesCache.getInstance().setLectureRepository(dbPostgresLectureRepository);
        LiteratureCache.getInstance().setLiteratureRepository(dbPostgresLiteratureRepository);

        LecturesCache.getInstance().updateCashedLectures();
        LiteratureCache.getInstance().updateCashedLiteratures();
    }

}
