import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class CustomServerSocket {
    public static void main(String[] args) {
        try (var serverSocket = new ServerSocket(7777);
             var socket = serverSocket.accept();
             var outputStream = new DataOutputStream(socket.getOutputStream());
             var inputStream = new DataInputStream(socket.getInputStream());
             var scanner = new Scanner(System.in)
             ) {
            var request = inputStream.readUTF();
            while (!"exit".equalsIgnoreCase(request)) {
                System.out.println("Client: " + request);
                var response = scanner.nextLine();
                outputStream.writeUTF(response);
                request = inputStream.readUTF();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}