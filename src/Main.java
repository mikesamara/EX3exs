

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isError = false;
        do {
            System.out.print("Введите данные (Фамилия Имя Отчество " +
                    "дата рождения" +
                    " номер телефона" +
                    " пол: ");
            String input = scanner.nextLine();

            String[] data = input.split(" ");


            try {
                if (data.length != 6) {
                    throw new IOException("Ошибка! Введено неверное количество данных.");
                }
                String surname = CheckingSurname(data[0]);
                String firstName = CheckingName(data[1]);
                String middleName = MiddleName(data[2]);
                String birthDate = isDateValid(data[3]) ;
                Long phoneNumber = ChekingNumber(data[4]);
                String gender = SexException(data[5]);

                String output = surname + ", " + firstName + ", " + middleName + ", " + birthDate +
                        "," + phoneNumber + ", " + gender + " \n";

                FileWriter fileWriter = new FileWriter(surname + ".txt", true);
                fileWriter.write(output);
                fileWriter.close();
                isError = false;
                System.out.println("Данные успешно сохранены в файле " + data[0] + ".txt");
            } catch (IOException e) {
                System.out.println("Вы ввели неверные данные!");
                System.out.println(e.getMessage());
                System.out.println("Попробуйте еще раз");
                isError = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Ошибка! Введены неполные данные.");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } while (isError);


    }

    public static String CheckingSurname(String surname) throws IOException {
        if (isNumeric(surname)) {
            throw new IOException("Фамилия не может быть числом");
        }
        return surname;
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String CheckingName(String name) throws IOException {
        if (isNumeric(name)) {
            throw new IOException("Имя не может быть числом");
        }
        return name;
    }

    public static String MiddleName(String middleName) throws IOException {
        if (isNumeric(middleName)) {
            throw new IOException("Отчество не может быть числом");
        }
        return middleName;
    }


    public static Long ChekingNumber(String number) throws IOException {
        if (number.matches("\\d+")) {
            return Long.parseLong(number);
        } else {
            throw new IOException("Номер должен содержать только цифры");
        }
    }

    public static String SexException(String gender) throws IOException {
        if (!(gender.equals("м") || gender.equals("ж"))) {
            throw new IOException("Пол указывается одной буквой: м или ж");
        }
        return gender;
    }

    public static String isDateValid(String date) throws IOException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.мм.yyyy");
        if (date.matches("^(0?[1-9]|[12][0-9]|3[01])[.](0?[1-9]|1[012])[.](0?19|20)\\d{2}$")){
            return date;
        }else{
            throw new IOException("Дата должна быть определенного образца: дд.мм.гггг");

        }
    }
}



