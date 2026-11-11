package co.edu.unilibre.objetosperdidos.vista;

import co.edu.unilibre.objetosperdidos.controlador.ObjetoController;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private final ObjetoController controller;

    public VentanaPrincipal() {
        this.controller = new ObjetoController();
        setTitle("Sistema Unificado de Objetos Perdidos - Universidad Libre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JTabbedPane tabbedPane = new JTabbedPane();

        PanelRegistroObjeto panelRegistro = new PanelRegistroObjeto(controller);
        PanelBusquedaObjeto panelBusqueda = new PanelBusquedaObjeto(controller);
        PanelDevolucionObjeto panelDevolucion = new PanelDevolucionObjeto(controller, panelBusqueda::cargarDatos);

        tabbedPane.addTab("Registro", panelRegistro);
        tabbedPane.addTab("Búsqueda y Consulta", panelBusqueda);
        tabbedPane.addTab("Devoluciones", panelDevolucion);

        tabbedPane.addChangeListener(e -> panelBusqueda.cargarDatos());

        add(tabbedPane, BorderLayout.CENTER);
    }
}
