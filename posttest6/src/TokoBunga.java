import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Pengguna;
import model.Transaksi;
import model.Bunga;
import model.CetakInfo;
import laporan.LaporanPenjualan;
import laporan.LaporanStok;

public class TokoBunga {
    static List<Bunga> daftarBunga = new ArrayList<>();
    static List<Transaksi> daftarTransaksi = new ArrayList<>();
    static List<Pengguna> penggunaList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Pengguna penggunaAktif;

    public static void main(String[] args) throws Exception {
        penggunaList.add(new Pengguna("admin", "admin","admin"));
        penggunaList.add(new Pengguna("kasir", "kasir", "kasir"));

        boolean isRunning = true;
        while(isRunning){
            if(penggunaAktif == null){
                if(!login()){
                    System.out.println("Login Gagal!");
                    continue;
                }
            }
            int pilih;
            if (penggunaAktif != null) {
                do {
                    System.out.println("\n=======Sistem Penjualan Dan Stock Bunga=======");
                    System.out.println("1. Tambah Bunga (Admin)");
                    System.out.println("2. Perbarui Bunga (Admin)");
                    System.out.println("3. Hapus Bunga (Admin)");
                    System.out.println("4. Buat Transaksi Penjualan (Kasir)");
                    System.out.println("5. Lihat Laporan Penjualan (Admin)");
                    System.out.println("6. Lihat Laporan Stok Bunga (Admin)");
                    System.out.println("7. Tampilkan Semua Bunga");
                    System.out.println("8. Logout");
                    System.out.println("9. Keluar");
                    System.out.print("Pilihan: ");
                    pilih = scanner.nextInt();
                    scanner.nextLine();

                    switch(pilih){
                        case 1:
                            if (penggunaAktif.getRole().equals("admin")) tambahBunga();
                            else System.out.println("Akses ditolak!");
                            break;
                        case 2:
                            if (penggunaAktif.getRole().equals("admin")) perbaruiBunga();
                            else System.out.println("Akses ditolak!");
                            break;
                        case 3:
                            if (penggunaAktif.getRole().equals("admin")) hapusBunga();
                            else System.out.println("Akses ditolak!");
                            break;
                        case 4:
                            if (penggunaAktif.getRole().equals("kasir")) buatTransaksi();
                            else System.out.println("Akses ditolak!");
                            break;
                        case 5:
                            if (penggunaAktif.getRole().equals("admin")) lihatLaporanPenjualan();
                            else System.out.println("Akses ditolak!");
                            break;
                        case 6:
                            if (penggunaAktif.getRole().equals("admin")) lihatLaporanStok();
                            else System.out.println("Akses ditolak!");
                            break;
                        case 7:
                            tampilkanSemuaBunga();
                            break;
                        case 8:
                            System.out.println("Logout berhasil. Silakan login kembali.");
                            penggunaAktif = null;
                            break;
                        case 9:
                            System.out.println("Keluar dari sistem.");
                            isRunning = false;
                            break;
                    }
                } while (isRunning && penggunaAktif != null);
            }
        }
    }

    public static boolean login() {
        try {
            System.out.print("Masukkan Username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan Password: ");
            String pass = scanner.nextLine();

            for (Pengguna pengguna : penggunaList) {
                if (pengguna.getUsername().equals(username) && pengguna.getPass().equals(pass)) {
                    penggunaAktif = pengguna;
                    System.out.println("Login berhasil sebagai " + pengguna.getRole());
                    return true;
                }
            }
            System.out.println("Username atau password salah!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat login: " + e.getMessage());
        }
        return false;
    }

    public static void tambahBunga() {
        try {
            System.out.print("Masukkan Kode Bunga: ");
            int kode = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Nama Bunga: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan Stok: ");
            int stock = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Harga: ");
            double harga = Double.parseDouble(scanner.nextLine());

            daftarBunga.add(new Bunga(kode, nama, stock, harga));
            System.out.println("Bunga berhasil ditambahkan!");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Pastikan angka dimasukkan dengan benar.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menambahkan bunga: " + e.getMessage());
        }
    }

    public static void perbaruiBunga() {
        try {
            System.out.print("Masukkan kode Bunga yang akan diperbarui: ");
            int kode = Integer.parseInt(scanner.nextLine());

            for (Bunga bunga : daftarBunga) {
                if (bunga.getKode() == kode) {
                    System.out.print("Masukkan Nama Baru: ");
                    bunga.setNama(scanner.nextLine());
                    System.out.print("Masukkan Stok Baru: ");
                    bunga.setStock(Integer.parseInt(scanner.nextLine()));
                    System.out.print("Masukkan Harga Baru: ");
                    bunga.setHarga(Double.parseDouble(scanner.nextLine()));
                    System.out.println("Data bunga berhasil diperbarui!");
                    return;
                }
            }
            System.out.println("Bunga tidak ditemukan!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat memperbarui bunga: " + e.getMessage());
        }
    }

    public static void hapusBunga() {
        try {
            System.out.print("Masukkan Kode Bunga yang akan dihapus: ");
            int kode = Integer.parseInt(scanner.nextLine());
            daftarBunga.removeIf(bunga -> bunga.getKode() == kode);
            System.out.println("Bunga berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menghapus bunga: " + e.getMessage());
        }
    }

    public static void buatTransaksi() {
        try {
            System.out.print("Masukkan Kode Bunga yang Dibeli: ");
            int kode = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Jumlah yang Dibeli: ");
            int jumlah = Integer.parseInt(scanner.nextLine());
            System.out.print("Masukkan Nama Pelanggan: ");
            String pelanggan = scanner.nextLine();

            for (Bunga bunga : daftarBunga) {
                if (bunga.getKode() == kode) {
                    if (bunga.getStock() >= jumlah) {
                        bunga.setStock(bunga.getStock() - jumlah);
                        Transaksi transaksi = new Transaksi(daftarTransaksi.size() + 1, bunga, jumlah, pelanggan, 0);
                        transaksi.setJumlah(jumlah, true);
                        daftarTransaksi.add(transaksi);
                        System.out.println("Transaksi berhasil! Total Harga: Rp" + transaksi.getTotalHarga());
                    } else {
                        System.out.println("Stok tidak mencukupi!");
                    }
                    return;
                }
            }
            System.out.println("Bunga tidak ditemukan!");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat membuat transaksi: " + e.getMessage());
        }
    }

    public static void lihatLaporanPenjualan(){
        try {
            LaporanPenjualan laporan = new LaporanPenjualan("Maret 2025", daftarTransaksi);
            laporan.tampilkanLaporan();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan laporan penjualan: " + e.getMessage());
        }
    }

    public static void lihatLaporanStok(){
        try {
            LaporanStok laporan = new LaporanStok("Maret 2025", daftarBunga);
            laporan.tampilkanLaporan();
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan laporan stok: " + e.getMessage());
        }
    }

    public static void tampilkanSemuaBunga() {
        try {
            System.out.println("\n=== Daftar Bunga ===");
            for (CetakInfo bunga : daftarBunga) {
                bunga.tampilkanInfo();
            }
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan saat menampilkan bunga: " + e.getMessage());
        }
    }
}