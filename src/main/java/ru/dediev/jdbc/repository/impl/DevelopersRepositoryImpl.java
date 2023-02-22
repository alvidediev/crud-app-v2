package ru.dediev.jdbc.repository.impl;

import ru.dediev.jdbc.config.DatabaseConnection;
import ru.dediev.jdbc.model.Developer;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.DevelopersRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DevelopersRepositoryImpl implements DevelopersRepository {

    Connection connection = DatabaseConnection.getInstance().getConnection();
    private final SkillRepositoryImpl skillRepository = new SkillRepositoryImpl();
    private final SpecialtyRepositoryImpl specialtyRepository = new SpecialtyRepositoryImpl();
    private Developer developer;

    @Override
    public Developer save(Developer developer) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO developers (first_name, last_name) VALUES (?, ?)"
        )) {
            developer.setId(getId());
            pStatement.setString(1, developer.getFirstName());
            pStatement.setString(2, developer.getLastName());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        developer.setStatus(Status.ACTIVE);
        return developer;
    }

    @Override
    public Developer getById(Integer integer) {
        developer = new Developer();
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM developers WHERE id_developer = ?"
        )) {
            pStatement.setInt(1, integer);
            ResultSet rs = pStatement.executeQuery();
            while (rs.next()) {
                developer.setId(rs.getInt("id_developer"));
                developer.setFirstName(rs.getString("first_name"));
                developer.setLastName(rs.getString("last_name"));
                developer.setSkill(skillRepository.getAllDeveloperSkills(getId()));
                developer.setSpecialty(specialtyRepository.getDeveloperSpecialty(getId()));
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
                developers.add(developer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developers;
    }

    @Override
    public int getId(){
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT id_developer FROM developers ORDER BY id_developer DESC LIMIT 1"
        )){
            final ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt("id_developer");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
    public void deleteById(Integer integer) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "DELETE FROM developers WHERE id_developer = ?;"
        )) {
            pStatement.setInt(1, integer);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        developer.setStatus(Status.DELETED);
    }
}
