package co.edu.unilibre.objetosperdidos.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InventarioRAM {
    private static final List<ObjetoPerdido> listaObjetos = new ArrayList<>();

    static {
        listaObjetos.add(new ObjetoPerdido(101, "Mochila Negra Targus", CategoriaObjeto.OTROS, "Biblioteca", java.time.LocalDate.now()));
        listaObjetos.add(new ObjetoPerdido(102, "Calculadora Casio fx-991", CategoriaObjeto.ELECTRONICOS, "Laboratorio", java.time.LocalDate.now()));
    }

    public static void agregarObjetoGlobal(ObjetoPerdido obj) {
        listaObjetos.add(obj);
    }

    public static Optional<ObjetoPerdido> buscarPorId(int id) {
        return listaObjetos.stream()
                .filter(o -> o.getCodigo() == id)
                .findFirst();
    }

    public static boolean actualizarEstadoEntregado(int idObjeto, String idReceptor, String nombreReceptor) {
        Optional<ObjetoPerdido> opt = buscarPorId(idObjeto);
        if (opt.isPresent()) {
            ObjetoPerdido obj = opt.get();
            obj.setEstado("Entregado");
            obj.setIdReceptor(idReceptor);
            obj.setNombreReceptor(nombreReceptor);
            obj.setFechaDevolucion(java.time.LocalDateTime.now());
            return true;
        }
        return false;
    }
}
