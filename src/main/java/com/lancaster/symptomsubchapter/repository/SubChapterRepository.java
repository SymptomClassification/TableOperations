package com.lancaster.symptomsubchapter.repository;

import com.lancaster.symptomsubchapter.model.SubChapter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubChapterRepository {
    private Connection con = null;

    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    private Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }

    public int saveSubchapter(SubChapter subChapter) throws SQLException {
        String create = "INSERT INTO subchapters (name, chapterId) " +
                " SELECT ?, ? FROM DUAL " +
                " WHERE NOT EXISTS (SELECT id FROM subchapters WHERE name = ? AND chapterId = ?)";

        PreparedStatement stm = getDBConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
        stm.setString(1, subChapter.getName());
        stm.setInt(2, subChapter.getChapterId());
        stm.setString(3, subChapter.getName());
        stm.setInt(4, subChapter.getChapterId());
        int affectedRows = stm.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Creating subchapter failed, subchapter with the same name and chapter already exists.");
        }
        ResultSet rs = stm.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1);
        } else {
            throw new SQLException("Creating subchapter failed, no ID obtained.");
        }
    }

    public List<SubChapter> selectSubChaptersForChapters(String chapterName) {
        List<SubChapter> subChapters = new ArrayList<>();
        String select = "SELECT * FROM subchapters, chapter WHERE subchapters.chapterId = chapter.id AND chapter.name = '" + chapterName + "'";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);
            while (rs.next()) {
                SubChapter subChapter = new SubChapter();
                subChapter.setId(rs.getInt("id"));
                subChapter.setName(rs.getString("name"));
                subChapter.setChapterId(rs.getInt("chapterId"));
                subChapters.add(subChapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subChapters;
    }


    public Optional<SubChapter> fetchSubChapterWithId(int id) {
        Optional<SubChapter> sub;
        SubChapter subChapter = new SubChapter();
        String select = "SELECT * FROM subchapters WHERE id = '" + id + "'";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);
            while (rs.next()) {
                subChapter.setId(rs.getInt("id"));
                subChapter.setName(rs.getString("name"));
                subChapter.setChapterId(rs.getInt("chapterId"));
            }
            sub = Optional.of(subChapter);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sub;
    }

    public Optional<SubChapter> updateSubChapter(SubChapter subChapter, int id) {
        Optional<SubChapter> sub = fetchSubChapterWithId(id);
        String sql = "UPDATE subchapters SET name = ?, chapterId = ? WHERE id = ?";

        if (!sub.isPresent()) {
            return Optional.empty();
        }

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            stm.setString(1, subChapter.getName());
            stm.setInt(2, subChapter.getChapterId());
            stm.setInt(3, id);
            stm.executeUpdate();
            sub = fetchSubChapterWithId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sub;
    }

    public Optional<SubChapter> fetchSubChapterWithName(String name) {
        Optional<SubChapter> ch = Optional.empty();
        SubChapter subChapter = new SubChapter();
        String select = "SELECT * FROM subchapters WHERE name = '" + name + "'";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);

            while (rs.next()) {
                subChapter.setId(rs.getInt("id"));
                subChapter.setName(rs.getString("name"));
                subChapter.setChapterId(rs.getInt("chapterId"));
                ch = Optional.of(subChapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ch;
    }

    public List<SubChapter> fetchSubChapters() {
        List<SubChapter> chapters = new ArrayList<>();
        SubChapter subChapter;
        String selectAll = "SELECT * FROM subchapters";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                subChapter = new SubChapter();
                subChapter.setId(rs.getInt("id"));
                subChapter.setName(rs.getString("name"));
                subChapter.setChapterId(rs.getInt("chapterId"));
                chapters.add(subChapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chapters;
    }
}
