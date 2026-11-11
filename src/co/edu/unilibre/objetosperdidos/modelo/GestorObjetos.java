package co.edu.unilibre.objetosperdidos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorObjetos {
    private static GestorObjetos instancia;
    private final List<ObjetoPerdido> listaObjetos;
    private int contadorId;

    private GestorObjetos() {
        listaObjetos = new ArrayList<>();
        contadorId = 1;
        // Datos de prueba iniciales
        agregarObjeto("Carnet Estudiantil", "Carnet U. Libre cod 2024-1", CategoriaObjeto.DOCUMENTOS, "Biblioteca Piso 2");
        agregarObjeto("Calculadora Casio", "Modelo FX-991EX negra", CategoriaObjeto.ELECTRONICA, "Laboratorio 3");
    }

    public static synchronized GestorObjetos getInstancia() {
        if (instancia == null) {
            instancia = new GestorObjetos();
        }
        return instancia;
    }

    public ObjetoPerdido agregarObjeto(String nombre, String descripcion, CategoriaObjeto categoria, String ubicacion) {
        ObjetoPerdido nuevo = new ObjetoPerdido(contadorId++, nombre, descripcion, categoria, ubicacion);
        listaObjetos.add(nuevo);
        return nuevo;
    }

    public List<ObjetoPerdido> obtenerTodos() {
        return new ArrayList<>(listaObjetos);
    }

    public List<ObjetoPerdido> buscar(String criterio, CategoriaObjeto categoria) {
        return listaObjetos.stream()
                .filter(o -> (criterio == null || criterio.trim().isEmpty() || 
                              o.getNombre().toLowerCase().contains(criterio.toLowerCase()) ||
                              o.getDescripcion().toLowerCase().contains(criterio.toLowerCase())))
                .filter(o -> (categoria == null || o.getCategoria() == categoria))
                .collect(Collectors.toList());
    }

    public boolean registrarDevolucion(int id, String reclamante) {
        for (ObjetoPerdido obj : listaObjetos) {
            if (obj.getId() == id && obj.getEstado() != EstadoObjeto.DEVUELTO) {
                obj.setEstado(EstadoObjeto.DEVUELTO);
                obj.setReclamante(reclamante);
                return true;
            }
        }
        return false;
    }
}
