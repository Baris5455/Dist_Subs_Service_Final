package dist_servers;


import java.io.IOException;

public class Server1 {
    public static void main(String[] args) {
        try {
           AnaSunucu server1 = new AnaSunucu(1);
           server1.Power();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
