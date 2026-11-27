package laboratoriumkomputer;

import java.util.Scanner;

public class Laboratoriumkomputer {

    public static String cekStatusTeks(boolean status) {
        return status ? "Tersedia" : "Maintenance";
    }

    public static void tampilkanDaftar(int[] id, String[] spek, boolean[] status) {
        System.out.println("\nDaftar Komputer Laboratorium:\n");
        for (int i = 0; i < id.length; i++) {
            System.out.println("ID Komputer : " + id[i]);
            System.out.println("Spesifikasi : " + spek[i]);
            System.out.println("Status      : " + cekStatusTeks(status[i]) + "\n");
        }
    }

    public static void ubahStatus(int targetId, int[] id, boolean[] status, Scanner scanner) {
        boolean ditemukan = false;
        for (int i = 0; i < id.length; i++) {
            if (id[i] == targetId) {
                ditemukan = true;
                System.out.print("Pilih Status (1 untuk Tersedia, 0 untuk Maintenance): ");
                int statusBaru = scanner.nextInt();

                switch (statusBaru) {
                    case 1 -> {
                        status[i] = true;
                        System.out.println("Status komputer diperbarui menjadi Tersedia.");
                    }
                    case 0 -> {
                        status[i] = false;
                        System.out.println("Status komputer diperbarui menjadi Maintenance.");
                    }
                    default -> System.out.println("Pilihan status tidak valid.");
                }
                break;
            }
        }
        if (!ditemukan) {
            System.out.println("Komputer dengan ID " + targetId + " tidak ditemukan.");
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] idKomputer = {101, 102, 103};
            String[] spesifikasi = {
                "Core i5, RAM 16GB, RTX 3060",
                "Core i3, RAM 8GB, GTX 1650",
                "Core i7, RAM 32GB, RTX 4070"
            };
            boolean[] statusTersedia = {true, true, false};
            int pilihan = 0;
            
            do {
                System.out.println("\nMenu Sistem Lab Komputer");
                System.out.println("1. Lihat Daftar Komputer");
                System.out.println("2. Ubah Status Komputer");
                System.out.println("3. Keluar");
                System.out.print("Pilih menu (1-3): ");
                
                pilihan = scanner.nextInt();
                
                switch (pilihan) {
                    case 1 -> tampilkanDaftar(idKomputer, spesifikasi, statusTersedia);
                    case 2 -> {
                        System.out.print("\nMasukkan ID Komputer: ");
                        int targetId = scanner.nextInt();
                        ubahStatus(targetId, idKomputer, statusTersedia, scanner);
                    }
                    case 3 -> System.out.println("Program selesai.");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } while (pilihan != 3);
        }
    }
}