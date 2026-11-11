package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.ObjetoController;
import co.edu.unilibre.objetosperdidos.modelo.CategoriaObjeto;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelBusquedaObjeto extends JPanel {
    private final ObjetoController controller;
    private JTextField txtBuscar;
    private JComboBox<Object> cbFiltroCategoria;
    private JTable tabla;
    private DefaultTableModel model;

    public PanelBusquedaObjeto(ObjetoController controller) {
        this.controller = controller;
        setLayout(new BorderLayout(10, 10));
        setBackground(Tema.COLOR_FONDO);
        initUI();
    }

    private void initUI() {
        JPanel pnlTop = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTop.add(new JLabel("Buscar:"));
        txtBuscar = new JTextField(15);
        pnlTop.add(txtBuscar);

        pnlTop.add(new JLabel("Categoría:"));
        cbFiltroCategoria = new JComboBox<>();
        cbFiltroCategoria.addItem("Todas");
        for (CategoriaObjeto c : CategoriaObjeto.values()) cbFiltroCategoria.addItem(c);
        pnlTop.add(cbFiltroCategoria);

        JButton btnBuscar = new JButton("Filtrar");
        btnBuscar.addActionListener(e -> cargarDatos());
        pnlTop.add(btnBuscar);

        add(pnlTop, BorderLayout.NORTH);

        String[] cols = {"ID", "Nombre", "Categoría", "Estado", "Ubicación", "Descripción", "Reclamante"};
        model = new DefaultTableModel(cols, 0);
        tabla = new JTable(model);
        add(new JScrollPane(tabla), BorderLayout.CENTER);

        cargarDatos();
    }

    public void cargarDatos() {
        model.setRowCount(0);
        String texto = txtBuscar.getText();
        Object selectedCat = cbFiltroCategoria.getSelectedItem();
        CategoriaObjeto cat = (selectedCat instanceof CategoriaObjeto) ? (CategoriaObjeto) selectedCat : null;

        List<ObjetoPerdido> lista = controller.buscarObjetos(texto, cat);
        for (ObjetoPerdido o : lista) {
            model.addRow(new Object[]{
                    o.getId(), o.getNombre(), o.getCategoria(), o.getEstado(),
                    o.getUbicacion(), o.getDescripcion(), o.getReclamante()
            });
        }
    }
}
