package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.ObjetoController;
import co.edu.unilibre.objetosperdidos.modelo.CategoriaObjeto;

import javax.swing.*;
import java.awt.*;

public class PanelRegistroObjeto extends JPanel {
    private final ObjetoController controller;
    private JTextField txtNombre, txtUbicacion;
    private JTextArea txtDescripcion;
    private JComboBox<CategoriaObjeto> cbCategoria;

    public PanelRegistroObjeto(ObjetoController controller) {
        this.controller = controller;
        setLayout(new GridBagLayout());
        setBackground(Tema.COLOR_FONDO);
        initUI();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Registro de Objeto Perdido", SwingConstants.CENTER);
        lblTitulo.setFont(Tema.FUENTE_TITULO);
        lblTitulo.setForeground(Tema.COLOR_SECUNDARIO);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("Nombre:"), gbc);
        txtNombre = new JTextField(20);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Categoría:"), gbc);
        cbCategoria = new JComboBox<>(CategoriaObjeto.values());
        gbc.gridx = 1;
        add(cbCategoria, gbc);

        gbc.gridy = 3; gbc.gridx = 0;
        add(new JLabel("Ubicación Encontrado:"), gbc);
        txtUbicacion = new JTextField(20);
        gbc.gridx = 1;
        add(txtUbicacion, gbc);

        gbc.gridy = 4; gbc.gridx = 0;
        add(new JLabel("Descripción:"), gbc);
        txtDescripcion = new JTextArea(4, 20);
        gbc.gridx = 1;
        add(new JScrollPane(txtDescripcion), gbc);

        JButton btnGuardar = new JButton("Registrar Objeto");
        btnGuardar.setBackground(Tema.COLOR_PRIMARIO);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> guardar());

        gbc.gridy = 5; gbc.gridx = 0; gbc.gridwidth = 2;
        add(btnGuardar, gbc);
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el nombre del objeto.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        controller.registrarObjeto(
                txtNombre.getText().trim(),
                txtDescripcion.getText().trim(),
                (CategoriaObjeto) cbCategoria.getSelectedItem(),
                txtUbicacion.getText().trim()
        );
        JOptionPane.showMessageDialog(this, "Objeto registrado con éxito.");
        txtNombre.setText("");
        txtDescripcion.setText("");
        txtUbicacion.setText("");
    }
}
