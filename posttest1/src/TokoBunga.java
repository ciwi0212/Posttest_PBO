import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Pengguna;
import model.Transaksi;
import model.Bunga;


public class TokoBunga {
    static List<Bunga> daftarBunga = new ArrayList<>();
    static List<Transaksi> daftarTransaksi = new ArrayList<>();
    static List<Pengguna> penggunaList = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static Pengguna penggunaAktif;
    public static void main(String[] args) throws Exception {
        penggunaList.add(new Pengguna("admin", "admin","admin"));
        penggunaList.add(new Pengguna("kasir", "kasir", "kasir"));

        if(!login()){
            System.out.println("Login Gagal!");
            return;
        }

        int pilih;
        do {
            System.out.println("\n=======Sistem Penjualan Dan Stock Bunga=======");
            System.out.println("1. Tambah Bunga");
            if(penggunaAktif.role.equals("admin")){
                System.out.println("2. Lihat Stok Bunga");
            }
            System.out.println("3. Perbarui Bunga (Admin)");
            System.out.println("4. Hapus Bunga (Admin)");
            System.out.println("5. Buat Transaksi Penjualan (Kasir)");
            System.out.println("6. Lihat Laporan Penjualan (Admin)");
            System.out.println("7. Keluar");
            System.out.print("Pilihan: ");
            pilih = scanner.nextInt();
            scanner.nextLine();

            switch(pilih){
                case 1:
                    if (penggunaAktif.role.equals("admin")) tambahBunga();
                    else System.out.println("Akses ditolak!");
                    break;
                case 2:
                    if (penggunaAktif.role.equals("admin")) lihatStokBunga();
                    else System.out.println("Akses ditolak!");
                    break;
                case 3:
                    if (penggunaAktif.role.equals("admin")) perbaruiBunga();
                    else System.out.println("Akses ditolak!");
                    break;
                case 4:
                    if (penggunaAktif.role.equals("admin")) hapusBunga();
                    else System.out.println("Akses ditolak!");
                    break;
                case 5:
                    if (penggunaAktif.role.equals("kasir")) buatTransaksi();
                    else System.out.println("Akses ditolak!");
                    break;
                case 6:
                    if (penggunaAktif.role.equals("admin")) lihatLaporan();
                    else System.out.println("Akses ditolak!");
                    break;
                case 7:
                    System.out.println("Keluar dari sistem.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                }
            } while (pilih != 7);
        }

        public static boolean login() {
            System.out.print("Masukkan Username: ");
            String username = scanner.nextLine();
            System.out.print("Masukkan Password: ");
            String pass = scanner.nextLine();
    
            for (Pengguna pengguna : penggunaList) {
                if (pengguna.username.equals(username) && pengguna.pass.equals(pass)) {
                    penggunaAktif = pengguna;
                    System.out.println("Login berhasil sebagai " + pengguna.role);
                    return true;
                }
            }
            System.out.println("Username atau password salah!");
            return false;
        }

        public static void tambahBunga() {
            System.out.print("Masukkan ID Bunga: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Masukkan Nama Bunga: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan Stok: ");
            int stock = scanner.nextInt();
            System.out.print("Masukkan Harga: ");
            double harga = scanner.nextDouble();
    
            daftarBunga.add(new Bunga(id, nama, stock, harga));
            System.out.println("Bunga berhasil ditambahkan!");
        }

        public static void lihatStokBunga() {
            System.out.println("\n=== Daftar Stok Bunga ===");
            for (Bunga bunga : daftarBunga) {
                System.out.println("ID: " + bunga.id + " | Nama: " + bunga.nama + " | Stok: " + bunga.stock + " | Harga: Rp" + bunga.harga);
            }
        }
        public static void perbaruiBunga() {
            System.out.print("Masukkan ID Bunga yang akan diperbarui: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            for (Bunga bunga : daftarBunga) {
                if (bunga.id == id) {
                    System.out.print("Masukkan Nama Baru: ");
                    bunga.nama = scanner.nextLine();
                    System.out.print("Masukkan Stok Baru: ");
                    bunga.stock = scanner.nextInt();
                    System.out.print("Masukkan Harga Baru: ");
                    bunga.harga = scanner.nextDouble();
                    System.out.println("Data bunga berhasil diperbarui!");
                    return;
                }
            }
            System.out.println("Bunga tidak ditemukan!");
        }

        public static void hapusBunga() {
            System.out.print("Masukkan ID Bunga yang akan dihapus: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            daftarBunga.removeIf(bunga -> bunga.id == id);
            System.out.println("Bunga berhasil dihapus!");
        }

        public static void buatTransaksi() {
            System.out.print("Masukkan ID Bunga yang Dibeli: ");
            int id = scanner.nextInt();
            System.out.print("Masukkan Jumlah yang Dibeli: ");
            int jumlah = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Masukkan Nama Pelanggan: ");
            String pelanggan = scanner.nextLine();
    
            for (Bunga bunga : daftarBunga) {
                if (bunga.id == id) {
                    if (bunga.stock >= jumlah) {
                        bunga.stock -= jumlah;
                        Transaksi transaksi = new Transaksi(daftarTransaksi.size() + 1, bunga, jumlah, pelanggan, jumlah);
                        daftarTransaksi.add(transaksi);
                        System.out.println("Transaksi berhasil! Total Harga: Rp" + transaksi.totalHarga);
                    } else {
                        System.out.println("Stok tidak mencukupi!");
                    }
                    return;
                }
            }
            System.out.println("Bunga tidak ditemukan!");
        }

        public static void lihatLaporan() {
            System.out.println("\n=== Laporan Penjualan ===");
            if (daftarTransaksi.isEmpty()) {
                System.out.println("Belum ada transaksi.");
            } else {
                for (Transaksi transaksi : daftarTransaksi) {
                    System.out.println("ID Transaksi: " + transaksi.id + 
                                       " | Bunga: " + transaksi.bunga.nama + 
                                       " | Jumlah: " + transaksi.jumlah + 
                                       " | Total Harga: Rp" + transaksi.totalHarga + 
                                       " | Pelanggan: " + transaksi.pelanggan);
                }
            }
        }
    }



