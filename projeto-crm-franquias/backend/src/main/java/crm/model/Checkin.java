package crm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Checkin")
public class Checkin {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "cliente_id")
    private int clienteId;
    @Column(name = "usuario_id")
    private int usuarioId;
    @Column(name = "franquia_id")
    private int idFranquia;
    private String data;
    private String hora;

    public Checkin(){}

    public Checkin(Integer id, int clienteId, int usuarioId,int idFranquia, String data, String hora) {
        this.id = id;
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.idFranquia = idFranquia;
        this.data = data;
        this.hora = hora;
    }

    public Integer getId() { return id; }

    public int getClienteId() { return clienteId; }

    public int getUsuarioId() { return usuarioId; }
    
    public int getIdFranquia() { return idFranquia; }

    public String getData() { return data; }

    public String getHora() { return hora; }

    public void setId(Integer id) { this.id = id; }

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