package ru.dediev.jdbc.view;

import ru.dediev.jdbc.config.DatabaseConnection;

import java.sql.SQLException;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);
    private final SpecialtiesView specialtiesView = new SpecialtiesView();
    private final SkillsView skillsView = new SkillsView();
    private final DevelopersView developersView = new DevelopersView();

    public void start() {
        System.out.println("Добро пожаловать в CrudApp! V2");
        System.out.println("ГЛАВНОЕ МЕНЮ!\n" +
                "Выберите один из вариантов\n" +
                "1)Меню разработчиков\n" +
                "2)Меню specialties\n" +
                "3)Меню skill's\n" +
                "4)Завершить программу");
        int choice = scanner.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    developersView.startWorkWithDevelopers();
                    break;
                case 2:
                    specialtiesView.startWorkWithSpecialties();
                    break;
                case 3:
                    skillsView.startWorkWithSkills();
                    break;
                case 4:
                    try {
                        DatabaseConnection.getInstance().getConnection().close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    System.exit(0);
                default:
                    System.out.println("Enter correct data...");
            }
        }
    }
}
