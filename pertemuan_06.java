import java.util.ArrayList;
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

    public final void setIdKomputer(int idKomputer) {
        if (idKomputer <= 0) {
            throw new IllegalArgumentException("ID Komputer harus berupa angka positif.");
        }
        this.idKomputer = idKomputer;
    }

    public final void setSpesifikasi(String spesifikasi) {
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

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Komputer> labKomputer = new ArrayList<>();
            
            labKomputer.add(new KomputerGaming(101, "Core i5, RAM 16GB", true, "RTX 3060"));
            labKomputer.add(new KomputerGaming(102, "Core i3, RAM 8GB", true, "GTX 1650"));
            labKomputer.add(new KomputerServer(103, "Core i7, RAM 32GB", false, "192.168.1.100"));
            
            int pilihan = 0;
            
            do {
                System.out.println("\nMenu Sistem Lab Komputer");
                System.out.println("1. Lihat Daftar Komputer");
                System.out.println("2. Ubah Status Komputer");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu (1-3): ");
                
                if (scanner.hasNextInt()) {
                    pilihan = scanner.nextInt();
                } else {
                    System.out.println("Input harus berupa angka.");
                    scanner.next();
                    continue;
                }
                
                switch (pilihan) {
                    case 1 -> {
                        System.out.println("\nDaftar Komputer Laboratorium:");
                        for (Komputer comp : labKomputer) {
                            comp.tampilkanInformasi();
                            System.out.println();
                        }
                    }
                    case 2 -> {
                        System.out.print("\nMasukkan ID Komputer: ");
                        int targetId = scanner.nextInt();
                        boolean ditemukan = false;
                        for (Komputer comp : labKomputer) {
                            if (comp.getIdKomputer() == targetId) {
                                ditemukan = true;
                                System.out.print("Pilih Status (1 untuk Tersedia, 0 untuk Maintenance): ");
                                int statusBaru = scanner.nextInt();
                                
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
                                break;
                            }
                        }   if (!ditemukan) {
                            System.out.println("Komputer dengan ID " + targetId + " tidak ditemukan.");
                        }
                    }
                    case 3 -> System.out.println("\nProgram selesai. Terima kasih.");
                    default -> System.out.println("\nPilihan tidak valid. Silakan pilih angka 1-3.");
                }
                
            } while (pilihan != 3);
        }
    }
}