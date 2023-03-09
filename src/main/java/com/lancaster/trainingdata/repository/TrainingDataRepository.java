package com.lancaster.trainingdata.repository;

import com.lancaster.trainingdata.model.TrainingData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public Optional<TrainingData> updateTrainingData(TrainingData trainingData, int id) {
        Optional<TrainingData> result = fetchTrainingDataWithId(id);
        String update = "UPDATE databasetrainingdata SET label = ? WHERE id = ?";

        if (result.isEmpty()) {
            return Optional.empty();
        }

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(update);
            stm.setInt(1, trainingData.getLabel());
            stm.setInt(2, id);
            stm.executeUpdate();
            result = fetchTrainingDataWithId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Optional<TrainingData> fetchTrainingDataWithId(int id) {
        String selectById = "SELECT * FROM databasetrainingdata WHERE id = ?";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(selectById);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return Optional.of(new TrainingData(rs.getInt("id"), rs.getString("symptom"), rs.getInt("label")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public int saveTrainingData(TrainingData trainingData) {
        String insert = "INSERT INTO databasetrainingdata (symptom, label) VALUES (?, ?)";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, trainingData.getSymptom());
            stm.setInt(2, trainingData.getLabel());
            int affectedRows = stm.executeUpdate();

            ResultSet rs = stm.getGeneratedKeys();
            if (affectedRows == 0) {
                throw new SQLException("Creating subchapter failed, subchapter with the same name and chapter already exists.");
            }
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Creating subchapter failed, no ID obtained.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
