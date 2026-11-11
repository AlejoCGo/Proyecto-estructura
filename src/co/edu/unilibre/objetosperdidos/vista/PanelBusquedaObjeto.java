package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.BusquedaController;
import co.edu.unilibre.objetosperdidos.modelo.CategoriaObjeto;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;

public class PanelBusquedaObjeto extends JPanel {

    private static final String TODAS = "Todas las categorías";

    private BusquedaController controller;
    private JButton btnBuscar;
    private JScrollPane jScrollPane1;
    private JLabel lblCategoria;
    private JLabel lblTitulo;
    private JPanel panelPrincipal;
    private JTable tablaObjetos;
    private JComboBox<Object> cmbCategoria;

    public PanelBusquedaObjeto() {
        initComponents();
        controller = new BusquedaController();

        tablaObjetos.setModel(
            new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID",
                    "Nombre",
                    "Categoria",
                    "Estado"
                }
            )
        );

        panelPrincipal.setBackground(new Color(30, 30, 30));
        lblTitulo.setForeground(Color.WHITE);
        lblCategoria.setForeground(Color.WHITE);

        btnBuscar.setBackground(new Color(192, 57, 43));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.setBorderPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        Tema.estilizarCombo(cmbCategoria);

        tablaObjetos.setRowHeight(28);
        tablaObjetos.setDefaultEditor(Object.class, null);
        tablaObjetos.getTableHeader().setBackground(new Color(192, 57, 43));
        tablaObjetos.getTableHeader().setForeground(Color.WHITE);
        tablaObjetos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tablaObjetos.setBackground(Color.WHITE);
        tablaObjetos.setForeground(Color.BLACK);
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        lblTitulo = new JLabel();
        lblCategoria = new JLabel();
        DefaultComboBoxModel<Object> modeloCategorias = new DefaultComboBoxModel<>();
        modeloCategorias.addElement(TODAS);
        for (CategoriaObjeto c : CategoriaObjeto.values()) {
            modeloCategorias.addElement(c);
        }
        cmbCategoria = new JComboBox<>(modeloCategorias);
        btnBuscar = new JButton();
        jScrollPane1 = new JScrollPane();
        tablaObjetos = new JTable();

        setBackground(new Color(30, 30, 30));

        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblTitulo.setForeground(new Color(255, 255, 255));
        lblTitulo.setText("BÚSQUEDA Y CONSULTA DE OBJETOS");

        lblCategoria.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblCategoria.setText("Categoría:");

        btnBuscar.setBackground(new Color(192, 57, 43));
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(this::btnBuscarActionPerformed);

        tablaObjetos.setModel(new DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nombre", "Categoria", "Estado"
            }
        ));
        jScrollPane1.setViewportView(tablaObjetos);

        GroupLayout panelPrincipalLayout = new GroupLayout(panelPrincipal);
        panelPrincipal.setLayout(panelPrincipalLayout);
        panelPrincipalLayout.setHorizontalGroup(
            panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelPrincipalLayout.createSequentialGroup()
                        .addComponent(lblCategoria)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCategoria, GroupLayout.PREFERRED_SIZE, 519, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addComponent(lblTitulo)
                .addContainerGap(200, Short.MAX_VALUE))
        );
        panelPrincipalLayout.setVerticalGroup(
            panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(panelPrincipalLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addGroup(panelPrincipalLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCategoria)
                    .addComponent(cmbCategoria, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 280, GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(panelPrincipal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

    private void btnBuscarActionPerformed(ActionEvent evt) {
        ejecutarBusqueda(true);
    }

    /** Recarga la tabla con el filtro actual (se usa al volver a esta pantalla). */
    public void refrescar() {
        ejecutarBusqueda(false);
    }

    private CategoriaObjeto categoriaSeleccionada() {
        Object seleccion = cmbCategoria.getSelectedItem();
        return (seleccion instanceof CategoriaObjeto) ? (CategoriaObjeto) seleccion : null;
    }

    private void ejecutarBusqueda(boolean avisarSiVacio) {
        List<ObjetoPerdido> lista = controller.buscar(categoriaSeleccionada());

        DefaultTableModel modelo = (DefaultTableModel) tablaObjetos.getModel();
        modelo.setRowCount(0);

        if (lista.isEmpty()) {
            if (avisarSiVacio) {
                JOptionPane.showMessageDialog(this, "No hay resultados");
            }
            return;
        }

        for (ObjetoPerdido obj : lista) {
            modelo.addRow(new Object[]{
                obj.getCodigo(),
                obj.getNombre(),
                obj.getCategoriaTexto(),
                obj.getEstado()
            });
        }
    }
}
