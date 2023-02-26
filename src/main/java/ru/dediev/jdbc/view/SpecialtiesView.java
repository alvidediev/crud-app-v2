package ru.dediev.jdbc.view;

import ru.dediev.jdbc.controller.DevelopersController;
import ru.dediev.jdbc.controller.SpecialtyController;
import ru.dediev.jdbc.model.Specialty;
import ru.dediev.jdbc.model.Status;

import java.util.Scanner;

public class SpecialtiesView {

    private Scanner scanner;
    private final SpecialtyController specialtyController = new SpecialtyController();

    public void startWorkWithSpecialties() {
        scanner = new Scanner(System.in);
        boolean stopper = true;

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Просмотр всех специальностей в базе\n" +
                "2) Найти специальность по ID записи\n" +
                "3) Изменить специальность в списке\n" +
                "4) Удалить специальности из списка\n" +
                "5) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (stopper) {
            switch (choice) {
                case 1:
                    showAllSpecialties();
                    stopper = false;
                    break;
                case 2:
                    getSpecialtyByID();
                    stopper = false;
                    break;
                case 3:
                    updateSpecialty();
                    stopper = false;
                    break;
                case 4:
                    deleteSpecialty();
                    stopper = false;
                    break;
                default:
                    MainView mainView = new MainView();
                    mainView.start();
            }
        }
    }


    public void addSpecialty() {
        scanner = new Scanner(System.in);
        DevelopersController developersController = new DevelopersController();
        Specialty specialty = new Specialty();

        final int developersId = developersController.getId();
        boolean stopper = true;
        System.out.println("Хотите добавить специальность ?\nВыберите:\n1)Да\n2)Нет");
        while (stopper){
            final int choice = scanner.nextInt();
            switch (choice){
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.println("Пожалуйста, Введите название умения:");
                    String nameOfSpecialty = scanner.nextLine();
                    specialty.setName(nameOfSpecialty);
                    specialty.setDeveloperId(developersId);
                    specialty.setStatus(Status.ACTIVE);
                    specialtyController.create(specialty);
                    System.out.println("Успешно добавлена специальность: " +
                            specialtyController.getById(specialtyController.getId()));
                    stopper = false;
                    break;
                case 2:
                    stopper = false;
                    break;
                default:
                    System.out.println("Enter correct data...");
            }
        }
    }

    private void showAllSpecialties() {
        System.out.println(specialtyController.readAll());
        startWorkWithSpecialties();
    }

    private void getSpecialtyByID(){
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, введите ID специальности, которую хотите в базе данных: ");
        final int specialtyId = scanner.nextInt();
        final Specialty specialtyById = specialtyController.getById(specialtyId);
        System.out.println("По ID:" + specialtyId + " найдена запись " + specialtyById);
        startWorkWithSpecialties();
    }

    private void updateSpecialty() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, введите ID специальности, которую хотите изменить:");
        final int id = scanner.nextInt();
        System.out.println("Введите новое имя специальности:");
        final String changedSpecialtyName = scanner.nextLine();
        specialtyController.update(id, changedSpecialtyName);
        System.out.println("Вы успешно изменили специальность " + specialtyController.read(id));
        startWorkWithSpecialties();
    }

    private void deleteSpecialty() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID специальности, которую хотите удалить");
        final int id = scanner.nextInt();
        specialtyController.delete(id);
        System.out.println(specialtyController.read(id));
        startWorkWithSpecialties();
    }
}
