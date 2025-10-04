package crm.model;

public class Franqueador extends Usuario {
    private String nomeEmpresa;

    // Construtor único e correto
    public Franqueador(int id, String email, String nomeUsuario, String senha, String nomeEmpresa) {
        // O tipo '2' é fixo para Franqueador. Um Franqueador não tem um idFranquia associado.
        super(id, email, nomeUsuario, senha, 2, 0); // Passando 0 ou um valor padrão para idFranquia
    }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }

    @Override
    public void exibirMenu() {
        System.out.println("========= MENU DO FRANQUEADOR =========");
        System.out.println("1. Gerenciar Usuários (Franqueados)");
        System.out.println("2. Consultar Clientes");
        System.out.println("3. Consultar Leads");
        System.out.println("4. Gerar Relatórios");
        System.out.println("5. Gerenciar Franquias");
        System.out.println("0. Sair (Logout)");
    }

    @Override
    public String toString() {
        return "Franqueador{ID=" + getId() + ", Nome='" + getNomeUsuario() + "', Empresa='" + nomeEmpresa + "'}";
    }
}