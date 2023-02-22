package ru.dediev.jdbc.repository.impl;


import ru.dediev.jdbc.config.DatabaseConnection;
import ru.dediev.jdbc.model.Skill;
import ru.dediev.jdbc.model.Status;
import ru.dediev.jdbc.repository.SkillRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SkillRepositoryImpl implements SkillRepository {

    Connection connection = DatabaseConnection.getInstance().getConnection();
    private Skill skill;
    private List<Skill> listOfSkills;

    @Override
    public Skill save(Skill skill) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO skill (skill_name, skill_developer_id) VALUES (?, ?) "
        )) {
            pStatement.setString(1, skill.getName());
            pStatement.setInt(2, skill.getDeveloperId());
            pStatement.executeUpdate();
            skill.setId(getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        skill.setStatus(Status.ACTIVE);
        return skill;
    }

    @Override
    public Skill getById(Integer integer) {
        skill = new Skill();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM skill WHERE id_skill = ?"
        )) {
            pStatement.setInt(1, integer);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                skill.setId(resultSet.getInt("id_skill"));
                skill.setName(resultSet.getString("skill_name"));
                skill.setDeveloperId((resultSet.getInt("skill_developer_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public int getId() {
        int skillId;
        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT id_skill FROM skill ORDER BY id_skill DESC LIMIT 1"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            if (resultSet.next()) {
                skillId = resultSet.getInt("id_skill");
                return skillId;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Skill> getAll() {
        listOfSkills = new ArrayList<>();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM skill"
        )) {
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                skill = new Skill();
                skill.setId(resultSet.getInt("id_skill"));
                skill.setName(resultSet.getString("skill_name"));
                skill.setDeveloperId((resultSet.getInt("skill_developer_id")));
                listOfSkills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSkills;
    }

    @Override
    public List<Skill> getAllDeveloperSkills(int developerId) {
        listOfSkills = new ArrayList<>();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM skill WHERE skill_developer_id = ?"
        )) {
            pStatement.setInt(1, developerId);
            final ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                skill = new Skill();
                skill.setId(resultSet.getInt("id_skill"));
                skill.setName(resultSet.getString("skill_name"));
                skill.setDeveloperId((resultSet.getInt("skill_developer_id")));
                listOfSkills.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfSkills;
    }

    @Override
    public Skill update(Skill skill) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "UPDATE skill SET skill_name = ? WHERE id_skill = ?"
        )) {
            pStatement.setString(1, skill.getName());
            pStatement.setInt(2, skill.getId());
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return skill;
    }

    @Override
    public void deleteById(Integer integer) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "DELETE FROM skill WHERE id_skill = ?"
        )) {
            pStatement.setInt(1, integer);
            pStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        skill.setStatus(Status.DELETED);
    }
}
