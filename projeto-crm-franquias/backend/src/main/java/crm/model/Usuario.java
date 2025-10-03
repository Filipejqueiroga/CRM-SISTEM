package crm.model;

public abstract class Usuario {
    private int id;
    private String email;
    private String nomeUsuario;
    private String senha;
    private int idFranquia;
    private int tipoUsuario;    //1- Franqueado, 2- Franqueador

    public Usuario(int id, String email, String nomeUsuario, String senha, int tipoUsuario ) {
        this.id = id;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.idFranquia = -1;
    }


    public Usuario(int id, String email, String nomeUsuario, String senha,int tipoUsuario, int idFranquia) {
        this.id = id;
        this.email = email;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
        this.idFranquia = idFranquia;

    }


    public int getId() { return id; }

    public String getEmail() { return email; }

    public String getNomeUsuario() { return nomeUsuario; }

    public String getSenha() { return senha; }

    public int getTipoUsuario() { return tipoUsuario; }

    public int getIdFranquia() { return idFranquia; }


    public void setId(int id) { this.id = id; }

    public void setEmail(String email) { this.email = email; }

    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public void setSenha(String senha) { this.senha = senha; }

    public void setTipoUsuario(int tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public void setIdFranquia(int idFranquia) { this.idFranquia = idFranquia; }


    @Override
    public String toString() { return "Usuario{id=" + id + ", nomeUsuario='" + nomeUsuario + "', email='" + email + "'}";
    }


    public abstract void exibirMenu();
}
