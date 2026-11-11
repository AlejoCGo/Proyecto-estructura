package co.edu.unilibre.objetosperdidos.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Collections;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.JTextComponent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.event.ChangeListener;

public final class Tema {

    public static final Color NEGRO = new Color(0, 0, 0);
    public static final Color BLANCO = new Color(255, 255, 255);
    public static final Color ROJO = new Color(229, 27, 27);
    public static final Color ROJO_OSCURO = new Color(139, 0, 0);

    public static final Font FUENTE = new Font(Font.SANS_SERIF, Font.PLAIN, 14);
    public static final Font FUENTE_NEGRITA = FUENTE.deriveFont(Font.BOLD);

    private static final int RELLENO_CAMPO = 7;

    private Tema() {}

    public static void instalar() {
        try {
            MetalLookAndFeel.setCurrentTheme(new TemaMetal());
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException ex) {}
        configurarColoresGlobales();
    }

    private static class TemaMetal extends DefaultMetalTheme {
        @Override public String getName() { return "Negro y rojo"; }
        @Override protected ColorUIResource getPrimary1() { return new ColorUIResource(ROJO); }
        @Override protected ColorUIResource getPrimary2() { return new ColorUIResource(ROJO_OSCURO); }
        @Override protected ColorUIResource getPrimary3() { return new ColorUIResource(ROJO); }
        @Override protected ColorUIResource getSecondary1() { return new ColorUIResource(ROJO); }
        @Override protected ColorUIResource getSecondary2() { return new ColorUIResource(ROJO_OSCURO); }
        @Override protected ColorUIResource getSecondary3() { return new ColorUIResource(NEGRO); }
        @Override protected ColorUIResource getBlack() { return new ColorUIResource(BLANCO); }
        @Override protected ColorUIResource getWhite() { return new ColorUIResource(NEGRO); }
        @Override public FontUIResource getControlTextFont() { return new FontUIResource(FUENTE); }
        @Override public FontUIResource getSystemTextFont() { return new FontUIResource(FUENTE); }
        @Override public FontUIResource getUserTextFont() { return new FontUIResource(FUENTE); }
        @Override public FontUIResource getMenuTextFont() { return new FontUIResource(FUENTE); }
        @Override public FontUIResource getWindowTitleFont() { return new FontUIResource(FUENTE_NEGRITA); }
        @Override public FontUIResource getSubTextFont() { return new FontUIResource(FUENTE); }
    }

    private static void configurarColoresGlobales() {
        FontUIResource fuente = new FontUIResource(FUENTE);
        for (Object clave : Collections.list(UIManager.getDefaults().keys())) {
            if (UIManager.get(clave) instanceof Font) {
                UIManager.put(clave, fuente);
            }
        }

        for (String c : new String[]{"Panel", "Label", "Viewport", "ScrollPane", "OptionPane", "CheckBox", "RadioButton", "TabbedPane"}) {
            UIManager.put(c + ".background", NEGRO);
            UIManager.put(c + ".foreground", BLANCO);
        }
        UIManager.put("control", NEGRO);
        UIManager.put("text", BLANCO);
        UIManager.put("window", NEGRO);
        UIManager.put("TabbedPane.selected", ROJO);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(2, 2, 2, 2));

        UIManager.put("OptionPane.messageForeground", BLANCO);
        UIManager.put("OptionPane.border", new BorderUIResource(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ROJO, 3), BorderFactory.createEmptyBorder(12, 12, 12, 12))));
        UIManager.put("ScrollPane.border", new BorderUIResource(BorderFactory.createLineBorder(ROJO, 1)));

        for (String c : new String[]{"TextField", "TextArea", "FormattedTextField", "PasswordField", "EditorPane", "TextPane"}) {
            UIManager.put(c + ".background", NEGRO);
            UIManager.put(c + ".foreground", BLANCO);
            UIManager.put(c + ".caretForeground", BLANCO);
            UIManager.put(c + ".selectionBackground", ROJO);
            UIManager.put(c + ".selectionForeground", BLANCO);
            UIManager.put(c + ".inactiveBackground", NEGRO);
            UIManager.put(c + ".inactiveForeground", ROJO_OSCURO);
        }

        UIManager.put("Button.background", NEGRO);
        UIManager.put("Button.foreground", BLANCO);
        UIManager.put("Button.select", ROJO_OSCURO);
        UIManager.put("Button.focus", ROJO);
        UIManager.put("Button.disabledText", ROJO_OSCURO);
        UIManager.put("Button.border", new BorderUIResource(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ROJO, 2), BorderFactory.createEmptyBorder(6, 18, 6, 18))));
        UIManager.put("Button.gradient", null);

        UIManager.put("ComboBox.background", NEGRO);
        UIManager.put("ComboBox.foreground", BLANCO);
        UIManager.put("ComboBox.selectionBackground", ROJO);
        UIManager.put("ComboBox.selectionForeground", BLANCO);
        UIManager.put("ComboBox.buttonBackground", NEGRO);
        UIManager.put("ComboBox.buttonShadow", ROJO);
        UIManager.put("ComboBox.buttonDarkShadow", ROJO);
        UIManager.put("ComboBox.buttonHighlight", ROJO);
        UIManager.put("ComboBox.disabledBackground", NEGRO);
        UIManager.put("ComboBox.disabledForeground", ROJO_OSCURO);
        UIManager.put("List.background", NEGRO);
        UIManager.put("List.foreground", BLANCO);
        UIManager.put("List.selectionBackground", ROJO);
        UIManager.put("List.selectionForeground", BLANCO);

        UIManager.put("Table.background", NEGRO);
        UIManager.put("Table.foreground", BLANCO);
        UIManager.put("Table.gridColor", ROJO);
        UIManager.put("Table.selectionBackground", ROJO);
        UIManager.put("Table.selectionForeground", BLANCO);
        UIManager.put("Table.cellNoFocusBorder", new BorderUIResource.EmptyBorderUIResource(2, 8, 2, 8));
        UIManager.put("Table.focusCellHighlightBorder", new BorderUIResource(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BLANCO, 1), BorderFactory.createEmptyBorder(1, 7, 1, 7))));
        UIManager.put("TableHeader.background", NEGRO);
        UIManager.put("TableHeader.foreground", BLANCO);

        UIManager.put("ScrollBar.background", NEGRO);
        UIManager.put("ScrollBar.foreground", BLANCO);
        UIManager.put("ScrollBar.track", NEGRO);
        UIManager.put("ScrollBar.trackHighlight", ROJO);
        UIManager.put("ScrollBar.thumb", ROJO);
        UIManager.put("ScrollBar.thumbShadow", ROJO_OSCURO);
        UIManager.put("ScrollBar.thumbHighlight", ROJO);
        UIManager.put("ScrollBar.gradient", null);

        UIManager.put("ToolTip.background", NEGRO);
        UIManager.put("ToolTip.foreground", BLANCO);
        UIManager.put("ToolTip.border", new BorderUIResource(BorderFactory.createLineBorder(ROJO, 1)));
    }

    public static Border borde(int total, int grosor) {
        int relleno = Math.max(0, total - grosor);
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ROJO, grosor),
                BorderFactory.createEmptyBorder(relleno, relleno, relleno, relleno));
    }

    public static TitledBorder bordeConTitulo(String titulo) {
        return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ROJO, 2), titulo,
                TitledBorder.LEADING, TitledBorder.TOP, FUENTE_NEGRITA, BLANCO);
    }

    public static void estilizarCampo(JTextComponent campo) {
        campo.setBackground(NEGRO);
        campo.setForeground(BLANCO);
        campo.setCaretColor(BLANCO);
        campo.setSelectionColor(ROJO);
        campo.setSelectedTextColor(BLANCO);
        campo.setBorder(borde(RELLENO_CAMPO, 1));
        campo.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { campo.setBorder(borde(RELLENO_CAMPO, 2)); }
            @Override public void focusLost(FocusEvent e) { campo.setBorder(borde(RELLENO_CAMPO, 1)); }
        });
    }

    public static void estilizarArea(JTextArea area, JScrollPane scroll) {
        area.setBackground(NEGRO);
        area.setForeground(BLANCO);
        area.setCaretColor(BLANCO);
        area.setSelectionColor(ROJO);
        area.setSelectedTextColor(BLANCO);
        area.setBorder(BorderFactory.createEmptyBorder(5, 6, 5, 6));
        estilizarScroll(scroll);
        scroll.setBorder(borde(2, 1));
        area.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) { scroll.setBorder(borde(2, 2)); }
            @Override public void focusLost(FocusEvent e) { scroll.setBorder(borde(2, 1)); }
        });
    }

    public static <T> void estilizarCombo(JComboBox<T> combo) {
        combo.setBackground(NEGRO);
        combo.setForeground(BLANCO);
        combo.setBorder(BorderFactory.createLineBorder(ROJO, 1));
        combo.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> lista, Object valor, int indice,
                                                          boolean seleccionado, boolean foco) {
                super.getListCellRendererComponent(lista, valor, indice, seleccionado, foco);
                setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));
                setBackground(seleccionado && indice >= 0 ? ROJO : NEGRO);
                setForeground(BLANCO);
                return this;
            }
        });
        for (Component hijo : combo.getComponents()) {
            if (hijo instanceof JButton) {
                ((JButton) hijo).setBorder(BorderFactory.createEmptyBorder(0, 6, 0, 6));
                hijo.setBackground(NEGRO);
            }
        }
    }

    public static void estilizarBoton(JButton boton) {
        boton.setFont(FUENTE_NEGRITA);
        boton.setBackground(NEGRO);
        boton.setForeground(BLANCO);
        boton.setFocusPainted(false);
        boton.setOpaque(true);
        boton.setContentAreaFilled(true);
        boton.setRolloverEnabled(true);
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(ROJO, 2), BorderFactory.createEmptyBorder(8, 22, 8, 22)));
        ChangeListener resaltar = e -> boton.setBackground(boton.getModel().isRollover() ? ROJO_OSCURO : NEGRO);
        boton.getModel().addChangeListener(resaltar);
    }

    public static void estilizarScroll(JScrollPane scroll) {
        scroll.setBackground(NEGRO);
        scroll.getViewport().setBackground(NEGRO);
    }

    public static void estilizarTabla(JTable tabla) {
        tabla.setBackground(NEGRO);
        tabla.setForeground(BLANCO);
        tabla.setGridColor(ROJO);
        tabla.setSelectionBackground(ROJO);
        tabla.setSelectionForeground(BLANCO);
        tabla.setShowGrid(true);
        tabla.setIntercellSpacing(new Dimension(1, 1));
        tabla.setRowHeight(28);
        tabla.setFillsViewportHeight(true);
        tabla.getTableHeader().setBackground(NEGRO);
        tabla.getTableHeader().setForeground(BLANCO);
        tabla.getTableHeader().setOpaque(true);
    }
}
