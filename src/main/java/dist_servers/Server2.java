package dist_servers;


import java.io.IOException;

public class Server2 {
    public static void main(String[] args) {
        try {
            AnaSunucu server1 = new AnaSunucu(2);
            server1.Power();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
