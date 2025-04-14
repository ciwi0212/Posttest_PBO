package laporan;

import model.Transaksi;
import java.util.List;

public class LaporanPenjualan extends Laporan {
    private List<Transaksi> listTransaksi;

    public LaporanPenjualan(String periode, List<Transaksi> listTransaksi){
        super(periode);
        this.listTransaksi = listTransaksi;
    }

    @Override
    public void tampilkanLaporan(){
        System.out.println("\n==== Laporan Penjualan Periode: " + getPeriode() + " ===");
        for (Transaksi transaksi : listTransaksi){
            System.out.println("ID Laporan: " + transaksi.getID() + ", Nama Pelanggan: " + transaksi.getPelanggan() + ", Bunga: " + transaksi.getBunga() + ", Jumlah Bunga Yang Dibeli: " + transaksi.getJumlah() + ", Total Harga: Rp" + transaksi.getTotalHarga());
        }
    }
}
