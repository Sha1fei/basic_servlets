import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;

public class CustomUDPSocket {
    public static void main(String[] args) {
        CustomUDPSocket.runUDPClient();
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