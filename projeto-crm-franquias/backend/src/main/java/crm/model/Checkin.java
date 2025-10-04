package crm.model;

public class Checkin {
    private int id;
    private int clienteId;
    private int usuarioId;
    private int idFranquia;
    private String data;
    private String hora;

    public Checkin(int id, int clienteId, int usuarioId,int idFranquia, String data, String hora) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.idFranquia = idFranquia;
        this.data = data;
        this.hora = hora;
    }

    public int getId() { return id; }

    public int getClienteId() { return clienteId; }

    public int getUsuarioId() { return usuarioId; }
    
    public int getIdFranquia() { return idFranquia; }

    public String getData() { return data; }

    public String getHora() { return hora; }

    public void setId(int id) { this.id = id; }

    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public void setIdFranquia(int idFranquia) { this.idFranquia = idFranquia; }

    public void setData(String data) { this.data = data; }

    public void setHora(String hora) { this.hora = hora; }


    @Override
    public String toString() {
        return "Checkin{id=" + id + ", clienteId = " + clienteId + ", usuarioId = " + usuarioId + ", franquiaId = "+idFranquia+", data = '" + data + "', hora = '" + hora + "'}";
    }
}
