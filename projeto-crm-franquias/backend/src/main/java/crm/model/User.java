public class User {
    private int id;
    private String email;
    private String user_name;
    private String password;

    public User (int id, String email, String user_name, String password){
        this.id = id;
        this.email = email;
        this.user_name = user_name;
        this.password = password;
    }

    // Get methods
    public int getId(){
        return this.id;
    }
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
    public void setId(int id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public void setPassword(String password){
        this.password = password;
    }

    // to string method
    public String toString(){
        return "User name: " + user_name + "\n" + "User email: " + email;
    }
}

