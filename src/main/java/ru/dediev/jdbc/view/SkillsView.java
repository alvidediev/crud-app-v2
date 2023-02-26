package ru.dediev.jdbc.view;

import ru.dediev.jdbc.controller.DevelopersController;
import ru.dediev.jdbc.controller.SkillsController;
import ru.dediev.jdbc.model.Skill;

import java.util.Scanner;

public class SkillsView {

    private Scanner scanner;
    private final SkillsController skillsController = new SkillsController();
    private final DevelopersController developersController = new DevelopersController();

    public void startWorkWithSkills() {
        scanner = new Scanner(System.in);
        boolean stopper = true;

        System.out.println("Добро пожаловать в меню специальностей!\n" +
                "1) Просмотр всех навыков в базе\n" +
                "2) Получить скилл по ID\n" +
                "3) Изменить навыки в списке\n" +
                "4) Удалить навык из списка\n" +
                "5) Вернуться в главное меню");

        final int choice = scanner.nextInt();
        while (stopper) {
            switch (choice) {
                case 1:
                    showAllSkills();
                    stopper = false;
                    break;
                case 2:
                    getSkillById();
                    stopper = false;
                    break;
                case 3:
                    updateSkill();
                    stopper = false;
                    break;
                case 4:
                    deleteSkill();
                    stopper = false;
                    break;
                default:
                    MainView mainView = new MainView();
                    mainView.start();
            }
        }
    }

    public void addSkill() {
        scanner = new Scanner(System.in);
        Skill skill = new Skill();

        boolean stopper = true;
        final int currentDeveloperId = developersController.getId();
        while (stopper) {
            System.out.println("Хотите добавить скилл?\n" +
                    "1) Да\n" +
                    "2) Нет");
            final int addSkillsChoice = scanner.nextInt();
            switch (addSkillsChoice) {
                case 1:
                    scanner = new Scanner(System.in);
                    System.out.println("Введите, пожалуйста, название вашего умения: ");
                    final String nameOfSkill = scanner.nextLine();
                    skill.setDeveloperId(currentDeveloperId);
                    skill.setName(nameOfSkill);
                    skillsController.create(skill);
                    final int id = skillsController.getId();
                    System.out.println("Вы успешно добавили умение: " + skillsController.getById(id));
                    continue;
                case 2:
                    stopper = false;
                    break;
                default:
                    System.out.println("Enter correct data...");
            }
        }
    }

    private void getSkillById() {
        scanner = new Scanner(System.in);

        System.out.println("Пожалуйста, введите ID скилла, который хотите найти: ");
        final int skillId = scanner.nextInt();
        final Skill skillById = skillsController.getById(skillId);
        System.out.println("Найден скилл: " + skillById);
        startWorkWithSkills();
    }

    private void showAllSkills() {
        System.out.println(skillsController.readAll());
        startWorkWithSkills();
    }

    private void updateSkill() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID навыка, который хотите изменить:");
        final int id = scanner.nextInt();
        System.out.println("Введите новое имя навыка");
        scanner.nextLine();
        final String name = scanner.nextLine();
        skillsController.update(id, name);
        System.out.println("Вы успешно изменили навык " + skillsController.read(id));
        startWorkWithSkills();
    }

    private void deleteSkill() {
        scanner = new Scanner(System.in);

        System.out.println("Введите ID навыка, который хотите удалить");
        final int id = scanner.nextInt();
        skillsController.delete(id);
        System.out.println(skillsController.read(id));
        startWorkWithSkills();
    }
}
