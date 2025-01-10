package dist_servers;


import java.io.IOException;

public class Server3 {
    public static void main(String[] args) {
        try {
            AnaSunucu server1 = new AnaSunucu(3);
            server1.Power();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
