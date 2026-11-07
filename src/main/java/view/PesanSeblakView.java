package view;

import controller.PesananController;
import model.Pesanan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesanSeblakView extends JFrame {

    private JComboBox<String> cbLevelPedas;
    private JCheckBox chkKerupuk, chkBakso, chkSosis;
    private JRadioButton rbPickup, rbDelivery;
    private ButtonGroup bgMetode;
    private JLabel lblTotalHarga;
    private JButton btnPesan;

    private PesananController controller;
    private double hargaDasar = 13000;

    public PesanSeblakView() {
        controller = new PesananController();

        setTitle("Pesan Seblak Ibu Lina");
        setSize(380, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 12));

        // 1. Judul Form
        JLabel lblTitle = new JLabel("Form Pemesanan Seblak");
        add(lblTitle);

        // 2. Level Pedas
        JPanel panelPedas = new JPanel();
        panelPedas.add(new JLabel("Level Pedas:"));
        String[] levels = {"Level 0", "Level 1", "Level 2", "Level 3"};
        cbLevelPedas = new JComboBox<>(levels);
        panelPedas.add(cbLevelPedas);
        add(panelPedas);

        // 3. Topping
        JPanel panelTopping = new JPanel();
        panelTopping.add(new JLabel("Topping (+2k):"));
        chkKerupuk = new JCheckBox("Kerupuk");
        chkBakso = new JCheckBox("Bakso");
        chkSosis = new JCheckBox("Sosis");
        panelTopping.add(chkKerupuk);
        panelTopping.add(chkBakso);
        panelTopping.add(chkSosis);
        add(panelTopping);

        // 4. Metode Pengambilan
        JPanel panelMetode = new JPanel();
        panelMetode.add(new JLabel("Metode:"));
        rbPickup = new JRadioButton("Pick-Up", true);
        rbDelivery = new JRadioButton("Delivery (+2k)");
        bgMetode = new ButtonGroup();
        bgMetode.add(rbPickup);
        bgMetode.add(rbDelivery);
        panelMetode.add(rbPickup);
        panelMetode.add(rbDelivery);
        add(panelMetode);

        // 5. Total Harga
        lblTotalHarga = new JLabel("Total Harga: Rp 13000");
        add(lblTotalHarga);

        // 6. Tombol Pesan
        btnPesan = new JButton("Pesan Sekarang");
        add(btnPesan);

        // Listener untuk Update Harga Real-time
        ActionListener listenerHarga = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungTotal();
            }
        };

        chkKerupuk.addActionListener(listenerHarga);
        chkBakso.addActionListener(listenerHarga);
        chkSosis.addActionListener(listenerHarga);
        rbPickup.addActionListener(listenerHarga);
        rbDelivery.addActionListener(listenerHarga);

        // Listener Tombol Pesan
        btnPesan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });
    }

    private void hitungTotal() {
        double total = hargaDasar;
        if (chkKerupuk.isSelected()) total += 2000;
        if (chkBakso.isSelected()) total += 2000;
        if (chkSosis.isSelected()) total += 2000;

        boolean isDelivery = rbDelivery.isSelected();
        double totalAkhir = controller.hitungTotalHarga(total, isDelivery);

        lblTotalHarga.setText("Total Harga: Rp " + (int) totalAkhir);
    }

    private void simpanData() {
        String idPesanan = "ORD-" + (System.currentTimeMillis() % 10000);
        String levelPedas = (String) cbLevelPedas.getSelectedItem();

        String topping = "";
        if (chkKerupuk.isSelected()) topping += "Kerupuk ";
        if (chkBakso.isSelected()) topping += "Bakso ";
        if (chkSosis.isSelected()) topping += "Sosis ";
        if (topping.isEmpty()) topping = "Tanpa Topping";

        String metode = rbDelivery.isSelected() ? "Delivery" : "Pick-Up";

        double total = hargaDasar;
        if (chkKerupuk.isSelected()) total += 2000;
        if (chkBakso.isSelected()) total += 2000;
        if (chkSosis.isSelected()) total += 2000;
        double totalAkhir = controller.hitungTotalHarga(total, rbDelivery.isSelected());

        Pesanan pesanan = new Pesanan(idPesanan, levelPedas, topping.trim(), metode, totalAkhir);

        if (controller.simpanPesanan(pesanan)) {
            JOptionPane.showMessageDialog(this, "Pesanan Berhasil Disimpan!\nID: " + idPesanan);
        } else {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan pesanan.");
        }
    }

    public static void main(String[] args) {
        new PesanSeblakView().setVisible(true);
    }
}