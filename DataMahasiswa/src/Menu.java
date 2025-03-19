import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Menu extends JFrame{
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(600, 450);

        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);

        // isi window
        window.setContentPane(window.mainPanel);

        // ubah warna background
        window.getContentPane().setBackground(Color.WHITE);

        // tampilkan window
        window.setVisible(true);

        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel jenisKelaminLabel;
    private JRadioButton PPRadioButton;
    private JLabel tempatTinggalLabel;
    private JRadioButton anakKostRadioButton;
    private JRadioButton asramaRadioButton;
    private ButtonGroup tempatTinggalGroup;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        // inisialisasi button group untuk radio button
        tempatTinggalGroup = new ButtonGroup();
        tempatTinggalGroup.add(anakKostRadioButton);
        tempatTinggalGroup.add(PPRadioButton);
        tempatTinggalGroup.add(asramaRadioButton);

        // set default selected radio button
        anakKostRadioButton.setSelected(true);

        // isi listMahasiswa
        populateList();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        // atur isi combo box
        jenisKelaminComboBox.addItem("Laki-laki");
        jenisKelaminComboBox.addItem("Perempuan");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    // tambah data baru
                    insertData();
                } else {
                    // update data
                    updateData();
                }
            }
        });

        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // konfirmasi sebelum menghapus
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String nim = mahasiswaTable.getValueAt(selectedIndex, 0).toString();
                String nama = mahasiswaTable.getValueAt(selectedIndex, 1).toString();
                String jenisKelamin = mahasiswaTable.getValueAt(selectedIndex, 2).toString();
                String tempatTinggal = mahasiswaTable.getValueAt(selectedIndex, 3).toString();

                // ubah isi textfield dan combo box
                nimField.setText(nim);
                namaField.setText(nama);
                jenisKelaminComboBox.setSelectedItem(jenisKelamin);

                // set radio button sesuai dengan tempatTinggal
                if (tempatTinggal.equals("Anak Kost")) {
                    anakKostRadioButton.setSelected(true);
                } else if (tempatTinggal.equals("PP (Pulang Pergi)")) {
                    PPRadioButton.setSelected(true);
                } else if (tempatTinggal.equals("Asrama")) {
                    asramaRadioButton.setSelected(true);
                }

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");

                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    // Fungsi untuk mendapatkan tempat tinggal yang dipilih dari radio button
    private String getSelectedTempatTinggal() {
        if (anakKostRadioButton.isSelected()) {
            return "Anak Kost";
        } else if (PPRadioButton.isSelected()) {
            return "PP (Pulang Pergi)";
        } else if (asramaRadioButton.isSelected()) {
            return "Asrama";
        }
        return "Anak Kost"; // default
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"NIM", "Nama", "Jenis Kelamin", "Tempat Tinggal"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel tableModel = new DefaultTableModel(null, column);

        // isi tabel dengan listMahasiswa
        for (Mahasiswa mahasiswa : listMahasiswa) {
            Object[] row = new Object[4];
            row[0] = mahasiswa.getNim();
            row[1] = mahasiswa.getNama();
            row[2] = mahasiswa.getJenisKelamin();
            row[3] = mahasiswa.getTempatTinggal();

            tableModel.addRow(row);
        }

        return tableModel;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String tempatTinggal = getSelectedTempatTinggal();

        // tambahkan data ke dalam list
        listMahasiswa.add(new Mahasiswa(nim, nama, jenisKelamin, tempatTinggal));

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText();
        String nama = namaField.getText();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString();
        String tempatTinggal = getSelectedTempatTinggal();

        // ubah data mahasiswa di list
        Mahasiswa mahasiswa = listMahasiswa.get(selectedIndex);
        mahasiswa.setNim(nim);
        mahasiswa.setNama(nama);
        mahasiswa.setJenisKelamin(jenisKelamin);
        mahasiswa.setTempatTinggal(tempatTinggal);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }

    public void deleteData() {
        // hapus data dari list
        listMahasiswa.remove(selectedIndex);

        // update tabel
        mahasiswaTable.setModel(setTable());

        // bersihkan form
        clearForm();

        // feedback
        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        jenisKelaminComboBox.setSelectedIndex(0);
        anakKostRadioButton.setSelected(true);

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }

    private void populateList() {
        // Menambahkan data dummy dengan tempat tinggal
        listMahasiswa.add(new Mahasiswa("2203999", "Amelia Zalfa Julianti", "Perempuan", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2202292", "Muhammad Iqbal Fadhilah", "Laki-laki", "PP (Pulang Pergi)"));
        listMahasiswa.add(new Mahasiswa("2202346", "Muhammad Rifky Afandi", "Laki-laki", "Asrama"));
        listMahasiswa.add(new Mahasiswa("2210239", "Muhammad Hanif Abdillah", "Laki-laki", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2202046", "Nurainun", "Perempuan", "PP (Pulang Pergi)"));
        listMahasiswa.add(new Mahasiswa("2205101", "Kelvin Julian Putra", "Laki-laki", "Asrama"));
        listMahasiswa.add(new Mahasiswa("2200163", "Rifanny Lysara Annastasya", "Perempuan", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2202869", "Revana Faliha Salma", "Perempuan", "PP (Pulang Pergi)"));
        listMahasiswa.add(new Mahasiswa("2209489", "Rakha Dhifiargo Hariadi", "Laki-laki", "Asrama"));
        listMahasiswa.add(new Mahasiswa("2203142", "Roshan Syalwan Nurilham", "Laki-laki", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2200311", "Raden Rahman Ismail", "Laki-laki", "PP (Pulang Pergi)"));
        listMahasiswa.add(new Mahasiswa("2200978", "Ratu Syahirah Khairunnisa", "Perempuan", "Asrama"));
        listMahasiswa.add(new Mahasiswa("2204509", "Muhammad Fahreza Fauzan", "Laki-laki", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2205027", "Muhammad Rizki Revandi", "Laki-laki", "PP (Pulang Pergi)"));
        listMahasiswa.add(new Mahasiswa("2203484", "Arya Aydin Margono", "Laki-laki", "Asrama"));
        listMahasiswa.add(new Mahasiswa("2200481", "Marvel Ravindra Dioputra", "Laki-laki", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2209889", "Muhammad Fadlul Hafiizh", "Laki-laki", "PP (Pulang Pergi)"));
        listMahasiswa.add(new Mahasiswa("2206697", "Rifa Sania", "Perempuan", "Asrama"));
        listMahasiswa.add(new Mahasiswa("2207260", "Imam Chalish Rafidhul Haque", "Laki-laki", "Anak Kost"));
        listMahasiswa.add(new Mahasiswa("2204343", "Meiva Labibah Putri", "Perempuan", "PP (Pulang Pergi)"));
    }
}