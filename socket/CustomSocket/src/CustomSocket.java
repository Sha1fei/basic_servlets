import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Scanner;

public class CustomSocket {
    public static void main(String[] args) {
        CustomSocket.runHttpClient();
        CustomSocket.runUDPClient(); // запускать либо http либо udp
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
    public static void runUDPClient(){
        try {
            var inetAddress = Inet4Address.getByName("localhost");
            try (var datagramSocket = new DatagramSocket();) {
                var bytes = "Hello from UDP client".getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, bytes.length, inetAddress, 7777);
                datagramSocket.send(packet);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}