public class Franqueado extends Usuario {
    private String nomeFranquia;
    private int idFranquiaLocal;


    public Franqueado(int id, String email, String nomeUsuario, String senha, String nomeFranquia, int idFranquia) {
        super(id, email, nomeUsuario, senha, idFranquia);
        this.nomeFranquia = nomeFranquia;
        this.idFranquiaLocal = idFranquia;
    }


    public Franqueado(int id, String email, String nomeUsuario, String senha, String nomeFranquia) {
        super(id, email, nomeUsuario, senha);
        this.nomeFranquia = nomeFranquia;
        this.idFranquiaLocal = -1;
    }


    public String getNomeFranquia() {
        return nomeFranquia; }

    public int getIdFranquiaLocal() {
        return idFranquiaLocal; }

    public void setNomeFranquia(String nomeFranquia) {
        this.nomeFranquia = nomeFranquia; }

    public void setIdFranquiaLocal(int idFranquiaLocal) {
        this.idFranquiaLocal = idFranquiaLocal; }


    @Override
    public void exibirMenu() {
        System.out.println("\n=== MENU DO FRANQUEADO ===");
        System.out.println("1- Cadastrar Cliente");
        System.out.println("2- Consultar Clientes da Franquia");
        System.out.println("3- Registrar Venda");
        System.out.println("4- Registrar Lead");
        System.out.println("5- Registrar Check-in");
        System.out.println("6- Resumo da Franquia");
        System.out.println("0- Sair");
        System.out.print("Escolha uma opção: ");
    }

    @Override
    public String toString() {
        return "Franqueado{ID=" + getId() + ", Nome='" + getNomeUsuario() + "', Email='" + getEmail() +
                "', Franquia='" + nomeFranquia + "', IDFranquia=" + idFranquiaLocal + "}";
    }

    public void exibirPerfil() {
        System.out.println("\n=== PERFIL DO FRANQUEADO ===");
        System.out.println("ID: " + getId());
        System.out.println("Nome: " + getNomeUsuario());
        System.out.println("Email: " + getEmail());
        System.out.println("Nome da Franquia: " + nomeFranquia);
        System.out.println("ID da Franquia: " + idFranquiaLocal);
    }

    public String toResumo() {
        return String.format("%s - %s (ID Franquia: %d)", getNomeUsuario(), nomeFranquia, idFranquiaLocal);
    }

    public void exibirInformacoesBasicas() {
        System.out.println("Nome do Franqueado: " + getNomeUsuario());
        System.out.println("Franquia: " + nomeFranquia);
    }

    public void exibirResumoFranquia() {
        System.out.println("\n--- Resumo da Franquia ---");
        System.out.println("Franquia: " + nomeFranquia);
        System.out.println("ID: " + idFranquiaLocal);
        System.out.println("Responsável: " + getNomeUsuario());
    }

    public boolean validarFranquia() {
        return nomeFranquia != null && !nomeFranquia.trim().isEmpty() && idFranquiaLocal > 0;
    }
}
