Java Tabanlı Sinema Müşteri Kayıt Sistemi

Proje Açıklaması

Bu proje, bir sinema müşteri kayıt sistemini Java programlama dili ve JSON formatı kullanarak geliştirmeyi amaçlamaktadır. Sistem, müşterilerin kişisel bilgilerini, rezervasyonlarını ve bilet alma geçmişlerini yönetmek için kullanılır. Amacı, sinema salonlarının müşteri veri tabanını etkin bir şekilde yönetmelerini sağlamaktır.

Müşteri Kayıt:
Kullanıcılar, kişisel bilgilerini (ad, soyad, e-posta, telefon numarası, vb.) sisteme ekleyebilir.

Bilet Rezervasyonu:
Kullanıcılar, mevcut film listesinde seçim yaparak bir film için bilet rezervasyonu yapabilirler.
Film seçiminden sonra, kullanıcıya rezervasyonu onaylayan bir fiş numarası verilir. Bu fiş numarası, rezervasyonun benzersizliğini sağlar.
Rezervasyon tamamlandıktan sonra, seçilen film ve bilet ücretinin bilgisi gösterilir.

Fiş Numarası ve Ücret Gösterimi:
Her bilet rezervasyonu, benzersiz bir fiş numarası alır. Bu fiş numarası, kullanıcıya rezervasyonunun başarıyla alındığını gösterir ve ilerleyen zamanlarda kullanıcının biletini doğrulamak için kullanılabilir.
Bilet ücreti, seçilen filmin fiyatına göre hesaplanır ve kullanıcıya gösterilir.

JSON Veri Saklama:
Hem müşteri bilgileri hem de bilet rezervasyon bilgileri JSON dosyaları olarak saklanır. Bu veriler gerektiğinde sisteme geri yüklenebilir.

Kullanılan Teknolojiler
Java SE
JSON Parsing Kütüphaneleri (Gson, Jackson vb.)
