package model;

public class Transaksi {
    public int id;
    public Bunga bunga;
    public int jumlah;
    public double totalHarga;
    public String pelanggan;
    
    public Transaksi(int id, Bunga bunga, int jumlah, String pelanggan, double totalHarga){
        this.id = id;
        this.bunga = bunga;
        this.jumlah = jumlah;
        this.totalHarga = bunga.harga * jumlah;
        this.pelanggan = pelanggan;
    }
}
