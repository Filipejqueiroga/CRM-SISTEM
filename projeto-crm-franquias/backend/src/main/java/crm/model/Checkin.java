package crm.model;

public class Checkin {

    private int id;
    private int clienteId;
    private int usuarioId;
    private int franquiaId; 
    private String data;
    private String hora;


    public Checkin(int id, int clienteId, int usuarioId, int franquiaId, String data, String hora) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.franquiaId = franquiaId;
        this.data = data;
        this.hora = hora;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
    
    public int getFranquiaId() {
        return franquiaId;
    }

    public void setFranquiaId(int franquiaId) {
        this.franquiaId = franquiaId;
    }
}