import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class CustomHttpSocket {
    public static void main(String[] args) {
        CustomHttpSocket.runHttpClient();
    }
    public static void runHttpClient(){
        try {
            var inetAddress = Inet4Address.getByName("localhost");
            try (var socket = new Socket(inetAddress, 7777);
                 var outputStream = new DataOutputStream(socket.getOutputStream());
                 var inputStream = new DataInputStream(socket.getInputStream());
                 var scanner = new Scanner(System.in)) {
                while (scanner.hasNextLine()) {
                    var request = scanner.nextLine();
                    outputStream.writeUTF(request);
                    var response = inputStream.readUTF();
                    System.out.println("Server: " + response);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}