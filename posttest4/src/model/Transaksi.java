package model;

public class Transaksi {
    private int id;
    private Bunga bunga;
    private int jumlah;
    private double totalHarga;
    private String pelanggan;
    
    public Transaksi(int id, Bunga bunga, int jumlah, String pelanggan, double totalHarga){
        this.id = id;
        this.bunga = bunga;
        this.jumlah = jumlah;
        this.totalHarga = bunga.getHarga() * jumlah;
        this.pelanggan = pelanggan;
    }

    public int getID(){
        return id;
    }
    public void setid(int id){
            this.id = id;
    }
    public Bunga getBunga(){
        return bunga;
    }
    public void setBunga(Bunga bunga){
        this.bunga = bunga;
    }
    public int getJumlah(){
        return jumlah;
    }
        
    public void setJumlah(int jumlah, boolean updateHarga){
        this.jumlah = jumlah;
        if(updateHarga){
            this.totalHarga = this.bunga.getHarga() * jumlah;
        }
    }
    public double getTotalHarga(){
        return totalHarga;
    }
    public void setTotalHarga(double totalHarga){
        this.totalHarga = totalHarga;
    }

    public String getPelanggan(){
        return pelanggan;
    }

    public void setPelanggan(String pelanggan){
        this.pelanggan = pelanggan;
    }
}
