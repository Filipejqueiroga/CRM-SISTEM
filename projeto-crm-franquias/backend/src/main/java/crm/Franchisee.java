public class Franchisee extends User {
    private String franchise_name;
    Franchisee (String email, String user_name, String password, String franchise_name){
        super(email, user_name, password);
        this.franchise_name = franchise_name;
    }

    public String getFranchisee_name(){
        return this.franchise_name;
    }
    public void setFranchisee_name(String franchisse_name){
        this.franchise_name = franchise_name;
    }
}
