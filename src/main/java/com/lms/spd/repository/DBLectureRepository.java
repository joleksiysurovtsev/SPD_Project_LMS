package com.lms.spd.repository;

import com.lms.spd.enums.LectureType;
import com.lms.spd.models.LectureIModel;
import com.lms.spd.models.interfaces.Lecture;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.IRepository;
import com.lms.spd.services.LiteratureServiceImpl;
import com.lms.spd.utils.Util;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DBLectureRepository implements IRepository<Lecture> {

    private Connection connection;

    public DBLectureRepository() {
    }

    public DBLectureRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Lecture> readAll() {
        List<Lecture> customerList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM lectures")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Lecture lecture = getItem(rs);
                customerList.add(lecture);
            }
        } catch (SQLException throwable) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(throwable.getStackTrace()));
        }
        return customerList;
    }

    @Override
    public Lecture getByID(int id) {
        Lecture result = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM lectures WHERE id = (?)")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                result = getItem(rs);
            }
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    /**
     * <font color="green">✅</font>
     */
    private Lecture getItem(ResultSet rs) throws SQLException {
        LiteratureServiceImpl literatureService = new LiteratureServiceImpl();
        Lecture lecture = new LectureIModel();
        lecture.setId(rs.getInt("id"));
        lecture.setNameOfLecture(rs.getString("name_of_lecture"));
        lecture.setLectorName(rs.getString("lector_name"));
        lecture.setType(LectureType.valueOf(rs.getString("lecture_type")));
        Calendar lectureDate = new GregorianCalendar();
        Timestamp dateWasAdded = rs.getTimestamp("lecture_date");
        lectureDate.setTime(dateWasAdded);
        lecture.setLectureDate(lectureDate);
        lecture.setLiteratures(new ArrayList<>());
        List<Literature> literature = literatureService.getLiteraturesBYLectureID(rs.getInt("id"));
        lecture.setLiteratures(literature);
        lecture.setDurationOfTheLesson(rs.getInt("duration_of_lesson"));
        return lecture;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Lecture create(Lecture item) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO lectures ( name_of_lecture, " +
                        "lecture_date, " +
                        "lector_name," +
                        "lecture_type," +
                        "duration_of_lesson) VALUES ((?),(?),(?),(?),(?)) RETURNING id as rsId")) {
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
            item.setId(id);
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return item;
    }

    public int addIdMapToLiteratureToLeturesTable(int id, Integer litIDInteger) {
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO literature_to_lectures ( lect_id,lit_id) VALUES ((?),(?)) ")) {
            statement.setInt(1, id);
            statement.setInt(2, litIDInteger);
            result = statement.executeUpdate();
        } catch (SQLException throwables) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(throwables.getStackTrace()));
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
                "UPDATE lectures SET name_of_lecture = (?), " +
                        "lecture_date= (?), lector_name= (?), " +
                        "lecture_type= (?),duration_of_lesson= (?)" +
                        " WHERE id = (?) RETURNING id")) {
            getCreateUpdateLectureStatement(item, statement);
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private int getCreateUpdateLectureStatement(Lecture item, PreparedStatement statement) throws SQLException {
        statement.setString(1, item.getNameOfLecture());
        statement.setTimestamp(2, new Timestamp(item.getLectureDate().getTimeInMillis()));
        statement.setString(3, item.getLectorName());
        statement.setString(4, item.getType().toString());
        statement.setInt(5, item.getDurationOfTheLesson());
        statement.setInt(6, item.getId());
        int id = -1;
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }

    /**
     * <font color="green">✅</font>
     */
    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM lectures WHERE id = (?) RETURNING id")) {
            statement.setInt(1, id);
            deleteFromLitToLectures(id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
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
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    public static List<Literature> getListDifferences(List<Literature> litList, List<Integer> idLitList) {
        List<Literature> collect = litList.stream().filter(literature -> idLitList.contains(literature.getId()))
                .collect(Collectors.toList());
        return collect.isEmpty() ? new ArrayList<>() : collect;
    }

    @Override
    public List<Lecture> literaturesBYLectureID(int id) {
        return new ArrayList<>();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
