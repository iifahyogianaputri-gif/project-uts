package model;

public class Pesanan {
    private String idPesanan;
    private String levelPedas;
    private String topping;
    private String metodePengambilan;
    private double totalHarga;

    public Pesanan(String idPesanan, String levelPedas, String topping, String metodePengambilan, double totalHarga) {
        this.idPesanan = idPesanan;
        this.levelPedas = levelPedas;
        this.topping = topping;
        this.metodePengambilan = metodePengambilan;
        this.totalHarga = totalHarga;
    }

    public String getIdPesanan() { return idPesanan; }
    public String getLevelPedas() { return levelPedas; }
    public String getTopping() { return topping; }
    public String getMetodePengambilan() { return metodePengambilan; }
    public double getTotalHarga() { return totalHarga; }
}