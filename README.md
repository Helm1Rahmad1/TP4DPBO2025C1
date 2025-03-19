# TP4DPBO2025C1

# Janji
Saya Muhammad Helmi Rahmadi dengan NIM 2311574 mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
![image](https://github.com/user-attachments/assets/74506147-41fe-4fdf-9b7c-dfcd87d1d7d3)

Desain program mencakup komponen input seperti **text field, combo box, dan radio button** untuk mengisi data mahasiswa, serta tabel untuk menampilkan data. Program ini menerapkan konsep **CRUD (Create, Read, Update, Delete)** sehingga pengguna dapat menambah, mengedit, dan menghapus data dengan mudah.

Program ini menggunakan **ArrayList** untuk menyimpan data mahasiswa secara dinamis. Ketika pengguna melakukan tindakan (misalnya menambah atau menghapus data), daftar mahasiswa diperbarui, dan tabel ditampilkan kembali dengan informasi terbaru. Selain itu, tampilan antarmuka dibuat sederhana dan user-friendly dengan warna background putih, serta tombol yang berubah sesuai konteks (misalnya, tombol "Add" berubah menjadi "Update" saat data dipilih).

Berikut adalah poin-poin desain program:
- **Bahasa Pemrograman:** Java (menggunakan Swing untuk GUI).
- **Struktur Data:** Menggunakan **ArrayList** untuk menyimpan daftar mahasiswa.
- **Komponen GUI:**
  - `JTextField` untuk input **NIM** dan **Nama.**
  - `JComboBox` untuk memilih **Jenis Kelamin.**
  - `JRadioButton` untuk memilih **Tempat Tinggal.**
  - `JTable` untuk menampilkan **daftar mahasiswa.**
  - `JButton` untuk operasi **Add, Update, Delete, dan Clear Form.**
- **Tabel Data:**
  - Memiliki **4 kolom: NIM, Nama, Jenis Kelamin, dan Tempat Tinggal.**
  - Menggunakan **DefaultTableModel** agar bisa diperbarui secara dinamis.
- **Interaksi Pengguna:**
  - Klik baris tabel akan mengisi form input.
  - Tombol "Add" berubah menjadi "Update" saat data dipilih.
  - Konfirmasi sebelum menghapus data.

# Alur Program

1. **Mulai** → Window utama (600x450 px) muncul dengan tabel data mahasiswa.  
2. **Tambah Data** → Isi form (NIM, Nama, Jenis Kelamin, Tempat Tinggal) → Klik **Add** → Data masuk ke tabel.  
3. **Pilih Data** → Klik baris di tabel → Form terisi otomatis → Tombol **Add** berubah jadi **Update**, dan **Delete** muncul.  
4. **Edit Data** → Ubah isian → Klik **Update** → Data diperbarui di tabel.  
5. **Hapus Data** → Klik **Delete** → Konfirmasi muncul → Pilih **Yes** → Data dihapus.  
6. **Batalkan Input/Edit** → Klik **Cancel** → Form kosong lagi, tombol kembali ke semula.  
7. **Tutup Program** → Klik **X** → Aplikasi berhenti.  

# Dokumentasi
https://github.com/user-attachments/assets/2e1dc820-2601-4e64-9370-56522dcdf9d7



