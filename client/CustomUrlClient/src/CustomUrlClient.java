import java.io.IOException;
import java.net.URL;

public class CustomUrlClient {
    public static void main(String[] args) {
        try {
            var url = new URL("https://www.google.com"); // обращение к урл
            var urlConnection = url.openConnection();
            System.out.println(urlConnection.getURL());
            System.out.println(urlConnection.getHeaderField("Content-Type"));
            System.out.println(new String(urlConnection.getInputStream().readAllBytes()));

            var url2 = new URL("file:/Users/valentinzadorozhniy/IdeaProjects/basic_servlets/client/CustomUrlClient/resources/test.json"); // обращение к файлу
            var urlConnection2 = url2.openConnection();
            System.out.println(new String(urlConnection2.getInputStream().readAllBytes()));
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}