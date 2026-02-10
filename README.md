# Reflection 1

## Clean Code Principles
Pada kode yang telah diimplementasikan di modul ini, saya telah menerapkan beberapa prinsip *Clean code* agar kode lebih mudah dibaca dan dipahami:

- **Meaningful Names:** Penamaan variabel dan metode dibuat deskriptif sehingga tujuannya jelas.
- **Comment:** Pengunaan komen yang minim, di modul ini bahkan tidak diaplikasikan. Karena berdasarakan pemahaman saya, komen disini lebih mengarah untuk menandai konteks, seperti risk, alasan teknis, dan sebagainya.

## 2. Secure Coding Practices
Pada modul ini, *Secure coding* practice yang dilakukan:

- **UUID untuk ID Produk:** Dalam `ProductServiceImpl`, saya menggunakan `UUID.randomUUID()` untuk membuat ID produk. Hal ini lebih aman digunakan dibandingkan menggunakan *integer* sekuensial (misalnya 1, 2, 3...) karena ID menjadi sulit ditebak oleh pihak yang tidak bertanggung jawab.

## 3. Mistakes & Areas for Improvement
Setelah mereview kembali, ada hal yang mungkin bisa lebih diimprove:

- **ProductServiceImpl:** Pada fungsi create dan edit, dalam satu fungsi saya mengubah state sekaligus mengembalikan data. Hal ini tidak sesuai dengan prinsip *Command-Query Separation*.
