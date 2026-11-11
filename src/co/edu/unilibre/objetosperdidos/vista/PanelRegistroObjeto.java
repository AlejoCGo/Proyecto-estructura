package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.modelo.CategoriaObjeto;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class PanelRegistroObjeto extends JPanel {

    public enum Campo { DESCRIPCION, CATEGORIA, LUGAR, FECHA }

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final JTextArea txtDescripcion = new JTextArea(3, 28);
    private final JComboBox<CategoriaObjeto> cmbCategoria = new JComboBox<>(CategoriaObjeto.values());
    private final JTextField txtLugar = new JTextField(28);
    private final JTextField txtFecha = new JTextField(10);
    private final JButton btnRegistrar = new JButton("Registrar objeto");

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new String[]{"Código", "Descripción", "Categoría", "Lugar", "Fecha", "Estado"}, 0) {
        @Override
        public boolean isCellEditable(int fila, int columna) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);

    public PanelRegistroObjeto() {
        setLayout(new BorderLayout(0, 12));
        setBackground(Tema.NEGRO);
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        add(crearFormulario(), BorderLayout.NORTH);
        add(crearTabla(), BorderLayout.CENTER);
        limpiarFormulario();
    }

    private JPanel crearFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Tema.NEGRO);
        panel.setBorder(Tema.bordeConTitulo("Registrar objeto encontrado"));

        txtDescripcion.setLineWrap(true);
        txtDescripcion.setWrapStyleWord(true);
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        Tema.estilizarArea(txtDescripcion, scrollDescripcion);

        Tema.estilizarCombo(cmbCategoria);
        Tema.estilizarCampo(txtLugar);
        Tema.estilizarCampo(txtFecha);
        txtFecha.setToolTipText("Formato dd/mm/aaaa");
        Tema.estilizarBoton(btnRegistrar);

        agregarFila(panel, 0, "Descripción *", scrollDescripcion);
        agregarFila(panel, 1, "Categoría *", cmbCategoria);
        agregarFila(panel, 2, "Lugar del hallazgo *", txtLugar);
        agregarFila(panel, 3, "Fecha (dd/mm/aaaa) *", txtFecha);

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.EAST;
        c.insets = new Insets(10, 8, 10, 8);
        panel.add(btnRegistrar, c);
        return panel;
    }

    private void agregarFila(JPanel panel, int fila, String etiqueta, JComponent campo) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridy = fila;
        c.insets = new Insets(5, 8, 5, 8);

        c.gridx = 0;
        c.anchor = GridBagConstraints.NORTHWEST;
        JLabel lbl = new JLabel(etiqueta);
        lbl.setForeground(Tema.BLANCO);
        panel.add(lbl, c);

        c.gridx = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(campo, c);
    }

    private JScrollPane crearTabla() {
        Tema.estilizarTabla(tabla);
        tabla.setAutoCreateRowSorter(true);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(95);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(230);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(170);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(130);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(110);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(110);

        JScrollPane scroll = new JScrollPane(tabla);
        Tema.estilizarScroll(scroll);
        scroll.setBorder(BorderFactory.createCompoundBorder(
                Tema.bordeConTitulo("Objetos registrados (en memoria)"),
                BorderFactory.createEmptyBorder(2, 6, 6, 6)));
        scroll.setPreferredSize(new Dimension(760, 220));
        return scroll;
    }

    public String getDescripcion() { return txtDescripcion.getText(); }
    public CategoriaObjeto getCategoria() { return (CategoriaObjeto) cmbCategoria.getSelectedItem(); }
    public String getLugar() { return txtLugar.getText(); }
    public String getFecha() { return txtFecha.getText(); }

    public void addRegistrarListener(ActionListener listener) {
        btnRegistrar.addActionListener(listener);
    }

    public void mostrarError(String mensaje, Campo campo) {
        JOptionPane.showMessageDialog(this, mensaje, "Datos no válidos", JOptionPane.ERROR_MESSAGE);
        switch (campo) {
            case DESCRIPCION: txtDescripcion.requestFocusInWindow(); break;
            case CATEGORIA:   cmbCategoria.requestFocusInWindow(); break;
            case LUGAR:       txtLugar.requestFocusInWindow(); break;
            case FECHA:       txtFecha.requestFocusInWindow(); txtFecha.selectAll(); break;
        }
    }

    public void mostrarConfirmacion(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
    }

    public void limpiarFormulario() {
        txtDescripcion.setText("");
        cmbCategoria.setSelectedIndex(-1);
        txtLugar.setText("");
        txtFecha.setText(LocalDate.now().format(FORMATO_FECHA));
        txtDescripcion.requestFocusInWindow();
    }

    public void actualizarTabla(List<ObjetoPerdido> objetos) {
        modeloTabla.setRowCount(0);
        for (ObjetoPerdido o : objetos) {
            modeloTabla.addRow(new Object[]{
                o.getCodigoFormateado(),
                o.getDescripcion(),
                o.getCategoriaTexto(),
                o.getLugar(),
                o.getFechaHallazgo().format(FORMATO_FECHA),
                o.getEstado()
            });
        }
    }
}
