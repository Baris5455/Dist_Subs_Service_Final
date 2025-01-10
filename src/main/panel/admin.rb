require_relative 'configuration_pb'
require_relative 'capacity_pb'
require_relative 'message_pb'

require 'socket'

CONFIG = "dist_server.conf"
HOST = "localhost"
JAVA_SERVER_PORT = [5001, 5002, 5003]
PYTHON_SERVER_PORT = 5004

def config_erisim(conf)
  fault_tolerance_level = 0
  begin
    File.open(conf, 'r') do |file|
      file.each_line do |line|
        fault_tolerance_level = line.split('=', 2).last.to_i
      end
    end
  rescue Errno::ENOENT
    puts "Okunacak dosya bulunamadı."
  rescue => e
    puts "Dosya okunurken hata oluştu: #{e.message}"
  end
  fault_tolerance_level
end

def java_sunucularına_baglanma
  connection = {}

  JAVA_SERVER_PORT.each_with_index do |port, index|
    sunucu_id = index + 1
    begin
      soket = TCPSocket.new(HOST, port)
      puts "Java sunucu #{sunucu_id} bağlantısı kuruldu port: #{port}"
      connection[sunucu_id] = soket
    rescue Errno::ECONNREFUSED
      puts "Sunucu: #{sunucu_id} ile (port: #{port})  arasında bağlantı problemi."
    rescue => e
      puts "Sunucu #{sunucu_id} bağlantı hatası oluştu: #{e.message}"
    end
  end

  connection
end

def python_sunucusuna_baglanma
  begin
    soket = TCPSocket.new(HOST, PYTHON_SERVER_PORT)
    puts "Python sunucusuyla bağlantı sağlandı port: #{PYTHON_SERVER_PORT}"
    return soket
  rescue Errno::ECONNREFUSED
    puts "Python sunucusu ile (#{PYTHON_SERVER_PORT}) numaralı port arasında bağlantı problemi."
  rescue => e
    puts "Python sunucusun bağlanılamadı: #{e.message}"
  end
  nil
end

def config_iletisimi(soket, fault_tolerance_level)
  begin
    config = Info::Configuration.new(fault_tolerance_level: fault_tolerance_level, method_type: Info::MethodType::START)
    puts "Gönderilen Fault tolerance leveli: #{fault_tolerance_level}"
    config_proto = config.to_proto
    soket.write([config_proto.size].pack("N") + config_proto)
    puts "Config gönderildi"

    cevap_boyutu = soket.read(4).unpack("N")[0]
    proto_cevabı = soket.read(cevap_boyutu)
    cevap = Info::Message.decode(proto_cevabı)
    puts "Sunucu cevabı: #{cevap.demand}, #{cevap.response}"

    cevap
  rescue IOError
    puts "Server bağlantı hatası."
  rescue => e
    puts "İletişim hatası: #{e.message}"
  end
end

def kapasite_sorgusu(soket, sunucu_id)
  begin
    istek = Info::Message.new(demand: Info::Demand::CPCTY)
    proto_istegi = istek.to_proto
    soket.write([proto_istegi.size].pack("N") + proto_istegi)
    puts "Capacity talebi gönderildi"

    kapasite2 = Info::Capacity.new(server_id: sunucu_id, timestamp: Time.now.to_i) # Doğru alan adı: server_id
    kapasite2_veri = kapasite2.to_proto
    soket.write([kapasite2_veri.size].pack("N") + kapasite2_veri)
    puts "Gönderildi"

    cevap_boyutu = soket.read(4).unpack("N")[0]
    proto_cevabı = soket.read(cevap_boyutu)
    cevap = Info::Capacity.decode(proto_cevabı)
    puts "Capacity alındı: Server#{cevap.server_id}, #{cevap.serverX_status}, #{cevap.timestamp}"

    cevap
  rescue IOError
    puts "Server bağlantısı problemli."
  rescue => e
    puts "Kapasite isteği problemli: #{e.message}"
  end
end

def python_islemleri(soket, kapasite, sunucu_id)
  begin
    guncel_kapasite = Info::Capacity.new(
      server_id: sunucu_id, # Doğru alan adı: server_id
      serverX_status: kapasite.serverX_status,
      timestamp: kapasite.timestamp
    )
    proto_kapasitesi = guncel_kapasite.to_proto
    soket.write([proto_kapasitesi.size].pack("N") + proto_kapasitesi)
    puts "Python sunucusuna kapasite bilgisi iletildi: Sunucu#{sunucu_id}"
  rescue IOError
    puts "Python sunucusunda bağlantı problemi var."
  rescue => e
    puts "Python sunucusuna kapasite gönderiminde hata oluştu: #{e.message}"
  end
  puts "------------------------------------------"
end

def sunucu_islemleri(java_baglantilari, python_baglantilari, fault_tolerance_level)
  cevaplar = []

  java_baglantilari.each_value do |connection|
    cevap = config_iletisimi(connection, fault_tolerance_level) # Burada config_iletisimi kullanıldı
    cevaplar << cevap if cevap
  end

  loop do
    java_baglantilari.each_with_index do |(sunucu_id, connection), index|

      begin
        puts "Java sunucu #{sunucu_id} çalışıyor."

        if cevaplar[index]&.response.to_s == "YEP"
          kapasite = kapasite_sorgusu(connection, sunucu_id)

          if kapasite
            python_islemleri(python_baglantilari, kapasite, sunucu_id)
          else
            puts "#{sunucu_id} numaralı sunucudan kapasite sorgulanamadı."
          end

        else
          puts "Sunucudan izinler alınamadı."
        end

      rescue IOError
        puts "#{sunucu_id} numaralı sunucuyla bağlantı kesildi."
      rescue => e
        puts "Sunucuda hata oluştu: #{e.message}"
      end
    end
    sleep(5)
  end
end

def baglanti_islemleri(java_baglantilari, python_baglantilari, fault_tolerance_level)
  loop do
    if java_baglantilari.any? && python_baglantilari
      sunucu_islemleri(java_baglantilari, python_baglantilari, fault_tolerance_level)
    else
      puts "Sunucuyla bağlantı kurulamadı! Yeniden deneniyor!.."
      sleep(5)

      loop do
        python_baglantilari = python_sunucusuna_baglanma()
        java_baglantilari = java_sunucularına_baglanma()

        if java_baglantilari.any? && python_baglantilari
          sunucu_islemleri(java_baglantilari, python_connection, fault_tolerance_level)
          break
        end
      end
    end
  end
end

fault_tolerance_level = config_erisim(CONFIG)
python_baglantilari = python_sunucusuna_baglanma()
java_baglantilari = java_sunucularına_baglanma()

baglanti_islemleri(java_baglantilari, python_baglantilari, fault_tolerance_level)
