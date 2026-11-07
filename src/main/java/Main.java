import view.PesanSeblakView;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PesanSeblakView().setVisible(true);
        });
    }
}