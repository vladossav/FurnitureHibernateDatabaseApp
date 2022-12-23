package org.example;

import de.vandermeer.asciitable.AsciiTable;
import org.example.entities.*;
import org.example.repositories.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;

public class Menu {
    FurnitureRepository furnRepo = new FurnitureRepository();
    AddressRepository addressRepo = new AddressRepository();
    CustomerRepository customerRepo = new CustomerRepository();
    ContractRepository contractRepo = new ContractRepository();
    SaleRepository saleRepo = new SaleRepository();

    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        new Menu();
    }

    public Menu() {
        boolean flagMainMenu = true;
        while (flagMainMenu) {
            mainMenu();
            System.out.print("Введите пункт меню: ");
            int num = getInputShort();
            switch (num) {
                default -> System.out.println("\nТакого пункта меню не существует!");
                case 0 -> flagMainMenu = false;
                case 1 -> furnitureAddMenu();
                case 2 -> furnitureEditMenu();
                case 3 -> showFurniture(furnRepo.getAll());
                case 4 -> furnitureDeleteMenu();
                case 5 -> furnitureSearchMenu();
                case 11 -> customerAddMenu();
                case 12 -> customerEditMenu();
                case 13 -> showCustomers(customerRepo.getAll());
                case 14 -> customerDeleteMenu();
                case 15 -> customerSearchMenu();
                case 21 -> contractAddMenu();
                case 22 -> contractEditMenu();
                case 23 -> showContracts(contractRepo.getAll());
                case 24 -> contractDeleteMenu();
                case 25 -> contractSearchMenu();
                case 31 -> saleAddMenu();
                case 32 -> saleEditMenu();
                case 33 -> showSales(saleRepo.getAll());
                case 34 -> saleDeleteMenu();
                case 35 -> saleSearchMenu();
            }
        }
    }

    void mainMenu() {
        System.out.println("\n------------------------ 30. База данных офисной мебели ------------------------");
        System.out.println("МЕБЕЛЬ\t\t\tЗАКАЗЧИКИ\t\tДОГОВОРЫ\t\tПРОДАЖИ");
        System.out.println("1. Добавить\t\t11. Добавить\t21. Добавить\t31. Добавить\t0. Выход");
        System.out.println("2. Изменить\t\t12. Изменить\t22. Изменить\t32. Изменить\t");
        System.out.println("3. Просмотр\t\t13. Просмотр\t23. Просмотр\t33. Просмотр\t");
        System.out.println("4. Удалить\t\t14. Удалить\t\t24. Удалить\t\t34. Удалить\t");
        System.out.println("5. Поиск\t\t15. Поиск\t\t25. Поиск\t\t35. Поиск\t");
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
            if(furnRepo.checkPrimaryKeyNotExists(id, Columns.FURNITURE_ID)) flag = false;
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
                    result = furnRepo.searchByString(str, Columns.FURNITURE_NAME);
                }
                case 2 -> { //модель
                    str = getInputString();
                    result = furnRepo.searchByString(str, Columns.FURNITURE_MODEL);
                }
                case 3-> { //стоимость
                    Double cost = getInputDouble();
                    str = cost.toString();
                    result = furnRepo.searchByRange100(str, Columns.FURNITURE_COST);
                }
                case 4-> { //цвет
                    str = getInputString();
                    result = furnRepo.searchByString(str, Columns.FURNITURE_COLOR);
                }
                case 5-> { //длина
                    Short len = getInputShort();
                    str = len.toString();
                    result = furnRepo.searchByRange100(str, Columns.FURNITURE_LEN);
                }
                case 6-> { //ширина
                    Short wid = getInputShort();
                    str = wid.toString();
                    result = furnRepo.searchByRange100(str, Columns.FURNITURE_WIDTH);
                }
                case 7-> { //высота
                    Short height = getInputShort();
                    str = height.toString();
                    result = furnRepo.searchByRange100(str, Columns.FURNITURE_HEIGHT);
                }
                case 8-> { //вес
                    Integer weigh = getInputInteger();
                    str = weigh.toString();
                    result = furnRepo.searchByRange100(str, Columns.FURNITURE_WEIGHT);
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
            System.out.println("Результаты поиска по запросу \"" + str + "\"");
            if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
            else showFurniture(result);
        }
    }

    void showFurniture(List<Furniture> list) {
        if (list.isEmpty()) {
            System.out.println("\nДанная таблица пуста!");
            return;
        }
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(Furniture.columns());
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
        boolean flag = true;
        Integer code = -1;
        while(flag) {
            System.out.print("Введите код заказчика: ");
             code = getInputInteger();
            if(customerRepo.checkPrimaryKeyNotExists(code, Columns.CUSTOMER_CODE)) flag = false;
            else System.out.println("Заказчик с таким кодом уже создан! Попробуйте еще раз");
        }
        System.out.print("Введите название заказчика: ");
        String name = getInputString();
        System.out.print("Введите город: ");
        String city = getInputString();
        System.out.print("Введите улицу: ");
        String street = getInputString();
        System.out.print("Введите номер здания: ");
        Short numBuilding = getInputShort();
        System.out.print("Введите номер телефона: ");
        String number = getInputPhone();

        flag = true;
        int id = 0;
        while(flag) {
            id = getRandomInt();
            if(addressRepo.checkPrimaryKeyNotExists(id, Columns.ADDRESS_ID)) flag = false;
        }
        Address address = new Address(id ,city,street, numBuilding);
        Customer customer = new Customer(code,name,number,address);
        addressRepo.save(address);
        customerRepo.save(customer);
        System.out.println("Объект добавлен!");
    }

    void customerEditMenu() {
        System.out.println("\nИзменение таблицы Заказчики");
        System.out.print("Введите номер строки для изменения: ");
        int numberStr = getInputIntegerLessMax(customerRepo.customerList.size());
        Customer customer = customerRepo.customerList.get(numberStr);
        boolean flag = true;
        while (flag) {
            System.out.println("\nИзменение таблицы Заказчики");
            showRaw(customer.getRawStringList());
            System.out.println("1. Код покупателя");
            System.out.println("2. Наименование");
            System.out.println("3. Город");
            System.out.println("4. Улица");
            System.out.println("5. Номер здания");
            System.out.println("6. Номер телефона");
            System.out.println("7. Сохранить изменения и выйти");
            System.out.println("0. Выйти в главное меню");
            System.out.println("Введите пункт меню: ");
            int num = getInputShort();

            switch (num) {
                case 0 -> flag = false;
                case 1 -> {
                    boolean flagCode = true;
                    Integer code = -1;
                    while(flagCode) {
                        System.out.print("Введите новый код заказчика: ");
                        code = getInputInteger();
                        if(customerRepo.checkPrimaryKeyNotExists(code, Columns.CUSTOMER_CODE)) flag = false;
                        else System.out.println("Заказчик с таким кодом уже создан! Попробуйте еще раз");
                    }
                    customer.setCode(code);
                }
                case 2 -> {
                    System.out.print("Введите новое имя заказчика: ");
                    customer.setName(getInputString());
                }
                case 3-> {
                    System.out.print("Введите новый город: ");
                    customer.getAddress().setCity(getInputString());
                }
                case 4-> {
                    System.out.print("Введите улицу: ");
                    customer.getAddress().setStreet(getInputString());
                }
                case 5-> {
                    System.out.print("Введите номер здания: ");
                    customer.getAddress().setNBuild(getInputShort());
                }
                case 6-> {
                    System.out.print("Введите новый номер телефона: ");
                    customer.setPhoneNum(getInputPhone());
                }
                case 7-> {
                    addressRepo.edit(customer.getAddress());
                    customerRepo.edit(customer);
                    System.out.println("Изменения сохранены!");
                    flag = false;
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
        }
    }

    void customerDeleteMenu() {
        System.out.println("\nУдаление из таблицы Заказчики");
        System.out.print("Введите номер строки для удаления: ");
        int numberStr = getInputIntegerLessMax(customerRepo.customerList.size());
        Customer customer = customerRepo.customerList.get(numberStr);
        addressRepo.delete(customer.getAddress());
        System.out.printf("\nСтрока %d успешно удалена!\n", numberStr);
    }

    void customerSearchMenu() {
        while (true) {
            System.out.println("\nПоиск по таблице Заказчики");
            System.out.println("1. Код покупателя");
            System.out.println("2. Наименование");
            System.out.println("3. Город");
            System.out.println("4. Улица");
            System.out.println("5. Номер телефона");
            System.out.println("0. Выход");
            System.out.print("Введите пункт меню: ");
            int num = getInputShort();
            if (num != 0) System.out.print("Введите поисковой запрос: ");
            String str = "";
            List<Customer> result = null;
            switch (num) {
                case 0 -> {
                    return;
                }
                case 1 -> { //код покупателя
                    Integer code = getInputInteger();
                    str = code.toString();
                    result = customerRepo.searchByRange100(str, Columns.CUSTOMER_CODE);
                }
                case 2 -> { //название
                    str = getInputString();
                    result = customerRepo.searchByString(str, Columns.CUSTOMER_NAME);
                }
                case 3-> { //город
                    str = getInputString();
                    result = customerRepo.searchByAddressString(str, Columns.ADDRESS_CITY);
                }
                case 4-> { //улица
                    str = getInputString();
                    result = customerRepo.searchByAddressString(str, Columns.ADDRESS_STREET);
                }
                case 5-> { //телефон
                    str = getInputPhone();
                    result = customerRepo.searchByString(str, Columns.CUSTOMER_PHONE);
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
            System.out.println("Результаты поиска по запросу \"" + str + "\"");
            if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
            else showCustomers(result);
        }
    }

    void showCustomers(List<Customer> list) {
        if (list.isEmpty()) {
            System.out.println("\nДанная таблица пуста!");
            return;
        }
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(Customer.columns());
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

    void contractAddMenu() {
        System.out.println("\nДобавление договора");
        boolean flag = true;
        Integer num = -1;
        while(flag) {
            System.out.print("Введите номер договора: ");
            num = getInputInteger();
            if(contractRepo.checkPrimaryKeyNotExists(num, Columns.CONTRACT_NUM)) flag = false;
            else System.out.println("Договор с данным номером уже существует!");
        }
        flag = true;
        Integer code = -1;
        while(flag) {
            System.out.print("Введите код заказчика: ");
             code = getInputInteger();
            if(customerRepo.checkPrimaryKeyNotExists(code, Columns.CUSTOMER_CODE))
                System.out.println("Заказчика с таким кодом не существует!");
            else flag = false;
        }
        System.out.println("Ввод даты регистрации");
        String regDate = getInputDate();
        System.out.println("Ввод даты выполнения");
        String doneDate = getInputDate();

        Contract contract = new Contract(num,code,regDate,doneDate);
        contractRepo.save(contract);
        System.out.println("Объект добавлен!");
    }

    void contractEditMenu() {
        System.out.println("\nИзменение таблицы Договоры");
        System.out.print("Введите номер строки для изменения: ");
        int numberStr = getInputIntegerLessMax(contractRepo.contractList.size());
        Contract contract = contractRepo.contractList.get(numberStr);

        while (true) {
            System.out.println("\nИзменение таблицы Договоры");
            showRaw(contract.getRawStringList());
            System.out.println("1. Номер договора");
            System.out.println("2. Код покупателя");
            System.out.println("3. Дата регистрации");
            System.out.println("4. Дата выполнения");
            System.out.println("5. Сохранить изменения и выйти");
            System.out.println("0. Выйти в главное меню");
            System.out.println("Введите пункт меню: ");
            int n = getInputShort();

            switch (n) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    boolean flagCode = true;
                    Integer number = -1;
                    while(flagCode) {
                        System.out.print("Введите новый номер договора: ");
                        number = getInputInteger();
                        if(contractRepo.checkPrimaryKeyNotExists(number, Columns.CONTRACT_NUM)) flagCode = false;
                        else System.out.println("Договор с данным номером уже существует!");
                    }
                    contract.setNumber(number);
                }
                case 2 -> {
                    boolean flagCode = true;
                    Integer code = -1;
                    while(flagCode) {
                        System.out.print("Введите новый код заказчика: ");
                        code = getInputInteger();
                        if(customerRepo.checkPrimaryKeyNotExists(code, Columns.CUSTOMER_CODE))
                            System.out.println("Заказчика с таким кодом не существует!");
                        else flagCode = false;
                    }
                    contract.setCustomerCode(code);
                }
                case 3-> {
                    System.out.println("Ввод новой даты регистрации");
                    String regDate = getInputDate();
                   contract.setRegDate(regDate);
                }
                case 4-> {
                    System.out.println("Ввод новой даты выполнения");
                    String doneDate = getInputDate();
                    contract.setDoneDate(doneDate);
                }
                case 5-> {
                    contractRepo.edit(contract);
                    System.out.println("Изменения сохранены!");
                    return;
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
        }
    }

    void showContracts(List<Contract> list) {
        if (list.isEmpty()) {
            System.out.println("\nДанная таблица пуста!");
            return;
        }
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(Contract.columns());
        for (int i = 0; i < list.size(); i++) {
            table.addRule();
            List<String> lt = list.get(i).getRawStringList();
            lt.set(0, String.valueOf(i));
            table.addRow(lt);
        }
        table.addRule();
        String tableStr = table.render(60);
        System.out.println(tableStr);
    }

    void contractDeleteMenu() {
        System.out.println("\nУдаление из таблицы Договоры");
        System.out.print("Введите номер строки для удаления: ");
        int numberStr = getInputIntegerLessMax(contractRepo.contractList.size());
        Contract contract = contractRepo.contractList.get(numberStr);
        contractRepo.delete(contract);
        System.out.printf("\nСтрока %d успешно удалена!\n", numberStr);
    }

    void contractSearchMenu() {
        while (true) {
            System.out.println("\nПоиск по таблице Договоры");
            System.out.println("1. Номер договора");
            System.out.println("2. Код покупателя");
            System.out.println("3. Дата регистрации");
            System.out.println("4. Дата выполнения");
            System.out.println("0. Выход");
            System.out.print("Введите пункт меню: ");
            int num = getInputShort();
            if (num != 0) System.out.print("Введите поисковой запрос: ");
            String str = "";
            List<Contract> result = null;
            switch (num) {
                case 0 -> {
                    return;
                }
                case 1 -> { //номер договора
                    Integer number = getInputInteger();
                    str = number.toString();
                    result = contractRepo.searchByRange100(str, Columns.CONTRACT_NUM);
                }
                case 2 -> { //код покупателя
                    Integer code = getInputInteger();
                    str = code.toString();
                    result = contractRepo.searchByRange100(str, Columns.CONTRACT_CUSTOMER_CODE);
                }
                case 3-> { //дата регистрации
                    str = getInputString();
                    result = contractRepo.searchByString(str, Columns.CONTRACT_REG_DATE);
                }
                case 4-> { //дата выполнения
                    str = getInputString();
                    result = contractRepo.searchByString(str, Columns.CONTRACT_DONE_DATE);
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
            System.out.println("Результаты поиска по запросу \"" + str + "\"");
            if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
            else showContracts(result);
        }
    }

    void saleAddMenu() {
        System.out.println("\nДобавление Продажи");
        boolean flag = true;
        Integer num = -1;
        while(flag) {
            System.out.print("Введите номер договора: ");
            num = getInputInteger();
            if(contractRepo.checkPrimaryKeyNotExists(num, Columns.CONTRACT_NUM))
                System.out.println("Договора с данным номером не существует!");
            else flag = false;
        }
        
        Furniture furniture = null;
        System.out.println("Выбор мебели");
        System.out.println("1. Выбрать из всего списка");
        System.out.println("2. Воспользоваться поиском");
        System.out.println("0. Отмена операции и выход в главное меню");
        switch (getInputShort()) {
            case 0 -> {
                return;
            }
            case 1 -> {
                showFurniture(furnRepo.getAll());
                System.out.print("Введите номер строки для добавления: ");
                int numStr = getInputIntegerLessMax(furnRepo.furnList.size());
                furniture = furnRepo.furnList.get(numStr);
            }
            case 2 -> {
                while (true) {
                    System.out.println("\nПоиск по таблице Мебель");
                    System.out.println("1. Название");
                    System.out.println("2. Модель");
                    System.out.println("3. Цвет");
                    System.out.print("Введите пункт меню: ");
                    int n = getInputShort();
                    if (n != 0) System.out.print("Введите поисковой запрос: ");
                    String str = "";
                    List<Furniture> result = null;
                    switch (n) {
                        case 1 -> { //название
                            str = getInputString();
                            result = furnRepo.searchByString(str, Columns.FURNITURE_NAME);
                        }
                        case 2 -> { //модель
                            str = getInputString();
                            result = furnRepo.searchByString(str, Columns.FURNITURE_MODEL);
                        }
                        case 3 -> { //color
                            str = getInputString();
                            result = furnRepo.searchByString(str, Columns.FURNITURE_COLOR);
                        }
                        default -> System.out.println("\nВведен некорректный пункт меню!");
                    }
                    System.out.println("Результаты поиска по запросу \"" + str + "\"");
                    if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
                    else {
                        showFurniture(result);
                        System.out.print("Введите номер строки для добавления: ");
                        int numStr = getInputIntegerLessMax(result.size());
                        furniture = result.get(numStr);
                        break;
                    }
                }
            }
        }
        flag = true;
        int id = 0;
        while(flag) {
            id = getRandomInt();
            if(saleRepo.checkPrimaryKeyNotExists(id, Columns.SALE_ID)) flag = false;
        }
        System.out.print("Введите количество товара: ");
        Integer amount = getInputInteger();
        Sale sale = new Sale(id, num, furniture, amount);
        saleRepo.save(sale);
        System.out.println("Объект добавлен!");
    }

    void saleEditMenu() {
        System.out.println("\nИзменение таблицы Продажи");
        System.out.print("Введите номер строки для изменения: ");
        int numberStr = getInputIntegerLessMax(saleRepo.saleList.size());
        Sale sale = saleRepo.saleList.get(numberStr);

        while (true) {
            System.out.println("\nИзменение таблицы Продажи");
            showRaw(sale.getRawStringList());
            System.out.println("1. Номер договора");
            System.out.println("2. Мебель");
            System.out.println("3. Количество заказов");
            System.out.println("4. Сохранить изменения и выйти");
            System.out.println("0. Выйти в главное меню");
            System.out.println("Введите пункт меню: ");
            int nn = getInputShort();

            switch (nn) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    boolean flag = true;
                    Integer num = -1;
                    while(flag) {
                        System.out.print("Введите номер договора: ");
                        num = getInputInteger();
                        if(contractRepo.checkPrimaryKeyNotExists(num, Columns.CONTRACT_NUM))
                            System.out.println("Договора с данным номером не существует!");
                        else flag = false;
                    }
                    sale.setContractNum(num);
                }
                case 2 -> {
                    Furniture furniture = null;
                    System.out.println("Выбор новой мебели");
                    System.out.println("1. Выбрать из всего списка");
                    System.out.println("2. Воспользоваться поиском");
                    System.out.println("0. Отмена операции и выход в главное меню");
                    switch (getInputShort()) {
                        case 0 -> {
                            return;
                        }
                        case 1 -> {
                            showFurniture(furnRepo.getAll());
                            System.out.print("Введите номер строки для добавления: ");
                            int numStr = getInputIntegerLessMax(furnRepo.furnList.size());
                            furniture = furnRepo.furnList.get(numStr);
                        }
                        case 2 -> {
                            while (true) {
                                System.out.println("\nПоиск по таблице Мебель");
                                System.out.println("1. Название");
                                System.out.println("2. Модель");
                                System.out.println("3. Цвет");
                                System.out.print("Введите пункт меню: ");
                                int n = getInputShort();
                                if (n != 0) System.out.print("Введите поисковой запрос: ");
                                String str = "";
                                List<Furniture> result = null;
                                switch (n) {
                                    case 1 -> { //название
                                        str = getInputString();
                                        result = furnRepo.searchByString(str, Columns.FURNITURE_NAME);
                                    }
                                    case 2 -> { //модель
                                        str = getInputString();
                                        result = furnRepo.searchByString(str, Columns.FURNITURE_MODEL);
                                    }
                                    case 3 -> { //color
                                        str = getInputString();
                                        result = furnRepo.searchByString(str, Columns.FURNITURE_COLOR);
                                    }
                                    default -> System.out.println("\nВведен некорректный пункт меню!");
                                }
                                System.out.println("Результаты поиска по запросу \"" + str + "\"");
                                if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
                                else {
                                    showFurniture(result);
                                    System.out.print("Введите номер строки для добавления: ");
                                    int numStr = getInputIntegerLessMax(result.size());
                                    furniture = result.get(numStr);
                                    break;
                                }
                            }
                        }
                    }
                    sale.setFurniture(furniture);
                }
                case 3-> {
                    System.out.print("Введите количество товара: ");
                    Integer amount = getInputInteger();
                    sale.setAmount(amount);
                }
                case 4-> {
                    saleRepo.edit(sale);
                    System.out.println("Изменения сохранены!");
                    return;
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
        }
    }

    void saleDeleteMenu() {
        System.out.println("\nУдаление из таблицы Продажи");
        System.out.print("Введите номер строки для удаления: ");
        int numberStr = getInputIntegerLessMax(saleRepo.saleList.size());
        Sale sale = saleRepo.saleList.get(numberStr);
        saleRepo.delete(sale);
        System.out.printf("\nСтрока %d успешно удалена!\n", numberStr);
    }

    void saleSearchMenu() {
        while (true) {
            System.out.println("\nПоиск по таблице Продажи");
            System.out.println("1. Номер договора");
            System.out.println("2. Название мебели");
            System.out.println("3. Модель мебели");
            System.out.println("4. Цвет мебели");
            System.out.println("5. Количество заказов");
            System.out.println("0. Выход");
            System.out.print("Введите пункт меню: ");
            int num = getInputShort();
            if (num != 0) System.out.print("Введите поисковой запрос: ");
            String str = "";
            List<Sale> result = null;
            switch (num) {
                case 0 -> {
                    return;
                }
                case 1 -> { //номер договора
                    Integer code = getInputInteger();
                    str = code.toString();
                    result = saleRepo.searchByRange100(str, Columns.SALE_CONTRACT_NUM);
                }
                case 2 -> { //название
                    str = getInputString();
                    result = saleRepo.searchByFurnitureString(str, Columns.FURNITURE_NAME);
                }
                case 3-> { //модель
                    str = getInputString();
                    result = saleRepo.searchByFurnitureString(str, Columns.FURNITURE_MODEL);
                }
                case 4-> { //цвет
                    str = getInputString();
                    result = saleRepo.searchByFurnitureString(str, Columns.FURNITURE_COLOR);
                }
                case 5-> { //количество
                    str = getInputPhone();
                    result = saleRepo.searchByRange100(str, Columns.SALE_AMOUNT);
                }
                default -> System.out.println("\nВведен некорректный пункт меню!");
            }
            System.out.println("Результаты поиска по запросу \"" + str + "\"");
            if (result.isEmpty() || result.equals(null)) System.out.println("\nНичего не найдено!");
            else showSales(result);
        }
    }

    void showSales(List<Sale> list) {
        if (list.isEmpty()) {
            System.out.println("\nДанная таблица пуста!");
            return;
        }
        AsciiTable table = new AsciiTable();
        table.addRule();
        table.addRow(Sale.columns());
        for (int i = 0; i < list.size(); i++) {
            table.addRule();
            List<String> lt = list.get(i).getRawStringList();
            lt.set(0, String.valueOf(i));
            table.addRow(lt);
        }
        table.addRule();
        String tableStr = table.render(100);
        System.out.println(tableStr);
    }

    void showRaw(List<String> str) {
        for (int i = 1; i < str.size(); i++) {
            if (str.get(i).equals("")) System.out.print("_ ");
            System.out.print(str.get(i) + "  ");
        }
        System.out.println();
    }

    String getInputDate() {
        boolean flag = true;
        String inputDate;
        short day = 0, month = 0, year = 0;
        while (flag) {
            System.out.print("Введите день: ");
            day = getInputShort();
            System.out.print("Введите месяц: ");
            month = getInputShort();
            System.out.print("Введите год: ");
            year = getInputShort();
            if (!isValidDate(day,month,year)) System.out.println("Дата введена неверно! Попробуйте еще раз");
            else flag = false;
        }
        String format = "%d.%d.%d";
        if (day < 10) format = "0%d.%d.%d";
        if (month < 10) format = "%d.0%d.%d";
        if (day < 10 && month < 10) format = "0%d.0%d.%d";
        inputDate = String.format(format, day,month, year);
        return inputDate;
    }

    boolean isValidDate(short day, short month, short year) {
        short[] daysInMonth = { 0,31,28,31,30,31,30,31,31,30,31,30,31 };

        if (year < 2020 || year > 2050) return false;
        if (year % 4 == 0)
            daysInMonth[2] = 29;
        if ((month < 1) || (month > 12)) return false;
        return (day >= 1) && (day <= daysInMonth[month]);
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

    String getInputPhone() {
        String str ="";
        boolean flag = true;
        while(flag) {
            try {
                str = getInputString();
                Long number = Long.parseLong(str);
                if (number < 0 || number > 89999999999L)
                    System.out.println("\nЗначение выходит за диапазон допустимых значений!");
                else flag = false;
            } catch(NumberFormatException e) {
                System.out.println("Введённая строка не является числом!");
            }
        }
        return str;
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
