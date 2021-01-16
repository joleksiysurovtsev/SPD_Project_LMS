package com.lms.spd.repository;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.IRepository;
import com.lms.spd.services.LiteratureServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class DBPostgresLectureRepository implements IRepository<Lecture> {

    private Connection connection;

    public DBPostgresLectureRepository() {

    }


    public DBPostgresLectureRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * <font color="green">✅</font>
     */
    @Override
    public List<Lecture> readAll() {
        List<Lecture> customerList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM lectures")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lecture literature = getItem(rs);
                customerList.add(literature);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    /**
     * <font color="green">✅</font>
     */
    @Override
    public Lecture getByID(int id) {
        Lecture result = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM lectures WHERE lect_id = (?)")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = getItem(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <font color="green">✅</font>
     */
    private Lecture getItem(ResultSet rs) throws SQLException {
        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
        Lecture lecture = new LectureIModel();
        int lectureID = rs.getInt("lect_id");
        lecture.setId(lectureID);
        lecture.setNameOfLecture(rs.getString("name_of_lecture"));

        Calendar lectureDate = new GregorianCalendar();
        Timestamp date_was_added1 = rs.getTimestamp("lecture_date");

        lectureDate.setTime(date_was_added1);
        lecture.setLectureDate(lectureDate);

        lecture.setLectorName(rs.getString("lector_name"));
        lecture.setType(LectureType.valueOf(rs.getString("lecture_type")));
        lecture.setDurationOfTheLesson(rs.getInt("duration_of_lesson"));
        lecture.setLiteratures(getListDifferences(literatureService.getItems(), getIdListLit(lectureID)));
        return lecture;
    }


    private List<Integer> getIdListLit(int id) {
        List<Integer> customerList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT lit_id FROM literature_to_lectures where lect_id = (?)")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                customerList.add(rs.getInt("lit_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }

    @Override
    public Lecture create(Lecture item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO lectures ( name_of_lecture, " +
                        "lecture_date, " +
                        "lector_name," +
                        "lecture_type," +
                        "duration_of_lesson) VALUES ((?),(?),(?),(?),(?)) RETURNING lect_id as rsId")) {
            //вернули айдишку лекции
            int result = getCreateUpdateLectureStatement(item, statement);
            item.setId(result);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    private int getCreateUpdateLectureStatement(Lecture item, PreparedStatement statement) throws SQLException {
        statement.setString(1, item.getNameOfLecture());
        statement.setTimestamp(2, new Timestamp(item.getLectureDate().getTimeInMillis()));
        statement.setString(3, item.getLectorName());
        statement.setString(4, item.getType().toString());
        statement.setInt(5, item.getDurationOfTheLesson());
        int id = -1;
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            id = resultSet.getInt("rsId");
        }
        return id;
    }

    public int addIdMapToLiteratureToLeturesTable(int id, Integer litIDInteger) {
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO literature_to_lectures ( lect_id,lit_id) VALUES ((?),(?)) ")) {
            statement.setInt(1, id);
            statement.setInt(2, litIDInteger);
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    /**
     * <font color="red">❎</font>
     */
    @Override
    public boolean update(Lecture item) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE lectures SET name_of_lecture = (?), lecture_date= (?), lector_name= (?), lecture_type= (?),duration_of_lesson= (?) WHERE lect_id = (?) RETURNING lect_id")) {
            getCreateUpdateLectureStatement(item, statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <font color="green">✅</font>
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM lectures WHERE lect_id = (?) RETURNING lect_id")) {
            statement.setInt(1, id);
            deleteFromLitToLectures(id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * <font color="green">✅ </font>
     */
    public boolean deleteFromLitToLectures(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM literature_to_lectures WHERE lect_id = (?) RETURNING lect_id")) {
            statement.setInt(1, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Method returns the collection of products from the first collection
     * that are not in the second collection
     */
    public static List<Literature> getListDifferences(List<Literature> litList, List<Integer> idLitList) {
        return litList.stream()
                .filter(literarure -> !idLitList.contains(literarure.getId()))
                .collect(Collectors.toList());
    }


    @Override
    @Deprecated
    public List<Lecture> literaturesBYLectureID(int id) {
        return null;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
