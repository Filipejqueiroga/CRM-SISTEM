public class User {
    private String email;
    private String user_name;
    private String password;

    User (String email, String user_name, String password){
        this.email = email;
        this.user_name = user_name;
        this.password = password;
    }

    // Get methods
    public String getEmail(){
        return this.email;
    }

    public String getUser_name(){
        return this.user_name;
    }
    public String getPassword(){
        return this.password;
    }

    // Set Methods
    public void setEmail(String email){
        this.email = email;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public void setPassword(String password){
        this.password = password;
    }
}

