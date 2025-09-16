public class Franchisee extends User {
    private String franchise_name;

    public Franchisee (int id, String email, String user_name, String password, String franchise_name){
        super(id, email, user_name, password);
        this.franchise_name = franchise_name;
    }

    public String getFranchise_name(){
        return this.franchise_name;
    }
    public void setFranchise_name(String franchise_name){
        this.franchise_name = franchise_name;
    }

    @Override
    public String toString(){
        return "Franchisee name: " + getUser_name() + "\n" + "Franchise: " + franchise_name;
    }
}
