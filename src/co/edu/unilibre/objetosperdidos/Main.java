package co.edu.unilibre.objetosperdidos;

import co.edu.unilibre.objetosperdidos.vista.Tema;
import co.edu.unilibre.objetosperdidos.vista.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tema.instalar();
            new VentanaPrincipal().setVisible(true);
        });
    }
}
