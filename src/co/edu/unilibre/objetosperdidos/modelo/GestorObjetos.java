package co.edu.unilibre.objetosperdidos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GestorObjetos {

    private static GestorObjetos instancia;
    private final List<ObjetoPerdido> objetos = new ArrayList<>();
    private int ultimoCodigo = 0;

    public GestorObjetos() {
        registrar("Carnet Estudiantil Unilibre", CategoriaObjeto.DOCUMENTOS, "Biblioteca Piso 2", LocalDate.now());
        registrar("Calculadora Casio fx-991", CategoriaObjeto.ELECTRONICOS, "Laboratorio 3", LocalDate.now());
    }

    public static synchronized GestorObjetos getInstancia() {
        if (instancia == null) {
            instancia = new GestorObjetos();
        }
        return instancia;
    }

    public ObjetoPerdido registrar(String descripcion, CategoriaObjeto categoria,
                                   String lugar, LocalDate fechaHallazgo) {
        ObjetoPerdido nuevo = new ObjetoPerdido(++ultimoCodigo, descripcion,
                categoria, lugar, fechaHallazgo);
        objetos.add(nuevo);
        InventarioRAM.agregarObjetoGlobal(nuevo);
        RepositorioObjetos.agregarObjetoGlobal(nuevo);
        return nuevo;
    }

    public List<ObjetoPerdido> obtenerTodos() {
        return Collections.unmodifiableList(objetos);
    }

    public int cantidad() {
        return objetos.size();
    }
}
