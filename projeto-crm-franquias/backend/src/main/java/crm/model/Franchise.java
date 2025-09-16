public class Franchise {
    int id;
    String name;
    String city;
    String status;

    Franchise (int id, String name, String city, String status){
        this.id = id;
        this.name = name;
        this.city = city;
        this.status = status;
    }
    public int getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getCity(){
        return this.city;
    }
    public String getStatus(){
        return this.status;
    }

    public void setId(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setStatus(String status){
        this.status = status;
    } 
}
