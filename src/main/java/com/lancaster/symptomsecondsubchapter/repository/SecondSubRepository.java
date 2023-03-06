package com.lancaster.symptomsecondsubchapter.repository;

import com.lancaster.symptomsecondsubchapter.model.SecondSub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SecondSubRepository {
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

    public List<SecondSub> fetchSecondSubs() {
        String sql = "SELECT * FROM secondsubchapters";
        List<SecondSub> secondSubs = new ArrayList<>();
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                SecondSub secondSub = new SecondSub();
                secondSub.setId(rs.getInt("id"));
                secondSub.setSubId(rs.getInt("subId"));
                secondSub.setMajor(rs.getString("major"));
                secondSub.setName(rs.getString("name"));
                secondSubs.add(secondSub);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return secondSubs;
    }

    public void saveSecondSub(SecondSub secondSub) {
        String sql = "INSERT INTO secondsubchapters (subId, major, name) VALUES (?, ?, ?)";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            stm.setInt(1, secondSub.getSubId());
            stm.setString(2, secondSub.getMajor());
            stm.setString(3, secondSub.getName());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSecondSub(int id) {
        String sql = "DELETE FROM secondsubchapters WHERE id = ?";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<SecondSub> fetchSecondSubWithId(int id) {
        Optional<SecondSub> sub;
        SecondSub secondSub = new SecondSub();
        String sql = "SELECT * FROM secondsubchapters WHERE id = ?";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                secondSub.setId(rs.getInt("id"));
                secondSub.setSubId(rs.getInt("subId"));
                secondSub.setMajor(rs.getString("major"));
                secondSub.setName(rs.getString("name"));
            }
            sub = Optional.of(secondSub);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sub;
    }

    public Optional<SecondSub> updateSecondSub(SecondSub secondSub, int id) {
        Optional<SecondSub> sub = fetchSecondSubWithId(id);

        if (!sub.isPresent()) {
            return Optional.empty();
        }

        String sql = "UPDATE secondsubchapters SET subId = ?, major = ?, name = ? WHERE id = ?";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            stm.setInt(1, secondSub.getSubId());
            stm.setString(2, secondSub.getMajor());
            stm.setString(3, secondSub.getName());
            stm.setInt(4, id);
            stm.executeUpdate();
            sub = fetchSecondSubWithId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return sub;

    }

    public Optional<SecondSub> fetchSecondSubWithName(String name) {
        Optional<SecondSub> sub;
        SecondSub secondSub = new SecondSub();
        String sql = "SELECT * FROM secondsubchapters WHERE name = ?";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(sql);
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                secondSub.setId(rs.getInt("id"));
                secondSub.setSubId(rs.getInt("subId"));
                secondSub.setMajor(rs.getString("major"));
                secondSub.setName(rs.getString("name"));
            }
            sub = Optional.of(secondSub);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return sub;
    }
}
