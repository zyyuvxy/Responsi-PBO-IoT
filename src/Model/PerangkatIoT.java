package Model;

public abstract class PerangkatIoT {

    //konstanta global
    public static final String LOKASI_DEFAULT = "Ruang Utama";

    //atribut
    private final String idPerangkat;
    private String nama;
    private String lokasi;
    private String status;

    //konstruktor pertama
    public PerangkatIoT(String idPerangkat, String nama){
        this(idPerangkat, nama, LOKASI_DEFAULT);
    }

    //konstruktor kedua
    public PerangkatIoT(String idPerangkat, String nama, String lokasi){
        this.idPerangkat = idPerangkat;
        this.nama = nama;
        this.lokasi = lokasi;
        this.status = "Nonaktif";
    }

    //getter & setter
    public String getIdPerangkat(){
        return idPerangkat;
    }
    public String getNama(){
        return nama;
    }
    public void setNama(String nama){
        this.nama = nama;
    }
    public String getLokasi(){
        return lokasi;
    }
    public void setLokasi(String lokasi){
        this.lokasi = lokasi;
    }
    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    //method void
    public void tampilkanInfo(){
        System.out.println("ID Perangkat    : " + idPerangkat);
        System.out.println("Nama            : " + nama);
        System.out.println("Lokasi          : " + lokasi);
        System.out.println("Status          : " + status);
    }

    //method return value
    public String getInformasi(){
        return nama + " berada di " + lokasi + " dengan status " + status;
    }

    //abstract method
    public abstract void prosesData(double nilai);
}
