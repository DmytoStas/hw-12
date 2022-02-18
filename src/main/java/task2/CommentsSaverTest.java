package task2;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;


public class CommentsSaverTest {

    private static final URI POST_URL = URI.create("https://jsonplaceholder.typicode.com");


    //Файл Json створюється тільки після того як scanner завершить роботу
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------");
            System.out.println("Щоб отримати всі коментарі до остраннього посту користувача який вас цікавить\n" +
                    "Введіть id (Може бути від 1 до 10)");

            String input = scanner.nextLine();
            if (!input.matches(".*\\d.*")) {
                 if (input.equals("exit")) {
                    System.out.println("Бувай!");
                    System.exit(0);
                } else {
                     System.out.println("Некоректне введення!");
                 }
            } else {
                int inputId = Integer.parseInt(input);
                if (inputId > 0 && inputId <= 10) {
                    CommentsSaver.writeJsonFile(POST_URL, inputId);
                } else {
                    System.out.println("Неправильний id користувача");
                }
            }
        }
    }
}
