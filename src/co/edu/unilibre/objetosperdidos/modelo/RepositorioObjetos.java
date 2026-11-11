package co.edu.unilibre.objetosperdidos.modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioObjetos {

    private static final List<ObjetoPerdido> objetos = new ArrayList<>();

    static {
        // ELECTRONICOS
        agregar(1, "Celular Samsung A54", CategoriaObjeto.ELECTRONICOS);
        agregar(2, "Portatil Lenovo", CategoriaObjeto.ELECTRONICOS);
        agregar(3, "Tablet Samsung", CategoriaObjeto.ELECTRONICOS);
        agregar(4, "Mouse Logitech", CategoriaObjeto.ELECTRONICOS);
        agregar(5, "Audifonos JBL", CategoriaObjeto.ELECTRONICOS);
        agregar(6, "Cargador HP", CategoriaObjeto.ELECTRONICOS);

        // UTILES Y LIBROS
        agregar(7, "Cuaderno Matematicas", CategoriaObjeto.UTILES);
        agregar(8, "Libro Programacion Java", CategoriaObjeto.UTILES);
        agregar(9, "Calculadora Casio", CategoriaObjeto.ELECTRONICOS);
        agregar(10, "Lapicero Negro", CategoriaObjeto.UTILES);
        agregar(11, "Carpeta Azul", CategoriaObjeto.UTILES);
        agregar(12, "Agenda Universitaria", CategoriaObjeto.UTILES);

        // PERSONALES (documentos, llaves, otros)
        agregar(13, "Billetera Negra", CategoriaObjeto.OTROS);
        agregar(14, "Llaves Toyota", CategoriaObjeto.LLAVES);
        agregar(15, "Documento de Identidad", CategoriaObjeto.DOCUMENTOS);
        agregar(16, "Perfume Adidas", CategoriaObjeto.OTROS);
        agregar(17, "Monedero Azul", CategoriaObjeto.ROPA_ACCESORIOS);
        agregar(18, "Gafas de Lectura", CategoriaObjeto.ROPA_ACCESORIOS);

        // ROPA Y ACCESORIOS
        agregar(19, "Reloj Casio", CategoriaObjeto.ROPA_ACCESORIOS);
        agregar(20, "Pulsera Plateada", CategoriaObjeto.ROPA_ACCESORIOS);
        agregar(21, "Gorra Negra", CategoriaObjeto.ROPA_ACCESORIOS);
        agregar(22, "Bufanda Roja", CategoriaObjeto.ROPA_ACCESORIOS);
        agregar(23, "Cinturon Cafe", CategoriaObjeto.ROPA_ACCESORIOS);
        agregar(24, "Anillo Plateado", CategoriaObjeto.ROPA_ACCESORIOS);
    }

    private static void agregar(int codigo, String nombre, CategoriaObjeto categoria) {
        objetos.add(new ObjetoPerdido(codigo, nombre, categoria, EstadoObjeto.DISPONIBLE.toString()));
    }

    public static void agregarObjetoGlobal(ObjetoPerdido obj) {
        objetos.add(obj);
    }

    public List<ObjetoPerdido> getObjetos() {
        return objetos;
    }
}
