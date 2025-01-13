Java Tabanlı Sinema Müşteri Kayıt Sistemi
Bu proje, Java programlama dili ve JSON formatı kullanılarak geliştirilmiş bir sinema müşteri kayıt sistemidir. Sistem, müşterilerin kişisel bilgilerini, rezervasyonlarını ve bilet alma geçmişlerini etkin bir şekilde yönetmeyi amaçlar. Sinema salonlarının müşteri veri tabanlarını dijital ortamda saklamasını ve yönetmesini kolaylaştırır.

Özellikler

Müşteri Kayıt: Kullanıcılar, sistemdeki müşteri kaydına ad, soyad, e-posta, telefon numarası gibi kişisel bilgilerini ekleyebilir.

Bilet Rezervasyonu: Kullanıcılar mevcut film listesinden seçim yaparak bir film için bilet rezervasyonu gerçekleştirebilir.
Film seçildikten sonra, kullanıcıya rezervasyonu onaylayan benzersiz bir fiş numarası verilir.
Rezervasyon tamamlandıktan sonra, seçilen film ile ilgili bilet ücreti ve fiş numarası kullanıcıya gösterilir.

Fiş Numarası ve Ücret Gösterimi:Her bilet rezervasyonu, sistem tarafından benzersiz bir fiş numarası alır. Bu fiş numarası, rezervasyonun benzersizliğini sağlar.
Bilet ücreti, film seçimine göre hesaplanır ve kullanıcıya gösterilir. Bu ücret, film fiyatlarıyla orantılı olarak belirlenir.

JSON Veri Saklama:Hem müşteri bilgileri hem de bilet rezervasyon bilgileri JSON formatında saklanır.
Veriler, gerektiğinde sistemin kapanmasının ardından yeniden yüklenebilir.
JSON dosyaları, veritabanı gibi çalışarak müşterilerin ve rezervasyonların bilgilerinin kaybolmadan saklanmasını sağlar.

Kullanılan Teknolojiler:
Java SE: Java programlama dilinin standart sürümü kullanılarak uygulama geliştirilmiştir.
JSON Parsing Kütüphaneleri: Gson, Jackson gibi kütüphaneler kullanılarak JSON formatında veriler işlenmiştir.
