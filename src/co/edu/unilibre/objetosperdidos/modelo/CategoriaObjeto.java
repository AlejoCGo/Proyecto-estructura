package co.edu.unilibre.objetosperdidos.modelo;

public enum CategoriaObjeto {
    DOCUMENTOS("Documentos y carné"),
    ELECTRONICOS("Electrónicos"),
    ROPA_ACCESORIOS("Ropa y accesorios"),
    UTILES("Útiles y libros"),
    LLAVES("Llaves"),
    OTROS("Otros");

    private final String etiqueta;

    CategoriaObjeto(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
