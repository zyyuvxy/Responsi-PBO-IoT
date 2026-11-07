import java.util.Scanner;

import Model.PerangkatIoT;
import Model.SensorSuhu;
import Model.LampuPintar;
import Model.KameraKeamanan;

import Interface.Terhubung;
import Interface.DapatDikendalikan;

public class Main {
    //method untuk membaca input integer
    public static int bacaAngka(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat!");
            }
        }
    }

    //method untuk baca input double
    public static double bacaDesimal(Scanner input, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Double.parseDouble(input.nextLine().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
            }
        }
    }

    //method menampilkan menu
    public static void tampilkanMenu() {
        System.out.println("\n========================================");
        System.out.println("          SMART HOME IoT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Lihat seluruh perangkat");
        System.out.println("2. Monitoring suhu ruangan");
        System.out.println("3. Kendalikan lampu pintar");
        System.out.println("4. Kendalikan kamera keamanan");
        System.out.println("5. Lihat informasi perangkat");
        System.out.println("6. Hubungkan seluruh perangkat");
        System.out.println("0. Keluar");
        System.out.println("========================================");
    }

    //method menampilkan daftar perangkat
    public static void tampilkanDaftar(PerangkatIoT[] perangkat) {
        System.out.println("\n===== DAFTAR PERANGKAT IoT =====");
        for (int i = 0; i < perangkat.length; i++) {
            System.out.println((i + 1) + ". " + perangkat[i].getNama());
            System.out.println("   ID     : " + perangkat[i].getIdPerangkat());
            System.out.println("   Lokasi : " + perangkat[i].getLokasi());
            System.out.println("   Status : " + perangkat[i].getStatus());
        }
    }

    //method monitoring suhu
    public static void monitoringSuhu(Scanner input, SensorSuhu sensor) {
        System.out.println("\n===== MONITORING SUHU =====");
        double suhu = bacaDesimal(input, "Masukkan suhu ruangan (Celsius): ");
        if (suhu < -50 || suhu > 100) {
            System.out.println("Nilai suhu di luar rentang simulasi.");
            return;
        }
        sensor.prosesData(suhu);
    }

    //method mengendalikan lampu
    public static void kendalikanLampu(Scanner input, LampuPintar lampu) {
        System.out.println("\n===== KONTROL LAMPU PINTAR =====");
        System.out.println("1. Nyalakan lampu");
        System.out.println("2. Matikan lampu");
        System.out.println("3. Atur tingkat kecerahan");
        int pilihan = bacaAngka(input, "Pilih aksi: ");
        DapatDikendalikan kontrolLampu = lampu;

        switch (pilihan) {
            case 1:
                kontrolLampu.kendalikan(true);
                break;
            case 2:
                kontrolLampu.kendalikan(false);
                break;
            case 3:
                int kecerahan = bacaAngka(input, "Masukkan kecerahan (0-100): ");
                if (kecerahan >= 0 && kecerahan <= 100) {lampu.prosesData(kecerahan);
                } else {
                    System.out.println("Kecerahan harus antara 0-100!");
                }
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
        }
    }

    //method mengendalikan kamera
    public static void kendalikanKamera(Scanner input, KameraKeamanan kamera) {
        System.out.println("\n===== KONTROL KAMERA KEAMANAN =====");
        System.out.println("1. Mulai merekam");
        System.out.println("2. Hentikan rekaman");
        System.out.println("3. Aktifkan mode malam");
        System.out.println("4. Nonaktifkan mode malam");
        int pilihan = bacaAngka(input, "Pilih aksi: ");
        DapatDikendalikan kontrolKamera = kamera;

        switch (pilihan) {
            case 1:
                kontrolKamera.kendalikan(true);
                break;
            case 2:
                kontrolKamera.kendalikan(false);
                break;
            case 3:
                kamera.prosesData(1);
                break;
            case 4:
                kamera.prosesData(0);
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
        }
    }

    //method mengendalikan informasi perangkat
    public static void tampilkanInformasi(PerangkatIoT[] perangkat) {
        System.out.println("\n===== INFORMASI SELURUH PERANGKAT =====");
        for (PerangkatIoT p : perangkat) {
            System.out.println("\n------------------------------");
            //polymorphism
            p.tampilkanInfo();
            System.out.println("------------------------------");
        }
    }

    //method menghubungkan seluruh perangkat
    public static void hubungkanSemua(PerangkatIoT[] perangkat) {
        System.out.println("\n===== KONEKSI PERANGKAT =====");
        for (PerangkatIoT p : perangkat) {
            if (p instanceof Terhubung) {
                Terhubung koneksi = (Terhubung) p;
                System.out.println("\nMenghubungkan " + p.getNama() + "...");
                koneksi.hubungkan();
            }
        }
    }

    //main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //inisialisasi objek perangkat
        SensorSuhu sensor = new SensorSuhu("SNS001", "Sensor Suhu Ruangan", "Ruang Tamu");
        LampuPintar lampu = new LampuPintar("LMP001", "Lampu Pintar", "Kamar Tidur");
        KameraKeamanan kamera = new KameraKeamanan("CAM001", "Kamera Keamanan", "Teras Rumah");
        //polymorphism melalui array super class
        PerangkatIoT[] perangkat = {sensor, lampu, kamera};

        System.out.println("Selamat Datang di SmartHome IoT System!");
        boolean berjalan = true;
        //perulangan menu
        while (berjalan) {
            tampilkanMenu();
            int pilihan = bacaAngka(input, "Pilih menu: ");

            switch (pilihan) {
                case 1:
                    tampilkanDaftar(perangkat);
                    break;
                case 2:
                    monitoringSuhu(input, sensor);
                    break;
                case 3:
                    kendalikanLampu(input, lampu);
                    break;
                case 4:
                    kendalikanKamera(input, kamera);
                    break;
                case 5:
                    tampilkanInformasi(perangkat);
                    break;
                case 6:
                    hubungkanSemua(perangkat);
                    break;
                case 0:
                    berjalan = false;

                    System.out.println("\nTerima kasih telah menggunakan " + "SmartHome IoT System!");
                    break;
                default:
                    System.out.println("Menu tidak tersedia. Silakan coba lagi.");
            }
        }
        input.close();
    }
}