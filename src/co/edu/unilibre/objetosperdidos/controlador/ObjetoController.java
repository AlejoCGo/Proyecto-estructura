package co.edu.unilibre.objetosperdidos.controlador;

import co.edu.unilibre.objetosperdidos.modelo.*;
import java.util.List;

public class ObjetoController {
    private final GestorObjetos gestor;

    public ObjetoController() {
        this.gestor = GestorObjetos.getInstancia();
    }

    public ObjetoPerdido registrarObjeto(String nombre, String descripcion, CategoriaObjeto categoria, String ubicacion) {
        return gestor.agregarObjeto(nombre, descripcion, categoria, ubicacion);
    }

    public List<ObjetoPerdido> listarObjetos() {
        return gestor.obtenerTodos();
    }

    public List<ObjetoPerdido> buscarObjetos(String texto, CategoriaObjeto categoria) {
        return gestor.buscar(texto, categoria);
    }

    public boolean procesarDevolucion(int id, String reclamante) {
        return gestor.registrarDevolucion(id, reclamante);
    }
}
