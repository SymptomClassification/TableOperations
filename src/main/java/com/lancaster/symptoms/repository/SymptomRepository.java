package com.lancaster.symptoms.repository;

import com.lancaster.symptoms.model.Symptom;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class SymptomRepository {
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

    public List<Symptom> fetchSymptoms() {
        List<Symptom> symptoms = new ArrayList<>();
        Symptom symptom;
        String selectAll = "SELECT * FROM symptoms";

        try {
            Statement stm = getDBConnection().createStatement();
            ResultSet rs = stm.executeQuery(selectAll);

            while (rs.next()) {
                symptom = new Symptom();
                symptom.setId(rs.getInt("id"));
                symptom.setSymptom(rs.getString("symptom"));
                symptoms.add(symptom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return symptoms;
    }

    public int saveSymptom(Symptom symptom) {
        String create = "INSERT INTO symptoms (symptom) VALUES (?)";
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
            stm.setString(1, symptom.getSymptom());
            stm.executeUpdate();
            // Retrieve the auto-generated ID
            ResultSet rs = stm.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                symptom.setId(id);
            } else {
                throw new SQLException("No ID was generated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return symptom.getId();
    }

    public Optional<Symptom> fetchSymptomWithId(int id) {
        Optional<Symptom> op = Optional.empty();
        String select = "SELECT * FROM symptoms WHERE id = ?";
        Symptom symptom;
        try {
            PreparedStatement stm = getDBConnection().prepareStatement(select);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                symptom = new Symptom();
                symptom.setId(rs.getInt("id"));
                symptom.setSymptom(rs.getString("symptom"));
                op = Optional.of(symptom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return op;
    }

    public Optional<Symptom> updateSymptom(Symptom symptom, int id) {
        Optional<Symptom> op = fetchSymptomWithId(id);

        String update = "UPDATE symptoms SET symptom = ? WHERE id = ?";

        if (!op.isPresent()) {
            return Optional.empty();
        }

        try {
            PreparedStatement stm = getDBConnection().prepareStatement(update);
            stm.setString(1, symptom.getSymptom());
            stm.setInt(2, id);
            stm.executeUpdate();
            op = fetchSymptomWithId(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return op;
    }


}
