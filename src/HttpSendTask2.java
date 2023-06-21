import com.google.gson.Gson;
import todo.UserTaskTwo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.File;
import java.io.FileWriter;

public class HttpSendTask2 {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    public static void allCommentsLastId(int idUser) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + idUser + "/posts"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        UserTaskTwo[] userForTaskTwos = GSON.fromJson(response.body(), UserTaskTwo[].class);
        int idPost = userForTaskTwos[userForTaskTwos.length-1].getId();

        allComents(idUser, idPost);
    }

    private static void allComents (int idUser, int idPost) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts/" + idPost + "/comments"))
                .GET()
                .build();

        HttpResponse <String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

        UserTaskTwo[ ] userComments = GSON.fromJson(response.body(), UserTaskTwo[].class);

        for(UserTaskTwo userComment : userComments) {

            int idPostComment = userComment.getId();
            String comment = userComment.getBody();
            writeFile(idUser, idPost, comment, idPostComment);
        }
    }

    private static void writeFile (int idUser, int idPost, String comments, int idPostComment){
        File file = new File("user-" + idUser + "-post-" + idPost + "-comments.json");

        try (FileWriter fileWriter = new FileWriter(file, true)){
            fileWriter.write("\t" + idPostComment + ". " + comments + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}