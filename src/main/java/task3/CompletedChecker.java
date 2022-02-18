package task3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import user.UserTodos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletedChecker {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static List<UserTodos> sendGetAll(URI uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/todos"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<UserTodos> usersTodos = GSON.fromJson(response.body(), new TypeToken<List<UserTodos>>() {
        }.getType());
        System.out.println("GET status - " + response.statusCode());
        return usersTodos;
    }

    public void checkCompletedJob(URI uri, int userId) throws IOException, InterruptedException {
        List<UserTodos> userTodos = sendGetAll(uri);
        Stream<UserTodos> stream = userTodos.stream()
                .filter(it -> it.getUserId() == userId)
                .filter(it -> !it.isCompleted());

        List<UserTodos> collect = stream.collect(Collectors.toList());

        prettyPrint(collect);

    }

    private void prettyPrint(List<UserTodos> collect) {
        System.out.println("Незавершені задачі для користувача по userId = " + collect.get(0).getUserId() + ":");
        for (UserTodos item : collect) {
            System.out.println(item);
        }
    }
}
