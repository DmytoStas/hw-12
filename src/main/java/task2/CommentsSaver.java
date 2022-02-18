package task2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import user.Comment;
import user.Post;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class CommentsSaver {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();

    private static Gson gson = new Gson();


    public static Post sendGetPostByUserId(URI uri, int id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/users/" + id + "/posts?id=" + (id * 10)))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Post> posts = gson.fromJson(response.body(), new TypeToken<List<Post>>() {
        }.getType());
        //System.out.println("GET status - " + response.statusCode());
        return posts.get(0);
    }

    public static List<Object> sendGetAllCommentsToPost(URI uri, int userId) throws IOException, InterruptedException {
        Post userPost = sendGetPostByUserId(uri, userId);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri + "/posts/" + userPost.getId() + "/comments"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<Comment> comments = gson.fromJson(response.body(), new TypeToken<List<Comment>>() {
        }.getType());
        System.out.println("GET status - " + response.statusCode());

        List<Object> objects = new ArrayList<>();
        objects.add(userPost);
        objects.addAll(comments);

        System.out.println("Останній пост користувача по id=" + userId + ":\n" + userPost);
        System.out.println("-----------");
        System.out.println("Коментарі до посту:");
        for (Comment comment : comments) {
            System.out.println(" - " + comment);
        }
        System.out.println("-----------");
        return objects;
    }

    public static void writeJsonFile(URI uri, int userId) throws IOException, InterruptedException {

        List<Object> commentsToPost = CommentsSaver.sendGetAllCommentsToPost(uri, userId);

        gson = new GsonBuilder().setPrettyPrinting().create();

        String jsonString = gson.toJson(commentsToPost);

        try (FileWriter writer = new FileWriter(".//src//main//resources//user-" + userId + "-post-" + (userId * 10) + ".json")) {
            writer.write(jsonString);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        System.out.println("Json файл створено!");

    }
}
