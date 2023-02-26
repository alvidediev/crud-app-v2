package ru.dediev.jdbc.repository.impl;

import ru.dediev.jdbc.config.DatabaseConnection;
import ru.dediev.jdbc.model.Developer;
import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.DevelopersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static ru.dediev.jdbc.model.Status.ACTIVE;
import static ru.dediev.jdbc.model.Status.DELETED;

public class DevelopersRepositoryImpl implements DevelopersRepository {

    Connection connection = DatabaseConnection.getInstance().getConnection();

    private final SkillRepositoryImpl skillRepository = new SkillRepositoryImpl();
    private Developer developer;

    @Override
    public Developer save(Developer developerToSave) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO developers (first_name, last_name, is_Active) VALUES (?, ?, true)"
        )) {
            developerToSave.setId(getId());
            pStatement.setString(1, developerToSave.getFirstName());
            pStatement.setString(2, developerToSave.getLastName());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        developer = getById(getId());
        developer.setStatus(ACTIVE);
        return developer;
    }

    @Override
    public Developer getById(Integer id) {
        developer = new Developer();
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM developers WHERE id_developer = ?"
        )) {
            pStatement.setInt(1, id);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                developer.setId(rs.getInt("id_developer"));
                if(developer.getId() == null){
                    System.out.println("Похоже, что Вы пытаетесь найти еще не созданного или удаленного\n ");
                    developer.setStatus(DELETED);
                }
                developer.setFirstName(rs.getString("first_name"));
                developer.setLastName(rs.getString("last_name"));
                developer.setSkill(skillRepository.getAllDeveloperSkills(id));
                developer.setSpecialty(selectDeveloperSpecialty());
                if (rs.getBoolean("is_Active")) {
                    developer.setStatus(ACTIVE);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public List<Developer> getAll() {
        List<Developer> developers = new ArrayList<>();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM developers " +
                        "LEFT JOIN skill ON developers.id_developer = skill.skill_developer_id " +
                        "LEFT JOIN specialty ON developers.id_developer = specialty.specialty_developer_id"
        )) {
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                developer = new Developer();
                developer.setId(rs.getInt("id_developer"));
                developer.setFirstName(rs.getString("first_name"));
                developer.setLastName(rs.getString("last_name"));
                developer.setSkill(skillRepository.getAll());
                developer.setSpecialty(selectDeveloperSpecialty());
                if (rs.getBoolean("is_Active")) {
                    developer.setStatus(ACTIVE);
                } else {
                    developer.setStatus(DELETED);
                }
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public Integer getId() {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT id_developer FROM developers ORDER BY id_developer DESC LIMIT 1"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_developer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Developer update(Developer developer) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "UPDATE developers SET first_name = ?, last_name = ? WHERE id_developer = ?"
        )) {
            pStatement.setString(1, developer.getFirstName());
            pStatement.setString(2, developer.getLastName());
            pStatement.setInt(3, developer.getId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    @Override
    public Developer deleteById(Integer integer) {
        developer = new Developer();
        try (PreparedStatement pStatement = connection.prepareStatement(
                "DELETE FROM developers WHERE id_developer = ?;"
        )) {
            pStatement.setInt(1, integer);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            developer.setStatus(DELETED);
        }
        return developer;
    }

    public Specialty selectDeveloperSpecialty() {
        Specialty specialty = new Specialty();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM developers " +
                        "LEFT join specialty ON id_developer = specialty_developer_id"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                specialty.setId(resultSet.getInt("id_specialty"));
                specialty.setName(resultSet.getString("specialty_name"));
                specialty.setDeveloperId(resultSet.getInt("specialty_developer_id"));
                if (resultSet.getBoolean("is_Active")) {
                    specialty.setStatus(ACTIVE);
                } else {
                    specialty.setStatus(Status.DELETED);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return specialty;
    }
}
