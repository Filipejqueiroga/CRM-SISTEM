public class Franchisor extends User {
    
    private String company_name;

    public Franchisor (int id, String email, String user_name, String password, String company_name){
        super(id, email, user_name, password);
        this.company_name = company_name;
    }

    public String getCompany_name(){
        return this.company_name;
    }
    public void setCompany_name(String company_name){
        this.company_name = company_name;
    }

    @Override
    public String toString(){
        return "Franchisor name: " +getUser_name()+ "\n" + "Franchisor: " + company_name;
    }
}

