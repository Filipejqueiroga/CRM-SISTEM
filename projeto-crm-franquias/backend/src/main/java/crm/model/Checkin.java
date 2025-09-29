package crm.model;

public class Checkin {
    private int id;
    private int cliente_id;
    private int usuario_id;
    private String data;
    private String hora;

    public Checkin(int id, int cliente_id, int usuario_id, String data, String hora) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.usuario_id = usuario_id;
        this.data = data;
        this.hora = hora;
    }

    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public int getCliente_id() { 
        return cliente_id; 
    }
    public void setCliente_id(int cliente_id) { 
        this.cliente_id = cliente_id; 
    }

    public int getUsuario_id() { 
        return usuario_id; 
    }
    public void setUsuario_id(int usuario_id) { 
        this.usuario_id = usuario_id; 
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
}