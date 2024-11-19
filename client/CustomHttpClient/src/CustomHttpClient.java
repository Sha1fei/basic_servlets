import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

public class CustomHttpClient {
    public static void main(String[] args) {
       var httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
        var httpRequest = HttpRequest.newBuilder(URI.create("https://www.google.com")).GET().build();
        try {
            var httpRequest2 = HttpRequest.newBuilder(URI.create("https://www.google.com"))
                    .POST(HttpRequest.BodyPublishers.ofFile(Path.of("/Users/valentinzadorozhniy/IdeaProjects/basic_servlets/client/CustomHttpClient/resources/test.json"))).build();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.headers());
            System.out.println(response.body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}