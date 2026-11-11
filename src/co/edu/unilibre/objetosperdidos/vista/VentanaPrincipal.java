package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.ObjetoController;
import co.edu.unilibre.objetosperdidos.modelo.GestorObjetos;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * Ventana principal con navegación por menú lateral (CardLayout):
 * un botón por pantalla, el activo resaltado en rojo.
 */
public class VentanaPrincipal extends JFrame {

    private static final String[] TITULOS = {
        "1. Registro de Objetos",
        "2. Búsqueda y Consulta",
        "3. Proceso de Devolución"
    };
    private static final int[] ATAJOS = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3};

    private final CardLayout tarjetas = new CardLayout();
    private final JPanel contenido = new JPanel(tarjetas);
    private final JButton[] botonesMenu = new JButton[TITULOS.length];
    private PanelBusquedaObjeto panelBusqueda;

    public VentanaPrincipal() {
        super("Sistema Integrado de Objetos Perdidos - Universidad Libre");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(900, 620));
        setSize(1050, 680);
        setLocationRelativeTo(null);

        initUI();
        navegarA(0);
    }

    private void initUI() {
        GestorObjetos modelo = GestorObjetos.getInstancia();
        PanelRegistroObjeto panelRegistro = new PanelRegistroObjeto();
        new ObjetoController(modelo, panelRegistro);

        panelBusqueda = new PanelBusquedaObjeto();
        PanelDevolucionObjeto panelDevolucion = new PanelDevolucionObjeto();

        contenido.setBackground(Tema.NEGRO);
        contenido.add(panelRegistro, TITULOS[0]);
        contenido.add(panelBusqueda, TITULOS[1]);
        contenido.add(panelDevolucion, TITULOS[2]);

        add(crearMenuLateral(), BorderLayout.WEST);
        add(contenido, BorderLayout.CENTER);
    }

    private JPanel crearMenuLateral() {
        JPanel menu = new JPanel(new BorderLayout(0, 14));
        menu.setBackground(Tema.NEGRO);
        menu.setPreferredSize(new Dimension(270, 0));
        menu.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 0, 2, Tema.ROJO),
                BorderFactory.createEmptyBorder(16, 12, 12, 12)));

        JLabel titulo = new JLabel("MENÚ", SwingConstants.LEFT);
        titulo.setFont(Tema.FUENTE_NEGRITA);
        titulo.setForeground(Tema.ROJO);
        menu.add(titulo, BorderLayout.NORTH);

        JPanel botones = new JPanel(new GridLayout(0, 1, 0, 8));
        botones.setOpaque(false);
        for (int i = 0; i < TITULOS.length; i++) {
            final int indice = i;
            JButton b = new JButton(TITULOS[i]);
            b.setFont(Tema.FUENTE_NEGRITA);
            b.setForeground(Tema.BLANCO);
            b.setHorizontalAlignment(SwingConstants.LEFT);
            b.setFocusPainted(false);
            b.setOpaque(true);
            b.setContentAreaFilled(true);
            b.setCursor(new Cursor(Cursor.HAND_CURSOR));
            b.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Tema.ROJO, 2),
                    BorderFactory.createEmptyBorder(12, 14, 12, 14)));
            b.setMnemonic(ATAJOS[i]);
            b.addActionListener(e -> navegarA(indice));
            botonesMenu[i] = b;
            botones.add(b);
        }

        // Los botones conservan su altura natural en la parte superior del menú.
        JPanel contenedorBotones = new JPanel(new BorderLayout());
        contenedorBotones.setOpaque(false);
        contenedorBotones.add(botones, BorderLayout.NORTH);
        menu.add(contenedorBotones, BorderLayout.CENTER);
        return menu;
    }

    private void navegarA(int indice) {
        tarjetas.show(contenido, TITULOS[indice]);
        for (int i = 0; i < botonesMenu.length; i++) {
            botonesMenu[i].setBackground(i == indice ? Tema.ROJO : Tema.NEGRO);
        }
        if (indice == 1) {
            panelBusqueda.refrescar();
        }
    }
}
