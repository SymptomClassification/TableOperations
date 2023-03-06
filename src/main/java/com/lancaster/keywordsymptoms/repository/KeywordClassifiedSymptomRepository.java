package com.lancaster.keywordsymptoms.repository;


import com.lancaster.keywordsymptoms.model.KeywordClassifiedSymptom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class KeywordClassifiedSymptomRepository {

    private Connection con = null;
    private final String TABLE_NAME = "keywordclassifiedsymptom";

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

    public List<KeywordClassifiedSymptom> fetchClassifiedSymptoms() {
        List<KeywordClassifiedSymptom> symptoms = new ArrayList<>();
        KeywordClassifiedSymptom symptom;
        String selectAll = "SELECT * FROM " + TABLE_NAME;

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                symptom = new KeywordClassifiedSymptom();
                symptom.setId(rs.getInt("id"));
                symptom.setSymptomId(rs.getInt("symptomId"));
                symptom.setChapterId(rs.getInt("chapterId"));
                symptom.setSubchapterId(rs.getInt("subchapterId"));
                symptom.setSecondsubId(rs.getInt("secondsubId"));
                symptoms.add(symptom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return symptoms;
    }

    public KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom) {
        String create = "INSERT INTO " + TABLE_NAME + " (symptomId, chapterId, subchapterId, secondsubId) " +
                " VALUES (?, ?, ?, ?)";


        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create);
            stm.setInt(1, keywordClassifiedSymptom.getSymptomId());
            stm.setInt(2, keywordClassifiedSymptom.getChapterId());
            stm.setInt(3, keywordClassifiedSymptom.getSubchapterId());
            stm.setInt(4, keywordClassifiedSymptom.getSecondsubId());
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return keywordClassifiedSymptom;
    }

    public Optional<KeywordClassifiedSymptom> fetchClassifiedSymptomWithId(int id) {
        Optional<KeywordClassifiedSymptom> ch = Optional.empty();
        KeywordClassifiedSymptom symptom = new KeywordClassifiedSymptom();
        String select = "SELECT * FROM " + TABLE_NAME + " WHERE id = " + id;

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);

            if (rs.next()) {
                symptom.setId(rs.getInt("id"));
                symptom.setSymptomId(rs.getInt("symptomId"));
                symptom.setChapterId(rs.getInt("chapterId"));
                symptom.setSubchapterId(rs.getInt("subchapterId"));
                symptom.setSecondsubId(rs.getInt("secondsubId"));
                ch = Optional.of(symptom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ch;
    }

    public Optional<KeywordClassifiedSymptom> fetchClassifiedSymptomWithSymptomId(int id) {
        Optional<KeywordClassifiedSymptom> op = Optional.empty();

        String select = "SELECT * FROM " + TABLE_NAME + " WHERE symptomId = " + id;

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(select);

            while (rs.next()) {
                KeywordClassifiedSymptom symptom = new KeywordClassifiedSymptom();
                symptom.setId(rs.getInt("id"));
                symptom.setSymptomId(rs.getInt("symptomId"));
                symptom.setChapterId(rs.getInt("chapterId"));
                symptom.setSubchapterId(rs.getInt("subchapterId"));
                symptom.setSecondsubId(rs.getInt("secondsubId"));

                op = Optional.of(symptom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return op;
    }

    public Optional<KeywordClassifiedSymptom> updateClassifiedSymptom(KeywordClassifiedSymptom symptom, int id) {
        Optional<KeywordClassifiedSymptom> op = fetchClassifiedSymptomWithSymptomId(id);
        String update = "UPDATE " + TABLE_NAME + " SET symptomId = ?, chapterId = ?, subchapterId = ?, secondsubId = ? WHERE symptomId = ?";

        if (!op.isPresent()) {
            return Optional.empty();
        }

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(update);
            stm.setInt(1, symptom.getSymptomId());
            stm.setInt(2, symptom.getChapterId());
            stm.setInt(3, symptom.getSubchapterId());
            stm.setInt(4, symptom.getSecondsubId());
            stm.setInt(5, id);
            stm.executeUpdate();
            op = fetchClassifiedSymptomWithSymptomId(symptom.getSymptomId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return op;
    }


}
