package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.DevolucionController;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.util.Optional;

public class PanelDevolucionObjeto extends JPanel {

    private JTextField txtIdObjeto;
    private JTextField txtDocReceptor;
    private JTextField txtNombreReceptor;
    private JTextArea txtPruebaPertenencia;
    private JTextArea txtComprobante;
    private JButton btnBuscar;
    private JButton btnProcesar;
    private JButton btnCancelar;

    private final DevolucionController controller;
    private ObjetoPerdido objetoSeleccionado;

    private final Color COLOR_FONDO_VENTANA = new Color(18, 18, 18);
    private final Color COLOR_PANEL = new Color(30, 30, 30);
    private final Color COLOR_ROJO_ACCENTO = new Color(211, 47, 47);
    private final Color COLOR_ROJO_BORDE = new Color(183, 28, 28);
    private final Color COLOR_BLANCO = new Color(255, 255, 255);
    private final Color COLOR_CAMPOS_BG = new Color(42, 42, 42);
    private final Color COLOR_BOTON_CANCELAR = new Color(45, 45, 45);

    public PanelDevolucionObjeto() {
        controller = new DevolucionController();
        initComponents();
    }

    private void initComponents() {
        setBackground(COLOR_FONDO_VENTANA);
        setLayout(new BorderLayout(15, 15));

        Font fontEtiquetas = new Font("SansSerif", Font.BOLD, 12);
        Font fontCampos = new Font("SansSerif", Font.PLAIN, 12);
        Font fontBotones = new Font("SansSerif", Font.BOLD, 13);

        JPanel panelForm = new JPanel(new GridBagLayout());
        panelForm.setBackground(COLOR_PANEL);

        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                new LineBorder(COLOR_ROJO_ACCENTO, 1),
                "Proceso de Devolución de Objetos (Datos de Reclamación)",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                fontEtiquetas,
                COLOR_ROJO_ACCENTO
        );
        panelForm.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(15, 15, 15, 15)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3;
        JLabel lblId = crearEtiquetaBlanca("ID Objeto Perdido (ej. 101, 102):", fontEtiquetas);
        panelForm.add(lblId, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        JPanel panelBusqueda = new JPanel(new BorderLayout(5, 0));
        panelBusqueda.setOpaque(false);
        txtIdObjeto = crearCampoTextoOscuro(fontCampos);

        btnBuscar = crearBotonPersonalizado("Buscar", COLOR_BLANCO, COLOR_FONDO_VENTANA, fontBotones);
        panelBusqueda.add(txtIdObjeto, BorderLayout.CENTER);
        panelBusqueda.add(btnBuscar, BorderLayout.EAST);
        panelForm.add(panelBusqueda, gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3;
        JLabel lblDoc = crearEtiquetaBlanca("Documento Identificación:", fontEtiquetas);
        panelForm.add(lblDoc, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        txtDocReceptor = crearCampoTextoOscuro(fontCampos);
        panelForm.add(txtDocReceptor, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3;
        JLabel lblNombre = crearEtiquetaBlanca("Nombre Completo Receptor:", fontEtiquetas);
        panelForm.add(lblNombre, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        txtNombreReceptor = crearCampoTextoOscuro(fontCampos);
        panelForm.add(txtNombreReceptor, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3;
        JLabel lblPrueba = crearEtiquetaBlanca("Prueba / Detalles de Pertenencia:", fontEtiquetas);
        panelForm.add(lblPrueba, gbc);

        gbc.gridx = 1; gbc.weightx = 0.7;
        txtPruebaPertenencia = new JTextArea(3, 20);
        txtPruebaPertenencia.setFont(fontCampos);
        txtPruebaPertenencia.setBackground(COLOR_CAMPOS_BG);
        txtPruebaPertenencia.setForeground(COLOR_BLANCO);
        txtPruebaPertenencia.setCaretColor(COLOR_BLANCO);
        txtPruebaPertenencia.setLineWrap(true);
        txtPruebaPertenencia.setWrapStyleWord(true);

        JScrollPane scrollPrueba = new JScrollPane(txtPruebaPertenencia);
        scrollPrueba.setPreferredSize(new Dimension(100, 60));
        scrollPrueba.setBorder(new LineBorder(new Color(70, 70, 70), 1));
        panelForm.add(scrollPrueba, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weightx = 0.5;
        btnProcesar = crearBotonPersonalizado("Validar y Entregar", COLOR_ROJO_ACCENTO, COLOR_BLANCO, fontBotones);
        panelForm.add(btnProcesar, gbc);

        gbc.gridx = 1; gbc.weightx = 0.5;
        btnCancelar = crearBotonPersonalizado("Cancelar / Limpiar", COLOR_BOTON_CANCELAR, COLOR_BLANCO, fontBotones);
        btnCancelar.setBorder(new CompoundBorder(new LineBorder(COLOR_ROJO_BORDE, 1), new EmptyBorder(7, 14, 7, 14)));
        panelForm.add(btnCancelar, gbc);

        JPanel contenedorNorte = new JPanel(new BorderLayout());
        contenedorNorte.setOpaque(false);
        contenedorNorte.setBorder(new EmptyBorder(10, 15, 0, 15));
        contenedorNorte.add(panelForm, BorderLayout.CENTER);
        add(contenedorNorte, BorderLayout.NORTH);

        txtComprobante = new JTextArea();
        txtComprobante.setEditable(false);
        txtComprobante.setFont(new Font("Monospaced", Font.PLAIN, 12));
        txtComprobante.setBackground(new Color(10, 10, 10));
        txtComprobante.setForeground(COLOR_BLANCO);
        txtComprobante.setCaretColor(COLOR_BLANCO);
        txtComprobante.setMargin(new Insets(10, 10, 10, 10));

        TitledBorder titledComprobante = BorderFactory.createTitledBorder(
                new LineBorder(COLOR_ROJO_ACCENTO, 1),
                "Comprobante / Recibo de Entrega",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                fontEtiquetas,
                COLOR_ROJO_ACCENTO
        );

        JScrollPane scrollComprobante = new JScrollPane(txtComprobante);
        scrollComprobante.setBorder(new CompoundBorder(
                new EmptyBorder(0, 15, 15, 15),
                titledComprobante
        ));
        add(scrollComprobante, BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> accionBuscarObjeto());
        btnProcesar.addActionListener(e -> accionProcesarDevolucion());
        btnCancelar.addActionListener(e -> accionCancelar());
    }

    private JLabel crearEtiquetaBlanca(String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        label.setForeground(COLOR_BLANCO);
        return label;
    }

    private JTextField crearCampoTextoOscuro(Font font) {
        JTextField tf = new JTextField();
        tf.setFont(font);
        tf.setBackground(COLOR_CAMPOS_BG);
        tf.setForeground(COLOR_BLANCO);
        tf.setCaretColor(COLOR_BLANCO);
        tf.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(70, 70, 70), 1),
                new EmptyBorder(4, 6, 4, 6)
        ));
        return tf;
    }

    private JButton crearBotonPersonalizado(String texto, Color bg, Color fg, Font font) {
        JButton btn = new JButton(texto);
        btn.setUI(new BasicButtonUI());
        btn.setFont(font);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        return btn;
    }

    private void accionBuscarObjeto() {
        try {
            int id = Integer.parseInt(txtIdObjeto.getText().trim());
            Optional<ObjetoPerdido> opt = controller.buscarObjeto(id);

            if (opt.isPresent()) {
                objetoSeleccionado = opt.get();
                if ("Entregado".equalsIgnoreCase(objetoSeleccionado.getEstado())) {
                    JOptionPane.showMessageDialog(this, "El objeto ya fue entregado previamente.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Objeto encontrado: " + objetoSeleccionado.getTitulo(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No existe objeto con el ID ingresado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un ID numérico válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionProcesarDevolucion() {
        if (objetoSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Debe buscar un objeto válido primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String doc = txtDocReceptor.getText();
        String nombre = txtNombreReceptor.getText();
        String prueba = txtPruebaPertenencia.getText();

        boolean esLegitimo = controller.esDuenioLegitimo(doc, prueba);

        if (!esLegitimo) {
            JOptionPane.showMessageDialog(this, "RECHAZADO: No se pudo verificar la pertenencia legítima del objeto.", "Devolución Cancelada", JOptionPane.ERROR_MESSAGE);
            accionCancelar();
        } else {
            boolean exito = controller.procesarDevolucion(objetoSeleccionado.getCodigo(), doc, nombre);
            if (exito) {
                String comprobante = controller.generarComprobante(objetoSeleccionado);
                txtComprobante.setText(comprobante);
                JOptionPane.showMessageDialog(this, "Objeto devuelto exitosamente.", "Proceso Finalizado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void accionCancelar() {
        txtIdObjeto.setText("");
        txtDocReceptor.setText("");
        txtNombreReceptor.setText("");
        txtPruebaPertenencia.setText("");
        txtComprobante.setText("");
        objetoSeleccionado = null;
    }
}
