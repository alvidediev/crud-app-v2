package ru.dediev.jdbc.view;


import ru.dediev.jdbc.controller.DevelopersController;
import ru.dediev.jdbc.model.Developer;

import java.util.Scanner;

public class DevelopersView {

    private final DevelopersController devsController = new DevelopersController();
    private final SkillsView skillsView = new SkillsView();
    SpecialtiesView specialtiesView = new SpecialtiesView();
    private Scanner scanner;

    public void startWorkWithDevelopers() {
        scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Добавить разработчика\n" +
                "2) Просмотр всех разработчиков в базе\n" +
                "3) Найти разработчика по ID\n" +
                "4) Изменить разработчика в списке\n" +
                "5) Удалить разработчика из списка\n" +
                "6) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    addDeveloper();
                    break;
                case 2:
                    showAllDevs();
                    break;
                case 3:
                    getById();
                    break;
                case 4:
                    updateDeveloper();
                    break;
                case 5:
                    deleteDeveloper();
                    break;
                default:
                    MainView mainView = new MainView();
                    mainView.start();
            }
        }
    }


    private void addDeveloper() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста! Введите фамилию разработчика:");
        String developersFirstName = scanner.nextLine();
        System.out.println("Пожалуйста! Введите имя разработчика:");
        String developersLastName = scanner.nextLine();
        devsController.create(developersFirstName, developersLastName);
        skillsView.addSkill();
        specialtiesView.addSpecialty();
        System.out.println("Вы успешно добавили разработчика: " + devsController.getById(devsController.getId()));
        startWorkWithDevelopers();
    }

    private void getById() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста введите ID для поиска:\n");
        final int idOfDeveloper = scanner.nextInt();
        final Developer developerById = devsController.getById(idOfDeveloper);
        System.out.println("По ID" + idOfDeveloper + "найден разработчик: " + developerById);
    }

    private void showAllDevs() {
        System.out.println(devsController.readAll());
        startWorkWithDevelopers();
    }

    private void updateDeveloper() {
        scanner = new Scanner(System.in);

        System.out.println(devsController.readAll());
        System.out.println("Введите ID разработчика, которого хотите отредактировать");
        int id = scanner.nextInt();
        System.out.println("Введите новую фамилию для разработчика");
        String firstName = scanner.nextLine();
        System.out.println("Введите новое имя для разработчика");
        String lastName = scanner.nextLine();
        devsController.update(id, firstName, lastName);
        System.out.println(devsController.read(id));
        startWorkWithDevelopers();
    }

    private void deleteDeveloper() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста введите ID разработчика, которого хотите удалить");
        int idOfDeveloper = scanner.nextInt();
        devsController.delete(idOfDeveloper);
        System.out.println(devsController.read(idOfDeveloper));
        startWorkWithDevelopers();
    }
}

