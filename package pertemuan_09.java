package pertemuan_09.materi.contoh.hierarchical_inheritance;

public class DemoHierarchicalInheritance {

    public static void main(String[] args) {

        Manager manager = new Manager("Andi", 8000000, 5);
        Developer developer = new Developer("Budi", 6000000, "Java");
        Designer designer = new Designer("Citra", 5500000, "Figma");

        System.out.println("=== DATA MANAGER ===");
        manager.tampilkanInfo();
        manager.bekerja();
        manager.mengelolaTim();

        System.out.println();

        System.out.println("=== DATA DEVELOPER ===");
        developer.tampilkanInfo();
        developer.bekerja();
        developer.menulisKode();

        System.out.println();

        System.out.println("=== DATA DESIGNER ===");
        designer.tampilkanInfo();
        designer.bekerja();
        designer.membuatDesain();
    }
}