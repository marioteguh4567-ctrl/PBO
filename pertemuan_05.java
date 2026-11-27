package LaboratoriumKomputer;

import java.util.ArrayList;
import java.util.Scanner;

class Komputer {
    private int idKomputer;
    private String spesifikasi;
    private boolean statusTersedia;

    public Komputer(int idKomputer, String spesifikasi, boolean statusTersedia) {
        this.idKomputer = idKomputer;
        this.spesifikasi = spesifikasi;
        this.statusTersedia = statusTersedia;
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
}

public class LaboratoriumKomputer {

    public static String cekStatusTeks(boolean status) {
        if (status) {
            return "Tersedia";
        } else {
            return "Maintenance";
        }
    }

    public static void tampilkanSemuaKomputer(ArrayList<Komputer> daftarKomputer) {
        System.out.println("\nDaftar Komputer Laboratorium:");
        System.out.println();

        for (Komputer comp : daftarKomputer) {
            System.out.println("ID Komputer : " + comp.getIdKomputer());
            System.out.println("Spesifikasi : " + comp.getSpesifikasi());
            System.out.println("Status      : " + cekStatusTeks(comp.isStatusTersedia()));
            System.out.println();
        }
    }

    // Membaca angka dengan aman (tidak crash jika input bukan angka)
    public static int bacaAngka(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Input harus berupa angka. Coba lagi: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ArrayList<Komputer> labKomputer = new ArrayList<>();
            
            labKomputer.add(new Komputer(101, "Core i5, RAM 16GB, RTX 3060", true));
            labKomputer.add(new Komputer(102, "Core i3, RAM 8GB, GTX 1650", true));
            labKomputer.add(new Komputer(103, "Core i7, RAM 32GB, RTX 4070", false));
            
            int pilihan = 0;
            
            do {
                System.out.println("\nMenu Sistem Lab Komputer");
                System.out.println("1. Lihat Daftar Komputer");
                System.out.println("2. Ubah Status Komputer");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu (1-3): ");
                
                pilihan = bacaAngka(scanner);
                
                switch (pilihan) {
                    case 1 -> tampilkanSemuaKomputer(labKomputer);
                    case 2 -> {
                        System.out.print("\nMasukkan ID Komputer: ");
                        int targetId = bacaAngka(scanner);
                        boolean ditemukan = false;
                        for (Komputer comp : labKomputer) {
                            if (comp.getIdKomputer() == targetId) {
                                ditemukan = true;
                                System.out.print("Pilih Status (1 untuk Tersedia, 0 untuk Maintenance): ");
                                int statusBaru = bacaAngka(scanner);
                                
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