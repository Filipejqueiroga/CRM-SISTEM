package crm.model;

import java.time.format.DateTimeFormatter;

public class Academia extends Franquia {
    private String ultimoCheckin;

    public Academia(int id, String nome, String cidade, String status, String ultimoCheckin) {
        super(id, nome, cidade, status);
        this.ultimoCheckin = ultimoCheckin;
    }

    public Academia(int id, String nome, String cidade, String status, String tipoNegocio, String ultimoCheckin) {
        super(id, nome, cidade, status, tipoNegocio);
        this.ultimoCheckin = ultimoCheckin;
    }

    public String getUltimoCheckin() { return ultimoCheckin; }

    public void setUltimoCheckin(String ultimoCheckin) { this.ultimoCheckin = ultimoCheckin; }


    public void registrarCheckin(Cliente cliente) {
        String now = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        this.ultimoCheckin = cliente.getNome() + " check-in em " + now;
        System.out.println("Check-in realizado para " + cliente.getNome() + " em " + now);
    }
}