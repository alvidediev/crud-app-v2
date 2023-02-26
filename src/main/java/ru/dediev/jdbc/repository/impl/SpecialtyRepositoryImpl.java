package ru.dediev.jdbc.repository.impl;


import ru.dediev.jdbc.config.DatabaseConnection;
import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.repository.SpecialtyRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.dediev.jdbc.model.Status.ACTIVE;
import static ru.dediev.jdbc.model.Status.DELETED;

public class SpecialtyRepositoryImpl implements SpecialtyRepository {

    Connection connection = DatabaseConnection.getInstance().getConnection();

    private Specialty devSpecialty;


    @Override
    public Specialty save(Specialty specialty) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO specialty (specialty_name, specialty_developer_id, is_Active) VALUES (?, ?, true)"
        )) {
            pStatement.setString(1, specialty.getName());
            pStatement.setInt(2, specialty.getDeveloperId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        devSpecialty = getById(getId());
        devSpecialty.setStatus(ACTIVE);
        return devSpecialty;
    }

    @Override
    public Specialty getById(Integer integer) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM specialty WHERE id_specialty = ?"
        )) {
            pStatement.setInt(1, integer);
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                devSpecialty = new Specialty();
                devSpecialty.setId(resultSet.getInt("id_specialty"));
                if(devSpecialty.getId() == null){
                    devSpecialty.setStatus(DELETED);
                }
                devSpecialty.setName(resultSet.getString("specialty_name"));
                devSpecialty.setDeveloperId(resultSet.getInt("specialty_developer_id"));
                if (resultSet.getBoolean("is_Active")) {
                    devSpecialty.setStatus(ACTIVE);
                } else {
                    devSpecialty.setStatus(DELETED);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devSpecialty;
    }

    @Override
    public Integer getId() {
        int specialtyId;
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT id_specialty FROM specialty ORDER BY id_specialty DESC LIMIT 1"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                specialtyId = resultSet.getInt("id_specialty");
                return specialtyId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
                devSpecialty = new Specialty();
                devSpecialty.setId(resultSet.getInt("id_specialty"));
                devSpecialty.setName(resultSet.getString("specialty_name"));
                devSpecialty.setDeveloperId(resultSet.getInt("specialty_developer_id"));
                if (resultSet.getBoolean("is_Active")) {
                    devSpecialty.setStatus(ACTIVE);
                } else {
                    devSpecialty.setStatus(DELETED);
                }
                specialties.add(devSpecialty);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialties;
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
    public Specialty deleteById(Integer integer) {
        devSpecialty = new Specialty();
        try (PreparedStatement pStatement = connection.prepareStatement(
                "DELETE FROM specialty WHERE id_specialty = ?"
        )) {
            pStatement.setInt(1, integer);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            devSpecialty.setStatus(DELETED);
        }
        return devSpecialty;
    }
}
