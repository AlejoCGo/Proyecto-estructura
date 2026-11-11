package co.edu.unilibre.objetosperdidos.controlador;

import co.edu.unilibre.objetosperdidos.modelo.InventarioRAM;
import co.edu.unilibre.objetosperdidos.modelo.ObjetoPerdido;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DevolucionController {

    public Optional<ObjetoPerdido> buscarObjeto(int idObjeto) {
        return InventarioRAM.buscarPorId(idObjeto);
    }

    public boolean esDuenioLegitimo(String documentoReceptor, String pruebaPertenencia) {
        if (documentoReceptor == null || documentoReceptor.trim().isEmpty()) {
            return false;
        }
        return pruebaPertenencia != null && pruebaPertenencia.trim().length() >= 5;
    }

    public boolean procesarDevolucion(int idObjeto, String documentoReceptor, String nombreReceptor) {
        return InventarioRAM.actualizarEstadoEntregado(idObjeto, documentoReceptor, nombreReceptor);
    }

    public String generarComprobante(ObjetoPerdido objeto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = (objeto.getFechaDevolucion() != null) 
                ? objeto.getFechaDevolucion().format(formatter) 
                : "N/A";

        StringBuilder sb = new StringBuilder();
        sb.append("=========================================\n");
        sb.append("     COMPROBANTE DE DEVOLUCIÓN DE OBJETO \n");
        sb.append("=========================================\n");
        sb.append("ID Objeto       : ").append(objeto.getCodigo()).append("\n");
        sb.append("Título          : ").append(objeto.getTitulo()).append("\n");
        sb.append("Descripción     : ").append(objeto.getDescripcion()).append("\n");
        sb.append("Categoría       : ").append(objeto.getCategoriaTexto()).append("\n");
        sb.append("Receptor (ID)   : ").append(objeto.getIdReceptor()).append("\n");
        sb.append("Nombre Receptor : ").append(objeto.getNombreReceptor()).append("\n");
        sb.append("Estado Actual   : ").append(objeto.getEstado()).append("\n");
        sb.append("Fecha/Hora      : ").append(fechaFormateada).append("\n");
        sb.append("=========================================\n");
        sb.append("Firma Entregado : _______________________\n");
        return sb.toString();
    }
}
