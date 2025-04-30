package laporan;

public abstract class Laporan {
    private String periode;

    public Laporan(String periode){
        this.periode = periode;
    }

    public String getPeriode(){
        return periode;
    }

    public void setPeriode(String periode){
        this.periode = periode;
    }

    public abstract void tampilkanLaporan(); 
}

