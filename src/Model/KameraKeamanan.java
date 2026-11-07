package Model;

import Interface.Terhubung;
import Interface.DapatDikendalikan;

public class KameraKeamanan extends PerangkatIoT implements Terhubung, DapatDikendalikan {

    //atribut khusus
    private String resolusi;
    private boolean merekam;
    private boolean modeMalam;

    //konstruktor pertama
    public KameraKeamanan(String id, String nama) {
        super(id, nama);
        this.resolusi = "1080p";
        this.merekam = false;
        this.modeMalam = false;
    }

    //konstruktor kedua
    public KameraKeamanan(String id, String nama, String lokasi) {
        super(id, nama, lokasi);
        this.resolusi = "1080p";
        this.merekam = false;
        this.modeMalam = false;
    }

    //getter & setter
    public String getResolusi() {
        return resolusi;
    }
    public void setResolusi(String resolusi) {
        this.resolusi = resolusi;
    }
    public boolean isMerekam() {
        return merekam;
    }
    public void setMerekam(boolean merekam) {
        this.merekam = merekam;
    }
    public boolean isModeMalam() {
        return modeMalam;
    }
    public void setModeMalam(boolean modeMalam) {
        this.modeMalam = modeMalam;
    }

    //implementasi interface terhubung
    @Override
    public void hubungkan() {
        System.out.println("Kamera berhasil terhubung ke jaringan.");
    }

    //implementasi interface dapat dikendalikan
    @Override
    public void kendalikan(boolean nyala) {
        if (nyala) {
            mulaiMerekam();
        } else {
            hentikanRekaman();
        }
    }

    //method memulai perekaman
    public void mulaiMerekam() {
        setMerekam(true);
        setStatus("Aktif");
        System.out.println("Kamera mulai melakukan perekaman.");
    }

    //method menghentikan perekaman
    public void hentikanRekaman() {
        setMerekam(false);
        setStatus("Standby");
        System.out.println("Perekaman kamera dihentikan.");
    }

    //implementasi abstract method
    @Override
    public void prosesData(double nilai) {
        System.out.println("\nMemproses kondisi kamera...");
        if (nilai == 1) {
            setModeMalam(true);
            System.out.println("Mode malam kamera diaktifkan.");
        } else {
            setModeMalam(false);
            System.out.println("Mode malam kamera dinonaktifkan.");
        }
    }

    //method overriding
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Resolusi   : " + resolusi);
        System.out.println("Merekam    : " + (merekam ? "Ya" : "Tidak"));
        System.out.println("Mode Malam : " + (modeMalam ? "Aktif" : "Nonaktif"));
    }
}