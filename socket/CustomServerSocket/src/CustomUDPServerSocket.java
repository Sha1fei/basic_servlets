import java.io.IOException;
import java.net.*;

public class CustomUDPServerSocket {
    public static void main(String[] args) {
        CustomUDPServerSocket.runUDPServer();
    }

    public static void runUDPServer(){
        try (var datagramServer = new DatagramSocket(7777);) {
            byte[] buffer = new byte[21];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            datagramServer.receive(packet);
            System.out.println(new String(buffer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}