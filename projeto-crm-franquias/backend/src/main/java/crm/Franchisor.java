public class Franchisor extends User {
    
    private String company_name;
    Franchisor (String email, String user_name, String password, String company_name){
        super(email, user_name, password);
        this.company_name = company_name;
    }

    public String getFranchisee_name(){
        return this.company_name;
    }
    public void setFranchisee_name(String company_name){
        this.company_name = company_name;
    }
}

