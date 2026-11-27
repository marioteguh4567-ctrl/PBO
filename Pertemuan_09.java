import java.util.Scanner;

class Komputer {
    int idKomputer;
    String spesifikasi;
    boolean statusTersedia;

    public void tampilkanInfoDasar() {
        System.out.println("ID Komputer : " + idKomputer);
        System.out.println("Spesifikasi : " + spesifikasi);
        System.out.println("Status      : " + (statusTersedia ? "Tersedia" : "Maintenance"));
    }
}

class KomputerGaming extends Komputer {
    String kartuGrafis;

    public void tampilkanInformasiGaming() {
        tampilkanInfoDasar();
        System.out.println("Tipe        : Gaming (GPU: " + kartuGrafis + ")");
    }
}

class KomputerServer extends Komputer {
    String ipAddress;

    public void tampilkanInformasiServer() {
        tampilkanInfoDasar();
        System.out.println("Tipe        : Server (IP: " + ipAddress + ")");
    }
}

public class LaboratoriumKomputer {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            KomputerGaming[] labGaming = new KomputerGaming[2];
            labGaming[0] = new KomputerGaming();
            labGaming[0].idKomputer = 101;
            labGaming[0].spesifikasi = "Core i5, RAM 16GB";
            labGaming[0].statusTersedia = true;
            labGaming[0].kartuGrafis = "RTX 3060";
            
            labGaming[1] = new KomputerGaming();
            labGaming[1].idKomputer = 102;
            labGaming[1].spesifikasi = "Core i3, RAM 8GB";
            labGaming[1].statusTersedia = true;
            labGaming[1].kartuGrafis = "GTX 1650";
            
            KomputerServer[] labServer = new KomputerServer[1];
            labServer[0] = new KomputerServer();
            labServer[0].idKomputer = 103;
            labServer[0].spesifikasi = "Core i7, RAM 32GB";
            labServer[0].statusTersedia = false;
            labServer[0].ipAddress = "192.168.1.100";
            
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
                    case 1:
                        System.out.println("\nDaftar Komputer Laboratorium:");
                        for (KomputerGaming labGaming1 : labGaming) {
                            labGaming1.tampilkanInformasiGaming();
                            System.out.println();
                        }   for (KomputerServer labServer1 : labServer) {
                            labServer1.tampilkanInformasiServer();
                            System.out.println();
                        }   break;
                    case 2:
                        System.out.print("\nMasukkan ID Komputer: ");
                        int targetId = scanner.nextInt();
                        boolean ditemukan = false;
                        for (KomputerGaming labGaming1 : labGaming) {
                            if (labGaming1.idKomputer == targetId) {
                                ditemukan = true;
                                System.out.print("Pilih Status (1 untuk Tersedia, 0 untuk Maintenance): ");
                                int statusBaru = scanner.nextInt();
                                switch (statusBaru) {
                                    case 1:
                                        labGaming1.statusTersedia = true;
                                        System.out.println("Status diperbarui.");
                                        break;
                                    case 0:
                                        labGaming1.statusTersedia = false;
                                        System.out.println("Status diperbarui.");
                                        break;
                                    default:
                                        System.out.println("Pilihan tidak valid.");
                                        break;
                                }
                            }
                        }   for (KomputerServer labServer1 : labServer) {
                            if (labServer1.idKomputer == targetId) {
                                ditemukan = true;
                                System.out.print("Pilih Status (1 untuk Tersedia, 0 untuk Maintenance): ");
                                int statusBaru = scanner.nextInt();
                                switch (statusBaru) {
                                    case 1:
                                        labServer1.statusTersedia = true;
                                        System.out.println("Status diperbarui.");
                                        break;
                                    case 0:
                                        labServer1.statusTersedia = false;
                                        System.out.println("Status diperbarui.");
                                        break;
                                    default:
                                        System.out.println("Pilihan tidak valid.");
                                        break;
                                }
                            }
                        }   if (!ditemukan) {
                            System.out.println("Komputer tidak ditemukan.");
                        }   break;
                    case 3:
                        System.out.println("\nProgram selesai.");
                        break;
                    default:
                        System.out.println("\nPilihan tidak valid.");
                        break;
                }
                
            } while (pilihan != 3);
        }
    }
} 