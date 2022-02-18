package task3;

import task2.CommentsSaver;

import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

public class CompletedCheckerTest {

    private static final URI COMPLETED_URL = URI.create("https://jsonplaceholder.typicode.com");

    public static void main(String[] args) throws IOException, InterruptedException {

        CompletedChecker completedChecker = new CompletedChecker();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("-----------");
            System.out.println("Щоб отримати всі невиконані завдання користувача який вас цікавить\n" +
                    "Введіть id (Може бути від 1 до 10)");

            String input = scanner.nextLine();
            if (!input.matches(".*\\d.*")) {
                if (input.equals("exit")) {
                    System.out.println("Бувай!");
                    System.exit(0);
                }
                System.out.println("Некоректне введення!");
            } else {
                int inputId = Integer.parseInt(input);
                if (inputId > 0 && inputId <= 10) {
                    completedChecker.checkCompletedJob(COMPLETED_URL, inputId);
                }
                System.out.println("Неправильний id користувача");
            }
        }
    }
}
