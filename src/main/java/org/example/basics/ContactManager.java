package org.example.basics;

import java.util.Scanner;


public class ContactManager {

    private static final int MAX_CONTACTS = 100; // максимальное число контактов

    private static final String[] names = new String[MAX_CONTACTS]; // массив имен

    private static final String[] phoneNumbers = new String[MAX_CONTACTS]; // массив номеров телефонов

    private static int countContacts = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean checkMenu = true; // флаг для отслеживания состояния меню
        while (checkMenu) {
            System.out.println("Пожалуйста, выберите пункт меню: \n1 - Добавить контакт \n" +
                    "2 - Просмотреть контакты \n" + "3 - Найти контакт \n"
                    + "4 - Удалить контакт \n" + "5 - Выйти");
            String choise = scanner.nextLine().trim();
            try {  // проверка на принадлежность введенного значения диапазону
                if (!((Integer.parseInt(choise) >= 1) && (Integer.parseInt(choise) <= 5))) {
                    System.out.println("Пункт меню должен быть числом от 1 до 5");
                    System.out.println("Пожалуйста, повторите ввод \n");
                    continue;

                }

            } catch (NumberFormatException e) { // обработка введенная строка,которая
                // не является числом
                System.out.println("Ошибка! Пункт меню должен быть числом!");
                System.out.println("Пожалуйста, повторите ввод! \n");
                continue;

            }
            switch (choise) {
                case "1" -> addContact();
                case "2" -> viewContacs();
                case "3" -> searchContact();
                case "4" -> deleteContact();
                case "5" -> {
                    System.out.println("Вы вышли из программы!");
                    checkMenu = false;
                }
            }

            System.out.println();

        }

        scanner.close();

    }

    private static void addContact() { // добавление контакта
        if (countContacts >= MAX_CONTACTS) { // проверка на выход контакта за пределы массива
            System.out.println("Ошибка! Для добавления контакта необходимо удалить " +
                    "хотя бы один контакт!");
            return;

        }
        System.out.println("Пожалуйста, введите имя контакта.");
        String nameContact = scanner.nextLine().trim();
        if (nameContact.isEmpty()) { // проверка имени на пустоту
            System.out.println("Ошибка! Имя контакта не может быть пустым");
            return;
        }
        names[countContacts] = nameContact;
        System.out.println("Пожалуйста, введите телефон контакта."); // проверка телефона на пустоту
        String phoneNumberContact = scanner.nextLine().trim();
        if (phoneNumberContact.isEmpty()) { // проверка телефона на пустоту
            System.out.println("Ошибка! Телефон контакта не может быть пустым");
            return;
        }
        phoneNumbers[countContacts] = phoneNumberContact;
        countContacts++;
        System.out.printf("%nКонтакт \"%s - %s\" успешно добавлен!%n%n", nameContact, phoneNumberContact);
    }

    private static void viewContacs() { // просмотр контактов
        if (countContacts == 0) { // проверка на пустоту контактов
            System.out.println("Внимание в системе нет ни одного сохраненного контакта!");
        } else {
            System.out.println("Текущие контакты:");
            for (int i = 0; i < countContacts; i++) { // вывод всех контактов с данным имененем
                System.out.printf("%d) Имя - %s, номер телефона - %s%n", i + 1, names[i], phoneNumbers[i]);
            }
        }

    }

    private static void searchContact() { // поиска контакта
        System.out.println("Пожалуйста имя контакта,который Вы хотите найти!");
        String nameSearch = scanner.nextLine().trim();
        if (nameSearch.isEmpty()) {
            System.out.println("Ошибка! Имя контакта не может быть пустым.");
            return;
        }
        int count = 0;
        for (int i = 0; i < countContacts; i++) {
            if (names[i].equals(nameSearch)) {
                System.out.printf("Найденный контакт: \"%s - %s\"%n", names[i], phoneNumbers[i]);
                count++;
            }
        }
        if (count == 0) { // если нет ни одного контакта с введенным имененем
            System.out.println("В системе нет ни одного контакта с данным именем!");
        }
    }

    private static void deleteContact() { // удаление контакта по имени
        System.out.println("Пожалуйста имя контакта,который Вы хотите удалить!");
        String nameDelete = scanner.nextLine().trim();
        Boolean isFoundForDelete = false;
        for (int i = 0; i < countContacts; i++) {
            if (names[i].equals(nameDelete)) {
                isFoundForDelete = true;
                int indexForChange = i; // индекс элемента, который будет заменяться правым от него элементом
                while (indexForChange < countContacts - 1) {
                    names[indexForChange] = names[indexForChange + 1];
                    phoneNumbers[indexForChange] = phoneNumbers[indexForChange + 1];
                    indexForChange++;
                }
                names[countContacts - 1] = null; // удаление крайнего элемента массива
                phoneNumbers[countContacts - 1] = null; // удаление крайнего элемента массива
                countContacts--;
                System.out.printf("Контакт с именем \"%s\" успешно удалён!%n", nameDelete);
                return;

            }
        }

        if (!isFoundForDelete) {
            System.out.println("Невозможно удалить контакт с таким имененем, так как он отсутствует в системе!");
        }
    }

}

