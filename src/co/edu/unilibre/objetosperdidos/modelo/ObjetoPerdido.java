package co.edu.unilibre.objetosperdidos.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ObjetoPerdido {

    private int codigo;
    private String titulo;
    private String descripcion;
    private CategoriaObjeto categoria;
    private String lugar;
    private LocalDate fechaHallazgo;
    private String estado;
    private String idReceptor;
    private String nombreReceptor;
    private LocalDateTime fechaDevolucion;

    public ObjetoPerdido(int codigo, String descripcion, CategoriaObjeto categoria,
                         String lugar, LocalDate fechaHallazgo) {
        this.codigo = codigo;
        this.titulo = descripcion;
        this.descripcion = descripcion;
        this.categoria = (categoria != null) ? categoria : CategoriaObjeto.OTROS;
        this.lugar = lugar;
        this.fechaHallazgo = fechaHallazgo;
        this.estado = EstadoObjeto.DISPONIBLE.toString();
    }

    public ObjetoPerdido(int codigo, String nombre, CategoriaObjeto categoria, String estado) {
        this.codigo = codigo;
        this.titulo = nombre;
        this.descripcion = nombre;
        this.categoria = (categoria != null) ? categoria : CategoriaObjeto.OTROS;
        this.lugar = "Instalaciones";
        this.fechaHallazgo = LocalDate.now();
        this.estado = estado;
    }

    public int getCodigo() { return codigo; }
    public int getId() { return codigo; }
    public String getTitulo() { return titulo; }
    public String getNombre() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public CategoriaObjeto getCategoria() { return categoria; }
    public String getCategoriaTexto() { return categoria.toString(); }
    public String getLugar() { return lugar; }
    public LocalDate getFechaHallazgo() { return fechaHallazgo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getIdReceptor() { return idReceptor; }
    public void setIdReceptor(String idReceptor) { this.idReceptor = idReceptor; }
    public String getNombreReceptor() { return nombreReceptor; }
    public void setNombreReceptor(String nombreReceptor) { this.nombreReceptor = nombreReceptor; }
    public LocalDateTime getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getCodigoFormateado() {
        return String.format("OBJ-%04d", codigo);
    }

    @Override
    public String toString() {
        return getCodigoFormateado() + " - " + titulo;
    }
}
