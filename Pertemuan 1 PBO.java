
import java.util.Scanner;

public class KelilingLingkaran {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        double jariJari, keliling;

        System.out.println("=== PROGRAM KELILING LINGKARAN ===");

        System.out.print("Masukkan jari-jari: ");
        jariJari = input.nextDouble();

        keliling = 2 * Math.PI * jariJari;

        System.out.println("Keliling Lingkaran = " + keliling);

        input.close();
    }
}
