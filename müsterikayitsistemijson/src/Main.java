import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class Film {
    private String ad;
    private int sure;
    private String tur;
    private List<String> seanslar;
    private int biletUcreti; 

    public Film(String ad, int sure, String tur, List<String> seanslar, int biletUcreti) {
        this.ad = ad;
        this.sure = sure;
        this.tur = tur;
        this.seanslar = seanslar;
        this.biletUcreti = biletUcreti; 
    }

    public Film(String filmAdi, int filmSure, String filmTuru, List<String> seanslar2) {
		// TODO Auto-generated constructor stub
	}

	public String getAd() {
        return ad;
    }

    public int getSure() {
        return sure;
    }

    public String getTur() {
        return tur;
    }

    public List<String> getSeanslar() {
        return seanslar;
    }

    public int getBiletUcreti() {
        return biletUcreti; 
    }
}


class Musteri {
    private String ad;
    private String telefon;
    private String mail;
    private int koltukSayisi;
    private int[] koltuklar;
    private String seans;
    private String fisNumarasi;

    public Musteri(String ad, String telefon, String mail, int koltukSayisi, int[] koltuklar, String seans) {
        this.ad = ad;
        this.telefon = telefon;
        this.mail = mail;
        this.koltukSayisi = koltukSayisi;
        this.koltuklar = koltuklar;
        this.seans = seans;
        this.fisNumarasi = "FIS-" + (int) (Math.random() * 10000);
    }

    public String getAd() {
        return ad;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getMail() {
        return mail;
    }

    public String getSeans() {
        return seans;
    }

    public int[] getKoltuklar() {
        return koltuklar;
    }

    public int getKoltukSayisi() {
        return koltukSayisi;
    }
    public double getBiletUcreti() {
        return getBiletUcreti();
    }

    public String getFisNumarasi() {
        return fisNumarasi;
    }
}

class SinemaSahnesi {
    private String salonAd;
    private Film film;
    boolean[] koltuklar;
    private List<Musteri> kayitliMusteriler;
    @SuppressWarnings("unused")
	private static int fisSayac = 1000;

    public SinemaSahnesi(String salonAd, Film film, int koltukSayisi) {
        this.salonAd = salonAd;
        this.film = film;
        this.koltuklar = new boolean[koltukSayisi];
        this.kayitliMusteriler = new ArrayList<>();
    }

    public String getSalonAd() {
        return salonAd;
    }

    public Film getFilm() {
        return film;
    }

    public void mevcutKoltuklariGoster() {
        System.out.print("Mevcut Koltuklar: ");
        for (int i = 0; i < koltuklar.length; i++) {
            if (!koltuklar[i]) {
                System.out.print((i + 1) + " ");
            }
        }
        System.out.println();
    }

    public boolean koltukKaydiYap(int[] koltukNo, Musteri musteri) {
        // Koltuk doluysa, kullanıcıya tekrar seçim yapması için yönlendir
        for (int i : koltukNo) {
            if (i < 0 || i >= koltuklar.length) {
                System.out.println("Geçersiz koltuk numarası.");
                return false;
            }
            if (koltuklar[i]) {
                System.out.println("Koltuk " + (i + 1) + " zaten dolu.");
                return false;
            }
        }

        
        for (int i : koltukNo) {
            koltuklar[i] = true;
        }

        kayitliMusteriler.add(musteri);
        System.out.println("Kayıt başarılı! Koltuklar: ");
        for (int i : koltukNo) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();

        
        double toplamUcret = musteri.getKoltukSayisi() * film.getBiletUcreti();
        System.out.println("Toplam Bilet Ücreti: " + toplamUcret + " TL");
        System.out.println("Fiş Numarası: " + musteri.getFisNumarasi());
        System.out.println("Fiş numarasıyla kasadan ödeme sağlayın.");

        kaydiJsonDosyasinaKaydet(musteri, koltukNo);
        return true;
    }


    public void kayitliMusterileriListele() {
        System.out.println(film.getAd() + " için kayitli müşteriler:");
        for (Musteri musteri : kayitliMusteriler) {
            System.out.print("Ad: " + musteri.getAd() + ", Telefon: " + musteri.getTelefon() + ", Mail: " + musteri.getMail() + ", Seans: " + musteri.getSeans());
            System.out.print(" | Koltuklar: ");
            for (int koltuk : musteri.getKoltuklar()) {
                System.out.print((koltuk + 1) + " ");
            }
            System.out.println();
        }
    }

    private void kaydiJsonDosyasinaKaydet(Musteri musteri, int[] koltukNo) {
    	
        String json = "{\n" +
                "  \"sinemaSalonu\": \"" + salonAd + "\",\n" +
                "  \"film\": \"" + film.getAd() + "\",\n" +
                "  \"musteri\": {\n" +
                "    \"ad\": \"" + musteri.getAd() + "\",\n" +
                "    \"telefon\": \"" + musteri.getTelefon() + "\",\n" +
                "    \"mail\": \"" + musteri.getMail() + "\"\n" +
                "  },\n" +
                "  \"koltuklar\": [";
        for (int i = 0; i < koltukNo.length; i++) {
            json += koltukNo[i] + (i == koltukNo.length - 1 ? "" : ", ");
        }
        json += "],\n" +
                "  \"seans\": \"" + musteri.getSeans() + "\",\n" +
                "  \"fisNumarasi\": \"" + musteri.getFisNumarasi() + "\",\n" +
                "  \"biletUcreti\": \"" + (musteri.getKoltukSayisi() * film.getBiletUcreti()) + "\"\n" +
                "}\n";

        try (FileWriter writer = new FileWriter("kayitlar.json", true)) {
            writer.write(json);
            writer.write(",\n");
        } catch (IOException e) {
            System.out.println("Kayıt kaydedilirken bir hata oluştu: " + e.getMessage());
        }
    }


    public void filmSil() {
        this.film = null;
        System.out.println("Film başarıyla silindi.");
    }

    public void filmEkle(Film yeniFilm) {
        this.film = yeniFilm;
        System.out.println("Yeni film başarıyla eklendi.");
    }
}

public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        List<SinemaSahnesi> sinemaSahneleri = new ArrayList<>();

        sinemaSahneleri.add(new SinemaSahnesi("Salon 1", new Film("Japon İşi", 89, "Komedi/Bilim Kurgu", List.of("12:00", "15:30", "19:00"),105), 10));
        sinemaSahneleri.add(new SinemaSahnesi("Salon 2", new Film("Şabaniye", 93, "Komedi/Romantik", List.of("13:00", "16:30", "20:00"),105), 10));
        sinemaSahneleri.add(new SinemaSahnesi("Salon 3", new Film("Neşeli Günler", 92, "Komedi/Dram", List.of("12:30", "16:00", "19:30"),105), 10));
        sinemaSahneleri.add(new SinemaSahnesi("Salon 4", new Film("Süt Kardeşler", 80, "Komedi/Korku", List.of("10:30", "14:00", "17:30"),105), 10));
        sinemaSahneleri.add(new SinemaSahnesi("Salon 5", new Film("Hababam Sınıfı", 90, "Komedi/Dram", List.of("11:00", "14:30", "18:00"),105), 10));

     
        System.out.println("Nostalji Sinema Salonuna Hoş Geldiniz!");
        System.out.println("Keyifle izlemenizi umuyoruz.\n"); 
        
        while (true) {
            System.out.println("\n1. Vizyondaki Filmleri Göster");
            System.out.println("2. Film için Kayıt Ol");
            System.out.println("3. Kayıtlı Müşterileri Göster");
            System.out.println("4. Yönetici Girişi");
            System.out.println("5. Çıkış");
            System.out.print("Bir seçenek girin: ");
            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    System.out.println("\n[Vizyondaki Filmler]");
                    for (int i = 0; i < sinemaSahneleri.size(); i++) {
                        SinemaSahnesi salon = sinemaSahneleri.get(i);
                        System.out.println((i + 1) + ". " + salon.getFilm().getAd() + " - " + salon.getFilm().getSure() + " dakika - Tür: " + salon.getFilm().getTur() + " - Salon: " + salon.getSalonAd());
                        System.out.println("Seanslar: " + String.join(", ", salon.getFilm().getSeanslar()));
                    }
                    break;

                case 2:
                    System.out.println("\n[Bir Film İçin Kayıt Ol]");
                    for (int i = 0; i < sinemaSahneleri.size(); i++) {
                        SinemaSahnesi salon = sinemaSahneleri.get(i);
                        System.out.println((i + 1) + ". " + salon.getSalonAd() + " - Film: " + salon.getFilm().getAd());
                    }
                    System.out.print("Salon numarasını seçin: ");
                    int salonNumarasi = scanner.nextInt() - 1;

                    if (salonNumarasi >= 0 && salonNumarasi < sinemaSahneleri.size()) {
                        SinemaSahnesi secilenSalon = sinemaSahneleri.get(salonNumarasi);
                        secilenSalon.mevcutKoltuklariGoster();

                        System.out.print("Kaç kişilik rezervasyon yapmak istiyorsunuz? ");
                        int koltukSayisi = scanner.nextInt();
                        scanner.nextLine();

                        int[] koltukNo = new int[koltukSayisi];
                        for (int i = 0; i < koltukSayisi; i++) {
                            while (true) {
                                System.out.print((i + 1) + ". Koltuk numarasını girin: ");
                                int koltuk = scanner.nextInt() - 1;
                                if (koltuk >= 0 && koltuk < secilenSalon.koltuklar.length && !secilenSalon.koltuklar[koltuk]) {
                                    koltukNo[i] = koltuk;
                                    break;
                                } else {
                                    System.out.println("Koltuk dolu veya geçersiz numara. Lütfen başka bir koltuk seçin.");
                                }
                            }
                        }
                        scanner.nextLine();

                        System.out.print("Adınızı girin: ");
                        String ad = scanner.nextLine();
                        System.out.print("Telefon numaranızı girin: ");
                        String telefon = scanner.nextLine();
                        System.out.print("E-posta adresinizi girin: ");
                        String mail = scanner.nextLine();

                        System.out.println("Seansları seçin:");
                        for (int i = 0; i < secilenSalon.getFilm().getSeanslar().size(); i++) {
                            System.out.println((i + 1) + ". " + secilenSalon.getFilm().getSeanslar().get(i));
                        }
                        System.out.print("Seans numarasını seçin: ");
                        int seansNumarasi = scanner.nextInt() - 1;
                        scanner.nextLine();

                        String seans = secilenSalon.getFilm().getSeanslar().get(seansNumarasi);
                        Musteri musteri = new Musteri(ad, telefon, mail, koltukSayisi, koltukNo, seans);
                        secilenSalon.koltukKaydiYap(koltukNo, musteri);
                    } else {
                        System.out.println("Geçersiz salon numarası.");
                    }
                    break;

                case 3:
                    System.out.println("\n[Kayıtlı Müşteriler]");
                    for (SinemaSahnesi salon : sinemaSahneleri) {
                        salon.kayitliMusterileriListele();
                    }
                    break;

                case 4:
                    System.out.println("\n[Yönetici İşlemleri]");
                    System.out.println("1. Film Sil");
                    System.out.println("2. Yeni Film Ekle");
                    System.out.print("Seçiminizi yapın: ");
                    int yoneticiSecim = scanner.nextInt();
                    scanner.nextLine();

                    if (yoneticiSecim == 1) {
                        System.out.println("\n[Yönetici - Film Sil]");
                        System.out.println("1. Film seçin:");
                        for (int i = 0; i < sinemaSahneleri.size(); i++) {
                            System.out.println((i + 1) + ". " + sinemaSahneleri.get(i).getFilm().getAd());
                        }
                        System.out.print("Film numarasını seçin: ");
                        int filmNumarasi = scanner.nextInt() - 1;
                        scanner.nextLine();

                        if (filmNumarasi >= 0 && filmNumarasi < sinemaSahneleri.size()) {
                            sinemaSahneleri.get(filmNumarasi).filmSil();
                        } else {
                            System.out.println("Geçersiz film numarası.");
                        }
                    } else if (yoneticiSecim == 2) {
                        System.out.println("\n[Yönetici - Yeni Film Ekle]");
                        System.out.print("Film adı: ");
                        String filmAdi = scanner.nextLine();
                        System.out.print("Film süresi (dakika): ");
                        int filmSure = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Film türü: ");
                        String filmTuru = scanner.nextLine();

                        System.out.println("Seansları girin (çıkmak için boş bırakın): ");
                        List<String> seanslar = new ArrayList<>();
                        while (true) {
                            System.out.print("Seans saati: ");
                            String seans = scanner.nextLine();
                            if (seans.isEmpty()) {
                                break;
                            }
                            seanslar.add(seans);
                        }

                        Film yeniFilm = new Film(filmAdi, filmSure, filmTuru, seanslar);
                        SinemaSahnesi yeniSalon = new SinemaSahnesi("Yeni Salon", yeniFilm, 10);
                        sinemaSahneleri.add(yeniSalon);
                        System.out.println("Yeni film başarıyla eklendi.");
                    } else {
                        System.out.println("Geçersiz seçenek.");
                    }
                    break;

                case 5:
                    System.out.println("Çıkılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçenek.");
                    break;
            }
        }
    }
} 