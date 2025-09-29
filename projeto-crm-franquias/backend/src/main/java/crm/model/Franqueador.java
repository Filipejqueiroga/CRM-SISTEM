package crm.model;

public class Franqueador extends Usuario {
    private String nomeEmpresa;


    public Franqueador(int id, String email, String nomeUsuario, String senha, String nomeEmpresa) {
        super(id, email, nomeUsuario, senha);
        this.nomeEmpresa = nomeEmpresa;
    }


    public Franqueador(int id, String email, String nomeUsuario, String senha, String nomeEmpresa, int idFranquia) {
        super(id, email, nomeUsuario, senha, idFranquia);
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa; }


    @Override
    public void exibirMenu() {
        System.out.println("1- Listar Franqueados");
        System.out.println("2- Consultar Clientes (todas as franquias)");
        System.out.println("3- Consultar Leads");
        System.out.println("4- Gerar Relatórios");
    }


    @Override
    public String toString() {
        return "Franqueador{nome='" + getNomeUsuario() + "', empresa='" + nomeEmpresa + "'}";
    }
}
