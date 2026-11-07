package Model;

import Interface.Terhubung;

public class SensorSuhu extends PerangkatIoT implements Terhubung {

    //atribut khusus
    private double suhuTerakhir;

    //konstruktor pertama
    public SensorSuhu(String id, String nama) {
        super(id, nama);
        this.suhuTerakhir = 0;
    }

    //konstruktor kedua
    public SensorSuhu(String id, String nama, String lokasi) {
        super(id, nama, lokasi);
        this.suhuTerakhir = 0;
    }

    //getter & setter
    public double getSuhuTerakhir() {
        return suhuTerakhir;
    }
    public void setSuhuTerakhir(double suhuTerakhir) {
        this.suhuTerakhir = suhuTerakhir;
    }

    //implementasi interface
    @Override
    public void hubungkan() {
        System.out.println("Sensor suhu berhasil terhubung ke jaringan.");
    }

    //implementasi abstract method
    @Override
    public void prosesData(double nilai) {
        this.suhuTerakhir = nilai;
        System.out.println("\nMemproses data suhu...");
        if (nilai <= 30) {
            setStatus("Normal");
            System.out.println("Suhu ruangan normal: " + nilai + " Celsius");
        } else {
            setStatus("Panas");
            System.out.println("Peringatan! Suhu ruangan terlalu panas: " + nilai + " Celsius");
        }
    }

    //method overriding
    @Override
    public void tampilkanInfo() {
        super.tampilkanInfo();
        System.out.println("Suhu Terakhir : " + suhuTerakhir + " Celsius");
    }
}