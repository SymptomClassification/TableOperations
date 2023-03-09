package com.lancaster.spacychapters.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SpacyChapterRepository {

    private Connection con = null;

    @Value("${spring.datasource.url}")
    String dbUrl;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;

    private java.sql.Connection getDBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        return con;
    }


    public List<Map<String, String>> getChapters() {
        List<Map<String, String>> result = new ArrayList<>();
        String query = "SELECT id, name, chapterId\n" +
                "FROM spacychapters\n" +
                "WHERE id = chapterId;";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Map<String, String> chapter = new HashMap<>();
                chapter.put("id", rs.getString("id"));
                chapter.put("name", rs.getString("name"));
                result.add(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<Map<String, String>> getSubChapters() {
        List<Map<String, String>> result = new ArrayList<>();
        String query = "SELECT id, name, chapterId\n" +
                "FROM spacychapters\n" +
                "WHERE id != chapterId;";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                Map<String, String> chapter = new HashMap<>();
                chapter.put("id", rs.getString("id"));
                chapter.put("name", rs.getString("name"));
                chapter.put("chapterId", rs.getString("chapterId"));
                result.add(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public List<Map<String, String>> getSubchapterOfChapter(int id) {
        List<Map<String, String>> result = new ArrayList<>();

        String query = "SELECT sc.id, sc.name, sc.chapterId\n" +
                "FROM spacychapters c\n" +
                "JOIN spacychapters sc ON c.id = sc.chapterId\n" +
                "WHERE c.id = ? AND sc.id != ?;";

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(query);
            stm.setInt(1, id);
            stm.setInt(2, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Map<String, String> chapter = new HashMap<>();
                chapter.put("id", rs.getString("id"));
                chapter.put("name", rs.getString("name"));
                chapter.put("chapterId", rs.getString("chapterId"));
                result.add(chapter);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
