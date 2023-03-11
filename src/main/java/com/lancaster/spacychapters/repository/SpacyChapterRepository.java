package com.lancaster.spacychapters.repository;

import com.lancaster.databaseaccess.DatabaseConnector;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SpacyChapterRepository {

    public List<Map<String, String>> getChapters() {
        List<Map<String, String>> result = new ArrayList<>();
        String query = "SELECT id, name, chapterId\n" +
                "FROM spacychapters\n" +
                "WHERE id = chapterId;";

        try {
            Statement stm = DatabaseConnector.getDBConnection().createStatement();
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
            Statement stm = DatabaseConnector.getDBConnection().createStatement();
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
            PreparedStatement stm = DatabaseConnector.getDBConnection().prepareStatement(query);
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
