package dist_servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import comm.proto.bilgi.CapacityProto.Capacity;
import comm.proto.bilgi.ConfigurationProto;
import comm.proto.bilgi.ConfigurationProto.Configuration;
import comm.proto.bilgi.MessageProto;
import comm.proto.bilgi.MessageProto.Message;
import comm.proto.bilgi.SubscriberProto.Subscriber;

public class AnaSunucu {

    public static final int ports[] = {
            5001, 5002, 5003
    };

    private int sunucuID;
    private int port;
    private boolean CanStart = false;
    private int toleranceLevel;
    private volatile boolean sunucuBaglantiAnı = true;

    private ServerSocket sunucuSoket;
    private List<Socket> sunucuSocketleri;

    private Map<Long, Subscriber> istemciVerileri;

    AnaSunucu(int sunucuID) {
        this.sunucuID = sunucuID;
        this.port = ports[sunucuID - 1];
        this.istemciVerileri = new TreeMap<Long, Subscriber>();
        this.sunucuSocketleri = new ArrayList<Socket>();
    }

    public void Power() throws IOException {
        sunucuSoket = new ServerSocket(this.port);
        connectAdmin();
    }

    private void sunucuBaglantilari(int toleranceLevel) {
        for (int port : ports) {
            if (port == this.port)
                continue;
            if (toleranceLevel > 0) // TOLERANS SEVİYESİ AYARLAMALARI
            {
                new Thread(() -> {sunucuSoketIletimi(port);}).start();
            } else {
                break;
            }
            toleranceLevel--;
        }
        Thread thread = new Thread(() -> {acceptServerSocket();});
        thread.start();
        try {
            thread.join(2000);
            if (thread.isAlive()) {
                sunucuBaglantiAnı = false;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void sunucuSoketIletimi(int port) {
        try {
            sunucuSocketleri.add(new Socket("localhost", port));
            System.out.println("servera bağlandı");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void acceptServerSocket() {
        try {
            sunucuSoket.setSoTimeout(1000);
            while (sunucuBaglantiAnı) {
                Socket socket = sunucuSoket.accept();
                System.out.println("bir servera bağlandı");
                new Thread(() -> {handleServer(socket);}).start();
            }
        } catch (IOException e) {
            try {
                sunucuSoket.setSoTimeout(0);
                System.out.println("Diğer serverlara bağlanma zamanı doldu.");
            } catch (SocketException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void handleServer(Socket socket) {
        try {
            while (CanStart) {
                Subscriber sub = Subscriber.parseFrom(get(socket));
                addClient(sub);
            }
        } catch (IOException e) {
            System.out.println("diğer serverlardan biri ile bağlantı koptu");
            e.printStackTrace();
        }
    }

    private synchronized void sendToServer(Subscriber subscriber) throws IOException {
        for (Socket socket : sunucuSocketleri) {
            send(subscriber, socket);
        }
    }

    private synchronized void addClient(Subscriber subscriber) {
        istemciVerileri.put(subscriber.getSubscriberId(), subscriber);
    }

    private synchronized void removeClient(Long subId) {
        istemciVerileri.remove(subId);
    }

    private void handleClient(Socket clientSocket) throws IOException {
        Subscriber sub = Subscriber.parseFrom(get(clientSocket));
        if (istemciVerileri.get(sub.getSubscriberId()) != sub) {
            switch (sub.getStatus()) {
                case "SUB":
                    addClient(sub);
                    System.out.println("Başarıyla abone olundu. Abone ID : " + sub.getSubscriberId());
                    send(Subscriber.newBuilder()
                            .setSubscriberId(sub.getSubscriberId())
                            .setName(sub.getName())
                            .setStatus("STATUS: SUB")
                            .build(), clientSocket);
                    break;
                case "DEL":
                    Long subId = sub.getSubscriberId();
                    removeClient(subId);
                    System.out.println("Abone başarıyla silindi. Abone ID : " + subId);
                    send(Subscriber.newBuilder()
                            .setSubscriberId(subId)
                            .setName(sub.getName())
                            .setStatus("STATUS: DEL")
                            .build(), clientSocket);
                    break;
                case "ONLN":
                    updateSubscriberStatus(sub, "online");
                    System.out.println("Abone çevrimiçi oldu. Abone ID : " + sub.getSubscriberId());
                    send(Subscriber.newBuilder()
                            .setSubscriberId(sub.getSubscriberId())
                            .setName(sub.getName())
                            .setStatus("STATUS: ONLN")
                            .build(), clientSocket);
                    break;
                case "OFLN":
                    updateSubscriberStatus(sub, "offline");
                    System.out.println("Abone çevrimdışı oldu. Abone ID : " + sub.getSubscriberId());
                    send(Subscriber.newBuilder()
                            .setSubscriberId(sub.getSubscriberId())
                            .setName(sub.getName())
                            .setStatus("STATUS: OFLN")
                            .build(), clientSocket);
                    break;
                default:
                    System.out.println("Anlamsız istek: " + sub.getStatus());
                    send(Subscriber.newBuilder()
                            .setSubscriberId(sub.getSubscriberId())
                            .setName(sub.getName())
                            .setStatus("STATUS: INVALID")
                            .build(), clientSocket);
                    break;
            }
            sendToServer(sub);
            System.out.println("Toplam abone sayısı: " + istemciVerileri.size());
        }
    }

    private synchronized void updateSubscriberStatus(Subscriber subscriber, String newStatus) {
        Subscriber updatedSub = Subscriber.newBuilder(subscriber)
                .setStatus(newStatus)
                .build();
        istemciVerileri.put(subscriber.getSubscriberId(), updatedSub);
    }


    private void connectCilents() {
        new Thread(() -> {
            while (CanStart) {
                try {
                    Socket clientSocket = sunucuSoket.accept();
                    System.out.println("Bir bağlantı kuruldu: " + clientSocket.getRemoteSocketAddress());
                    new Thread(() -> {
                        try {
                            handleClient(clientSocket);
                        } catch (IOException e) {
                            System.out.println("client patladı");
                            //e.printStackTrace();
                        }
                    }).start();
                } catch (Exception e) {
                    System.out.println("bir şeyler olmadı");
                }
            }
        }).start();
    }

    private void connectAdmin() {
        try (Socket clientSocket = sunucuSoket.accept()) {
            System.out.println("admin clienta bağlandi");
            Configuration conf = Configuration.parseFrom(get(clientSocket));
            Message message = createMessage(conf);
            send(message, clientSocket);
            System.out.println("mesaj gönderildi");
            handleAdmin(clientSocket);
        } catch (IOException | InterruptedException e) {
            System.out.println("Admin bağlantısı kurulamadı, kesildi veya sorun oluştu. Server kapatılıyor");
            System.exit(1);
        }
    }

    private void handleAdmin(Socket adminSocket) throws InvalidProtocolBufferException, IOException, InterruptedException {
        Message message;
        Capacity recivedCapacity;
        if (CanStart) {
            sunucuBaglantilari(toleranceLevel);
            Thread.sleep(1000);
            connectCilents();
            System.out.println("Admin isteklerine yanıt verilebilir durumda");
            while (CanStart) {
                message = Message.parseFrom(get(adminSocket));
                recivedCapacity = Capacity.parseFrom(get(adminSocket));
                if (message.getDemand() == MessageProto.Demand.CPCTY && recivedCapacity.getServerId() == sunucuID) {
                    Capacity capacity = createCapacity();
                    send(capacity, adminSocket);
                }
            }
        }
    }

    //MessageLite protolordaki toByteArray fonksiyonunu barındıran arayüzdür
    public static <T extends MessageLite> void send(T response, Socket socket) throws IOException {
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        byte[] responseBytes = response.toByteArray();
        output.writeInt(responseBytes.length);
        output.write(responseBytes);
    }

    public static byte[] get(Socket socket) throws IOException {
        DataInputStream input = new DataInputStream(socket.getInputStream());
        int length = input.readInt();
        byte[] requestBytes = new byte[length];
        input.readFully(requestBytes);
        return requestBytes;
    }

    private Message createMessage(Configuration configuration) {
        toleranceLevel = configuration.getFaultToleranceLevel();
        CanStart = configuration.getMethodType() == ConfigurationProto.MethodType.START;
        return Message.newBuilder().setDemand(MessageProto.Demand.STRT).setResponse(CanStart ? MessageProto.Response.YEP : MessageProto.Response.NOP).build();
    }

    private Capacity createCapacity() {
        return Capacity.newBuilder().setServerXStatus(istemciVerileri.size()).setTimestamp(System.currentTimeMillis()/1000).setServerId(sunucuID).build();
    }
}