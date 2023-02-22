package ru.dediev.jdbc.repository.impl;


import ru.dediev.jdbc.config.DatabaseConnection;
import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.SpecialtyRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    Connection connection = DatabaseConnection.getInstance().getConnection();
    private Specialty specialty = null;

    @Override
    public Specialty save(Specialty specialty) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO specialty (specialty_name, specialty_developer_id) VALUES (?, ?)"
        )) {
            pStatement.setString(1, specialty.getName());
            pStatement.setInt(2, specialty.getDeveloperId());
            pStatement.executeUpdate();
            specialty.setId(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        specialty.setStatus(Status.ACTIVE);
        return specialty;
    }

    @Override
    public Specialty getById(Integer integer) {

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM specialty WHERE id_specialty = ?"
        )) {
            pStatement.setInt(1, integer);
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                specialty = new Specialty();
                specialty.setId(resultSet.getInt("id_specialty"));
                specialty.setName(resultSet.getString("specialty_name"));
                specialty.setDeveloperId(resultSet.getInt("specialty_developer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public int getId() {
        int specialtyId;
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT id_specialty FROM specialty ORDER BY id_specialty DESC LIMIT 1"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                specialtyId = resultSet.getInt("id_specialty");
                return specialtyId;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Specialty> getAll() {
        List<Specialty> specialties = new ArrayList<>();


        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM specialty"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                specialty = new Specialty();
                specialty.setId(resultSet.getInt("id_specialty"));
                specialty.setName(resultSet.getString("specialty_name"));
                specialty.setDeveloperId(resultSet.getInt("specialty_developer_id"));
                specialties.add(specialty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialties;
    }

    @Override
    public Specialty getDeveloperSpecialty(int id) {

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM specialty WHERE specialty_developer_id = ?"
        )) {
            pStatement.setInt(1, id);
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                specialty = new Specialty();
                specialty.setId(resultSet.getInt("id_specialty"));
                specialty.setName(resultSet.getString("specialty_name"));
                specialty.setDeveloperId(resultSet.getInt("specialty_developer_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public Specialty update(Specialty specialty) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "UPDATE specialty SET specialty_name = ?"
        )) {
            pStatement.setString(1, specialty.getName());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }

    @Override
    public void deleteById(Integer integer) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "DELETE FROM specialty WHERE id_specialty = ?"
        )) {
            pStatement.setInt(1, integer);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        specialty.setStatus(Status.DELETED);
    }
}
