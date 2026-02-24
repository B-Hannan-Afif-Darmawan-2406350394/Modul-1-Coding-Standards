# Reflection 1

## Clean Code Principles
Pada kode yang telah diimplementasikan di modul ini, saya telah menerapkan beberapa prinsip *Clean code* agar kode lebih mudah dibaca dan dipahami:

- **Meaningful Names:** Penamaan variabel dan metode dibuat deskriptif sehingga tujuannya jelas.
- **Comment:** Pengunaan komen yang minim, di modul ini bahkan tidak diaplikasikan. Karena berdasarakan pemahaman saya, komen disini lebih mengarah untuk menandai konteks, seperti risk, alasan teknis, dan sebagainya.

## Secure Coding Practices
Pada modul ini, *Secure coding* practice yang dilakukan:

- **UUID untuk ID Produk:** Dalam `ProductServiceImpl`, saya menggunakan `UUID.randomUUID()` untuk membuat ID produk. Hal ini lebih aman digunakan dibandingkan menggunakan *integer* sekuensial (misalnya 1, 2, 3...) karena ID menjadi sulit ditebak oleh pihak yang tidak bertanggung jawab.

## Mistakes & Areas for Improvement
Setelah mereview kembali, ada hal yang mungkin bisa lebih diimprove:

- **ProductServiceImpl:** Pada fungsi create dan edit, dalam satu fungsi saya mengubah state sekaligus mengembalikan data. Hal ini tidak sesuai dengan prinsip *Command-Query Separation*.

# Reflection 2

## Unit Testing & Code Coverage
Setelah menulis unit test, saya merasa kode yang dibuat sudah lebih terasa aman karena sudah diperiksa.

**Berapa banyak unit test yang harus dibuat dalam satu class?**
Setau saya tidak ada angka fix, namun ada beberapa jenis yang perlu dibuat:
1.  **Positive Scenarios:** Kode berjalan sesuai harapan dengan input yang valid.
2.  **Negative Scenarios:** Kode menangani input tidak valid atau error dengan benar (misalnya, input jumlah negatif atau mencari ID yang tidak ada).
3.  **Edge Cases:** Nilai batas (misalnya, jumlah 0, string kosong, atau nama yang sangat panjang).

**Code Coverage:**
*Code coverage* seperti arti namanya berguna untuk melihat apakah ada baris kode yang tidak dijalankan saat pengujian, bisa jadi redundant ataupun tidak terkena uji.

## Functional Test Cleanliness
Cleanliness pada kode tersebut akan berkurang, karena melanggar prinsip **DRY (Don't Repeat Yourself)** dan menurunkan kualitas kode.

# Reflection 3 (MODULE 2)

## Code Quality Issues & Strategy
1. Redundant declaration of thrown exception\
Menghapus deklarasi `throws ...` dari method agar kode lebih bersih.
2. Unused import (UUID)
Menghapus baris import yang tidak terpakai untuk menjaga kerapian kode.
3. Removing public modifier
Menghapus keyword `public` pada class, karena berdasarkan analysis lebih baik default.
4. No assertion
Menambahkan `assertEquals` ke dalam test tersebut.
5. Reduce duplication in ProductRepository tests
Memindahkan kode yang berulang ke dalam `@BeforeEach`.

## Met CI/CD Requirement
Alasan:
1. **Automated Testing**: File workflow (CI) secara otomatis menjalankan ./gradlew test setiap kali ada push atau pull_request, sehingga memastikan kevaliditasnya.
2. **Code Quality Checks**: SonarCloud Analysis dan Scorecard supply-chain security berjalan otomatis pada setiap push. Ini memverifikasi standar kualitas dan keamanan kode tanpa campur tangan secara manual.
3. **Fast Feedback**: Karena workflow ini dijalankan setiap push, developer langsung mendapatkan notifikasi (Green/Red checklist) jika ada kode yang rusak.
4. **Automated Delivery**: Walaupun tidak ada YAML khusus deploy, tapi repository ini sudah diintegrasikan dengan Railway (PaaS) pada branch main. 


