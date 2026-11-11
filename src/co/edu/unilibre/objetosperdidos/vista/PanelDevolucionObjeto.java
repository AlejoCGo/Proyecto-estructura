package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.ObjetoController;

import javax.swing.*;
import java.awt.*;

public class PanelDevolucionObjeto extends JPanel {
    private final ObjetoController controller;
    private JTextField txtId, txtReclamante;
    private Runnable onDevolucionExitosa;

    public PanelDevolucionObjeto(ObjetoController controller, Runnable onDevolucionExitosa) {
        this.controller = controller;
        this.onDevolucionExitosa = onDevolucionExitosa;
        setLayout(new GridBagLayout());
        setBackground(Tema.COLOR_FONDO);
        initUI();
    }

    private void initUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Procesar Devolución de Objeto", SwingConstants.CENTER);
        lblTitulo.setFont(Tema.FUENTE_TITULO);
        lblTitulo.setForeground(Tema.COLOR_SECUNDARIO);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1; gbc.gridx = 0;
        add(new JLabel("ID Objeto:"), gbc);
        txtId = new JTextField(10);
        gbc.gridx = 1;
        add(txtId, gbc);

        gbc.gridy = 2; gbc.gridx = 0;
        add(new JLabel("Nombre Reclamante:"), gbc);
        txtReclamante = new JTextField(20);
        gbc.gridx = 1;
        add(txtReclamante, gbc);

        JButton btnProcesar = new JButton("Confirmar Devolución");
        btnProcesar.setBackground(Tema.COLOR_PRIMARIO);
        btnProcesar.setForeground(Color.WHITE);
        btnProcesar.addActionListener(e -> procesar());

        gbc.gridy = 3; gbc.gridx = 0; gbc.gridwidth = 2;
        add(btnProcesar, gbc);
    }

    private void procesar() {
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            String reclamante = txtReclamante.getText().trim();
            if (reclamante.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe ingresar el nombre de la persona que reclama.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean exito = controller.procesarDevolucion(id, reclamante);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Devolución registrada correctamente.");
                txtId.setText("");
                txtReclamante.setText("");
                if (onDevolucionExitosa != null) onDevolucionExitosa.run();
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró un objeto disponible con el ID especificado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
