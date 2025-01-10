PROGRAMIN GENEL İŞLEYİŞİ
-----------------------------------------------------
Bu program, sunuculardan gelen kapasite verilerini gerçek zamanlı olarak alır ve görselleştirir. Sunucu, soket bağlantıları üzerinden istemcilerle iletişim kurarak Protobuf formatında gelen kapasite verilerini işler ve her sunucu için bir kuyrukta saklar. Toplanan veriler matplotlib ile sürekli güncellenen bir grafik üzerinde görselleştirilir. Program, çoklu iş parçacığı kullanarak hem istemcilerden veri almayı hem de grafikleri dinamik olarak güncellemeyi aynı anda gerçekleştirir. Hata yönetimi ve veri güvenliği için kilit ve hata yakalama mekanizmaları kullanılmıştır.

---------------------------------------------------------------

PROGRAMIN ÖZELLİK VE FONKSİYONLARI:
-----------------------------------------------------
### plotter.py fonksiyonları:

- [x] Kapasite Verilerinin İşlenmesi: Sunucudan gelen kapasite verileri soket üzerinden alınır. Protobuf ile ayrıştırılarak kapasite ve zaman bilgileri sunucu adlarına göre kuyruğa eklenir.
- [x] Sunucu Verilerinin Yönetimi: Kapasite verileri, her sunucu için ayrı bir kuyrukta tutulur. Kuyruklar maksimum 10 kapasiteye sahiptir ve dolu olduğunda en eski veri çıkarılır.
- [x] Veri Güvenliği İçin Kilit Mekanizması: Birden fazla iş parçacığının aynı anda veri kuyruğuna erişimini kontrol etmek için kilit mekanizması (threading.Lock) kullanılır.
- [x] Grafik Görselleştirme: Sunuculardan gelen kapasite verileri matplotlib kullanılarak gerçek zamanlı bir grafik üzerinde görselleştirilir.
Grafikler, her sunucu için ayrı renkte ve tarzda çizilir.
- [x] Dinamik Ölçekleme: Grafik, zaman eksenine göre dinamik olarak ölçeklenir. Görselleştirme her 3 saniyede bir güncellenir.
- [x] Sunucu Dinleme ve İstemci Bağlantıları: Sunucu, localhost:5004 üzerinde soket bağlantılarını dinler. Yeni istemciler bağlandığında ayrı bir iş parçacığı başlatılır.
- [x] Çoklu İstemci İşlemi: Her istemci bağlantısı için ayrı bir iş parçacığı oluşturulur ve bu iş parçacığı kapasite verilerini alıp işler.
- [x] Hata Yönetimi: Tüm veri işleme ve bağlantı süreçlerinde hatalar yakalanır ve ayrıntılı hata raporları konsola yazdırılır.
- [x] Çoklu İş Parçacıklı Çalışma: Sunucu işlemleri ve grafik oluşturma ayrı iş parçacıklarında çalıştırılarak sistemin eş zamanlı olarak veri işlemesi ve grafik oluşturması sağlanır.

### admin.rb fonksiyonları:

- [x] Konfigürasyon Okuma: Admin, dist_server.conf dosyasını okuyarak hata tolerans seviyesini (fault tolerance level) alır.
- [x] Java Sunucularına Bağlantı: Admin, tanımlı port numaraları üzerinden Java sunucularına bağlanmaya çalışır. Her bağlantı başarılıysa, bu sunucuların soketleri kaydedilir.
- [x] Python Sunucusuna Bağlantı: Python sunucusuna belirlenmiş port üzerinden bağlantı sağlanır ve soket kaydedilir. Bağlantı hataları raporlanır.
- [x] Sunucu Konfigürasyonu Gönderimi: Admin, bağlı sunuculara protokol formatında hata tolerans seviyesi ve başlatma komutunu içeren bir yapı (Configuration) gönderir.
Sunucudan gelen cevaplar (başarılı/başarısız) işlenir.
- [x] Python Sunucusuna Kapasite Bilgisi Gönderme: Alınan kapasite bilgileri Python sunucusuna protokol formatında gönderilir. Bu, Java sunucularındaki kapasitenin merkezi olarak işlenmesine olanak tanır.
- [x] Bağlantı Hata Yönetimi: Bağlantı kopmaları ve iletişim hataları her aşamada işlenir. Sorun çıktığında tekrar bağlantı denemesi yapılır.
- [x] Sürekli İzleme ve Yeniden Bağlantı: Admin, bağlantıları düzenli olarak kontrol eder ve sorun oluşursa bağlantı işlemini yeniden başlatır.
- [x] Dinamik Sunucu Yönetimi: Tüm sunucuların çalışabilir durumda olduğundan emin olunarak, istekler ve veri aktarımı döngüsel olarak yönetilir.

### ServerX.java fonksiyonları:

- [x] Başlatma ve Admin Bağlantısı: Server, Admin_client.rb tarafından başlatma komutu alana kadar çalışmaz. Admin ile bağlantı kurularak başlangıç ayarları ve tolerans seviyesi alınır. Başarılı bir bağlantı kurulduğunda geri dönüş yapılır.
- [x] Tolerans Seviyesi Yönetimi: Server, hata toleransı seviyesine göre diğer sunuculara bağlanmaya çalışır. Belirtilen tolerans seviyesi kadar yedek sunucularla iletişim kurulur ve bağlantılar sağlanamazsa bu durum yönetilir.
- [x] Hata Yönetimi: Hata toleransı prensiplerine uygun şekilde sunucular arasında bağlantı kopması durumunda diğer sunucular çalışmaya devam eder. Hatalar loglanır ve gerekirse bağlantılar yeniden başlatılır.
- [x] Client Bağlantıları: Server, client'ların SUB (ekleme) ve DEL (silme) işlemlerini yönetir. Gelen istekler doğru bir şekilde işlenir ve ilgili client'lara durum güncellemeleri gönderilir.
- [x] Senkrone Çalışma ve Çoklu İstek Yönetimi: Aynı anda birden fazla istek karşılanabilir. Gelen isteklerin client'tan mı yoksa diğer sunuculardan mı geldiği ayırt edilir.
- [x] Yedekleme ve Veri Paylaşımı: Sunucular arasında yedekleme yapılabilir. Yeni bir client ekleme veya silme işlemi, diğer sunucularla senkronize edilir.
- [x] Kapasite Yönetimi: Admin'den gelen kapasite sorgularına yanıt verilir. Sunucu üzerindeki mevcut abone sayısı ve durum bilgisi protokol üzerinden admin'e iletilir.
- [x] Protokol Kullanımı: Tüm iletişim işlemleri için Protobuf kullanılır. Veriler, Protobuf formatında gönderilir ve alınır.

---------------------------------------------------------------

Ekip Üyeleri ve Görevleri
--------------------------------
•Barış KAYA - 22060359 -> Server BackEnd Tasarımı.

•Veli Buğra ŞAHİN - 22060389 -> Client Tasarımı , Host ve Port Ayarlamaları.

•Nazımhan GÜR - 22060361 -> Panel ve Admin Kodlaması.

•Emir KILIÇER - 22060376 -> Plotting Kodlaması ve Kapasite Görsellemesi.

------------------------------------

Youtube Linki
-----------------------------
https://www.youtube.com/watch?v=YM9pdN7Gxqs

