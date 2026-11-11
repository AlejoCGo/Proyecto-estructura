package co.edu.unilibre.objetosperdidos.controlador;

import co.edu.unilibre.objetosperdidos.modelo.CategoriaObjeto;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;
import co.edu.unilibre.objetosperdidos.modelo.RepositorioObjetos;

import java.util.ArrayList;
import java.util.List;

public class BusquedaController {

    private final RepositorioObjetos repositorio = new RepositorioObjetos();

    /** Devuelve los objetos de la categoría indicada; si es null, devuelve todos. */
    public List<ObjetoPerdido> buscar(CategoriaObjeto categoria) {
        List<ObjetoPerdido> resultados = new ArrayList<>();
        for (ObjetoPerdido obj : repositorio.getObjetos()) {
            if (categoria == null || obj.getCategoria() == categoria) {
                resultados.add(obj);
            }
        }
        return resultados;
    }
}
