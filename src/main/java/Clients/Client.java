package Clients;

import comm.proto.bilgi.SubscriberProto;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SUNUCU_ADRESI = "localhost"; // localhost olarak ayarlandı
    private static final int SUNUCU_PORTU = 5001; // Hedef port

    public static void main(String[] args) {
        try (Socket soket = new Socket(SUNUCU_ADRESI, SUNUCU_PORTU);
             OutputStream cikisAkisi = soket.getOutputStream();
             DataInputStream veriGirisAkisi = new DataInputStream(soket.getInputStream());
             Scanner tarayici = new Scanner(System.in)) {

            System.out.println(SUNUCU_PORTU);
            // Kullanıcıdan giriş al
            System.out.print("Lütfen adınızı girin: ");
            String ad = tarayici.nextLine();

            System.out.print("Yapmak istediğiniz işlemi giriniz(SUB, DEL, ONLN, OFLN): ");
            String durum = tarayici.nextLine();

            // Gönderilecek Subscriber nesnesini oluştur
            SubscriberProto.Subscriber abone = SubscriberProto.Subscriber.newBuilder()
                    .setSubscriberId(1) // ID'yi belirleyin
                    .setName(ad) // Kullanıcıdan alınan ad
                    .setStatus(durum) // Kullanıcıdan alınan durum
                    .build();

            // Mesajı sunucuya gönder
            mesajGonder(cikisAkisi, abone);
            System.out.println("Abonelik bilgileri sunuculara gönderildi.");

            // Sunucudan yanıtı al
            SubscriberProto.Subscriber yanit = mesajAl(veriGirisAkisi);
            System.out.println("Sunucudan gelen yanıt: " + yanit);

        } catch (IOException e) {
            System.err.println("Client hatası: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static SubscriberProto.Subscriber mesajAl(DataInputStream veriGirisAkisi) throws IOException {
        int mesajUzunlugu = veriGirisAkisi.readInt();
        byte[] mesajBaytlari = new byte[mesajUzunlugu];
        veriGirisAkisi.readFully(mesajBaytlari);
        return SubscriberProto.Subscriber.parseFrom(mesajBaytlari);
    }

    private static void mesajGonder(OutputStream cikisAkisi, SubscriberProto.Subscriber mesaj) throws IOException {
        byte[] mesajBaytlari = mesaj.toByteArray();
        DataOutputStream veriCikisi = new DataOutputStream(cikisAkisi);
        veriCikisi.writeInt(mesajBaytlari.length); // Mesaj uzunluğunu yaz
        veriCikisi.write(mesajBaytlari); // Mesaj içeriğini yaz
        veriCikisi.flush(); // Veriyi gönder
    }
}
