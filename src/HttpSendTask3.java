import com.google.gson.Gson;
import todo.UserTaskTwo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpSendTask3 {

    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();

    public static void openTaskForUser (int id) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://jsonplaceholder.typicode.com/users/" + id + "/todos"))
                .GET()
                .build();

        HttpResponse<String> response = CLIENT.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        UserTaskTwo[] userOpenTasks = GSON.fromJson(response.body(), UserTaskTwo[].class);
        int i = 1;
        for (UserTaskTwo userOpenTask : userOpenTasks){
            if (userOpenTask.getCompleted().equals("false")){
                System.out.println(i + ". " + userOpenTask.getTitle());
                i++;
            }
        }
    }
}
