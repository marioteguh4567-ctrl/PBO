import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


class Komputer {
    private int idKomputer;
    private String spesifikasi;
    private boolean statusTersedia;

    public Komputer(int idKomputer, String spesifikasi, boolean statusTersedia) {
        setIdKomputer(idKomputer);
        setSpesifikasi(spesifikasi);
        this.statusTersedia = statusTersedia;
    }

    private void setIdKomputer(int idKomputer) {
        if (idKomputer <= 0) {
            throw new IllegalArgumentException("ID Komputer harus berupa angka positif.");
        }
        this.idKomputer = idKomputer;
    }

    private void setSpesifikasi(String spesifikasi) {
        if (spesifikasi == null || spesifikasi.trim().isEmpty()) {
            throw new IllegalArgumentException("Spesifikasi tidak boleh kosong.");
        }
        this.spesifikasi = spesifikasi;
    }

    public int getIdKomputer() {
        return idKomputer;
    }

    public String getSpesifikasi() {
        return spesifikasi;
    }

    public boolean isStatusTersedia() {
        return statusTersedia;
    }

    public void setStatusTersedia(boolean statusTersedia) {
        this.statusTersedia = statusTersedia;
    }

    // equals() dibandingkan berdasarkan ID (ISI objek),
    // sedangkan operator == membandingkan REFERENSI (alamat objek).
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Komputer)) return false;
        return idKomputer == ((Komputer) o).idKomputer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKomputer);
    }

    public void tampilkanInformasi() {
        System.out.println("ID Komputer : " + idKomputer);
        System.out.println("Spesifikasi : " + spesifikasi);
        System.out.println("Status      : " + (statusTersedia ? "Tersedia" : "Maintenance"));
    }
}

class KomputerGaming extends Komputer {
    private String kartuGrafis;

    public KomputerGaming(int idKomputer, String spesifikasi, boolean statusTersedia, String kartuGrafis) {
        super(idKomputer, spesifikasi, statusTersedia);
        this.kartuGrafis = kartuGrafis;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Tipe        : Gaming (GPU: " + kartuGrafis + ")");
    }
}

class KomputerServer extends Komputer {
    private String ipAddress;

    public KomputerServer(int idKomputer, String spesifikasi, boolean statusTersedia, String ipAddress) {
        super(idKomputer, spesifikasi, statusTersedia);
        this.ipAddress = ipAddress;
    }

    @Override
    public void tampilkanInformasi() {
        super.tampilkanInformasi();
        System.out.println("Tipe        : Server (IP: " + ipAddress + ")");
    }
}

public class LaboratoriumKomputer {

    // ===================== HELPER =====================

    private static int bacaInt(Scanner scanner, String pesan) {
        while (true) {
            System.out.print(pesan);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            }
            System.out.println("Input harus berupa angka.");
            scanner.next();
        }
    }

    // "Alamat" objek di heap (pendekatan terbaik di Java untuk melihat identitas referensi)
    private static String alamat(Object o) {
        return o == null ? "null" : "@" + Integer.toHexString(System.identityHashCode(o));
    }

    private static long memoriTerpakaiKB() {
        Runtime rt = Runtime.getRuntime();
        return (rt.totalMemory() - rt.freeMemory()) / 1024;
    }

    private static Komputer cariById(ArrayList<Komputer> daftar, int id) {
        for (Komputer k : daftar) {
            if (k.getIdKomputer() == id) {
                return k;
            }
        }
        return null; // referensi null = "tidak menunjuk ke objek apa pun"
    }

    // ===================== PASS BY VALUE =====================

    // Java selalu pass-by-value. Yang disalin adalah NILAI REFERENSI-nya,
    // jadi isi objek yang sama bisa diubah dari dalam method.
    private static void ubahIsiObjek(Komputer k) {
        k.setStatusTersedia(!k.isStatusTersedia());
    }

    // Mengganti referensi parameter TIDAK mempengaruhi variabel di pemanggil,
    // karena parameter hanya salinan referensi.
    private static void gantiReferensi(Komputer k) {
        k = new Komputer(999, "Objek baru (hanya hidup di dalam method)", true);
        System.out.println("   Di dalam method, k menunjuk ke " + alamat(k) + " (ID " + k.getIdKomputer() + ")");
    }

    // ===================== DEMO 1: REFERENSI (POINTER) =====================

    private static void demoReferensi(ArrayList<Komputer> lab) {
        System.out.println("\n=== DEMO REFERENSI (pengganti pointer) ===");
        if (lab.isEmpty()) {
            System.out.println("Daftar komputer kosong, tambahkan data dulu.");
            return;
        }

        Komputer a = lab.get(0);
        boolean statusAwal = a.isStatusTersedia();

        // 1. Alias: dua variabel, satu objek
        Komputer b = a; // TIDAK membuat objek baru, hanya menyalin referensi
        System.out.println("\n1. Alias (dua variabel, satu objek)");
        System.out.println("   a menunjuk ke " + alamat(a));
        System.out.println("   b menunjuk ke " + alamat(b));
        System.out.println("   a == b ? " + (a == b));

        b.setStatusTersedia(!statusAwal);
        System.out.println("   Status diubah lewat b, status di a ikut berubah: "
                + (a.isStatusTersedia() ? "Tersedia" : "Maintenance"));
        a.setStatusTersedia(statusAwal); // kembalikan seperti semula

        // 2. == vs equals()
        Komputer c = new Komputer(a.getIdKomputer(), a.getSpesifikasi(), a.isStatusTersedia());
        System.out.println("\n2. == vs equals()");
        System.out.println("   c adalah objek baru di " + alamat(c) + " dengan ID yang sama");
        System.out.println("   a == c      ? " + (a == c) + "  (alamat berbeda)");
        System.out.println("   a.equals(c) ? " + a.equals(c) + "  (ID sama)");

        // 3. Pass by value
        System.out.println("\n3. Pass by value (yang disalin adalah referensinya)");
        System.out.println("   Sebelum ubahIsiObjek   : " + (a.isStatusTersedia() ? "Tersedia" : "Maintenance"));
        ubahIsiObjek(a);
        System.out.println("   Sesudah ubahIsiObjek   : " + (a.isStatusTersedia() ? "Tersedia" : "Maintenance")
                + "  (isi objek berubah)");
        ubahIsiObjek(a); // kembalikan

        System.out.println("   Sebelum gantiReferensi : a menunjuk ke " + alamat(a));
        gantiReferensi(a);
        System.out.println("   Sesudah gantiReferensi : a menunjuk ke " + alamat(a) + "  (tidak berubah)");

        // 4. null
        System.out.println("\n4. null dan NullPointerException");
        Komputer kosong = null;
        System.out.println("   kosong menunjuk ke " + alamat(kosong));
        try {
            kosong.tampilkanInformasi();
        } catch (NullPointerException e) {
            System.out.println("   NullPointerException: tidak bisa memanggil method lewat referensi null.");
        }
        if (kosong != null) {
            kosong.tampilkanInformasi();
        } else {
            System.out.println("   Pengecekan 'if (kosong != null)' mencegah error tersebut.");
        }
    }

    // ===================== DEMO 2: MANAJEMEN MEMORI =====================

    private static void demoMemori() {
        System.out.println("\n=== DEMO MANAJEMEN MEMORI (Heap & Garbage Collector) ===");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Memori maksimum JVM : " + (rt.maxMemory() / 1024 / 1024) + " MB");

        System.gc();
        long awal = memoriTerpakaiKB();
        System.out.println("\nMemori terpakai awal            : " + awal + " KB");

        // Alokasi banyak objek di heap
        ArrayList<Komputer> sementara = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            sementara.add(new Komputer(i, "Komputer sementara ke-" + i, true));
        }
        long sesudahAlokasi = memoriTerpakaiKB();
        System.out.println("Setelah membuat 100.000 objek   : " + sesudahAlokasi + " KB");

        // Lepaskan referensi: objek menjadi "unreachable" dan layak dibersihkan GC
        sementara = null;
        System.gc(); // hanya PERMINTAAN, JVM tidak wajib langsung menjalankannya
        long sesudahGC = memoriTerpakaiKB();
        System.out.println("Setelah referensi = null & GC   : " + sesudahGC + " KB");
        System.out.println("(Angka bisa berbeda tiap JVM; GC tidak dijamin berjalan seketika.)");

        // WeakReference: pantau apakah objek sudah dibersihkan
        System.out.println("\nPemantauan objek dengan WeakReference:");
        Komputer temp = new Komputer(500, "Objek sementara", true);
        WeakReference<Komputer> pantau = new WeakReference<>(temp);
        System.out.println("   Selama 'temp' masih menunjuk, objek ada? " + (pantau.get() != null));

        temp = null; // tidak ada lagi referensi kuat ke objek
        System.gc();
        System.out.println("   Setelah temp = null dan GC, objek masih ada? " + (pantau.get() != null));
        System.out.println("   (false = sudah dibersihkan GC; true = GC belum berjalan)");
    }

    // ===================== DEMO 3: HAPUS OBJEK =====================

    private static void hapusKomputer(Scanner scanner, ArrayList<Komputer> lab) {
        int id = bacaInt(scanner, "\nMasukkan ID Komputer yang akan dihapus: ");
        Komputer target = cariById(lab, id);

        if (target == null) {
            System.out.println("Komputer dengan ID " + id + " tidak ditemukan.");
            return;
        }

        lab.remove(target);
        System.out.println("Komputer ID " + id + " dihapus dari daftar.");
        System.out.println("Objek di " + alamat(target) + " masih dipegang variabel 'target' sampai method selesai.");

        target = null; // lepas referensi terakhir
        System.out.println("Setelah target = null, tidak ada referensi tersisa, objek layak dibersihkan GC.");
    }

    // ===================== MAIN =====================

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Komputer> labKomputer = new ArrayList<>();
            
            labKomputer.add(new KomputerGaming(101, "Core i5, RAM 16GB", true, "RTX 3060"));
            labKomputer.add(new KomputerGaming(102, "Core i3, RAM 8GB", true, "GTX 1650"));
            labKomputer.add(new KomputerServer(103, "Core i7, RAM 32GB", false, "192.168.1.100"));
            
            int pilihan;
            
            do {
                System.out.println("\nMenu Sistem Lab Komputer (Pointer & Memory Management)");
                System.out.println("1. Lihat Daftar Komputer (beserta alamat objek)");
                System.out.println("2. Ubah Status Komputer");
                System.out.println("3. Demo Referensi (pengganti pointer)");
                System.out.println("4. Demo Manajemen Memori (Heap & GC)");
                System.out.println("5. Hapus Komputer (lepas referensi)");
                System.out.println("6. Keluar");
                pilihan = bacaInt(scanner, "Pilih menu (1-6): ");
                
                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\nDaftar Komputer Laboratorium:");
                        if (labKomputer.isEmpty()) {
                            System.out.println("(kosong)");
                        }
                        for (Komputer comp : labKomputer) {
                            System.out.println("Alamat objek: " + alamat(comp));
                            comp.tampilkanInformasi();
                            System.out.println();
                        }
                    }
                    
                    case 2 -> {
                        int targetId = bacaInt(scanner, "\nMasukkan ID Komputer: ");
                        Komputer comp = cariById(labKomputer, targetId);
                        
                        if (comp == null) {
                            System.out.println("Komputer dengan ID " + targetId + " tidak ditemukan.");
                            break;
                        }
                        
                        int statusBaru = bacaInt(scanner, "Pilih Status (1 untuk Tersedia, 0 untuk Maintenance): ");
                        switch (statusBaru) {
                            case 1 -> {
                                comp.setStatusTersedia(true);
                                System.out.println("Status komputer diperbarui menjadi Tersedia.");
                            }
                            case 0 -> {
                                comp.setStatusTersedia(false);
                                System.out.println("Status komputer diperbarui menjadi Maintenance.");
                            }
                            default -> System.out.println("Pilihan status tidak valid.");
                        }
                    }
                    
                    
                    
                    case 3 -> demoReferensi(labKomputer);
                    
                    case 4 -> demoMemori();
                    
                    case 5 -> hapusKomputer(scanner, labKomputer);
                    
                    case 6 -> System.out.println("\nProgram selesai. Terima kasih.");
                    
                    default -> System.out.println("\nPilihan tidak valid. Silakan pilih angka 1-6.");
                }
                
            } while (pilihan != 6);
        }
    }
}