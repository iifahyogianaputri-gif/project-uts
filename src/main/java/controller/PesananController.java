package controller;

import model.DatabaseConnection;
import model.Pesanan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PesananController {

    public double hitungTotalHarga(double hargaDasar, boolean isDelivery) {
        double total = hargaDasar;
        if (isDelivery) {
            total += 2000.0; // Ongkir Rp2.000
        }
        return total;
    }

    public boolean simpanPesanan(Pesanan pesanan) {
        String sql = "INSERT INTO pesanan (id_pesanan, level_pedas, topping, metode_pengambilan, total_harga) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, pesanan.getIdPesanan());
            stmt.setString(2, pesanan.getLevelPedas());
            stmt.setString(3, pesanan.getTopping());
            stmt.setString(4, pesanan.getMetodePengambilan());
            stmt.setDouble(5, pesanan.getTotalHarga());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.err.println("Gagal menyimpan pesanan: " + e.getMessage());
            return false;
        }
    }
}