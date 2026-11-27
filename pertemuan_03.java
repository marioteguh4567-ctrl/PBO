package laboratoriumkomputer;

import java.util.Scanner;

public class LaboratoriumKomputer {
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

                if (scanner.hasNextInt()) {
                    pilihan = scanner.nextInt();
                } else {
                    System.out.println("Input harus berupa angka.");
                    scanner.next();
                    continue;
                }

                switch (pilihan) {
                    case 1 -> {
                        System.out.println(
                            "\nDaftar Komputer Laboratorium:\n"
                        );

                        for (int i = 0; i < idKomputer.length; i++) {
                            System.out.println(
                                "ID Komputer : " + idKomputer[i]
                            );

                            System.out.println(
                                "Spesifikasi : " + spesifikasi[i]
                            );

                            if (statusTersedia[i]) {
                                System.out.println(
                                    "Status      : Tersedia\n"
                                );
                            } else {
                                System.out.println(
                                    "Status      : Maintenance\n"
                                );
                            }
                        }
                    }

                    case 2 -> {
                        System.out.print("\nMasukkan ID Komputer: ");
                        int targetId = scanner.nextInt();

                        boolean ditemukan = false;

                        for (int i = 0; i < idKomputer.length; i++) {
                            if (idKomputer[i] == targetId) {
                                ditemukan = true;

                                System.out.print(
                                    "Pilih Status (1 untuk Tersedia, "
                                    + "0 untuk Maintenance): "
                                );

                                int statusBaru = scanner.nextInt();

                                switch (statusBaru) {
                                    case 1 -> {
                                        statusTersedia[i] = true;
                                        System.out.println(
                                            "Status komputer diperbarui "
                                            + "menjadi Tersedia."
                                        );
                                    }

                                    case 0 -> {
                                        statusTersedia[i] = false;
                                        System.out.println(
                                            "Status komputer diperbarui "
                                            + "menjadi Maintenance."
                                        );
                                    }

                                    default -> System.out.println(
                                        "Pilihan status tidak valid."
                                    );
                                }

                                break;
                            }
                        }

                        if (!ditemukan) {
                            System.out.println(
                                "Komputer dengan ID " + targetId
                                + " tidak ditemukan."
                            );
                        }
                    }

                    case 3 -> System.out.println(
                        "\nProgram selesai. Terima kasih."
                    );

                    default -> System.out.println(
                        "\nPilihan tidak valid. Silakan pilih angka 1-3."
                    );
                }

            } while (pilihan != 3);
        }
    }
}