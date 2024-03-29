package com.lms.spd.repository;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.IRepository;
import com.lms.spd.utils.Util;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class DBLiteratureRepository implements IRepository<Literature> {

    private Connection connection;

    public DBLiteratureRepository() {
    }

    public DBLiteratureRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Literature> readAll() {
        List<Literature> customerList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM literature")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Literature literature = getItem(rs);
                customerList.add(literature);
            }
        } catch (SQLException throwables) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(throwables.getStackTrace()));
        }
        return customerList;
    }

    @Override
    public Literature getByID(int id) {
        Literature result = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM literature WHERE id = (?)")) {
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

    private Literature getItem(ResultSet rs) throws SQLException {
        Literature literature = new BookModel();
        int litid = rs.getInt("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        LiteratureType type = LiteratureType.valueOf(rs.getString("type"));
        Calendar dateResourceWasAdded = new GregorianCalendar();
        dateResourceWasAdded.setTime(rs.getDate("date_was_added"));
        switch (type) {
            case BOOK:
                literature = new BookModel();
                String genre = rs.getString("genre");
                int publishedInYear = rs.getInt("published_in_year");
                literature.setId(litid);
                literature.setTitle(title);
                literature.setAuthor(author);
                literature.setDateResourceWasAdded(dateResourceWasAdded);
                literature.setGenre(genre);
                literature.setPublishedInYear(publishedInYear);
                break;
            case JOURNAL_ARTICLE:
                literature = new JournalArticleModel();
                String titleOfArticle = rs.getString("title_of_article");
                int issueOfTheJournal = rs.getInt("issue_of_journal");
                literature.setId(litid);
                literature.setTitle(title);
                literature.setAuthor(author);
                literature.setDateResourceWasAdded(dateResourceWasAdded);
                literature.setTitleOfArticle(titleOfArticle);
                literature.setIssueOfTheJournal(issueOfTheJournal);
                break;
            case INTERNET_ARTICLE:
                literature = new InternetArticleModel();
                String urlAddress = rs.getString("url_address");
                literature.setId(litid);
                literature.setTitle(title);
                literature.setAuthor(author);
                literature.setDateResourceWasAdded(dateResourceWasAdded);
                literature.setUrlAddress(urlAddress);
                break;
            default:
                break;
        }
        return literature;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public Literature create(Literature item) {
        int result;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO literature ( type, title, author, date_was_added, " +
                        "genre, published_in_year, url_address, issue_of_journal, title_of_article) " +
                        "VALUES ( (?), (?), (?),(?),(?),(?),(?),(?),(?)) RETURNING id")) {
            statement.setString(1, item.getType().toString());
            statement.setString(2, item.getTitle());
            statement.setString(3, item.getAuthor());
            statement.setTimestamp(4, new Timestamp(item.getDateResourceWasAdded().getTimeInMillis()));
            statement.setString(5, item.getGenre());
            statement.setInt(6, item.getPublishedInYear());
            statement.setString(7, item.getUrlAddress());
            statement.setInt(8, item.getIssueOfTheJournal());
            statement.setString(9, item.getTitleOfArticle());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getInt("id");
                item.setId(result);
            }
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return item;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public boolean update(Literature item) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE literature SET " +
                        "title = (?), " +
                        "author = (?)," +
                        "date_was_added = (?)," +
                        "genre = (?)," +
                        "published_in_year = (?)," +
                        "url_address = (?)," +
                        "issue_of_journal =(?)," +
                        "title_of_article =(?) WHERE id = (?) RETURNING id")) {
            statement.setString(1, item.getTitle());
            statement.setString(2, item.getAuthor());
            statement.setDate(3, (Date) item.getDateResourceWasAdded().getTime());
            statement.setString(4, item.getGenre());
            statement.setInt(5, item.getPublishedInYear());
            statement.setString(6, item.getUrlAddress());
            statement.setInt(7, item.getIssueOfTheJournal());
            statement.setString(8, item.getTitleOfArticle());
            statement.setInt(9, item.getId());

            result = statement.executeQuery().next();
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM literature WHERE id = (?) RETURNING id")) {
            statement.setInt(1, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return result;
    }

    @Override
    public List<Literature> literaturesBYLectureID(int id) {
        List<Integer> idLitList = getListLectureId(id);
        return idLitList.stream().map(this::getByID).collect(Collectors.toList());
    }

    private List<Integer> getListLectureId(int id) {
        List<Integer> idLitList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT lit_id FROM literature_to_lectures WHERE lect_id = (?)")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                idLitList.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            Util.GLOBAL_LOGGER.info(Arrays.toString(e.getStackTrace()));
        }
        return idLitList;
    }

    @Override
    public int addIdMapToLiteratureToLeturesTable(int id, Integer integers) {
        return -1;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
