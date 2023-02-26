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

import static ru.dediev.jdbc.model.Status.*;

public class SkillRepositoryImpl implements SkillRepository {

    Connection connection = DatabaseConnection.getInstance().getConnection();
    private Skill devSkill;
    private List<Skill> listOfSkills;

    @Override
    public Skill save(Skill skill) {
        try (PreparedStatement pStatement = connection.prepareStatement(
                "INSERT INTO skill (skill_name, skill_developer_id, is_Active) VALUES (?, ?, true)"
        )) {
            pStatement.setString(1, skill.getName());
            pStatement.setInt(2, skill.getDeveloperId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        devSkill = getById(getId());
        devSkill.setStatus(ACTIVE);
        return devSkill;
    }

    @Override
    public Skill getById(Integer id) {
        devSkill = new Skill();

        try (PreparedStatement pStatement = connection.prepareStatement(
                "SELECT * FROM skill WHERE id_skill = ?"
        )) {
            pStatement.setInt(1, id);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                devSkill.setId(resultSet.getInt("id_skill"));
                if (devSkill.getId() == null) {
                    System.out.println("Похоже, что скилл, который вы ищете по " + id + " еще не создан" +
                            "либо удален.\nНиже информация по Вашему запросу");
                    devSkill.setStatus(DELETED);
                }
                devSkill.setName(resultSet.getString("skill_name"));
                devSkill.setDeveloperId((resultSet.getInt("skill_developer_id")));
                if (resultSet.getBoolean("is_Active")) {
                    devSkill.setStatus(ACTIVE);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return devSkill;
    }

    @Override
    public Integer getId() {
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
                devSkill = new Skill();
                devSkill.setId(resultSet.getInt("id_skill"));
                if (devSkill.getId() == null) {
                    System.out.println("Похоже, что в базу еще не добавлены данные о скиллах");
                }
                devSkill.setName(resultSet.getString("skill_name"));
                devSkill.setDeveloperId((resultSet.getInt("skill_developer_id")));
                if (resultSet.getBoolean("is_Active")) {
                    devSkill.setStatus(ACTIVE);
                } else {
                    devSkill.setStatus(DELETED);
                }
                listOfSkills.add(devSkill);
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
                devSkill = new Skill();
                devSkill.setId(resultSet.getInt("id_skill"));
                devSkill.setName(resultSet.getString("skill_name"));
                devSkill.setDeveloperId((resultSet.getInt("skill_developer_id")));
                if (resultSet.getBoolean("is_Active")) {
                    devSkill.setStatus(ACTIVE);
                } else {
                    devSkill.setStatus(DELETED);
                }
                listOfSkills.add(devSkill);
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
    public Skill deleteById(Integer integer) {
        devSkill = new Skill();
        try (PreparedStatement pStatement = connection.prepareStatement(
                "DELETE FROM skill WHERE id_skill = ?"
        )) {
            pStatement.setInt(1, integer);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            devSkill.setStatus(DELETED);
        }
        return devSkill;
    }
}
