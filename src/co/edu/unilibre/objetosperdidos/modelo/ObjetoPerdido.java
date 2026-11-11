package co.edu.unilibre.objetosperdidos.modelo;

import java.time.LocalDate;

public class ObjetoPerdido {
    private int id;
    private String nombre;
    private String descripcion;
    private CategoriaObjeto categoria;
    private EstadoObjeto estado;
    private String ubicacion;
    private LocalDate fechaRegistro;
    private String reclamante;

    public ObjetoPerdido(int id, String nombre, String descripcion, CategoriaObjeto categoria, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.ubicacion = ubicacion;
        this.estado = EstadoObjeto.REGISTRADO;
        this.fechaRegistro = LocalDate.now();
        this.reclamante = "";
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public CategoriaObjeto getCategoria() { return categoria; }
    public EstadoObjeto getEstado() { return estado; }
    public void setEstado(EstadoObjeto estado) { this.estado = estado; }
    public String getUbicacion() { return ubicacion; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public String getReclamante() { return reclamante; }
    public void setReclamante(String reclamante) { this.reclamante = reclamante; }
}
