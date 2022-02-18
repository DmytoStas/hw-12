package task1;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import user.User;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class HttpUtil {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static User sendPost(URI uri, User user) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(user);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .header("Content-type", "application/json")
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("POST status - " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }

    public static User sendPut(URI uri, int id, User updatedUser) throws IOException, InterruptedException {
        final String requestBody = GSON.toJson(updatedUser);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users/" + id))
                .header("Content-type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        final  HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("PUT status - " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }

    public static User sendGetById(URI uri, int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users/" + id))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET status - " + response.statusCode());
        return GSON.fromJson(response.body(), User.class);
    }

    public static List<User> sendGetAll(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {}.getType());
        System.out.println("GET status - " + response.statusCode());
        return users;
    }

    public static void sendDelete(URI uri, int id) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users/" + id))
                .DELETE()
                .build();
        final HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("DELETE status - " + response.statusCode());
        System.out.println("------------");
    }
    public static User sendGetByUserName(URI uri, String userName) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users?username=" + userName))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("GET status - " + response.statusCode());
        List<User> users = GSON.fromJson(response.body(), new TypeToken<List<User>>() {}.getType());
        return users.get(0);
    }
}
