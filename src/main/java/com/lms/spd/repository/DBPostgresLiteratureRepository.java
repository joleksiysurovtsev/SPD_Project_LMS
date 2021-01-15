package com.lms.spd.repository;

import com.lms.spd.enums.LiteratureType;
import com.lms.spd.models.BookModel;
import com.lms.spd.models.InternetArticleModel;
import com.lms.spd.models.JournalArticleModel;
import com.lms.spd.models.interfaces.Literature;
import com.lms.spd.repository.interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DBPostgresLiteratureRepository implements IRepository<Literature> {

    private Connection connection;

    public DBPostgresLiteratureRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Literature> readAll() {
        List<Literature> customerList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM literature")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Literature literature = getItem(rs);
                customerList.add(literature);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customerList;
    }


    @Override
    public Literature getByID(int id) {
        Literature result = null;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM literature WHERE literature.lit_id = (?)")) {
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

    private Literature getItem(ResultSet rs) throws SQLException {
        Literature literature = null;
        int litid = rs.getInt("id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        LiteratureType type = LiteratureType.valueOf(rs.getString("type"));
        Calendar dateResourceWasAdded = new GregorianCalendar();
        dateResourceWasAdded.setTime(rs.getDate("date_was_added"));
        switch (type) {
            case BOOK:
                String genre = rs.getString("genre");
                int publishedInYear = rs.getInt("published_in_year");
                literature = new BookModel(litid, title, author, type, dateResourceWasAdded, genre, publishedInYear);
                break;
            case JOURNAL_ARTICLE:
                String titleOfArticle = rs.getString("title_of_article");
                int issueOfTheJournal = rs.getInt("issue_of_journal");
                literature = new JournalArticleModel(litid, title, author, type, dateResourceWasAdded, titleOfArticle, issueOfTheJournal);
                break;
            case INTERNET_ARTICLE:
                String urlAddress = rs.getString("url_address");
                literature = new InternetArticleModel(litid, title, author, type, dateResourceWasAdded, urlAddress);
                break;
            default:
                break;
        }
        return literature;
    }

    @Override
    public Literature create(Literature item) {
        int result = -1;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO literature (lit_id, type, title, author, date_was_added, genre, published_in_year, url_address, issue_of_journal, title_of_article) VALUES (DEFAULT, (?), (?), (?),(?),(?),(?),(?),(?),(?)) RETURNING lit_id")) {
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
                result = resultSet.getInt("lit_id");
                item.setId(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

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
                        "title_of_article =(?) WHERE lit_id = (?) RETURNING lit_id")) {
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
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM literature WHERE lit_id = (?) RETURNING lit_id")) {
            statement.setInt(1, id);
            result = statement.executeQuery().next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    @Deprecated
    public void addIdMapToLiteratureToLeturesTable(int id, List<Integer> integers) {

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
