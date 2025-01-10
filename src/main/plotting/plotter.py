from capacity_pb2 import Capacity
from queue import Queue
import matplotlib.pyplot as plt
import socket
import struct
import threading
import traceback
import time


sunucu_verileri = {
    'sunucuA': Queue(maxsize=10),
    'sunucuB': Queue(maxsize=10),
    'sunucuC': Queue(maxsize=10)
}
kilit = threading.Lock()
calisma_baslangici = time.time()
def kapasite_verisi_isle(istemci):
    while True:
        try:
            veri_bilgisi = istemci.recv(4)
            if not veri_bilgisi:
                break
            veri_uzunlugu = struct.unpack("!I", veri_bilgisi)[0]

            kapasite_verisi = istemci.recv(veri_uzunlugu)
            kapasite_objesi = Capacity()
            try:
                kapasite_objesi.ParseFromString(kapasite_verisi)
            except Exception as hata:
                print(f"Veri ayrıştırma hatası: {hata}")
                print(traceback.format_exc())
                continue
            sunucu_isim = f"sunucu{chr(64 + kapasite_objesi.server_id)}"
            print(f"Sunucu: {sunucu_isim}, Kapasite: {kapasite_objesi.serverX_status}, Zaman: {kapasite_objesi.timestamp}")
            with kilit:
                mevcut_zaman = time.time() - calisma_baslangici
                if sunucu_isim in sunucu_verileri:
                    sunucu_kuyrugu = sunucu_verileri[sunucu_isim]
                    if sunucu_kuyrugu.full():
                        sunucu_kuyrugu.get()
                    sunucu_kuyrugu.put((kapasite_objesi.serverX_status, mevcut_zaman))
                else:
                    print(f"Bilinmeyen Sunucu ID: {kapasite_objesi.server_id}")
        except Exception as hata:
            print(f"Hata oluştu: {hata}")
            print(traceback.format_exc())
            break

def grafik_olustur():
    plt.ion()
    sekil, eksen = plt.subplots(figsize=(12, 6))

    sunucu_stil_ayarlari = {
        'sunucuA': {'renk': 'blue', 'cizgi': (5, (10, 3)), 'etiket': 'Sunucu A'},
        'sunucuB': {'renk': 'red', 'cizgi': (0, (5, 1, 1, 1)), 'etiket': 'Sunucu B'},
        'sunucuC': {'renk': 'green', 'cizgi': ':', 'etiket': 'Sunucu C'}
    }

    cizgi_elemanlari = {}
    eksen.set_xlabel("Zaman (saniye)")
    eksen.set_ylabel("Kapasite")
    eksen.set_yticks(range(0, 11, 1))
    eksen.set_xlim(0, 10)
    plt.tight_layout()

    while True:
        try:
            with kilit:
                etkin_sunucular = [isim for isim, kuyruk in sunucu_verileri.items() if not kuyruk.empty()]

                for sunucu_isim in etkin_sunucular:
                    if sunucu_isim not in cizgi_elemanlari:
                        stil = sunucu_stil_ayarlari[sunucu_isim]
                        cizgi_elemanlari[sunucu_isim] = eksen.plot(
                            [], [], color=stil['renk'], linestyle=stil['cizgi'], label=stil['etiket']
                        )[0]

                    veri_listesi = list(sunucu_verileri[sunucu_isim].queue)
                    zaman_degerleri = [veri[1] for veri in veri_listesi]
                    kapasite_degerleri = [veri[0] for veri in veri_listesi]
                    cizgi_elemanlari[sunucu_isim].set_data(zaman_degerleri, kapasite_degerleri)

                if etkin_sunucular:
                    maksimum_zaman = max(
                        max(veri[1] for veri in sunucu_verileri[sunucu].queue)
                        for sunucu in etkin_sunucular
                    )
                    eksen.set_xlim(max(0, maksimum_zaman - 10), maksimum_zaman)
                    eksen.legend()
            eksen.relim()
            eksen.autoscale_view(scaley=True, scalex=False)
            x_baslangic, x_bitis = eksen.get_xlim()
            eksen.set_xticks(range(int(x_baslangic), int(x_bitis) + 1, 1))
            plt.pause(3)

        except Exception as hata:
            print(f"Grafik oluşturma sırasında hata: {hata}")
            print(traceback.format_exc())
            break

def sunucu_islemleri():
    sunucu_soketi = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    try:
        sunucu_soketi.bind(('localhost', 5004))
        sunucu_soketi.listen(1)
        print("Sunucu hazır: Port 5004'te dinleniyor...")
        while True:
            try:
                istemci_soketi, istemci_adresi = sunucu_soketi.accept()
                print(f"Yeni istemci bağlandı: {istemci_adresi}")
                istemci_is_parcacigi = threading.Thread(
                    target=kapasite_verisi_isle,
                    args=(istemci_soketi,),
                    daemon=True
                )
                istemci_is_parcacigi.start()
            except Exception as baglanti_hatasi:
                print(f"Bağlantı sırasında hata: {baglanti_hatasi}")
                print(traceback.format_exc())
    except Exception as sunucu_hatasi:
        print(f"Sunucu başlatılamadı: {sunucu_hatasi}")
        print(traceback.format_exc())
    finally:
        sunucu_soketi.close()
        print("Sunucu kapatıldı.")
if __name__ == "__main__":
    sunucu_is_parcacigi = threading.Thread(target=sunucu_islemleri, daemon=True)
    sunucu_is_parcacigi.start()
    grafik_olustur()
