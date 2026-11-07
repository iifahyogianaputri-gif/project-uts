package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/seblak_ibulina?createDatabaseIfNotExist=true";
    private static final String USER = "root";
    private static final String PASS = "Ifahputri15!"; 

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Driver MySQL Connector tidak ditemukan!");
            e.printStackTrace();
        }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static void initTable() {
        String sql = "CREATE TABLE IF NOT EXISTS pesanan ("
                   + "id_pesanan VARCHAR(50) PRIMARY KEY, "
                   + "level_pedas VARCHAR(20), "
                   + "topping VARCHAR(100), "
                   + "metode_pengambilan VARCHAR(20), "
                   + "total_harga DOUBLE"
                   + ");";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabel 'pesanan' siap digunakan.");
        } catch (SQLException e) {
            System.err.println("Gagal inisialisasi tabel: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        initTable();
    }
}