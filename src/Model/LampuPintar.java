package Model;

import Interface.Terhubung;
import Interface.DapatDikendalikan;

public class LampuPintar extends PerangkatIoT implements Terhubung, DapatDikendalikan {

    //atribut khusus
    private int tingkatKecerahan;
    private boolean menyala;

    //konstruktor pertama
    public LampuPintar(String id, String nama) {
        super(id, nama);
        this.tingkatKecerahan = 0;
        this.menyala = false;
    }

    //konstruktor kedua
    public LampuPintar(String id, String nama, String lokasi) {
        super(id, nama, lokasi);
        this.tingkatKecerahan = 0;
        this.menyala = false;
    }

    //getter & setter
    public int getTingkatKecerahan() {
        return tingkatKecerahan;
    }
    public void setTingkatKecerahan(int tingkatKecerahan) {
        if (tingkatKecerahan >= 0 && tingkatKecerahan <= 100) {
            this.tingkatKecerahan = tingkatKecerahan;
        }
    }
    public boolean isMenyala() {
        return menyala;
    }
    public void setMenyala(boolean menyala) {
        this.menyala = menyala;
    }

    //implementasi interface terhubung
    @Override
    public void hubungkan() {
        System.out.println("Lampu pintar berhasil terhubung ke jaringan.");
    }

    //implementasi interface dapat dikendalikan
    @Override
    public void kendalikan(boolean nyala) {
        this.menyala = nyala;
        if (nyala) {
            setStatus("Menyala");
            setTingkatKecerahan(100);
            System.out.println("Lampu berhasil dinyalakan.");
        } else {
            setStatus("Mati");
            setTingkatKecerahan(0);
            System.out.println("Lampu berhasil dimatikan.");
        }
    }

    //implementasi abstract method
    @Override
    public void prosesData(double nilai) {
        System.out.println("\nMemproses tingkat kecerahan...");
        if (nilai > 0 && nilai <= 100) {
            setTingkatKecerahan((int) nilai);
            setMenyala(true);
            setStatus("Menyala");
            System.out.println("Kecerahan lampu diatur menjadi " + tingkatKecerahan + "%");
        } else if (nilai == 0) {
            setTingkatKecerahan(0);
            setMenyala(false);
            setStatus("Mati");
            System.out.println("Lampu dalam kondisi mati.");
        } else {
            System.out.println("Nilai kecerahan harus berada pada rentang 0-100.");
        }
    }

    //method overriding
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Kecerahan : " + tingkatKecerahan + "%");
        System.out.println("Kondisi   : " + (menyala ? "ON" : "OFF"));
    }
}