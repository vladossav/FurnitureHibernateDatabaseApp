package org.example;

import de.vandermeer.asciitable.AsciiTable;
import org.example.entities.Address;
import org.example.entities.Customer;
import org.example.entities.Furniture;
import org.example.repositories.BaseRepo;
import org.example.repositories.FurnitureRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;

public class Menu {
    FurnitureRepository furnRepo = new FurnitureRepository();

    public Menu() {
        boolean flagMainMenu = true;
        while (flagMainMenu) {
            mainMenu();
            int num = getInputShort();
            switch (num) {
                case 0 -> flagMainMenu = false;
                case 1 -> furnitureAddMenu();
                case 2 -> furnitureEditMenu();
                case 3 -> showFurniture(furnRepo.getAll());
                case 4 -> furnitureDeleteMenu();
                case 5 -> furnitureSearchMenu();
                default -> System.out.println("\nТакого пункта меню не существует!");
            }
        }
    }

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        new Menu();
        //Furniture furn = new Furniture(9999,"Тумба Хорошая","Т-2",23.2,"серый",
        //                (short) 233, (short) 234, (short) 244,2000);
    }

    void mainMenu() {
        System.out.println("\n------------------------ 30. База данных офисной мебели ------------------------");
        System.out.println("МЕБЕЛЬ\t\t\tЗАКАЗЧИКИ\t\tДОГОВОРЫ\t\tПРОДАЖИ");
        System.out.println("1. Добавить\t\t11. Добавить\t21. Добавить\t31. Добавить\t0. Выход");
        System.out.println("2. Изменить\t\t12. Изменить\t22. Изменить\t32. Изменить\t");
        System.out.println("3. Просмотр\t\t13. Просмотр\t23. Просмотр\t33. Просмотр\t");
        System.out.println("4. Удалить\t\t14. Удалить\t\t24. Удалить\t\t34. Удалить\t");
        System.out.println("5. Поиск\t\t15. Поиск\t\t25. Поиск\t\t35. Поиск\t");
        System.out.print("Введите пункт меню: ");
    }

    void furnitureAddMenu() {
        System.out.println("\nДобавление мебели");
        System.out.print("Введите название мебели: ");
        String name = getInputString();
        System.out.print("Введите модель: ");
        String model = getInputString();
        System.out.print("Введите стоимость: ");
        Double cost = getInputDouble();
        System.out.println("Введите характеристики мебели");
        System.out.print("Введите цвет: ");
        String color = getInputString();
        System.out.print("Введите длину (в мм): ");
        Short length = getInputShort();
        System.out.print("Введите ширину (в мм): ");
        Short width = getInputShort();
        System.out.print("Введите высоту (в мм): ");
        Short height = getInputShort();
        System.out.print("Введите вес (в г): ");
        Integer weight = getInputInteger();
        boolean flag = true;
        int id = 0;
        while(flag) {
            id = getRandomInt();
            if(furnRepo.checkIdNotExists(id)) flag = false;
        }
        Furniture furniture = new Furniture(id,name,model,cost,
                color,length,width,height,weight);
        furnRepo.save(furniture);
        System.out.println("\nОбъект добавлен!");
    }

    void furnitureEditMenu() {
        if (furnRepo.furnList == null) furnRepo.getAll();
        System.out.println("\nИзменение таблицы Мебель");
        System.out.print("Введите номер строки для изменения: ");
        int numberStr = getInputIntegerLessMax(furnRepo.furnList.size());
        Furniture furniture = furnRepo.furnList.get(numberStr);
        boolean flag = true;
        while (flag) {
            System.out.println("\nИзменение таблицы Мебель");
            showRaw(furniture.getRawStringList());
            System.out.println("1. Название");
            System.out.println("2. Модель");
            System.out.println("3. Стоимость");
            System.out.println("4. Цвет");
            System.out.println("5. Длина");
            System.out.println("6. Ширина");
            System.out.println("7. Высота");
            System.out.println("8. Вес");
            System.out.println("9. Сохранить изменения и выйти");
            System.out.println("0. Выйти без сохранения");
            System.out.println("Введите пункт меню: ");
            int num = getInputShort();

            switch (num) {
                case 0 -> flag = false;
                case 1 -> {
                    System.out.print("Введите новое название: ");
                    furniture.setName(getInputString());
                }
                case 2 -> {
                    System.out.print("Введите новую модель: ");
                    furniture.setModel(getInputString());
                }
                case 3-> {
                    System.out.print("Введите новую стоимость: ");
                    furniture.setCost(getInputDouble());
                }
                case 4-> {
                    System.out.print("Введите новый цвет: ");
                    furniture.setColor(getInputString());
                }
                case 5-> {
                    System.out.print("Введите новую длину: ");
                    furniture.setLength(getInputShort());
                }
                case 6-> {
                    System.out.print("Введите новую ширину: ");
                    furniture.setWidth(getInputShort());
                }
                case 7-> {
                    System.out.print("Введите новую высоту: ");
                    furniture.setHeight(getInputShort());
                }
                case 8-> {
                    System.out.print("Введите новый вес: ");
                    furniture.setWeight(getInputInteger());
                }
                case 9-> {
                    furnRepo.edit(furniture);
                    System.out.println("Изменения сохранены!");
                    flag = false;
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
        }
    }

    void furnitureDeleteMenu() {
        if (furnRepo.furnList == null) furnRepo.getAll();
        System.out.println("\nУдаление из таблицы Мебель");
        System.out.print("Введите номер строки для удаления: ");
        int numberStr = getInputIntegerLessMax(furnRepo.furnList.size());
        Furniture furniture = furnRepo.furnList.get(numberStr);
        furnRepo.delete(furniture);
        System.out.printf("\nСтрока %d успешно удалена!\n", numberStr);
    }

    void furnitureSearchMenu() {
        if (furnRepo.furnList == null) furnRepo.getAll();
        while (true) {
            System.out.println("\nПоиск по таблице Мебель");
            System.out.println("1. Название");
            System.out.println("2. Модель");
            System.out.println("3. Стоимость (+-100)");
            System.out.println("4. Цвет");
            System.out.println("5. Длина (+-100 мм)");
            System.out.println("6. Ширина (+-100 мм)");
            System.out.println("7. Высота (+-100 мм)");
            System.out.println("8. Вес (+-100 г)");
            System.out.println("0. Выход");
            System.out.print("Введите пункт меню: ");
            int num = getInputShort();
            if (num != 0) System.out.print("Введите поисковой запрос: ");
            String str = "";
            List<Furniture> result = null;
            switch (num) {
                case 0 -> {
                    return;
                }
                case 1 -> { //название
                    str = getInputString();
                    result = furnRepo.searchByString(str, BaseRepo.Columns.FURNITURE_NAME);
                }
                case 2 -> { //модель
                    str = getInputString();
                    result = furnRepo.searchByString(str, BaseRepo.Columns.FURNITURE_MODEL);
                }
                case 3-> { //стоимость
                    Double cost = getInputDouble();
                    str = cost.toString();
                    result = furnRepo.searchByRange100(str, BaseRepo.Columns.FURNITURE_COST);
                }
                case 4-> { //цвет
                    str = getInputString();
                    result = furnRepo.searchByString(str, BaseRepo.Columns.FURNITURE_COLOR);
                }
                case 5-> { //длина
                    Short len = getInputShort();
                    str = len.toString();
                    result = furnRepo.searchByRange100(str, BaseRepo.Columns.FURNITURE_LEN);
                }
                case 6-> { //ширина
                    Short wid = getInputShort();
                    str = wid.toString();
                    result = furnRepo.searchByRange100(str, BaseRepo.Columns.FURNITURE_WIDTH);
                }
                case 7-> { //высота
                    Short height = getInputShort();
                    str = height.toString();
                    result = furnRepo.searchByRange100(str, BaseRepo.Columns.FURNITURE_HEIGHT);
                }
                case 8-> { //вес
                    Integer weigh = getInputInteger();
                    str = weigh.toString();
                    result = furnRepo.searchByRange100(str, BaseRepo.Columns.FURNITURE_WEIGHT);
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
            System.out.println("Результаты поиска по запросу \"" + str + "\"");
            if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
            else showFurniture(result);
        }
    }

    void showRaw(List<String> str) {
        for (int i = 1; i < str.size(); i++) {
            if (str.get(i).equals("")) System.out.print("_ ");
            System.out.print(str.get(i) + "  ");
        }
        System.out.println();
    }

    void showFurniture(List<Furniture> list) {
        List<String> cols = List.of("Номер","Наименование","Модель","Стоимость",
                "Цвет","Длина(мм)","Ширина(мм)","Высота(мм)","Вес(г)");
        if (list.isEmpty()) {
            System.out.println("\nДанная таблица пуста!");
            return;
        }
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(cols);
        for (int i = 0; i < list.size(); i++) {
            table.addRule();
            List<String> lt = list.get(i).getRawStringList();
            lt.set(0, String.valueOf(i));
            table.addRow(lt);
        }
        table.addRule();
        String tableStr = table.render(150);
        System.out.println(tableStr);
    }

    void customerAddMenu() {
        System.out.println("\nДобавление заказчика");
        System.out.print("Введите код заказчика: ");
        Integer code = getInputInteger();
        System.out.print("Введите название заказчика: ");
        String name = getInputString();
        System.out.print("Введите город: ");
        String city = getInputString();
        System.out.print("Введите улицу: ");
        String street = getInputString();
        System.out.println("Введите номер здания: ");
        Short numBuilding = getInputShort();
        System.out.println("Введите номер телефона: ");
        Long number = Long.parseLong(getInputString());

        Address address = new Address(-1,city,street, numBuilding);
        Customer customer = new Customer(code,name,-1,number.toString());

        //add to repository
    }

    void contractAddMenu() {
        System.out.println("\nДобавление договора");
        System.out.print("Введите номер договора: ");
        Integer num = Integer.parseInt(getInputString());
        System.out.print("Введите код заказчика: ");
        Integer code = Integer.parseInt(getInputString());
        System.out.print("Введите дату регистрации: ");
        String city = getInputString();
        System.out.print("Введите дату выполнения: ");
        String street = getInputString();
    }

    void customerEditMenu() {
        System.out.println("\nИзменение таблицы Заказчики");
        System.out.println("1. Код покупателя");
        System.out.println("2. Наименование");
        System.out.println("3. Город");
        System.out.println("4. Улица");
        System.out.println("5. Номер здания");
        System.out.println("6. Номер телефона");
        System.out.println("0. Выйти в главное меню");
        System.out.println("Введите пункт меню: ");
    }

    void contractEditMenu() {
        System.out.println("\nИзменение таблицы Договор");
        System.out.println("1. Номер договора");
        System.out.println("2. Код покупателя");
        System.out.println("3. Дата регистрации");
        System.out.println("4. Дата выполнения");
        System.out.println("0. Выйти в главное меню");
        System.out.println("Введите пункт меню: ");
    }

    void saleEditMenu() {
        System.out.println("\nИзменение таблицы Продажи");
        System.out.println("1. Номер договора");
        System.out.println("2. Количество заказов");
        System.out.println("3. Мебель");
        System.out.println("0. Выйти в главное меню");
        System.out.println("Введите пункт меню: ");
    }

    String getInputString() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    Integer getInputIntegerLessMax(int max) {
        boolean flag = true;
        int num = 0;
        while (flag) {
            num = getInputInteger();
            if (num >= max) System.out.printf("\nЗначение не может быть больше %d\n", max-1);
            else flag = false;
        }
        return num;
    }

    Integer getInputInteger() {
        Integer number = 0;
        boolean flag = true;
        while(flag) {
            try {
                String str = getInputString();
                number = Integer.parseInt(str);
                if (number < 0) System.out.println("\nЗначение не может быть отрицательным!");
                else flag = false;
            } catch(NumberFormatException e) {
                System.out.println("Введённая строка не является числом!");
            }
        }
        return number;
    }

    Double getInputDouble() {
        Double number = 0.d;
        boolean flag = true;
        while(flag) {
            try {
                String str = getInputString();
                number = Double.parseDouble(str);
                flag = false;
            } catch(NumberFormatException e) {
                System.out.println("Введённая строка не является числом!");
            }
        }
        return number;
    }

    Short getInputShort() {
        Short number = 0;
        boolean flag = true;
        while(flag) {
            try {
                String str = getInputString();
                number = Short.parseShort(str);
                flag = false;
            }
            catch(NumberFormatException e) {
                System.out.println("Введённая строка не является числом " +
                        "или число выходит за диапазон допустимых значений!");
            }
        }
        return number;
    }

    public int getRandomInt() {
        return (int) (Math.random()*2000000000) + 100;
    }
}