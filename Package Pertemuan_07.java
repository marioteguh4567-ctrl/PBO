
public class CallingSequence {

    public static void main(String[] args) {

        System.out.println("1. Program dimulai");

        // main() memanggil method pertama
        methodPertama();

        System.out.println("4. Program selesai");
    }

    static void methodPertama() {

        System.out.println("2. Method pertama dijalankan");

        // Method pertama memanggil method kedua
        methodKedua();

        System.out.println("3. Method pertama selesai");
    }

    static void methodKedua() {

        System.out.println("Method kedua dijalankan");
    }
}

/*
Perhatikan urutannya:
1. Java menjalankan main().
2. main() memanggil methodPertama().
3. methodPertama() memanggil methodKedua().
4. methodKedua() selesai, lalu kembali ke methodPertama().
5. Setelah methodPertama() selesai, kembali ke main().

Hubungannya dengan Memory Management:
Setiap pemanggilan method membutuhkan call frame
yang disimpan sementara di dalam call stack.

Call frame ditambahkan ketika method dipanggil
dan dilepas ketika method selesai.

Call stack menggunakan prinsip LIFO
(Last In, First Out).
*/
