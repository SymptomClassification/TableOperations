package com.lancaster.trainingdata.repository;

import com.lancaster.trainingdata.model.TrainingData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class TrainingDataRepository {

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


    public List<TrainingData> fetchTrainingData() {
        List<TrainingData> trainingData = new ArrayList<>();
        TrainingData data;
        String selectAll = "SELECT * FROM databasetrainingdata";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                data =
                        new TrainingData(rs.getInt("id"), rs.getString("symptom"), rs.getInt("label"));
                trainingData.add(data);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return trainingData;
    }

    public List<Map<String, String>> getTrainingDataDescriptions() {
        List<Map<String, String>> result = new ArrayList<>();
        String query = "SELECT dt.id, dt.symptom, \n" +
                "       COALESCE(c2.name, c1.name) AS chapterName, \n" +
                "       CASE WHEN c2.id IS NOT NULL THEN c1.name ELSE NULL END AS subchapterName\n" +
                "FROM databasetrainingdata dt\n" +
                "LEFT JOIN spacychapters c1 ON dt.label = c1.id\n" +
                "LEFT JOIN spacychapters c2 ON c1.chapterId = c2.id;";
        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(query);

            while (rs.next()) {
                result.add(Map.of(
                        "id", rs.getString("id"),
                        "symptom", rs.getString("symptom"),
                        "chapterName", rs.getString("chapterName"),
                        "subchapterName", rs.getString("subchapterName")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
