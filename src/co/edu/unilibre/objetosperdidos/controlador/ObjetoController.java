package co.edu.unilibre.objetosperdidos.controlador;

import co.edu.unilibre.objetosperdidos.modelo.CategoriaObjeto;
import co.edu.unilibre.objetosperdidos.modelo.GestorObjetos;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;
import co.edu.unilibre.objetosperdidos.vista.PanelRegistroObjeto;
import co.edu.unilibre.objetosperdidos.vista.PanelRegistroObjeto.Campo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class ObjetoController implements ActionListener {

    private static final int MIN_DESCRIPCION = 5;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private final GestorObjetos modelo;
    private final PanelRegistroObjeto vista;

    public ObjetoController(GestorObjetos modelo, PanelRegistroObjeto vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.addRegistrarListener(this);
        this.vista.actualizarTabla(modelo.obtenerTodos());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        registrarObjeto();
    }

    private void registrarObjeto() {
        String descripcion = vista.getDescripcion().trim();
        CategoriaObjeto categoria = vista.getCategoria();
        String lugar = vista.getLugar().trim();
        String textoFecha = vista.getFecha().trim();

        if (descripcion.length() < MIN_DESCRIPCION) {
            vista.mostrarError("La descripción es obligatoria (mínimo " + MIN_DESCRIPCION
                    + " caracteres).", Campo.DESCRIPCION);
            return;
        }
        if (categoria == null) {
            vista.mostrarError("Seleccione una categoría.", Campo.CATEGORIA);
            return;
        }
        if (lugar.isEmpty()) {
            vista.mostrarError("Indique el lugar donde se encontró el objeto.", Campo.LUGAR);
            return;
        }
        LocalDate fecha;
        try {
            fecha = LocalDate.parse(textoFecha, FORMATO_FECHA);
        } catch (DateTimeParseException ex) {
            vista.mostrarError("La fecha no es válida. Use el formato dd/mm/aaaa.", Campo.FECHA);
            return;
        }
        if (fecha.isAfter(LocalDate.now())) {
            vista.mostrarError("La fecha del hallazgo no puede ser futura.", Campo.FECHA);
            return;
        }

        ObjetoPerdido nuevo = modelo.registrar(descripcion, categoria, lugar, fecha);
        vista.actualizarTabla(modelo.obtenerTodos());
        vista.limpiarFormulario();
        vista.mostrarConfirmacion("Objeto registrado con el código " + nuevo.getCodigoFormateado() + ".");
    }
}
