package com.lancaster.symptomsubtitle.repository;

import com.lancaster.symptomsubtitle.model.Subtitle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SubtitleRepository {

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

    public List<Subtitle> fetchSubtitles() {
        List<Subtitle> subtitles = new ArrayList<>();
        Subtitle subtitle;
        String selectAll = "SELECT * FROM subtitles";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                subtitle =
                        new Subtitle(rs.getInt("id"), rs.getInt("chapterId"), rs.getString("name"));
                subtitles.add(subtitle);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subtitles;

    }

    public void saveSubtitle(Subtitle subtitle) {
        String create = "INSERT INTO subtitles (id, chapterId, name ) " +
                " VALUES (?, ?, ?)";

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create);
            stm.setInt(1, subtitle.getId());
            stm.setInt(2, subtitle.getChapterId());
            stm.setString(3, subtitle.getName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSubtitle(int id) {
        String delete = "DELETE FROM subtitles WHERE id = ?";

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(delete);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Optional<Subtitle> fetchSubtitleWithId(int id) {
        String select = "SELECT * FROM subtitles WHERE id = ?";
        Subtitle subtitle = null;

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(select);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                subtitle =
                        new Subtitle(rs.getInt("id"), rs.getInt("chapter_id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(subtitle);
    }

    public Optional<Subtitle> updateSubtitle(Subtitle subtitle, int id) {
        Optional<Subtitle> sub = fetchSubtitleWithId(id);
        String update = "UPDATE subtitles SET chapterId = ?, name = ? WHERE id = ?";

        if (!sub.isPresent()) {
            return Optional.empty();
        }

        try (PreparedStatement stm = getDBConnection().prepareStatement(update)) {
            stm.setInt(1, subtitle.getChapterId());
            stm.setString(2, subtitle.getName());
            stm.setInt(3, subtitle.getId());
            stm.executeUpdate();
            sub = fetchSubtitleWithId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sub;
    }

    public Optional<Subtitle> fetchSubtitleWithName(String name) {
        String select = "SELECT * FROM subtitles WHERE name = ?";
        Subtitle subtitle = null;

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(select);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                subtitle =
                        new Subtitle(rs.getInt("id"), rs.getInt("chapterId"), rs.getString("name"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(subtitle);
    }
}
