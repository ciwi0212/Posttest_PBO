package laporan;

import model.Bunga;
import java.util.List;

public class LaporanStok extends Laporan{
    private List<Bunga> listBunga;

    public LaporanStok(String periode, List<Bunga> listBunga){
        super(periode);
        this.listBunga = listBunga;
    }

    @Override
    public void tampilkanLaporan(){
        System.out.println("\n === Laporan Stok Bunga Periode: " + getPeriode() + " ===");
        for (Bunga bunga : listBunga){
            System.out.println("Kode: " + bunga.getKode() + ", Nama Bunga: " + bunga.getNama() + ", Stok Tersisa: " + bunga.getStock());
        } 
    }
}
