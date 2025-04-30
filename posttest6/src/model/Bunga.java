package model;

public class Bunga implements CetakInfo {
    private int kode;
    private String nama;
    private int stock;
    private double harga;
    
    public Bunga(int kode, String nama, int stock, double harga){
        this.kode = kode;
        this.nama = nama;
        this.stock = stock;
        this.harga = harga;
    }

    public int getKode(){
        return kode;
    }

    public void setKode(int kode){
            this.kode = kode;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public int getStock(){
        return stock;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }

    public double getHarga(){
        return harga;
    }

    public void setHarga(double harga){
        this.harga = harga;
    }

    @Override
    public void tampilkanInfo(){
        System.out.println("Kode: " + kode + ", Nama: " + nama + ", Stok: " + stock + ", Harga: Rp" + harga);
    }

    @Override
    public void cetakLabel(){
        System.out.println("=== Label ===\n" + nama + " (Rp" + harga + ")\nStok: " + stock);
    }

    @Override
    public String toString(){
        return nama;
    }
}
