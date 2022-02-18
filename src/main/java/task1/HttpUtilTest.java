package task1;

import user.parameters.Address;
import user.User;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class HttpUtilTest {

    private static final String FILE_PATH = "src/main/resources/user.json";

    private static final URI USERS_URL = URI.create("https://jsonplaceholder.typicode.com");

    public static void main(String[] args) throws IOException, InterruptedException {

         int userId = 6;

         String userName = "Antonette";

        //Створений дефолтний користувач з файлу user.json
        User user = User.createUserFromJson(FILE_PATH);

        //Метод POST для користувача
        User sentUser = HttpUtil.sendPost(USERS_URL, user);
        System.out.println("Відповідь з сервера на метод sendPost:");
        System.out.println(sentUser);
        System.out.println("------------");

        //Метод GET за id номером користувача
        User getById = HttpUtil.sendGetById(USERS_URL, userId);
        System.out.println("Користувач за номером id = " + userId + ":\n" + getById);
        System.out.println("------------");

        //Метод PUT(update) для користувача за необхідним id номером
        Address address = getById.getAddress();
        address.setCity("Cherkasy");
        User putUser = HttpUtil.sendPut(USERS_URL, userId, getById);
        System.out.println("Оновлений користувачза номером id = " + userId + ":\n" + putUser);
        System.out.println("------------");

        //Метод DELETE для користувача за необхідним id номером
        HttpUtil.sendDelete(USERS_URL, userId);

        //Метод GET для всіх користувачів на сайті
        List<User> users = HttpUtil.sendGetAll(USERS_URL);
        System.out.println("Список всіх користувачів:");
        for (User account : users) {
            System.out.println(account);
        }
        System.out.println("------------");

        //Метод GET за userName користувача
        User getByUserName = HttpUtil.sendGetByUserName(USERS_URL, userName);
        System.out.println("Користувач за логіном = " + userName + ":\n" + getByUserName);
        System.out.println("------------");
    }


}
