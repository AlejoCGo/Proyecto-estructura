package co.edu.unilibre.objetosperdidos.modelo;

public enum EstadoObjeto {
    DISPONIBLE("Disponible"),
    EN_RECLAMACION("En reclamación"),
    ENTREGADO("Entregado"),
    VENCIDO("Vencido"),
    DISPUESTO("Dispuesto");

    private final String etiqueta;

    EstadoObjeto(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
}
